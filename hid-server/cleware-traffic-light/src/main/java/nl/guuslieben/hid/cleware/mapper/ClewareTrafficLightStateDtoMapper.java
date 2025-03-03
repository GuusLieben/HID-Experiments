package nl.guuslieben.hid.cleware.mapper;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;
import nl.guuslieben.hid.cleware.dto.ClewareTrafficLightDeviceStateDto;
import nl.guuslieben.hid.cleware.device.ClewareTrafficLightColor;
import nl.guuslieben.hid.cleware.device.ClewareTrafficLightDeviceState;
import nl.guuslieben.hid.std.mapper.HidDeviceStateDtoMapper;
import org.springframework.stereotype.Service;

@Service
public class ClewareTrafficLightStateDtoMapper implements HidDeviceStateDtoMapper<ClewareTrafficLightDeviceState, ClewareTrafficLightDeviceStateDto> {

    @Override
    public ClewareTrafficLightDeviceStateDto map(ClewareTrafficLightDeviceState state) {
        Set<String> enabledColors = state.getEnabledColors().stream()
            .map(Enum::name)
            .collect(Collectors.toSet());
        return new ClewareTrafficLightDeviceStateDto(enabledColors);
    }

    @Override
    public ClewareTrafficLightDeviceState map(ClewareTrafficLightDeviceStateDto dto) {
        Set<ClewareTrafficLightColor> enabledColors = dto.enabledColors().stream()
            .map(ClewareTrafficLightColor::valueOf)
            .collect(Collectors.toSet());

        ClewareTrafficLightDeviceState state = new ClewareTrafficLightDeviceState();
        state.setEnabledColors(enabledColors.isEmpty()
            ? EnumSet.noneOf(ClewareTrafficLightColor.class)
            : EnumSet.copyOf(enabledColors));
        return state;
    }
}
