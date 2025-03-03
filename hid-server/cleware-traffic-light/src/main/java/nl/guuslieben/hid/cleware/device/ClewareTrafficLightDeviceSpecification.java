package nl.guuslieben.hid.cleware.device;

import java.util.function.Predicate;
import nl.guuslieben.hid.std.devices.DeviceSpecification;
import org.hid4java.HidDevice;

public class ClewareTrafficLightDeviceSpecification implements DeviceSpecification<ClewareTrafficLightDeviceState> {

    private static final int PRODUCT_ID = 0x0008;
    private static final int VENDOR_ID = 0x0D50;

    @Override
    public boolean isSatisfiedBy(HidDevice device) {
        return device.getProductId() == PRODUCT_ID && device.getVendorId() == VENDOR_ID;
    }

    @Override
    public ClewareTrafficLightDeviceState getDeviceState(HidDevice device) {
        this.ensureDeviceOpen(device);

        byte[] buffer = new byte[1];
        device.read(buffer);
        int state = -(buffer[0]) % 97;

        ClewareTrafficLightDeviceState deviceState = new ClewareTrafficLightDeviceState();
        for (ClewareTrafficLightColor color : ClewareTrafficLightColor.values()) {
            boolean isEnabled = (state & color.getStatePosition()) == 0;
            deviceState.setColorState(color, isEnabled);
        }
        return deviceState;
    }

    @Override
    public boolean setDeviceState(HidDevice device, ClewareTrafficLightDeviceState state) {
        if (state.equals(this.getDeviceState(device))) {
            return true;
        }
        return this.bufferedWrite(device, state::isEnabled);
    }

    @Override
    public boolean resetDeviceState(HidDevice device) {
        return this.bufferedWrite(device, color -> false);
    }

    private boolean bufferedWrite(HidDevice device, Predicate<ClewareTrafficLightColor> enabled) {
        this.ensureDeviceOpen(device);

        boolean pass = true;
        for (ClewareTrafficLightColor color : ClewareTrafficLightColor.values()) {
            byte[] buffer = this.createStateBuffer(device, color, enabled.test(color));
            int written = device.write(buffer, buffer.length, (byte) 0x1);
            pass &= written > 0; // -2 is invalid write, -1 is error, 0 is no written bytes
        }
        return pass;
    }

    private void ensureDeviceOpen(HidDevice device) {
        // Note short-circuit order. If device is already open, we don't need to try to re-open it.
        if (device.isClosed() && !device.open()) {
            throw new IllegalStateException("Could not open device");
        }
    }

    private byte[] createStateBuffer(HidDevice device, ClewareTrafficLightColor color, boolean enabled) {
        // [interface, color address, state]
        return new byte[] {
            (byte) device.getInterfaceNumber(),
            color.getAddress(),
            (byte) (enabled ? 0x1 : 0x0)
        };
    }
}
