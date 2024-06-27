package iser.apiOrion.DTO;


import lombok.Data;

@Data
public class SensorDTO {

    /*
     * id: Identificador del sensor
     * idEstacion: Identificador de la estación
     * nombre: Nombre del sensor
     * descripcion: Descripción del sensor
     * config: Configuración del sensor
     * ubicacion: Ubicación del sensor
     */

    private String id;
    private String idEstacion;
    private String nombre;
    private String descripcion;
    private boolean config;
    private String ubicacion;

}
