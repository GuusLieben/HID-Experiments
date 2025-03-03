package nl.guuslieben.hid.std.dto;

public record HidDeviceDTO(
    HidDeviceInformation information,
    HidDeviceConfiguration configuration
) {

    public record HidDeviceInformation(
        int vendorId,
        int productId,
        String serialNumber,
        int releaseNumber,
        String manufacturer,
        String product,
        String path
    ) {}

    public record HidDeviceConfiguration(
        int usagePage,
        int usage,
        int interfaceNumber
    ) {}
}
