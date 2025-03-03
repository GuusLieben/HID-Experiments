package nl.guuslieben.hid.std.configuration;

import java.util.Set;
import nl.guuslieben.hid.std.devices.DeviceSpecification;
import nl.guuslieben.hid.std.listeners.HidDeviceListener;
import nl.guuslieben.hid.std.listeners.HidDeviceLoggingListener;
import nl.guuslieben.hid.std.registry.HidDeviceRegistry;
import org.hid4java.HidServicesListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.objenesis.instantiator.util.ClassUtils;

public class HidDeviceServerConfiguration {

    @Bean
    HidDeviceRegistry registry() {
        return new HidDeviceRegistry();
    }

    @Bean
    HidDeviceListener deviceListener(Set<HidServicesListener> listeners) {
        return new HidDeviceListener(listeners);
    }

    @Bean
    DeviceSpecification<?> deviceSpecification(@Value("${hid.device.specification}") Class<? extends DeviceSpecification<?>> specification) {
        return ClassUtils.newInstance(specification);
    }

    @Bean
    @ConditionalOnProperty(value = "hid.logging.enabled", havingValue = "true")
    HidServicesListener loggingListener() {
        return new HidDeviceLoggingListener();
    }
}
