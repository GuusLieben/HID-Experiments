package nl.guuslieben.experiment.hid;

import java.util.Set;
import nl.guuslieben.experiment.hid.devices.DeviceSpecification;
import nl.guuslieben.experiment.hid.listeners.HidDeviceListener;
import nl.guuslieben.experiment.hid.listeners.HidDeviceLoggingListener;
import nl.guuslieben.experiment.hid.registry.HidDeviceRegistry;
import org.hid4java.HidServicesListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.objenesis.instantiator.util.ClassUtils;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HidApplication {

	public static void main(String[] args) {
		SpringApplication.run(HidApplication.class, args);
	}

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
	@ConditionalOnProperty(value = "hid.listener.logging", havingValue = "true")
	HidServicesListener loggingListener() {
		return new HidDeviceLoggingListener();
	}
}
