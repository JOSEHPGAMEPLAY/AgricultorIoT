package iser.apiOrion.repository;

import iser.apiOrion.collection.TuyaSensorData;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface TuyaSensorDataRepository extends MongoRepository<TuyaSensorData, String> {
    Optional<TuyaSensorData> findByNombre(String nombre);
}



