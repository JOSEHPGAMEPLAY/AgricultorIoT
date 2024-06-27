package iser.apiOrion.repository;

import iser.apiOrion.collection.UsuarioEstacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UsuarioEstacionRepository extends MongoRepository<UsuarioEstacion, String> {

    List<UsuarioEstacion> findByIdUsuario(String idUsuario);

    List<UsuarioEstacion> findByIdEstacion(String idEstacion);

    int countByIdEstacion(String idEstacion);
}
