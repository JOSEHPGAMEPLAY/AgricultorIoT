package iser.apiOrion.repository;

import iser.apiOrion.collection.UsuarioHibernadero;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioHibernaderoRepository extends MongoRepository<UsuarioHibernadero, String> {
}
