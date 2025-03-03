package nl.guuslieben.hid.cleware.device;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.Objects;

public class ClewareTrafficLightDeviceState implements Serializable {

    private EnumSet<ClewareTrafficLightColor> enabledColors;

    public ClewareTrafficLightDeviceState() {
        this.enabledColors = EnumSet.noneOf(ClewareTrafficLightColor.class);
    }

    public EnumSet<ClewareTrafficLightColor> getEnabledColors() {
        return this.enabledColors;
    }

    public boolean isEnabled(ClewareTrafficLightColor color) {
        return this.enabledColors.contains(color);
    }

    public void setEnabledColors(EnumSet<ClewareTrafficLightColor> enabledColors) {
        this.enabledColors = enabledColors;
    }

    public void addColor(ClewareTrafficLightColor color) {
        this.enabledColors.add(color);
    }

    public void removeColor(ClewareTrafficLightColor color) {
        this.enabledColors.remove(color);
    }

    public void setColorState(ClewareTrafficLightColor color, boolean isEnabled) {
        if (isEnabled) {
            this.addColor(color);
        } else {
            this.removeColor(color);
        }
    }

    public void clearColors() {
        this.enabledColors.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        ClewareTrafficLightDeviceState that = (ClewareTrafficLightDeviceState) o;
        return Objects.equals(this.enabledColors, that.enabledColors);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.enabledColors);
    }

    @Override
    public String toString() {
        return "ClewareTrafficLightDeviceState{" +
            "enabledColors=" + this.enabledColors +
            '}';
    }
}
