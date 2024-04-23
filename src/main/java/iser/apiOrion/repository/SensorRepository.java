package iser.apiOrion.repository;

import iser.apiOrion.collection.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SensorRepository extends MongoRepository<Sensor, String> {
}
