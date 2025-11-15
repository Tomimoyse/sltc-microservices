# Database: DataBase_Depositos

## Description
This database maintains the registry of warehouses/depots and their storage costs for the Depositos microservice.

## Tables

### DEPOSITO
Stores warehouse/depot information.

**Columns:**
- `id_deposito` (INTEGER, PRIMARY KEY): Unique depot identifier
- `nombre` (VARCHAR(100), NOT NULL): Depot name
- `direccion` (VARCHAR(255), NOT NULL): Physical address
- `latitud` (DECIMAL(10, 8)): Latitude coordinate
- `longitud` (DECIMAL(11, 8)): Longitude coordinate
- `costo_diario` (DECIMAL(10, 2), NOT NULL): Daily storage cost
- `created_at` (TIMESTAMP): Record creation timestamp
- `updated_at` (TIMESTAMP): Record last update timestamp

**Indexes:**
- `idx_deposito_nombre`: Index on nombre for faster lookups
- `idx_deposito_coords`: Compound index on (latitud, longitud) for location-based queries

**Use Cases:**
- Location-based searches for nearest depot
- Cost calculation for storage at specific depots
- Depot availability and capacity management

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

## Note
This microservice maintains the same DEPOSITO table structure as found in the Camiones microservice, allowing for independent depot management and queries.
