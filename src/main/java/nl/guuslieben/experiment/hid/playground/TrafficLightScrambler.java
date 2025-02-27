package nl.guuslieben.experiment.hid.playground;

import nl.guuslieben.experiment.hid.devices.DeviceSpecification;
import nl.guuslieben.experiment.hid.devices.cleware.ClewareTrafficLightColor;
import nl.guuslieben.experiment.hid.devices.cleware.ClewareTrafficLightDeviceSpecification;
import nl.guuslieben.experiment.hid.devices.cleware.ClewareTrafficLightDeviceState;
import nl.guuslieben.experiment.hid.registry.HidDeviceRegistry;
import org.hid4java.HidDevice;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TrafficLightScrambler {

    private final DeviceSpecification<ClewareTrafficLightDeviceState> specification = new ClewareTrafficLightDeviceSpecification();
    private final HidDeviceRegistry registry;

    public TrafficLightScrambler(HidDeviceRegistry registry) {
        this.registry = registry;
    }

    @Scheduled(fixedRateString = "${hid.device.state.scramble.interval}")
    public void scrambleTrafficLight() {
        for (HidDevice device : this.registry.getDevices(this.specification)) {
            this.scrambleTrafficLight(device);
        }
    }

    private void scrambleTrafficLight(HidDevice device) {
        ClewareTrafficLightDeviceState deviceState = new ClewareTrafficLightDeviceState();
        for (ClewareTrafficLightColor color : ClewareTrafficLightColor.values()) {
            deviceState.setColorState(color, Math.random() > 0.5);
        }
        this.specification.setDeviceState(device, deviceState);
        System.out.println("Scrambled traffic light: " + deviceState);
    }
}
