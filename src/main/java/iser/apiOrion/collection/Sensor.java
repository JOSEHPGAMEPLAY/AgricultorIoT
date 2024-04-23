package iser.apiOrion.collection;


import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "Sensor")
public class Sensor {

    @Id
    private String id;

    @Field(name = "temperatura")
    private Double temperatura;

    @Field(name = "humedad")
    private Double humedad;

}
