package iser.apiOrion.service;

import iser.apiOrion.collection.Sensor;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface SensorService {

    ResponseEntity<?> buscarTodos();

    ResponseEntity<?> buscarPorId(String id);

    ResponseEntity<?> crearSensor(Sensor sensor);

    ResponseEntity<?> actualizarSensor(Sensor sensor);

    ResponseEntity<?> borrarSensor(String id);


}
