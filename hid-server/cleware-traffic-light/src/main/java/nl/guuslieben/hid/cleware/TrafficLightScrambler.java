package nl.guuslieben.hid.cleware;

import nl.guuslieben.hid.cleware.device.ClewareTrafficLightColor;
import nl.guuslieben.hid.cleware.device.ClewareTrafficLightDeviceSpecification;
import nl.guuslieben.hid.cleware.device.ClewareTrafficLightDeviceState;
import nl.guuslieben.hid.std.devices.DeviceSpecification;
import nl.guuslieben.hid.std.registry.HidDeviceRegistry;
import org.hid4java.HidDevice;
import org.springframework.scheduling.annotation.Scheduled;

//@Service
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
