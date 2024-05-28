package iser.apiOrion.controller;


import iser.apiOrion.service.DatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("api/v1/datos")
public class DatosController {

    @Autowired
    DatosService datosService;

    @GetMapping("/rangoFechas")
    public ResponseEntity<?> rangoFechas(@RequestParam("fechaInicial") String fechaIncial,
                                         @RequestParam("fechafinal") String fechaFinal) throws ParseException {

        //String fechaString = "2024-05-09";

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        Date fechainicio = formato.parse(fechaIncial);
        Date fechafinal = formato.parse(fechaFinal);
        return datosService.rangofecha(fechainicio, fechafinal);
    }

}
