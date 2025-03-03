package nl.guuslieben.hid.cleware.service;

import nl.guuslieben.hid.cleware.device.ClewareTrafficLightDeviceState;
import nl.guuslieben.hid.std.devices.DeviceSpecification;
import nl.guuslieben.hid.std.registry.HidDeviceRegistry;
import nl.guuslieben.hid.std.service.AbstractHidDeviceService;
import org.springframework.stereotype.Service;

@Service
public class ClewareTrafficLightDeviceService extends AbstractHidDeviceService<ClewareTrafficLightDeviceState> {

    public ClewareTrafficLightDeviceService(
        DeviceSpecification<ClewareTrafficLightDeviceState> specification,
        HidDeviceRegistry registry) {
        super(specification, registry);
    }
}
