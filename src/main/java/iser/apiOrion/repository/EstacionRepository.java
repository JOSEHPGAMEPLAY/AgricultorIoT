package iser.apiOrion.repository;

import iser.apiOrion.collection.Estacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EstacionRepository extends MongoRepository<Estacion, String> {

    List<Estacion> findAllByEncargado(String encargado);

}
