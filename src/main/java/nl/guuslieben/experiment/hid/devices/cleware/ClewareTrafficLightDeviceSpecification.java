package nl.guuslieben.experiment.hid.devices.cleware;

import nl.guuslieben.experiment.hid.devices.DeviceSpecification;
import org.hid4java.HidDevice;

public class ClewareTrafficLightDeviceSpecification implements DeviceSpecification<ClewareTrafficLightDeviceState> {

    private static final int PRODUCT_ID = 0x0008;
    private static final int VENDOR_ID = 0x0D50;
    private static final byte INTERFACE = 0x0;

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
    public void setDeviceState(HidDevice device, ClewareTrafficLightDeviceState state) {
        if (state.equals(this.getDeviceState(device))) {
            return;
        }
        this.ensureDeviceOpen(device);

        for (ClewareTrafficLightColor color : ClewareTrafficLightColor.values()) {
            boolean enabled = state.isEnabled(color);
            byte[] buffer = this.createStateBuffer(color, enabled);
            device.write(buffer, buffer.length, (byte) 0x1);
        }
    }

    @Override
    public void resetDeviceState(HidDevice device) {
        this.ensureDeviceOpen(device);
        for (ClewareTrafficLightColor color : ClewareTrafficLightColor.values()) {
            byte[] buffer = this.createStateBuffer(color, false);
            device.write(buffer, buffer.length, (byte) 0x1);
        }
    }

    private void ensureDeviceOpen(HidDevice device) {
        // Note short-circuit order. If device is already open, we don't need to try to re-open it.
        if (device.isClosed() && !device.open()) {
            throw new IllegalStateException("Could not open device");
        }
    }

    private byte[] createStateBuffer(ClewareTrafficLightColor color, boolean enabled) {
        // [interface, color address, state]
        return new byte[] {
            INTERFACE,
            color.getAddress(),
            (byte) (enabled ? 0x1 : 0x0)
        };
    }
}
