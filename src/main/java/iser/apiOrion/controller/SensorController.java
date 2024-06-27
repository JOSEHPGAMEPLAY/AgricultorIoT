package iser.apiOrion.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import iser.apiOrion.collection.Sensor;
import iser.apiOrion.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @Operation(summary = "Obtener todos los datos de los sensores",
            description = "Obtiene todos los datos de los sensores registrados en la base de datos, Consulta detalles de los sensores.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Sensor.class)))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/obtenerTodos")
    public ResponseEntity<?> obtenerTodos(){
        return sensorService.buscarTodos();
    }

    @Operation(summary = "Obtener un sensor por su id",
            description = "Obtiene los datos de un sensor en especifico por su id, Consulta detalles de un sensor en especifico.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sensor.class))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/obtenerPorId")
    public ResponseEntity<?> obtenerPorId(@RequestParam String id){
        return sensorService.buscarPorId(id);
    }

    @Operation(summary = "Insertar un sensor",
            description = "Inserta un sensor en la base de datos, Insertando los detalles de un sensor en la base de datos.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sensor.class))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @PostMapping("/crearSensor")
    public ResponseEntity<?> crearSensor(@RequestBody Sensor sensor){
        return sensorService.crearSensor(sensor);
    }

    @Operation(summary = "Elimina un sensor",
            description = "Elimina un sensor en la base de datos, Eliminando los detalles de un sensor en la base de datos.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"operacion exitosa\"}"))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @DeleteMapping("/borrarSensor")
    public ResponseEntity<?> borrarSensor(@RequestParam String id){
        return sensorService.borrarSensor(id);
    }

    @Operation(summary = "Obtener un sensor por su hibernadero",
            description = "Obtiene los datos de un sensor en especifico por su hibernadero, Consulta detalles de un sensor en especifico.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sensor.class))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/obtenerPorEstacion")
    public ResponseEntity<?> obtenerPorEstacion(@RequestParam String idHibernadero){
        return sensorService.buscarPorEstacion(idHibernadero);
    }

}
