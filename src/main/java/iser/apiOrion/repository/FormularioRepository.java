package iser.apiOrion.repository;

import iser.apiOrion.collection.Formulario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormularioRepository extends MongoRepository<Formulario, String> {
}
