package iser.apiOrion.collection;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "usuariohibernadero")
public class UsuarioHibernadero {

    @Id
    private String id;

    @Field(name = "id_hibernadero")
    private String idHibernadero;

    @Field(name = "id_usuario")
    private String idUsuario;

}
