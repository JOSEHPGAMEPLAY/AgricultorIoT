package iser.apiOrion.collection;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "TipoCultivo")
public class TipoCultivo {

    @Id
    private String id;

    @Field(name = "nombre")
    private String nombre;

    @Field(name = "descripcion")
    private String descripcion;

}
