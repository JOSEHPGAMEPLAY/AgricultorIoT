package iser.apiOrion.controller;

import iser.apiOrion.collection.Sensor;
import iser.apiOrion.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("api/v1/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping("/obtenerTodos")
    public ResponseEntity<?> obtenerTodos(){
        return sensorService.buscarTodos();
    }

    @GetMapping("/obtenerPorId")
    public ResponseEntity<?> obtenerPorId(String id){
        return sensorService.buscarPorId(id);
    }

    @PostMapping("/crearSensor")
    public ResponseEntity<?> crearSensor(Sensor sensor){
        return sensorService.crearSensor(sensor);
    }

    @PutMapping("/actualizarSensor")
    public ResponseEntity<?> actualizarSensor(Sensor sensor){
        return sensorService.actualizarSensor(sensor);
    }

    @DeleteMapping("/borrarSensor")
    public ResponseEntity<?> borrarSensor(String id){
        return sensorService.borrarSensor(id);
    }

    @GetMapping("/rangoFechas")
    public ResponseEntity<?> rangoFechas(@RequestParam("fechaInicial") String fechaIncial,
                                          @RequestParam("fechafinal") String fechaFinal) throws ParseException {

        //String fechaString = "2024-05-09";
        System.out.println("Fecha inicial: " + fechaIncial);
        System.out.println("Fecha final: " + fechaFinal);

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        Date fechainicio = formato.parse(fechaIncial);
        Date fechafinal = formato.parse(fechaFinal);
        return sensorService.rangofecha(fechainicio, fechafinal);
    }

}
