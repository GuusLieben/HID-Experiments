package nl.guuslieben.hid.std.devices;

import java.io.Serializable;
import org.hid4java.HidDevice;

public interface DeviceSpecification<S extends Serializable> {

    boolean isSatisfiedBy(HidDevice device);

    S getDeviceState(HidDevice device);

    boolean setDeviceState(HidDevice device, S state);

    boolean resetDeviceState(HidDevice device);
}
