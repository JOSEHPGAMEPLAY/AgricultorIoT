package iser.apiOrion.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import iser.apiOrion.DTO.DatosDTO;
import iser.apiOrion.collection.Datos;
import iser.apiOrion.service.DatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("api/v1/datos")
public class DatosController {

    @Autowired
    DatosService datosService;

    @Operation(summary = "Rangos de fechas por sensor",
            description = "Obtiene los datos de un sensor en un rango de fechas determinado")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Datos.class)))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/rangoFechasporSensor")
    public ResponseEntity<?> rangoFechasPorHibernadero(@RequestParam("fechaInicial")    String fechaIncial,
                                                       @RequestParam("fechafinal")      String fechaFinal,
                                                       @RequestParam("idSensor")        String idSensor ) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");//String fechaString = "2024-05-09";
        Date fechainicio = formato.parse(fechaIncial);
        Date fechafinal = formato.parse(fechaFinal);
        return datosService.rangofecha(fechainicio, fechafinal, idSensor);
    }

    @Operation(summary = "insertar datos de un sensor",
            description = "Inserta los datos de un sensor en la base de datos, debe recibir el id del sensor, el valor y la fecha en la que se tomo la medicion ademas de esto este es el endpoint que debe consumir el arduino que este recibiendo los datos de este sensor.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DatosDTO.class))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @PostMapping("/insertar")
    public ResponseEntity<?> insertar(@RequestBody DatosDTO datosDTO)  {
        Date fechaDate = new Date();
        System.out.println("Datos: " + datosDTO.getIdSensor() + " " + datosDTO.getValor() + " " + fechaDate);
        return datosService.insertar(datosDTO.getIdSensor(), datosDTO.getValor(), fechaDate);
    }


}
