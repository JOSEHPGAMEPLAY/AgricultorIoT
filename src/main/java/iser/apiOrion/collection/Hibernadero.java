package iser.apiOrion.collection;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "hibernadero")
public class Hibernadero {

    @Field(name = "id")
    private String id;

    @Field(name = "imagen")
    private String imagen;

    @Field(name = "ciudad")
    private String ciudad;

    @Field(name = "departamento")
    private String departamento;

    @Field(name = "nombre")
    private String nombre;

    @Field(name = "encargado")
    private String encargado;

    @Field(name = "detalles")
    private String detalles;

    @Field(name = "estado")
    private String estado;


}
