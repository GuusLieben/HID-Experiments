package nl.guuslieben.hid.std.registry;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import nl.guuslieben.hid.std.devices.DeviceSpecification;
import org.hid4java.HidDevice;
import org.hid4java.HidServicesListener;
import org.hid4java.event.HidServicesEvent;

public class HidDeviceRegistry implements HidServicesListener {

    private final Map<String, HidDevice> devices = new ConcurrentHashMap<>();

    public Set<HidDevice> getDevices() {
        return Set.copyOf(this.devices.values());
    }

    public Set<HidDevice> getDevices(DeviceSpecification<?> specification) {
        return this.getDevices().stream()
                .filter(specification::isSatisfiedBy)
                .collect(Collectors.toSet());
    }

    public void addDevice(HidDevice device) {
        this.devices.put(device.getSerialNumber(), device);
    }

    public void removeDevice(HidDevice device) {
        this.devices.remove(device.getSerialNumber());
    }

    @Override
    public void hidDeviceAttached(HidServicesEvent event) {
        this.addDevice(event.getHidDevice());
    }

    @Override
    public void hidDeviceDetached(HidServicesEvent event) {
        this.removeDevice(event.getHidDevice());
    }

    @Override
    public void hidFailure(HidServicesEvent event) {
        // Do nothing
    }

    @Override
    public void hidDataReceived(HidServicesEvent event) {
        // Do nothing
    }
}
