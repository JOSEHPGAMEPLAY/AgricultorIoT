package iser.apiOrion.service;

import iser.apiOrion.collection.TuyaSensorData;
import iser.apiOrion.repository.TuyaSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TuyaSensorService {

    @Autowired
    private TuyaSensorRepository repository;

    public ResponseEntity<?> guardarDatos(TuyaSensorData datos) {
        if (datos == null || (datos.getPh() == null && datos.getTemperatura() == null)) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Datos vacíos o inválidos");
            return ResponseEntity.badRequest().body(error);
        }

        repository.save(datos);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Datos guardados correctamente");
        return ResponseEntity.ok(response);
    }
}
