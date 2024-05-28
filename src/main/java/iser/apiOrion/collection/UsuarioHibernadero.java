package iser.apiOrion.collection;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "usuariohibernadero")
public class UsuarioHibernadero {

    @Field(name = "id_hibernadero")
    private String idHibernadero;

    @Field(name = "usuario")
    private String idUsuario;

}
