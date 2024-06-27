package iser.apiOrion.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import iser.apiOrion.collection.TipoCultivo;
import iser.apiOrion.service.TipoCultivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tipoCultivo")
public class TipoCultivoController {

    @Autowired
    TipoCultivoService tipoCultivoService;

    @Operation(summary = "Obtener todos los tipos de cultivo",
            description = "Obtiene todos los tipos de cultivo registrados en la base de datos, Consulta detalles de los tipos de cultivo.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TipoCultivo.class)))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/buscarTodos")
    public ResponseEntity<?> buscarTodos() {
        return tipoCultivoService.buscarTodos();
    }


    @Operation(summary = "Obtener un tipo de cultivo por su id",
            description = "Obtiene los datos de un tipo de cultivo en especifico por su id, Consulta detalles de un tipo de cultivo en especifico.")
    @ApiResponses(value = {

                @ApiResponse(responseCode = "200", description = "operacion exitosa",
                        content = { @Content(mediaType = "application/json",
                                schema = @Schema(implementation = TipoCultivo.class))}),
                @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @GetMapping("/buscarPorId")
    public ResponseEntity<?> buscarPorId(@RequestParam String id) {
        return tipoCultivoService.buscarPorId(id);
    }


    @Operation(summary = "Guardar un tipo de cultivo",
            description = "Guarda los datos de un tipo de cultivo en la base de datos, Guardando los detalles de un tipo de cultivo en la base de datos.")
    @ApiResponses(value = {

                @ApiResponse(responseCode = "200", description = "operacion exitosa",
                        content = { @Content(mediaType = "application/json",
                                schema = @Schema(implementation = TipoCultivo.class))}),
                @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody TipoCultivo tipoCultivo) {
        return tipoCultivoService.guardar(tipoCultivo);
    }

    @Operation(summary = "Actualizar un tipo de cultivo",
            description = "Actualiza los datos de un tipo de cultivo en la base de datos, Actualizando los detalles de un tipo de cultivo en la base de datos.")
    @ApiResponses(value = {

                @ApiResponse(responseCode = "200", description = "operacion exitosa",
                        content = { @Content(mediaType = "application/json",
                                schema = @Schema(implementation = TipoCultivo.class))}),
                @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody TipoCultivo tipoCultivo) {
        return tipoCultivoService.actualizar(tipoCultivo);
    }

    @Operation(summary = "Eliminar un tipo de cultivo",
            description = "Elimina los datos de un tipo de cultivo en la base de datos, Eliminando los detalles de un tipo de cultivo en la base de datos.")
    @ApiResponses(value = {

                @ApiResponse(responseCode = "200", description = "operacion exitosa",
                        content = { @Content(mediaType = "application/json",
                                examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"operacion exitosa\"}"))}),
                @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                        examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminar(@RequestParam String id) {
        return tipoCultivoService.eliminar(id);
    }

}
