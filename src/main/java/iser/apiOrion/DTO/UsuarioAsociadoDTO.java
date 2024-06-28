package iser.apiOrion.DTO;

import lombok.Data;

@Data
public class UsuarioAsociadoDTO {

    /**
     * id id del usuario asociado
     * idUsuario id del usuario
     * idEstacion id de la estacion
     * usuario nick del usuario
     */
    private String id;
    private String idUsuario;
    private String idEstacion;
    private String usuario;

}
