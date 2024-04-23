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

    @Field(name = "nombre")
    private String nombre;

    @Field(name = "apellido")
    private String apellido;

    @Field(name = "edad")
    private int edad;

    @Field(name = "email")
    private String email;

    @Field(name = "password")
    private String password;

}
