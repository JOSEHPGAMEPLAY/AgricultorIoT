package iser.apiOrion.serviceImplement;

import iser.apiOrion.DTO.SensorDTO;
import iser.apiOrion.collection.Estacion;
import iser.apiOrion.collection.Sensor;
import iser.apiOrion.repository.EstacionRepository;
import iser.apiOrion.repository.SensorRepository;
import iser.apiOrion.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static iser.apiOrion.constant.messageConstant.buildMessage;

@Service
public class SensorServiceImpl implements SensorService {

    /**
     * Repositorio de sensores
     */
    @Autowired
    private SensorRepository sensorRepository;

    /**
     * Repositorio de estaciones
     */
    @Autowired
    EstacionRepository estacionRepository;


    /**
     * Metodo que permite obtener todos los sensores
     *
     * @return lista de sensores
     */
    @Override
    public ResponseEntity<?> buscarTodos() {
        try {
            List<Sensor> sensors = sensorRepository.findAll();
            List<SensorDTO> sensorDTOList = new ArrayList<>();
            String ubicacion = "";
            for(Sensor sensor : sensors){
                SensorDTO sensorDTO = new SensorDTO();
                sensorDTO.setId(sensor.getId());
                sensorDTO.setIdHibernadero(sensor.getIdHibernadero());
                sensorDTO.setNombre(sensor.getNombre());
                sensorDTO.setDescripcion(sensor.getDescripcion());
                sensorDTO.setConfig(sensor.isConfig());
                Optional<Estacion> estacion = estacionRepository.findById(sensor.getIdHibernadero());
                ubicacion = estacion.map(value -> value.getCiudad() + " - " + value.getDepartamento()).orElse("No se encontro la ubicacion");
                sensorDTO.setUbicacion(ubicacion);
                sensorDTOList.add(sensorDTO);
            }

            return ResponseEntity.ok(sensorDTOList);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Metodo que permite obtener un sensor por su id
     *
     * @param id id del sensor
     * @return sensor
     */
    @Override
    public ResponseEntity<?> buscarPorId(String id) {
        try {
            Optional<Sensor> sensor = sensorRepository.findById(id);
            if (sensor.isEmpty()) {
                return ResponseEntity.badRequest().body(buildMessage("Sensor no encontrado"));
            }

            SensorDTO sensorDTO = new SensorDTO();
            sensorDTO.setId(sensor.get().getId());
            sensorDTO.setIdHibernadero(sensor.get().getIdHibernadero());
            sensorDTO.setNombre(sensor.get().getNombre());
            sensorDTO.setDescripcion(sensor.get().getDescripcion());
            sensorDTO.setConfig(sensor.get().isConfig());
            Optional<Estacion> estacion = estacionRepository.findById(sensor.get().getIdHibernadero());
            String ubicacion = estacion.map(value -> value.getCiudad() + " - " + value.getDepartamento()).orElse("No se encontro la ubicacion");
            sensorDTO.setUbicacion(ubicacion);

            return ResponseEntity.ok(sensorDTO);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Metodo que permite crear un sensor
     *
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
     *
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
     *
     * @param id id del sensor
     * @return sensor borrado
     */
    @Override
    public ResponseEntity<?> borrarSensor(String id) {
        try {
            sensorRepository.deleteById(id);
            return ResponseEntity.ok(buildMessage("Sensor borrado"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Metodo que permite obtener los sensores de un hibernadero
     *
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
