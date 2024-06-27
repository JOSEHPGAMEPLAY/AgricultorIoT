package iser.apiOrion.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import iser.apiOrion.collection.Usuario;
import iser.apiOrion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Operation(summary = "Obtener todos los usuarios",
            description = "Obtiene todos los datos de los usuarios registrados en la base de datos, Consulta detalles de los usuarios.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/buscarTodos")
    public ResponseEntity<?> buscarTodos() {
        return usuarioService.buscarTodos();
    }

    @Operation(summary = "Obtener un usuario por su id",
            description = "Obtiene los datos de un usuario en especifico por su id, Consulta detalles de un usuario en especifico.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(String id) {
        return usuarioService.buscar(id);
    }

    @Operation(summary = "Actualiza un usuario",
            description = "Actualiza los datos de un usuario en la base de datos, Actualizando los detalles de un usuario en la base de datos.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(Usuario usuario) {
        return usuarioService.actualizar(usuario);
    }

    //eliminar
    @Operation(summary = "Eliminar un usuario",
            description = "Elimina los datos de un usuario en la base de datos, Eliminando los detalles de un usuario en la base de datos.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"operacion exitosa\"}"))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminar(String id) {
        return usuarioService.eliminar(id);
    }


}
