# Database: DataBase_Tramos

## Description
This database stores route segment (tramo) information for the Tramos microservice.

## Tables

### TRAMO
Stores route segment information.

**Columns:**
- `id_tramo` (INTEGER, PRIMARY KEY): Unique segment identifier
- `origen` (VARCHAR(255), NOT NULL): Origin location
- `destino` (VARCHAR(255), NOT NULL): Destination location
- `tipo` (VARCHAR(50), NOT NULL): Segment type (DIRECTO, CON_PARADAS, INTERNACIONAL)
- `estado` (VARCHAR(50), NOT NULL): Segment status (PLANIFICADO, EN_CURSO, COMPLETADO, CANCELADO)
- `costo_aprox` (DECIMAL(10, 2)): Approximate cost
- `costo_real` (DECIMAL(10, 2)): Actual cost
- `fecha_inicio` (TIMESTAMP): Start date/time
- `fecha_fin` (TIMESTAMP): End date/time
- `dominio_camion` (VARCHAR(20)): Reference to truck (from Camiones microservice)
- `id_ruta` (INTEGER): Reference to route (from Rutas microservice)
- `id_tramo_dep` (INTEGER): Reference to tramo_deposito (from Rutas microservice)
- `created_at` (TIMESTAMP): Record creation timestamp
- `updated_at` (TIMESTAMP): Record last update timestamp

**Indexes:**
- `idx_tramo_estado`: Index on estado for filtering by status
- `idx_tramo_camion`: Index on dominio_camion for truck lookups
- `idx_tramo_ruta`: Index on id_ruta for route lookups
- `idx_tramo_tramo_dep`: Index on id_tramo_dep for tramo-deposito relationships
- `idx_tramo_fecha_inicio`: Index on fecha_inicio for time-based queries
- `idx_tramo_fecha_fin`: Index on fecha_fin for time-based queries

**Constraints:**
- `tipo` must be one of: DIRECTO, CON_PARADAS, INTERNACIONAL
- `estado` must be one of: PLANIFICADO, EN_CURSO, COMPLETADO, CANCELADO

**Relationships:**
- Each TRAMO may reference one CAMION (via dominio_camion)
- Each TRAMO may reference one RUTA (via id_ruta)
- Each TRAMO may reference one TRAMO_DEPOSITO (via id_tramo_dep)
- Note: Foreign key relationships are logical (not enforced by database) since these entities are in different microservices

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
