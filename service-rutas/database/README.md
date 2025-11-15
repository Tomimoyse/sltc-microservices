# Database: DataBase_Rutas

## Description
This database stores route (ruta) and tramo-deposito relationship information for the Rutas microservice.

## Tables

### RUTA
Stores route information.

**Columns:**
- `id_ruta` (INTEGER, PRIMARY KEY): Unique route identifier
- `cant_tramos` (INTEGER, NOT NULL, DEFAULT 0): Number of segments in the route
- `cant_depositos` (INTEGER, NOT NULL, DEFAULT 0): Number of warehouses/depots in the route
- `id_solicitud` (INTEGER, NOT NULL): Reference to request (from Solicitudes microservice)
- `created_at` (TIMESTAMP): Record creation timestamp
- `updated_at` (TIMESTAMP): Record last update timestamp

**Indexes:**
- `idx_ruta_solicitud`: Index on id_solicitud for faster lookups by request

**Relationships:**
- Each RUTA references one SOLICITUD (via id_solicitud)
- Note: Foreign key relationship is logical (not enforced by database) since solicitud is in a different microservice

---

### TRAMO_DEPOSITO
Stores the relationship between tramos (segments) and depositos (warehouses).

**Columns:**
- `id_tramo_dep` (INTEGER, PRIMARY KEY): Unique tramo-deposito relationship identifier
- `id_deposito` (INTEGER, NOT NULL): Reference to deposito
- `id_tramo` (INTEGER, NOT NULL): Reference to tramo
- `created_at` (TIMESTAMP): Record creation timestamp
- `updated_at` (TIMESTAMP): Record last update timestamp

**Indexes:**
- `idx_tramo_deposito_deposito`: Index on id_deposito for faster lookups
- `idx_tramo_deposito_tramo`: Index on id_tramo for faster lookups
- `idx_tramo_deposito_unique`: Unique compound index on (id_tramo, id_deposito)

**Constraints:**
- The combination of (id_tramo, id_deposito) must be unique

**Relationships:**
- Each TRAMO_DEPOSITO references one DEPOSITO
- Each TRAMO_DEPOSITO references one TRAMO
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
