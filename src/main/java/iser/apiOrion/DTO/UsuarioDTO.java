package iser.apiOrion.DTO;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class UsuarioDTO {

    private String nombre;

    private String apellido;

    private Integer edad;

}
