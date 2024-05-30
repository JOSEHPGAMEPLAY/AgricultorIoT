package iser.apiOrion.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import iser.apiOrion.service.ValidacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/validacion")
public class ValidacionController {

    @Autowired
    ValidacionService validacionService;

    @Operation(summary = "Crear un codigo de validacion",
            description = "Crea un codigo de validacion para un usuario en especifico, Creando un codigo de validacion para un usuario en especifico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @PostMapping("/crearCodigo")
    public ResponseEntity<?> crearCodigo(@RequestParam("usuario") String usuario) {
        return validacionService.crearCodigoValidacion(usuario);
    }

    //validarCodigo
    @Operation(summary = "Validar un codigo de validacion",
            description = "Valida un codigo de validacion para un usuario en especifico, Validando un codigo de validacion para un usuario en especifico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operacion exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "peticion fallida", content = { @io.swagger.v3.oas.annotations.media.Content (mediaType = "application/json",
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\":\"peticion fallida\"}"))}),
    })
    @PutMapping("/validarCodigo")
    public ResponseEntity<?> validarCodigo(@RequestParam("usuario") String usuario, @RequestParam("codigo") String codigo) {
        return validacionService.validarCodigoValidacion(usuario, codigo);
    }

}
