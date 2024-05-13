package iser.apiOrion.collection;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Document(collection = "Sensor")
public class Sensor {

    @Id
    @JsonIgnore
    private String id;

    @Field(name = "temperatura")
    private Double temperatura;

    @Field(name = "humedad")
    private Double humedad;

    @Field(name = "fecha")
    @JsonIgnore
    private Date fecha;

}
