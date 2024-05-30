package iser.apiOrion.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "validacion")
public class Validacion {

    @Id
    private String id;

    @Field(name = "usuario")
    private String usuario;

    @Field(name = "email")
    private String email;

    @Field(name = "codigo")
    private String codigo;

}
