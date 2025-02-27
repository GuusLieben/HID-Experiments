package nl.guuslieben.experiment.hid.service;

import jakarta.annotation.PostConstruct;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import nl.guuslieben.experiment.hid.listeners.HidDeviceListener;
import org.hid4java.HidDevice;
import org.hid4java.HidManager;
import org.hid4java.HidServices;
import org.hid4java.HidServicesSpecification;
import org.hid4java.ScanMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HidDeviceScanner {

    private static final Logger LOGGER = LoggerFactory.getLogger(HidDeviceScanner.class);

    private final HidDeviceListener hidDeviceListener;
    private HidServices hidServices;

    public HidDeviceScanner(HidDeviceListener hidDeviceListener) {
        this.hidDeviceListener = hidDeviceListener;
    }

    @PostConstruct
    protected void initializeHidScan() {
        HidServicesSpecification specification = new HidServicesSpecification();
        specification.setAutoStart(false);
        specification.setScanMode(ScanMode.NO_SCAN);

        this.hidServices = HidManager.getHidServices(specification);
        this.hidServices.addHidServicesListener(this.hidDeviceListener);
        this.hidServices.start();
    }

    public Set<HidDevice> getAttachedHidDevices() {
        return Set.copyOf(this.hidServices.getAttachedHidDevices());
    }

    public Set<HidDevice> getAttachedHidDevices(Predicate<HidDevice> predicate) {
        return this.getAttachedHidDevices().stream()
            .filter(predicate)
            .collect(Collectors.toSet());
    }

    public Set<HidDevice> getAttachedHidDevices(int vendorId, int productId) {
        return this.getAttachedHidDevices(device -> device.getVendorId() == vendorId && device.getProductId() == productId);
    }
}
