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

    /**
     * Repositorio de sensores
     */
    @Autowired
    private SensorRepository sensorRepository;


    /**
     * Metodo que permite obtener todos los sensores
     * @return lista de sensores
     */
    @Override
    public ResponseEntity<?> buscarTodos() {
        try {
            List<Sensor> sensors = sensorRepository.findAll();
            return ResponseEntity.ok(sensors);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Metodo que permite obtener un sensor por su id
     * @param id id del sensor
     * @return sensor
     */
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

    /**
     * Metodo que permite crear un sensor
     * @param sensor sensor a crear
     * @return sensor creado
     */
    @Override
    public ResponseEntity<?> crearSensor(Sensor sensor) {
        try {
            sensorRepository.save(sensor);
            return ResponseEntity.ok(sensor);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Metodo que permite actualizar un sensor
     * @param sensor sensor a actualizar
     * @return sensor actualizado
     */
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

    /**
     * Metodo que permite borrar un sensor
     * @param id id del sensor
     * @return sensor borrado
     */
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

    /**
     * Metodo que permite obtener los sensores de un hibernadero
     * @param idHibernadero id del hibernadero
     * @return lista de sensores
     */
    @Override
    public ResponseEntity<?> buscarPorHibernadero(String idHibernadero) {
        try {
            List<Sensor> sensors = sensorRepository.findByidHibernadero(idHibernadero);
            return ResponseEntity.ok(sensors);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
