package iser.apiOrion.repository;

import iser.apiOrion.collection.Datos;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatosRepository extends MongoRepository<Datos, String> {
}
