package nl.guuslieben.experiment.hid.service;

import nl.guuslieben.experiment.hid.devices.DeviceSpecification;
import nl.guuslieben.experiment.hid.registry.HidDeviceRegistry;
import org.hid4java.HidDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class HidDeviceStateLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(HidDeviceStateLogger.class);

    private final DeviceSpecification<?> specification;
    private final HidDeviceRegistry registry;

    public HidDeviceStateLogger(DeviceSpecification<?> specification, HidDeviceRegistry registry) {
        this.specification = specification;
        this.registry = registry;
    }

    @Scheduled(fixedRateString = "${hid.device.state.logger.interval}")
    public void readDeviceStateBySpecification() {
        for (HidDevice device : this.registry.getDevices(this.specification)) {
            if (device.open()) {
                Object state = this.specification.getDeviceState(device);
                String deviceIdentifier = device.getSerialNumber();
                if (deviceIdentifier.isBlank()) {
                    deviceIdentifier = device.getPath();
                }
                LOGGER.info("State for device {}: {}", deviceIdentifier, state);
            }
        }
    }
}
