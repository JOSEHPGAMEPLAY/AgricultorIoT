package iser.apiOrion.repository;

import iser.apiOrion.collection.UsuarioHibernadero;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UsuarioHibernaderoRepository extends MongoRepository<UsuarioHibernadero, String> {

    List<UsuarioHibernadero> findByIdUsuario(String idUsuario);

}
