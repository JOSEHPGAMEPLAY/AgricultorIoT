package iser.apiOrion.DTO;

import lombok.Data;

@Data
public class UsuarioEstacionDTO {

    /*
     * id: Identificador de la estacion
     * idEstacion: Identificador de la estacion
     * idUsuario: Identificador del usuario
     * imagen: Imagen de la estacion
     * ciudad: Ciudad de la estacion
     * departamento: Departamento de la estacion
     * nombre: Nombre de la estacion
     * encargado: Encargado de la estacion
     * detalles: Detalles de la estacion
     * estado: Estado de la estacion
     * idTipoCultivo: Identificador del tipo de cultivo
     * nombreTipoCultivo: Nombre del tipo de cultivo
     * Numero_Asociados: Numero de asociados
     */

    private String id;
    private String idEstacion;
    private String idUsuario;
    private String imagen;
    private String ciudad;
    private String departamento;
    private String nombre;
    private String encargado;
    private String detalles;
    private String estado;
    private String idTipoCultivo;
    private String nombreTipoCultivo;
    private Integer Numero_Asociados;

}
