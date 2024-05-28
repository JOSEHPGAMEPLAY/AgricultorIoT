package iser.apiOrion.collection;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Document(collection = "datos")
public class Datos {

    @Field(name = "id_sensor")
    private String idSensor;

    @Field(name = "valor")
    private String valor;

    @Field(name = "fecha")
    private Date fecha;

}