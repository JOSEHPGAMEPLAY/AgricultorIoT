package iser.apiOrion.DTO;


import lombok.Data;

@Data
public class SensorDTO {

    /*
     * id: Identificador del sensor
     * idEstacion: Identificador de la estacion
     * nombre: Nombre del sensor
     * descripcion: Descripcion del sensor
     * config: Configuracin del sensor
     * ubicacion: Ubicacion del sensor
     */

    private String id;
    private String idEstacion;
    private String nombre;
    private String descripcion;
    private boolean config;
    private String ubicacion;

}
