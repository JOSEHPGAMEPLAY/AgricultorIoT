package iser.apiOrion.collection;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "sensor")
public class Sensor {

    @Id
    private String id;

    @Field(name = "id_hibernadero")
    private String idHibernadero;

    @Field(name = "nombre")
    private String nombre;

    @Field(name = "descripcion")
    private String descripcion;

    @Field(name = "config")
    private boolean config;

}
