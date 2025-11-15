# Database: DataBase_Tarifas

## Description
This database stores pricing tariffs for different truck types for the Tarifas microservice.

## Tables

### TARIFA
Stores pricing tariffs for different truck types.

**Columns:**
- `id_tarifa` (INTEGER, PRIMARY KEY): Unique tariff identifier
- `tipo_camion` (VARCHAR(50), NOT NULL): Truck type
- `costo_km` (DECIMAL(10, 2), NOT NULL): Cost per kilometer
- `valor_combustible` (DECIMAL(10, 2), NOT NULL): Fuel value/cost
- `created_at` (TIMESTAMP): Record creation timestamp
- `updated_at` (TIMESTAMP): Record last update timestamp

**Indexes:**
- `idx_tarifa_tipo`: Index on tipo_camion for faster lookups

**Use Cases:**
- Tariff calculation for different truck types
- Cost estimation based on distance and truck type
- Fuel cost management

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
