package iser.apiOrion.serviceImplement;

import iser.apiOrion.collection.Sensor;
import iser.apiOrion.repository.SensorRepository;
import iser.apiOrion.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorRepository sensorRepository;


    @Override
    public ResponseEntity<?> buscarTodos() {
        try {
            List<Sensor> sensors = sensorRepository.findAll();
            if (!sensors.isEmpty()) {
                sensors.forEach(Sensor::getFecha);
            }
            return ResponseEntity.ok(sensors);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> buscarPorId(String id) {
        try {
            Sensor sensor = sensorRepository.findById(id).get();
            return ResponseEntity.ok(sensor);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> crearSensor(Sensor sensor) {
        try {
            sensor.setFecha(new Date());
            sensorRepository.save(sensor);
            return ResponseEntity.ok(sensor);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> actualizarSensor(Sensor sensor) {
        try {
            sensorRepository.save(sensor);
            return ResponseEntity.ok(sensor);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> borrarSensor(String id) {
        try {
            sensorRepository.deleteById(id);
            return ResponseEntity.ok("Sensor borrado");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> rangofecha(Date fechainicio, Date fechafin) {
        try {
            List<Sensor> sensors = sensorRepository.findByFechaBetween(fechainicio, fechafin);
            if (!sensors.isEmpty()) {
                sensors.forEach(Sensor::getFecha);
            }
            return ResponseEntity.ok(sensors);
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
}
