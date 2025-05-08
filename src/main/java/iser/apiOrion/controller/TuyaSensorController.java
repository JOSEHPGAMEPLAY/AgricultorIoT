package iser.apiOrion.controller;

import iser.apiOrion.collection.TuyaSensorData;
import iser.apiOrion.repository.TuyaSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tuya")
public class TuyaSensorController {

    @Autowired
    private TuyaSensorRepository repository;

    @PostMapping("/datos")
    public ResponseEntity<TuyaSensorData> guardarDatos(@RequestBody TuyaSensorData datos) {
        TuyaSensorData guardado = repository.save(datos);
        return ResponseEntity.ok(guardado);
    }
}



