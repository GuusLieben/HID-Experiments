package nl.guuslieben.hid.std.mapper;

import java.io.Serializable;

public interface HidDeviceStateDtoMapper<S extends Serializable, SD> {

    SD map(S state);

    S map(SD dto);
}
