package nl.guuslieben.experiment.hid.listeners;

import java.util.Set;
import org.hid4java.HidServicesListener;
import org.hid4java.event.HidServicesEvent;

public class HidDeviceListener implements HidServicesListener {

    private final Set<HidServicesListener> listeners;

    public HidDeviceListener(Set<HidServicesListener> listeners) {
        this.listeners = listeners;
    }

    @Override
    public void hidDeviceAttached(HidServicesEvent hidServicesEvent) {
        this.listeners.forEach(listener -> listener.hidDeviceAttached(hidServicesEvent));
    }

    @Override
    public void hidDeviceDetached(HidServicesEvent hidServicesEvent) {
        this.listeners.forEach(listener -> listener.hidDeviceDetached(hidServicesEvent));
    }

    @Override
    public void hidFailure(HidServicesEvent hidServicesEvent) {
        this.listeners.forEach(listener -> listener.hidFailure(hidServicesEvent));
    }

    @Override
    public void hidDataReceived(HidServicesEvent hidServicesEvent) {
        this.listeners.forEach(listener -> listener.hidDataReceived(hidServicesEvent));
    }
}
