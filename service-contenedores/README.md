# Microservicio Contenedores

Microservicio para la gestión de contenedores en el sistema SLTC.

## Descripción

Este microservicio proporciona funcionalidades para:
- Registrar y consultar contenedores
- Consultar estado de contenedores
- Filtrar contenedores por cliente
- Actualizar información de contenedores

## Tecnologías

- **Java 17**
- **Spring Boot 3.1.4**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **SpringDoc OpenAPI (Swagger)**
- **Spring Validation**
- **Spring Actuator**

## Endpoints

### Contenedores

- `POST /api/contenedores` - Registrar un nuevo contenedor
- `GET /api/contenedores` - Listar todos los contenedores
- `GET /api/contenedores/{id}` - Obtener un contenedor por ID
- `PUT /api/contenedores/{id}` - Actualizar un contenedor
- `DELETE /api/contenedores/{id}` - Eliminar un contenedor
- `GET /api/contenedores/cliente/{idCliente}` - Filtrar contenedores por cliente
- `GET /api/contenedores/estado/{estado}` - Consultar contenedores por estado

## Configuración

El microservicio se ejecuta en el puerto **8082** por defecto.

### Base de Datos

Requiere una base de datos PostgreSQL con las siguientes configuraciones:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/contenedores_db
    username: postgres
    password: postgres
```

## Ejecución

### Requisitos previos

1. Java 17 o superior instalado
2. PostgreSQL instalado y ejecutándose
3. Base de datos `contenedores_db` creada

### Ejecutar el microservicio

```bash
./mvnw spring-boot:run
```

O después de compilar:

```bash
java -jar target/service-contenedores-0.0.1-SNAPSHOT.jar
```

## Documentación API

Una vez que el microservicio esté en ejecución, la documentación Swagger estará disponible en:

- **Swagger UI**: http://localhost:8082/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8082/api-docs

## Health Check

El endpoint de salud está disponible en:

```
GET http://localhost:8082/actuator/health
```

## Modelo de Datos

### Contenedor

```json
{
  "id": 1,
  "numeroContenedor": "CONT-001",
  "tipo": "20ft",
  "peso": 5000.0,
  "volumen": 33.0,
  "estado": "DISPONIBLE",
  "idCliente": 1,
  "createdAt": "2025-11-15T10:00:00",
  "updatedAt": "2025-11-15T10:00:00"
}
```

## Validaciones

- `numeroContenedor`: obligatorio, único
- `tipo`: obligatorio
- `peso`: obligatorio, debe ser positivo
- `volumen`: obligatorio, debe ser positivo
- `estado`: obligatorio
- `idCliente`: obligatorio
