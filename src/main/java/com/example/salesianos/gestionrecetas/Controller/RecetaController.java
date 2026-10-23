package com.example.salesianos.gestionrecetas.Controller;

import com.example.salesianos.gestionrecetas.Dto.IngredienteDto;
import com.example.salesianos.gestionrecetas.Dto.RecetaDto;
import com.example.salesianos.gestionrecetas.Model.Receta;
import com.example.salesianos.gestionrecetas.Service.RecetaService;
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
@RequestMapping("/api/v1/recetas")
@RequiredArgsConstructor
@Tag(name = "Receta Controller", description = "Un controlador de recetas")
public class RecetaController {

    private final RecetaService recetaService;


    @Operation(
            summary = "Obtener una receta por ID",
            description = "Permite obtener una receta específica por su ID, incluyendo sus ingredientes"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Receta encontrada",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = RecetaDto.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Receta encontrada",
                                            summary = "Ejemplo de receta con sus ingredientes",
                                            value = """
                                                    {
                                                      "id": 1,
                                                      "nombre": "Tortilla de patatas",
                                                      "tiempoDePreparacion": 30,
                                                      "dificultad": "MEDIA",
                                                      "idCategoria": 1,
                                                      "ingredientes": [
                                                        {
                                                          "id": 1,
                                                          "nombre": "Patata"
                                                        },
                                                        {
                                                          "id": 2,
                                                          "nombre": "Huevo"
                                                        }
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se ha encontrado la receta con ese ID",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 404",
                                            summary = "Mensaje de error cuando la receta no existe",
                                            value = """
                                                    
                                                    {
                                                      "detail": "No se ha encontrado la receta con ID: 99",
                                                      "instance": "/api/v1/recetas/99",
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
    @GetMapping("/{id}")
    public ResponseEntity<RecetaDto> getRecetaById(@PathVariable Long id) {
        return ResponseEntity.ok(RecetaDto.of(recetaService.getRecetaById(id)));
    }

    @Operation(
            summary = "Obtener todas las recetas",
            description = "Permite obtener todas las recetas disponibles con sus ingredientes"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listado de recetas",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = RecetaDto.class
                                            )
                                    ),
                                    examples = @ExampleObject(
                                            name = "Listado de recetas",
                                            summary = "Ejemplo de listado con varias recetas",
                                            value = """
                    [
                      {
                        "id": 1,
                        "nombre": "Tortilla de patatas",
                        "tiempoDePreparacion": 30,
                        "dificultad": "MEDIA",
                        "idCategoria": 1,
                        "ingredientes": [
                          {
                            "id": 1,
                            "nombre": "Patata"
                          },
                          {
                            "id": 2,
                            "nombre": "Huevo"
                          }
                        ]
                      },
                      {
                        "id": 2,
                        "nombre": "Ensalada César",
                        "tiempoDePreparacion": 15,
                        "dificultad": "FACIL",
                        "idCategoria": 2,
                        "ingredientes": [
                          {
                            "id": 3,
                            "nombre": "Lechuga"
                          },
                          {
                            "id": 4,
                            "nombre": "Pollo"
                          }
                        ]
                      }
                    ]
                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se han encontrado recetas",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 404",
                                            summary = "Mensaje de error informando de que no hay recetas",
                                            value = """
                                                    
                                                    {
                                                      "detail": "No se han encontrado recetas",
                                                      "instance": "/api/v1/recetas",
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
    public ResponseEntity<List<RecetaDto>> getAll(){
        List<RecetaDto> listaDto = recetaService.getAll()
                .stream()
                .map(RecetaDto::of)
                .toList();
        return ResponseEntity.ok(listaDto);
    }


    @Operation(
            summary = "Crear una nueva receta",
            description = "Permite crear una nueva receta asociada a una categoría"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Receta creada correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = RecetaDto.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Receta creada",
                                            summary = "Ejemplo de receta recién creada",
                                            value = """
                                                    {
                                                      "id": 3,
                                                      "nombre": "Paella valenciana",
                                                      "tiempoDePreparacion": 60,
                                                      "dificultad": "DIFICIL",
                                                      "idCategoria": 1,
                                                      "ingredientes": []
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error de validación o tiempo de preparación inválido",
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
                                                      "instance": "/api/v1/recetas",
                                                      "status": 400,
                                                      "title": "Bad Request",
                                                      "type": "about:blank",
                                                      "invalid-params": [
                                                        {
                                                          "object": "recetaDto",
                                                          "message": "must not be blank",
                                                          "field": "nombre"
                                                        }
                                                      ]
                                                    }
                                                    
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se ha encontrado la categoría asociada",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 404",
                                            summary = "La categoría indicada no existe",
                                            value = """
                                                    
                                                    {
                                                      "detail": "No se ha encontrado la categoría con ID: 99",
                                                      "instance": "/api/v1/recetas",
                                                      "status": 404,
                                                      "title": "Recurso no encontrado",
                                                      "type": "https://example.com/errors/not-found"
                                                    }
                                                    
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Ya existe una receta con ese nombre",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 409",
                                            summary = "Nombre de receta duplicado",
                                            value = """
                                                    
                                                    {
                                                      "detail": "Ya existe una receta con ese nombre",
                                                      "instance": "/api/v1/recetas",
                                                      "status": 409,
                                                      "title": "Nombre de receta duplicado",
                                                      "type": "https://example.com/errors/receta-nombre-duplicado"
                                                    }
                                                    
                                                    """
                                    )
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<RecetaDto> addReceta(@Valid @RequestBody RecetaDto dto) {

        Receta guardada = recetaService.addReceta(dto);

        return ResponseEntity.status(201).body(RecetaDto.of(guardada));

    }

    @Operation(
            summary = "Eliminar una receta",
            description = "Permite eliminar una receta existente por su ID"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Receta eliminada correctamente"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se ha encontrado la receta con ese ID",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 404",
                                            summary = "Mensaje de error cuando la receta no existe",
                                            value = """
                                                    
                                                    {
                                                      "detail": "No se ha encontrado la receta con ID: 99",
                                                      "instance": "/api/v1/recetas/99",
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReceta(@PathVariable Long id) {
        recetaService.eliminarReceta(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar una receta",
            description = "Permite actualizar una receta existente por su ID"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Receta actualizada correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = RecetaDto.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Receta actualizada",
                                            summary = "Ejemplo de receta actualizada",
                                            value = """
                                                    {
                                                      "id": 1,
                                                      "nombre": "Tortilla española",
                                                      "tiempoDePreparacion": 35,
                                                      "dificultad": "MEDIA",
                                                      "idCategoria": 1,
                                                      "ingredientes": [
                                                        {
                                                          "id": 1,
                                                          "nombre": "Patata"
                                                        },
                                                        {
                                                          "id": 2,
                                                          "nombre": "Huevo"
                                                        }
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se ha encontrado la receta con ese ID",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 404",
                                            summary = "Mensaje de error cuando la receta no existe",
                                            value = """
                                                    
                                                    {
                                                      "detail": "No se ha encontrado la receta con ID: 99",
                                                      "instance": "/api/v1/recetas/99",
                                                      "status": 404,
                                                      "title": "Recurso no encontrado",
                                                      "type": "https://example.com/errors/not-found"
                                                    }
                                                    
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error de validación o tiempo de preparación inválido",
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
                                                      "detail": "El tiempo de preparación debe ser mayor que 0",
                                                      "instance": "/api/v1/recetas/1",
                                                      "status": 400,
                                                      "title": "Tiempo de preparación inválido",
                                                      "type": "https://example.com/errors/tiempo-invalido"
                                                    }
                                                    
                                                    """
                                    )
                            )
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<RecetaDto> updateReceta(@PathVariable Long id, @Valid @RequestBody RecetaDto dto) {

        return ResponseEntity.ok(
                RecetaDto.of(recetaService.updateReceta(id,dto))
        );
    }

    @Operation(
            summary = "Añadir un ingrediente a una receta",
            description = "Permite añadir un ingrediente existente a una receta por sus IDs"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Ingrediente añadido a la receta correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = RecetaDto.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Ingrediente añadido",
                                            summary = "Ejemplo de receta con el nuevo ingrediente",
                                            value = """
                                                    {
                                                      "id": 1,
                                                      "nombre": "Tortilla de patatas",
                                                      "tiempoDePreparacion": 30,
                                                      "dificultad": "MEDIA",
                                                      "idCategoria": 1,
                                                      "ingredientes": [
                                                        {
                                                          "id": 1,
                                                          "nombre": "Patata"
                                                        },
                                                        {
                                                          "id": 2,
                                                          "nombre": "Huevo"
                                                        },
                                                        {
                                                          "id": 5,
                                                          "nombre": "Cebolla"
                                                        }
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se ha encontrado la receta o el ingrediente",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 404",
                                            summary = "Receta o ingrediente no encontrado",
                                            value = """
                                                    
                                                    {
                                                      "detail": "No se ha encontrado la receta con ID: 99",
                                                      "instance": "/api/v1/recetas/99/ingredientes",
                                                      "status": 404,
                                                      "title": "Recurso no encontrado",
                                                      "type": "https://example.com/errors/not-found"
                                                    }
                                                    
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "El ingrediente ya está añadido a la receta",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 409",
                                            summary = "El ingrediente ya estaba en la receta",
                                            value = """
                                                    
                                                    {
                                                      "detail": "El ingrediente ya está añadido a esta receta",
                                                      "instance": "/api/v1/recetas/1/ingredientes",
                                                      "status": 409,
                                                      "title": "Ingrediente ya añadido",
                                                      "type": "https://example.com/errors/ingrediente-ya-anadido"
                                                    }
                                                    
                                                    """
                                    )
                            )
                    )
            }
    )
    @PostMapping("/{recetaId}/ingredientes")
    public ResponseEntity<RecetaDto> anadirIngrediente(@PathVariable Long recetaId, @Valid @RequestBody IngredienteDto dto) {

        Receta receta = recetaService.anadirIngrediente(recetaId, dto.id());

        return ResponseEntity.status(201).body(RecetaDto.of(receta));
    }
}
