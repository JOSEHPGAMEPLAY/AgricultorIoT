package iser.apiOrion.repository;

import iser.apiOrion.collection.Validacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidacionRepository extends MongoRepository<Validacion, String> {

    Optional<Validacion> findByUsuarioAndCodigo(String usuario, String codigo);

}
