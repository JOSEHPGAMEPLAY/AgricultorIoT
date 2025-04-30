package iser.apiOrion.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tuya_sensor_data") // coleccion creada en BD
public class TuyaSensorData {

    @Id
    private String id; // ID automático generado en BD

    private Double ph;
    private Double orp;
    private Double ec;
    private Double tds;
    private Double salinidad;
    private Double temperatura;
}

