package iser.apiOrion.collection;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "usuarioestacion")
public class UsuarioEstacion {

    @Id
    private String id;

    @Field(name = "idEstacion")
    private String idEstacion;

    @Field(name = "id_usuario")
    private String idUsuario;

}
