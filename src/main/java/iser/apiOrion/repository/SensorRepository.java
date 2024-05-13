package iser.apiOrion.repository;

import iser.apiOrion.collection.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface SensorRepository extends MongoRepository<Sensor, String> {

    //Consultar por rango de fechas
    List<Sensor> findByFechaBetween(Date fecha1, Date fecha2);
}
