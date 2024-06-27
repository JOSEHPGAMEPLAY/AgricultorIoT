package iser.apiOrion.repository;

import iser.apiOrion.collection.Estacion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstacionRepository extends MongoRepository<Estacion, String> {

}
