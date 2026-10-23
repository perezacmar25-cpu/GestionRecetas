package com.example.salesianos.gestionrecetas.Controller;

import com.example.salesianos.gestionrecetas.Dto.CategoriaDto;
import com.example.salesianos.gestionrecetas.Model.Categoria;
import com.example.salesianos.gestionrecetas.Service.CategoriaService;
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
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
@Tag(name = "Categoria Controller", description = "Un controlador de categorías de recetas")
public class CategoriaController {

    private final CategoriaService categoriaService;


    @Operation(
            summary = "Obtener todas las categorías",
            description = "Permite obtener todas las categorías disponibles con sus recetas asociadas"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listado de categorías",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = CategoriaDto.class
                                            )
                                    ),
                                    examples = @ExampleObject(
                                            name = "Listado de categorías",
                                            summary = "Ejemplo de listado con varias categorías",
                                            value = """
                    [
                      {
                        "id": 1,
                        "nombre": "Postres",
                        "descripcion": "Recetas de postres y dulces",
                        "recetas": [
                          {
                            "id": 10,
                            "nombre": "Tarta de queso"
                          }
                        ]
                      },
                      {
                        "id": 2,
                        "nombre": "Entrantes",
                        "descripcion": "Recetas de entrantes y aperitivos",
                        "recetas": []
                      }
                    ]
                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se han encontrado categorías",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 404",
                                            summary = "Mensaje de error informando de que no hay categorías",
                                            value = """
                                                    
                                                    {
                                                      "detail": "No se han encontrado categorías",
                                                      "instance": "/api/v1/categorias",
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
    public ResponseEntity<List<CategoriaDto>> getAll() {

        List<CategoriaDto> listadto = categoriaService.getAll()
                .stream()
                .map(CategoriaDto::of)
                .toList();

        return ResponseEntity.ok(listadto);
    }


    @Operation(
            summary = "Crear una nueva categoría",
            description = "Permite crear una nueva categoría de recetas"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Categoría creada correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CategoriaDto.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Categoría creada",
                                            summary = "Ejemplo de categoría recién creada",
                                            value = """
                                                    {
                                                      "id": 3,
                                                      "nombre": "Sopas",
                                                      "descripcion": "Recetas de sopas y cremas",
                                                      "recetas": []
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
                                                      "instance": "/api/v1/categorias",
                                                      "status": 400,
                                                      "title": "Bad Request",
                                                      "type": "about:blank",
                                                      "invalid-params": [
                                                        {
                                                          "object": "categoriaDto",
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
    public ResponseEntity<CategoriaDto> addCategoria(@Valid @RequestBody CategoriaDto categoriaDto){

        return ResponseEntity.status(201)
                .body(CategoriaDto.of(categoriaService.crearCategoria(categoriaDto)));

    }

    @Operation(
            summary = "Actualizar una categoría",
            description = "Permite actualizar una categoría existente por su ID"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categoría actualizada correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CategoriaDto.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Categoría actualizada",
                                            summary = "Ejemplo de categoría actualizada",
                                            value = """
                                                    {
                                                      "id": 1,
                                                      "nombre": "Postres actualizados",
                                                      "descripcion": "Recetas de postres y repostería",
                                                      "recetas": [
                                                        {
                                                          "id": 10,
                                                          "nombre": "Tarta de queso"
                                                        }
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se ha encontrado la categoría con ese ID",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 404",
                                            summary = "Mensaje de error cuando la categoría no existe",
                                            value = """
                                                    
                                                    {
                                                      "detail": "No se ha encontrado la categoría con ID: 99",
                                                      "instance": "/api/v1/categorias/99",
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
                                                      "instance": "/api/v1/categorias/1",
                                                      "status": 400,
                                                      "title": "Bad Request",
                                                      "type": "about:blank",
                                                      "invalid-params": [
                                                        {
                                                          "object": "categoriaDto",
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
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> updateCategoria(@PathVariable Long id ,@Valid @RequestBody CategoriaDto categoria){

        return ResponseEntity.ok(
                CategoriaDto.of(categoriaService.updateCategoria(id,categoria))
        );
    }

    @Operation(
            summary = "Eliminar una categoría",
            description = "Permite eliminar una categoría existente por su ID"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Categoría eliminada correctamente"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se ha encontrado la categoría con ese ID",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 404",
                                            summary = "Mensaje de error cuando la categoría no existe",
                                            value = """
                                                    
                                                    {
                                                      "detail": "No se ha encontrado la categoría con ID: 99",
                                                      "instance": "/api/v1/categorias/99",
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
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();

    }

    @Operation(
            summary = "Obtener una categoría por ID",
            description = "Permite obtener una categoría específica por su ID, incluyendo sus recetas"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categoría encontrada",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CategoriaDto.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Categoría encontrada",
                                            summary = "Ejemplo de categoría con sus recetas",
                                            value = """
                                                    {
                                                      "id": 1,
                                                      "nombre": "Postres",
                                                      "descripcion": "Recetas de postres y dulces",
                                                      "recetas": [
                                                        {
                                                          "id": 10,
                                                          "nombre": "Tarta de queso"
                                                        },
                                                        {
                                                          "id": 11,
                                                          "nombre": "Flan de huevo"
                                                        }
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se ha encontrado la categoría con ese ID",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 404",
                                            summary = "Mensaje de error cuando la categoría no existe",
                                            value = """
                                                    
                                                    {
                                                      "detail": "No se ha encontrado la categoría con ID: 99",
                                                      "instance": "/api/v1/categorias/99",
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
    public ResponseEntity<CategoriaDto> getCategoriaById(@PathVariable Long id) {
        Categoria categoria = categoriaService.getCategoriaById(id);
        return ResponseEntity.ok(CategoriaDto.of(categoria));
    }


}
