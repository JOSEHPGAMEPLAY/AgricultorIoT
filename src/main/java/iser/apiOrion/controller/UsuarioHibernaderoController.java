package iser.apiOrion.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import iser.apiOrion.collection.Hibernadero;
import iser.apiOrion.collection.UsuarioHibernadero;
import iser.apiOrion.service.UsuarioHibernaderoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarioHibernadero")
public class UsuarioHibernaderoController {

    @Autowired
    UsuarioHibernaderoService usuarioHibernaderoService;

    @Operation(summary = "Buscar todos los datos de los hibernaderos y usuarios",
            description = "Obtiene todos los usuarios asociados a los hibernaderos registrados en la base de datos, Consulta detalles de los usuarios asociados a los hibernaderos.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UsuarioHibernadero.class)))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/buscarHibernaderosUsuario")
    public ResponseEntity<?> buscarTodos(String id) {
        return usuarioHibernaderoService.buscarHibernaderosUsuario(id);
    }

    @Operation(summary = "Consulta por id de usuarioHibernadero",
            description = "Obtiene los datos de un usuarioHibernadero en especifico por su id, Consulta detalles de un usuarioHibernadero en especifico.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioHibernadero.class))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/buscarPorId")
    public ResponseEntity<?> buscarPorId(String id) {
        return usuarioHibernaderoService.buscarPorId(id);
    }

    @Operation(summary = "Asocia un usuario a un hibernadero",
            description = "Asocia un usuario a un hibernadero en la base de datos, Insertando los detalles de un usuario asociado a un hibernadero en la base de datos.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioHibernadero.class))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @PostMapping("/crearUsuarioHibernadero")
    public ResponseEntity<?> crearUsuarioHibernadero(String idUsuario, String idHibernadero) {
        return usuarioHibernaderoService.crearUsuarioHibernadero(idUsuario, idHibernadero);
    }

    @Operation(summary = "Elimina un usuarioHibernadero",
            description = "Elimina un usuarioHibernadero en la base de datos, Eliminando los detalles de un usuario asociado a un hibernadero en la base de datos.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"operacion exitosa\"}"))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @DeleteMapping("/borrarUsuarioHibernadero")
    public ResponseEntity<?> borrarUsuarioHibernadero(String id) {
        return usuarioHibernaderoService.borrarUsuarioHibernadero(id);
    }

    @Operation(summary = "Buscar los hibernaderos asociados a un usuario",
            description = "Obtiene los datos de los hibernaderos asociados a un usuario en especifico por su id, Consulta detalles de los hibernaderos asociados a un usuario en especifico.")
    @ApiResponses(value = {

                @ApiResponse(responseCode = "200", description = "operacion exitosa",
                        content = { @Content(mediaType = "application/json",
                                schema = @Schema(implementation = UsuarioHibernadero.class))}),
                @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/buscarPorUsuario")
    public ResponseEntity<?> buscarPorUsuario(String idUsuario) {
        return usuarioHibernaderoService.buscarPorUsuario(idUsuario);
    }

    @Operation(summary = "Buscar los usuarios asociados a un hibernadero",
            description = "Obtiene los datos de los usuarios asociados a un hibernadero en especifico por su id, Consulta detalles de los usuarios asociados a un hibernadero en especifico.")
    @ApiResponses(value = {

                @ApiResponse(responseCode = "200", description = "operacion exitosa",
                        content = { @Content(mediaType = "application/json",
                                schema = @Schema(implementation = UsuarioHibernadero.class))}),
                @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/buscarPorHibernadero")
    public ResponseEntity<?> buscarPorHibernadero(String idHibernadero) {
        return usuarioHibernaderoService.buscarPorHibernadero(idHibernadero);
    }

}
