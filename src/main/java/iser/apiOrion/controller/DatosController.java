package iser.apiOrion.controller;


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

    @GetMapping("/rangoFechasporHibernadero")
    public ResponseEntity<?> rangoFechasPorHibernadero(@RequestParam("fechaInicial")    String fechaIncial,
                                                       @RequestParam("fechafinal")      String fechaFinal,
                                                       @RequestParam("idSensor")        String idSensor ) throws ParseException {

        //String fechaString = "2024-05-09";

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        Date fechainicio = formato.parse(fechaIncial);
        Date fechafinal = formato.parse(fechaFinal);
        return datosService.rangofecha(fechainicio, fechafinal, idSensor);
    }

    //insertar
    @PostMapping("/insertar")
    public ResponseEntity<?> insertar(@RequestParam("idSensor") String idSensor,
                                     @RequestParam("valor") String valor) throws ParseException {
        Date fechaDate = new Date();
        return datosService.insertar(idSensor, valor, fechaDate);
    }




}
