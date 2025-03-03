package nl.guuslieben.hid.std.service;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import nl.guuslieben.hid.std.devices.DeviceSpecification;
import nl.guuslieben.hid.std.registry.HidDeviceRegistry;
import nl.guuslieben.hid.std.util.DeviceStateUtils;
import org.hid4java.HidDevice;

public abstract class AbstractHidDeviceService<S extends Serializable> {

    private final DeviceSpecification<S> specification;
    private final HidDeviceRegistry registry;

    public AbstractHidDeviceService(DeviceSpecification<S> specification, HidDeviceRegistry registry) {
        this.specification = specification;
        this.registry = registry;
    }

    public Set<HidDevice> getDevices() {
        return this.registry.getDevices(this.specification);
    }

    public Optional<HidDevice> getDeviceBySerialNumber(String serialNumber) {
        return this.registry.getDevices(this.specification).stream()
            .filter(device -> device.getSerialNumber().equals(serialNumber))
            .findFirst();
    }

    public Optional<S> getDeviceStateBySerialNumber(String serialNumber) {
        return this.getDeviceBySerialNumber(serialNumber)
            .map(this.specification::getDeviceState);
    }

    public boolean setDeviceStateBySerialNumber(String serialNumber, S state) {
        boolean executed = this.getDeviceBySerialNumber(serialNumber)
            .map(device -> this.specification.setDeviceState(device, state))
            .orElse(false);
        if (executed) {
            // Some HID devices take a while to update their state, so ensure everything is in sync before returning
            DeviceStateUtils.awaitCondition(
                () -> this.getDeviceStateBySerialNumber(serialNumber).map(state::equals).orElse(false),
                50,
                1000
            );
        }
        return executed;
    }
}
