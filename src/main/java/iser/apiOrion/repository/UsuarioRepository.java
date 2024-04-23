package iser.apiOrion.repository;

import iser.apiOrion.collection.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}
