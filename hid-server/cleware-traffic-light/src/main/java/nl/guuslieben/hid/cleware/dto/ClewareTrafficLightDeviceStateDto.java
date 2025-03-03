package nl.guuslieben.hid.cleware.dto;

import java.util.Set;

public record ClewareTrafficLightDeviceStateDto(
    Set<String> enabledColors
) {
}
