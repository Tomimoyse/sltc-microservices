# API Gateway

API Gateway centralizado para el sistema de microservicios SLTC utilizando Spring Cloud Gateway.

## Descripción

El API Gateway proporciona:
- Enrutamiento centralizado a todos los microservicios
- Configuración CORS
- Rate limiting para protección contra abuso
- Endpoints de monitoreo y health checks

## Puerto

El API Gateway se ejecuta en el puerto **8080** por defecto.

## Rutas Configuradas

Todas las rutas están configuradas con el prefijo `/api`:

| Ruta | Microservicio | Puerto Destino |
|------|--------------|----------------|
| `/api/contenedores/**` | service-contenedores | 8082 |
| `/api/depositos/**` | service-depositos | 8083 |
| `/api/tarifas/**` | service-tarifas | 8084 |
| `/api/camiones/**` | service-camiones | 8085 |
| `/api/tramos/**` | service-tramos | 8086 |
| `/api/rutas/**` | service-rutas | 8087 |
| `/api/solicitudes/**` | service-solicitudes | 8081 |

## Configuración CORS

El Gateway está configurado para aceptar:
- Todos los orígenes (`*`)
- Métodos: GET, POST, PUT, DELETE, OPTIONS
- Todos los headers
- Edad máxima: 3600 segundos

## Rate Limiting

Cada ruta tiene configurado rate limiting con:
- Tasa de reabastecimiento: 10 solicitudes/segundo
- Capacidad de ráfaga: 20 solicitudes

## Ejecución

### Iniciar el Gateway

```bash
./mvnw spring-boot:run -pl api-gateway
```

O después de compilar:

```bash
java -jar api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar
```

### Health Check

```
GET http://localhost:8080/actuator/health
```

### Gateway Routes Info

```
GET http://localhost:8080/actuator/gateway/routes
```

## Ejemplo de Uso

Una vez que el Gateway y los microservicios estén ejecutándose, puedes acceder a cualquier microservicio a través del Gateway:

```bash
# Listar contenedores
curl http://localhost:8080/api/contenedores

# Obtener depósito por ID
curl http://localhost:8080/api/depositos/1

# Crear nueva tarifa
curl -X POST http://localhost:8080/api/tarifas \
  -H "Content-Type: application/json" \
  -d '{"tipo": "ESTANDAR", "tarifaBase": 100.0, "tarifaPorKm": 5.0, "valorCombustible": 1.5}'
```

## Tecnologías

- Spring Boot 3.1.4
- Spring Cloud Gateway 2022.0.4
- Spring Boot Actuator
- SpringDoc OpenAPI (WebFlux)

## Notas

- Para producción, se recomienda configurar autenticación JWT
- El rate limiting actual requiere Redis para funcionar en producción
- Las rutas están configuradas para entornos de desarrollo (localhost)
