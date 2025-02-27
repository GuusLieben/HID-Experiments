package nl.guuslieben.experiment.hid.devices.cleware;

public enum ClewareTrafficLightColor {
    RED((byte) 0x10, 1),
    YELLOW((byte) 0x11, 4),
    GREEN((byte) 0x12, 16),
    ;

    private final byte address;
    private final int statePosition;

    ClewareTrafficLightColor(byte address, int statePosition) {
        this.address = address;
        this.statePosition = statePosition;
    }

    public byte getAddress() {
        return this.address;
    }

    public int getStatePosition() {
        return this.statePosition;
    }
}
