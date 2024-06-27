package iser.apiOrion.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import iser.apiOrion.collection.Usuario;
import iser.apiOrion.collection.UsuarioEstacion;
import iser.apiOrion.service.UsuarioEstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarioEstacion")
public class UsuarioEstacionController {

    @Autowired
    UsuarioEstacionService usuarioEstacionService;

    @Operation(summary = "Buscar todos los datos de las estaciones y usuarios",
            description = "Obtiene todos los usuarios asociados a las estaciones registrados en la base de datos, Consulta detalles de los usuarios asociados a las estaciones.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UsuarioEstacion.class)))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/buscarEstacionesUsuario")
    public ResponseEntity<?> buscarTodos(String id) {
        return usuarioEstacionService.buscarHibernaderosUsuario(id);
    }

    @Operation(summary = "Consulta por id de usuarioEstacion",
            description = "Obtiene los datos de un usuarioEstacion en especifico por su id, Consulta detalles de un usuarioEstacion en especifico.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioEstacion.class))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/buscarPorId")
    public ResponseEntity<?> buscarPorId(String id) {
        return usuarioEstacionService.buscarPorId(id);
    }

    @Operation(summary = "Asocia un usuario a un estacion",
            description = "Asocia un usuario a un estacion en la base de datos, Insertando los detalles de un usuario asociado a un estacion en la base de datos.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioEstacion.class))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @PostMapping("/crearUsuarioEstacion")
    public ResponseEntity<?> crearUsuarioHibernadero(String idUsuario, String idEstacion) {
        return usuarioEstacionService.crearUsuarioHibernadero(idUsuario, idEstacion);
    }

    @Operation(summary = "Elimina un usuarioEstacion",
            description = "Elimina un usuarioEstacion en la base de datos, Eliminando los detalles de un usuario asociado a un estacion en la base de datos.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = {@Content(mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"operacion exitosa\"}"))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @DeleteMapping("/borrarUsuarioEstacion")
    public ResponseEntity<?> borrarUsuarioHibernadero(String id) {
        return usuarioEstacionService.borrarUsuarioHibernadero(id);
    }

    @Operation(summary = "Buscar los estaciones asociados a un usuario",
            description = "Obtiene los datos de las estaciones asociados a un usuario en especifico por su id, Consulta detalles de las estaciones asociados a un usuario en especifico.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioEstacion.class))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/buscarPorUsuario")
    public ResponseEntity<?> buscarPorUsuario(String idUsuario) {
        return usuarioEstacionService.buscarPorUsuario(idUsuario);
    }

    @Operation(summary = "Buscar los usuarios asociados a un estacion",
            description = "Obtiene los datos de los usuarios asociados a un estacion en especifico por su id, Consulta detalles de los usuarios asociados a un estacion en especifico.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioEstacion.class))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/buscarPorEstacion")
    public ResponseEntity<?> buscarPorHibernadero(String idEstacion) {
        return usuarioEstacionService.buscarPorHibernadero(idEstacion);
    }

    @Operation(summary = "Buscar los usuarios no asociados a un estacion",
            description = "Obtiene los datos de los usuarios no asociados a un estacion en especifico por su id, Consulta detalles de los usuarios no asociados a un estacion en especifico.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/buscarUsuarioSinInvernadero")
    public ResponseEntity<?> buscarUsuarioSinInvernadero(String idEstacion) {
        return usuarioEstacionService.buscarUsuarioSinInvernadero(idEstacion);
    }

}
