package iser.apiOrion.DTO;

import lombok.Data;

@Data
public class UsuarioEstacionDTO {

    private String id;
    private String idHibernadero;
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
