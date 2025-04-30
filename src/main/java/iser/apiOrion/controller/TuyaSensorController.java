package iser.apiOrion.controller;

import iser.apiOrion.collection.TuyaSensorData;
import iser.apiOrion.repository.TuyaSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;


import java.util.List;

@RestController
@RequestMapping("/tuya")
public class TuyaSensorController {

    @Autowired
    private TuyaSensorRepository repository;
    @Operation(summary = "POST / recibe los datos del sensor y los guarda")
    // POST: Recibe datos del sensor y guarda
    @PostMapping("/datos")
    public ResponseEntity<?> recibirDatos(@RequestBody TuyaSensorData datos) {
        if (datos == null) {
            return ResponseEntity.badRequest().body("Datos no válidos");
        }

        // Validación sencilla: asegurarse que no vengan todos nulos
        if (datos.getPh() == null && datos.getOrp() == null &&
                datos.getEc() == null && datos.getTds() == null &&
                datos.getSalinidad() == null && datos.getTemperatura() == null) {
            return ResponseEntity.badRequest().body("Debe enviar al menos un dato del sensor");
        }

        repository.save(datos);
        return ResponseEntity.ok("Datos del sensor guardados correctamente");
    }

    // GET: Consultar todos los datos guardados
    @Operation(summary = "GET / consulta los datos guardados")
    @GetMapping("/datos")
    public ResponseEntity<List<TuyaSensorData>> obtenerDatos() {
        List<TuyaSensorData> datos = repository.findAll();
        return ResponseEntity.ok(datos);
    }
}


