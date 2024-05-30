package iser.apiOrion.repository;

import iser.apiOrion.collection.Hibernadero;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HibernaderoRepository extends MongoRepository<Hibernadero, String> {

}
