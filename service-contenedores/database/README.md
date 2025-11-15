# Database: DataBase_Contenedores

## Description
This database stores container (contenedor) information for the Contenedores microservice.

## Tables

### CONTENEDOR
Stores container information.

**Columns:**
- `id_contenedor` (INTEGER, PRIMARY KEY): Unique container identifier
- `peso` (DECIMAL(10, 2), NOT NULL): Container weight in kg
- `volumen` (DECIMAL(10, 2), NOT NULL): Container volume in cubic meters
- `estado` (VARCHAR(50), NOT NULL): Container status (DISPONIBLE, EN_TRANSITO, ENTREGADO, PENDIENTE)
- `id_cliente` (INTEGER, NOT NULL): Reference to customer (from Clientes microservice)
- `created_at` (TIMESTAMP): Record creation timestamp
- `updated_at` (TIMESTAMP): Record last update timestamp

**Indexes:**
- `idx_contenedor_cliente`: Index on id_cliente for faster lookups by customer
- `idx_contenedor_estado`: Index on estado for filtering by status

**Constraints:**
- `estado` must be one of: DISPONIBLE, EN_TRANSITO, ENTREGADO, PENDIENTE

**Relationships:**
- Each CONTENEDOR references one CLIENTE (via id_cliente)
- Note: Foreign key relationship is logical (not enforced by database) since cliente is in a different microservice

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
