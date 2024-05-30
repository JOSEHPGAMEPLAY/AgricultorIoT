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
    public ResponseEntity<?> rangofecha(Date fechainicio, Date fechafin, String idSensor) {
        try {
            List<Datos> datos = datosRepository.findByIdSensorAndFechaBetween(idSensor, fechainicio, fechafin);
            return ResponseEntity.ok(datos);
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> insertar(String idSensor, String valor, Date fecha) {
        try {
            Datos datos = new Datos();
            datos.setIdSensor(idSensor);
            datos.setValor(valor);
            datos.setFecha(fecha);

            return ResponseEntity.ok(datosRepository.save(datos));
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }


}
