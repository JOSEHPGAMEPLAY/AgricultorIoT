package iser.apiOrion.repository;

import iser.apiOrion.collection.Datos;
import iser.apiOrion.collection.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface DatosRepository extends MongoRepository<Datos, String> {

    //List<Sensor> findByFechaBetween(Date fecha1, Date fecha2);
    List<Datos> findByFechaBetween(Date fecha1, Date fecha2);
}
