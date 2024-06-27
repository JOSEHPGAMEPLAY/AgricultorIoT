package iser.apiOrion.DTO;

import lombok.Data;

@Data
public class EstacionDTO {
    /*
        * id: Identificador de la estacion
        * imagen: Imagen de la estacion
        * ciudad: Ciudad de la estacion
        * departamento: Departamento de la estacion
        * nombre: Nombre de la estacion
        * encargado: Encargado de la estacion
        * detalles: Detalles de la estacion
        * estado: Estado de la estacion
        * idTipoCultivo: Identificador del tipo de cultivo
        * descripcionTipoCultivo: Descripcion del tipo de cultivo
        * Numero_Asociados: Numero de asociados
     */

    private String id;
    private String imagen;
    private String ciudad;
    private String departamento;
    private String nombre;
    private String encargado;
    private String detalles;
    private String estado;
    private String idTipoCultivo;
    private String descripcionTipoCultivo;
    private int Numero_Asociados;

}
