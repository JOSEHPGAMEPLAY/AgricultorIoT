package iser.apiOrion.controller;

import iser.apiOrion.collection.TuyaSensorData;
import iser.apiOrion.service.TuyaSensorDataService;
import iser.apiOrion.DTO.TuyaSensorDataDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tuya", produces = MediaType.APPLICATION_JSON_VALUE)
public class TuyaSensorDataController {

    @Autowired
    private TuyaSensorDataService service;

    @Operation(summary = "Insertar datos del sensor Tuya")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "400", description = "Petición fallida")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TuyaSensorData> save(@RequestBody TuyaSensorDataDTO dto) {
        TuyaSensorData data = new TuyaSensorData();
        data.setNombre(dto.getNombre());
        data.setPh(dto.getPh());
        data.setOrp(dto.getOrp());
        data.setEc(dto.getEc());
        data.setTds(dto.getTds());
        data.setSalinidad(dto.getSalinidad());
        data.setTemperatura(dto.getTemperatura());
        data.setTimestamp(dto.getTimestamp() != null ? dto.getTimestamp() : LocalDateTime.now());
        return ResponseEntity.ok(service.save(data));
    }

    @Operation(summary = "Obtener todos los datos del sensor Tuya")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa")
    })
    @GetMapping
    public ResponseEntity<List<TuyaSensorData>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Obtener sensor Tuya por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Sensor no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TuyaSensorData> findById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar datos del sensor Tuya")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Sensor no encontrado")
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TuyaSensorData> update(@PathVariable String id, @RequestBody TuyaSensorDataDTO dto) {
        TuyaSensorData data = new TuyaSensorData();
        data.setNombre(dto.getNombre());
        data.setPh(dto.getPh());
        data.setOrp(dto.getOrp());
        data.setEc(dto.getEc());
        data.setTds(dto.getTds());
        data.setSalinidad(dto.getSalinidad());
        data.setTemperatura(dto.getTemperatura());
        data.setTimestamp(dto.getTimestamp() != null ? dto.getTimestamp() : LocalDateTime.now());

        TuyaSensorData updated = service.update(id, data);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Eliminar datos del sensor Tuya por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Eliminación exitosa"),
        @ApiResponse(responseCode = "404", description = "Sensor no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}






