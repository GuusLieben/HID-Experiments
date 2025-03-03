package nl.guuslieben.hid.std.mapper;

import nl.guuslieben.hid.std.dto.HidDeviceDTO;
import org.hid4java.HidDevice;
import org.springframework.stereotype.Component;

@Component
public class HidDeviceDtoMapper {

    public HidDeviceDTO map(HidDevice device) {
        HidDeviceDTO.HidDeviceInformation information = new HidDeviceDTO.HidDeviceInformation(
            device.getVendorId(),
            device.getProductId(),
            device.getSerialNumber(),
            device.getReleaseNumber(),
            device.getManufacturer(),
            device.getProduct(),
            device.getPath()
        );
        HidDeviceDTO.HidDeviceConfiguration configuration = new HidDeviceDTO.HidDeviceConfiguration(
            device.getUsagePage(),
            device.getUsage(),
            device.getInterfaceNumber()
        );
        return new HidDeviceDTO(information, configuration);
    }
}
