package nl.guuslieben.experiment.hid.service;

import nl.guuslieben.experiment.hid.devices.DeviceSpecification;
import nl.guuslieben.experiment.hid.registry.HidDeviceRegistry;
import org.hid4java.HidDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Service;

@Service
public class HidDeviceResetShutdownHook implements ApplicationListener<ContextClosedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HidDeviceResetShutdownHook.class);

    private final DeviceSpecification<?> specification;
    private final HidDeviceRegistry registry;

    public HidDeviceResetShutdownHook(DeviceSpecification<?> specification, HidDeviceRegistry registry) {
        this.specification = specification;
        this.registry = registry;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        for (HidDevice device : this.registry.getDevices(this.specification)) {
            this.specification.resetDeviceState(device);
            this.registry.removeDevice(device);
            device.close();
            LOGGER.info("Reset device state for device {}", device.getSerialNumber());
        }
    }
}
