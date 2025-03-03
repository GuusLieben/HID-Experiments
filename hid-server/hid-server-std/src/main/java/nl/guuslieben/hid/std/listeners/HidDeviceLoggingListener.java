package nl.guuslieben.hid.std.listeners;

import org.hid4java.HidServicesListener;
import org.hid4java.event.HidServicesEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HidDeviceLoggingListener implements HidServicesListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(HidDeviceLoggingListener.class);

    @Override
    public void hidDeviceAttached(HidServicesEvent hidServicesEvent) {
        LOGGER.info("Device attached: {}", hidServicesEvent);
    }

    @Override
    public void hidDeviceDetached(HidServicesEvent hidServicesEvent) {
        LOGGER.info("Device detached: {}", hidServicesEvent);
    }

    @Override
    public void hidFailure(HidServicesEvent hidServicesEvent) {
        LOGGER.error("HID failure: {}", hidServicesEvent);
    }

    @Override
    public void hidDataReceived(HidServicesEvent hidServicesEvent) {
        LOGGER.info("Data received: {}", hidServicesEvent);
    }
}
