package iser.apiOrion.DTO;

import lombok.Data;

@Data
public class UsuarioEstacionDTO {

    /*
     * id: Identificador de la estación
     * idEstacion: Identificador de la estación
     * idUsuario: Identificador del usuario
     * imagen: Imagen de la estación
     * ciudad: Ciudad de la estación
     * departamento: Departamento de la estación
     * nombre: Nombre de la estación
     * encargado: Encargado de la estación
     * detalles: Detalles de la estación
     * estado: Estado de la estación
     * idTipoCultivo: Identificador del tipo de cultivo
     * nombreTipoCultivo: Nombre del tipo de cultivo
     * Numero_Asociados: Número de asociados
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
