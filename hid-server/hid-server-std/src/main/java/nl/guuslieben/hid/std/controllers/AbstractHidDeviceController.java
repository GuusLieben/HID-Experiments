package nl.guuslieben.hid.std.controllers;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;
import nl.guuslieben.hid.std.dto.HidDeviceDTO;
import nl.guuslieben.hid.std.mapper.HidDeviceDtoMapper;
import nl.guuslieben.hid.std.mapper.HidDeviceStateDtoMapper;
import nl.guuslieben.hid.std.service.AbstractHidDeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${hid.api.base-path}")
public abstract class AbstractHidDeviceController<S extends Serializable, SD> {

    private final AbstractHidDeviceService<S> deviceService;
    private final HidDeviceDtoMapper deviceMapper;
    private final HidDeviceStateDtoMapper<S, SD> stateMapper;

    public AbstractHidDeviceController(AbstractHidDeviceService<S> deviceService, HidDeviceDtoMapper deviceMapper,
        HidDeviceStateDtoMapper<S, SD> stateMapper) {
        this.deviceService = deviceService;
        this.deviceMapper = deviceMapper;
        this.stateMapper = stateMapper;
    }

    @GetMapping
    public Set<HidDeviceDTO> getDevices() {
        return this.deviceService.getDevices().stream()
            .map(this.deviceMapper::map)
            .collect(Collectors.toSet());
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<HidDeviceDTO> getDevice(@PathVariable String serialNumber) {
        return this.deviceService.getDeviceBySerialNumber(serialNumber)
            .map(this.deviceMapper::map)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{serialNumber}/state")
    public ResponseEntity<SD> getDeviceState(@PathVariable String serialNumber) {
        return this.deviceService.getDeviceStateBySerialNumber(serialNumber)
            .map(this.stateMapper::map)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{serialNumber}/state")
    public ResponseEntity<SD> setDeviceState(@PathVariable String serialNumber, @RequestBody SD state) {
        if (this.deviceService.setDeviceStateBySerialNumber(serialNumber, this.stateMapper.map(state))) {
            return ResponseEntity.ok(state);
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
