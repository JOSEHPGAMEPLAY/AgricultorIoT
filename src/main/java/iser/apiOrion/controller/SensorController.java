package iser.apiOrion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sensor")
public class SensorController {

    @GetMapping("/saludo")
    ResponseEntity<?> saludo() {
        return ResponseEntity.ok("Hola");
    }

}
