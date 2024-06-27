package iser.apiOrion.DTO;

import lombok.Data;

@Data
public class EstacionDTO {

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
