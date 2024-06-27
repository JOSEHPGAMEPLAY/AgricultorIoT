package iser.apiOrion.DTO;

import lombok.Data;

@Data
public class EstacionDTO {
    /*
        * id: Identificador de la estación
        * imagen: Imagen de la estación
        * ciudad: Ciudad de la estación
        * departamento: Departamento de la estación
        * nombre: Nombre de la estación
        * encargado: Encargado de la estación
        * detalles: Detalles de la estación
        * estado: Estado de la estación
        * idTipoCultivo: Identificador del tipo de cultivo
        * descripcionTipoCultivo: Descripción del tipo de cultivo
        * Numero_Asociados: Número de asociados
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
