package nl.guuslieben.hid.cleware.controller;

import nl.guuslieben.hid.cleware.device.ClewareTrafficLightDeviceState;
import nl.guuslieben.hid.cleware.dto.ClewareTrafficLightDeviceStateDto;
import nl.guuslieben.hid.cleware.mapper.ClewareTrafficLightStateDtoMapper;
import nl.guuslieben.hid.cleware.service.ClewareTrafficLightDeviceService;
import nl.guuslieben.hid.std.controllers.AbstractHidDeviceController;
import nl.guuslieben.hid.std.mapper.HidDeviceDtoMapper;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClewareTrafficLightController extends AbstractHidDeviceController<ClewareTrafficLightDeviceState, ClewareTrafficLightDeviceStateDto> {

    public ClewareTrafficLightController(
        ClewareTrafficLightDeviceService deviceService,
        HidDeviceDtoMapper deviceMapper,
        ClewareTrafficLightStateDtoMapper stateMapper) {
        super(deviceService, deviceMapper, stateMapper);
    }
}
