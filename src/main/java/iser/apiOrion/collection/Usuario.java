package iser.apiOrion.collection;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "usuario")
public class Usuario {

    @Id
    private String id;

    @Field(name = "usuario")
    private String usuario;

    @Field(name = "nombres")
    private String nombres;

    @Field(name = "email")
    private String email;

    @Field(name = "clave")
    private String clave;

}
