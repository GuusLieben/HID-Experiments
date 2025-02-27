package nl.guuslieben.experiment.hid.devices;

import org.hid4java.HidDevice;

public interface DeviceSpecification<S> {

    boolean isSatisfiedBy(HidDevice device);

    S getDeviceState(HidDevice device);

    void setDeviceState(HidDevice device, S state);

    void resetDeviceState(HidDevice device);
}
