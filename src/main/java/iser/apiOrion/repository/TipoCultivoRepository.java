package iser.apiOrion.repository;

import iser.apiOrion.collection.TipoCultivo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TipoCultivoRepository extends MongoRepository<TipoCultivo, String> {
}
