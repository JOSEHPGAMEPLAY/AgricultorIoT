package iser.apiOrion.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import iser.apiOrion.collection.Formulario;
import iser.apiOrion.collection.Sensor;
import iser.apiOrion.service.FormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/formulario")
public class FormularioController {

    @Autowired
    FormularioService formularioService;

    @Operation(summary = "Obtener todos los formularios registrados.",
            description = "Obtiene todos los formularios de solicitud para participar en el proyecto, Consulta detalles de los formularios.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Formulario.class)))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/obtenerTodos")
    public ResponseEntity<?> obtenerTodos(){
        return formularioService.buscarTodos();
    }

    @Operation(summary = "Crear un formulario de solicitud de participacion en el proyecto.",
            description = "Registra un formulario de solicitud para participar en el proyecto, Insertando los detalles de un formulario en la base de datos.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Formulario.class))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @PostMapping("/crear")
    public ResponseEntity<?> crear(Formulario formulario){
        return formularioService.crear(formulario);
    }

    @Operation(summary = "Elimina un formulario de solicitud de participacion en el proyecto.",
            description = "Elimina un formulario de solicitud para participar en el proyecto, Eliminando los detalles de un formulario en la base de datos.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"operacion exitosa\"}"))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @DeleteMapping("/borrar")
    public ResponseEntity<?> borrar(String id){
        return formularioService.borrar(id);
    }

    @Operation(summary = "Obtener un formulario por su id",
            description = "Obtiene los datos de un formulario en especifico por su id, Consulta detalles de un formulario en especifico.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Formulario.class))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/obtenerPorId")
    public ResponseEntity<?> obtenerPorId(String id){
        return formularioService.buscarPorId(id);
    }

    //actualizar
    @Operation(summary = "Actualizar un formulario de solicitud de participacion en el proyecto.",
            description = "Actualiza un formulario de solicitud para participar en el proyecto, Actualizando los detalles de un formulario en la base de datos.")
    @ApiResponses(value = {

                @ApiResponse(responseCode = "200", description = "operacion exitosa",
                        content = { @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Formulario.class))}),
                @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(Formulario formulario){
        return formularioService.actualizar(formulario);
    }

}
