package iser.apiOrion.serviceImplement;


import iser.apiOrion.collection.Datos;
import iser.apiOrion.repository.DatosRepository;
import iser.apiOrion.service.DatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DatosServiceImpl implements DatosService {

    @Autowired
    DatosRepository datosRepository;


    @Override
    public ResponseEntity<?> rangofecha(Date fechainicio, Date fechafin) {
        try {
            List<Datos> datos = datosRepository.findByFechaBetween(fechainicio, fechafin);
            return ResponseEntity.ok(datos);
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

}
