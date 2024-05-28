package iser.apiOrion.repository;

import iser.apiOrion.collection.Hibernadero;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HibernaderoRepository extends MongoRepository<Hibernadero, String> {
}
