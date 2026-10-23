# Gestión de Recetas

API REST desarrollada con Spring Boot para gestionar recetas, categorías e ingredientes.

## Tecnologías

- Java 25
- Spring Boot 4.1.1 (Web, Data JPA, Validation)
- H2 (base de datos en memoria)
- springdoc-openapi (Swagger UI)
- Maven

## Ejecución

Requisitos: JDK 25.

```bash
git clone <(https://github.com/perezacmar25-cpu/GestionRecetas.git)>
cd GestionRecetas
./mvnw spring-boot:run
```

La API estará disponible en `http://localhost:8080`. Los datos se almacenan en memoria y se pierden al detener la aplicación.

## Modelo de datos

- **Categoria**: `id`, `nombre` (único), `descripcion`.
- **Receta**: `id`, `nombre` (único), `tiempoPreparacionMin`, `dificultad` (`FACIL`, `MEDIA`, `DIFICIL`).
- **Ingrediente**: `id`, `nombre` (único).

Relaciones:
- Una categoría tiene muchas recetas (1:N).
- Una receta tiene muchos ingredientes y un ingrediente se usa en muchas recetas (M:N).

## Endpoints

URL base: `/api/v1`

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/categorias` | Listar categorías |
| GET | `/categorias/{id}` | Obtener una categoría |
| POST | `/categorias` | Crear una categoría |
| PUT | `/categorias/{id}` | Actualizar una categoría |
| DELETE | `/categorias/{id}` | Eliminar una categoría |
| GET | `/ingredientes` | Listar ingredientes |
| POST | `/ingredientes` | Crear un ingrediente |
| GET | `/recetas` | Listar recetas |
| GET | `/recetas/{id}` | Obtener una receta con sus ingredientes |
| POST | `/recetas` | Crear una receta (requiere una categoría existente) |
| PUT | `/recetas/{id}` | Actualizar una receta |
| DELETE | `/recetas/{id}` | Eliminar una receta |
| POST | `/recetas/{recetaId}/ingredientes` | Añadir un ingrediente a una receta |

### Ejemplo: crear una receta

`POST /api/v1/recetas`

```json
{
  "nombre": "Tarta de queso",
  "tiempoDePreparacion": 60,
  "dificultad": "MEDIA",
  "idCategoria": 1,
  "ingredientes": []
}
```

## Validaciones

- `nombre` de categoría, receta e ingrediente: obligatorio y único.
- `tiempoDePreparacion`, `dificultad` e `idCategoria`: obligatorios.
- Los ingredientes anidados en una receta también se validan.
- Validación personalizada `@TiempoCoherente`: una receta de dificultad `FACIL` no puede superar los 60 minutos.

## Gestión de errores

Los errores se devuelven en formato `ProblemDetail` y se gestionan en `GlobalExceptionHandler`.

| Código | Situación |
|---|---|
| 400 | Validación fallida o tiempo de preparación menor o igual a cero (`TiempoInvalidoException`) |
| 404 | Categoría, receta o ingrediente no encontrado |
| 409 | Nombre duplicado (categoría, receta o ingrediente) o ingrediente ya añadido a la receta (`IngredienteAlredyAddedException`) |

## Documentación de la API

Con la aplicación en marcha:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI (JSON): `http://localhost:8080/v3/api-docs`

## Base de datos H2

Consola: `http://localhost:8080/h2-console`

- JDBC URL: `jdbc:h2:mem:recetasdb`
- Usuario: `sa`
- Contraseña: vacía

## Colecciones de Postman

En la carpeta `postman/` hay una colección por recurso (categorías, ingredientes y recetas) y un archivo de entorno.

1. Importar los cuatro archivos en Postman.
2. Seleccionar el entorno de la colección.
3. Con la aplicación en marcha, ejecutar las colecciones en este orden: categorías, ingredientes y recetas.
