package com.example.salesianos.gestionrecetas.Controller;

import com.example.salesianos.gestionrecetas.Dto.IngredienteDto;
import com.example.salesianos.gestionrecetas.Model.Ingrediente;
import com.example.salesianos.gestionrecetas.Service.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredientes")
@RequiredArgsConstructor
@Tag(name = "Ingrediente Controller", description = "Un controlador de ingredientes")
public class IngredienteController {

    private final IngredienteService ingredienteService;

    @Operation(
            summary = "Obtener todos los ingredientes",
            description = "Permite obtener todos los ingredientes disponibles"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listado de ingredientes",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = IngredienteDto.class
                                            )
                                    ),
                                    examples = @ExampleObject(
                                            name = "Listado de ingredientes",
                                            summary = "Ejemplo de listado con varios ingredientes",
                                            value = """
                    [
                      {
                        "id": 1,
                        "nombre": "Patata"
                      },
                      {
                        "id": 2,
                        "nombre": "Huevo"
                      },
                      {
                        "id": 3,
                        "nombre": "Tomate"
                      }
                    ]
                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se han encontrado ingredientes",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 404",
                                            summary = "Mensaje de error informando de que no hay ingredientes",
                                            value = """
                                                    
                                                    {
                                                      "detail": "No se han encontrado ingredientes",
                                                      "instance": "/api/v1/ingredientes",
                                                      "status": 404,
                                                      "title": "Recurso no encontrado",
                                                      "type": "https://example.com/errors/not-found"
                                                    }
                                                    
                                                    """
                                    )
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<IngredienteDto>> getAll() {

        List<IngredienteDto> lista = ingredienteService.getAll()
                .stream()
                .map(IngredienteDto::of)
                .toList();

        return ResponseEntity.ok(lista);
    }

    @Operation(
            summary = "Crear un nuevo ingrediente",
            description = "Permite crear un nuevo ingrediente"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Ingrediente creado correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = IngredienteDto.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Ingrediente creado",
                                            summary = "Ejemplo de ingrediente recién creado",
                                            value = """
                                                    {
                                                      "id": 4,
                                                      "nombre": "Cebolla"
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error de validación en los datos enviados",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 400",
                                            summary = "Mensaje de error de validación",
                                            value = """
                                                    
                                                    {
                                                      "detail": "Error de validación",
                                                      "instance": "/api/v1/ingredientes",
                                                      "status": 400,
                                                      "title": "Bad Request",
                                                      "type": "about:blank",
                                                      "invalid-params": [
                                                        {
                                                          "object": "ingredienteDto",
                                                          "message": "must not be blank",
                                                          "field": "nombre"
                                                        }
                                                      ]
                                                    }
                                                    
                                                    """
                                    )
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<IngredienteDto> addIngrediente(@Valid @RequestBody IngredienteDto dto) {

        Ingrediente ingrediente = ingredienteService.crearIngrediente(dto);

        return ResponseEntity.status(201)
                .body(IngredienteDto.of(ingrediente));

    }

}
