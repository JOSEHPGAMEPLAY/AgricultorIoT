package iser.apiOrion.repository;

import iser.apiOrion.collection.Hibernadero;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HibernaderoRepository extends MongoRepository<Hibernadero, String> {


}
