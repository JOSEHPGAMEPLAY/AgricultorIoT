package iser.apiOrion.DTO;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class UsuarioDTO {

    /**
     * id: Identificador del usuario
     * nombre: Nombre del usuario
     * apellido: Apellido del usuario
     * edad: Edad del usuario
     */

    private String nombre;
    private String apellido;
    private Integer edad;

}
