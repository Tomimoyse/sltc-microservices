# Database: DataBase_Solicitudes

## Description
This database stores request/order (solicitud) information for the Solicitudes microservice.

## Tables

### SOLICITUD
Stores request/order information.

**Columns:**
- `id_solicitud` (INTEGER, PRIMARY KEY): Unique request identifier
- `costo_estimado` (DECIMAL(10, 2)): Estimated cost for the request
- `tiempo_estimado` (INTEGER): Estimated time in hours
- `costo_final` (DECIMAL(10, 2)): Final actual cost
- `tiempo_real` (INTEGER): Actual time taken in hours
- `estado` (VARCHAR(50), NOT NULL): Request status (PENDIENTE, EN_PROCESO, COMPLETADA, CANCELADA)
- `id_cliente` (INTEGER, NOT NULL): Reference to customer (from Clientes microservice)
- `id_contenedor` (INTEGER, NOT NULL): Reference to container (from Clientes microservice)
- `created_at` (TIMESTAMP): Record creation timestamp
- `updated_at` (TIMESTAMP): Record last update timestamp

**Indexes:**
- `idx_solicitud_cliente`: Index on id_cliente for faster lookups by customer
- `idx_solicitud_contenedor`: Index on id_contenedor for faster lookups by container
- `idx_solicitud_estado`: Index on estado for filtering by status
- `idx_solicitud_created_at`: Index on created_at for time-based queries

**Constraints:**
- `estado` must be one of: PENDIENTE, EN_PROCESO, COMPLETADA, CANCELADA

**Relationships:**
- Each SOLICITUD references one CLIENTE (via id_cliente)
- Each SOLICITUD references one CONTENEDOR (via id_contenedor)
- Note: Foreign key relationships are logical (not enforced by database) since cliente and contenedor are in a different microservice

---

## Database Schema Location
The SQL schema file is located at: `src/main/resources/db/schema.sql`

## Usage
The schema is automatically initialized by Spring Boot on application startup when configured with:
```yaml
spring:
  sql:
    init:
      mode: always
      schema-locations: classpath:db/schema.sql
```
