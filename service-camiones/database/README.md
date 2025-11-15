# Database: DataBase_Camiones

## Description
This database manages trucks, pricing tariffs, warehouses, and route segments for the Camiones microservice.

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

---

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

---

### CAMION
Stores truck and driver information.

**Columns:**
- `dominio` (VARCHAR(20), PRIMARY KEY): License plate / truck identifier
- `nom_chofer` (VARCHAR(100), NOT NULL): Driver name
- `telefono` (VARCHAR(20)): Phone number
- `cap_peso` (DECIMAL(10, 2), NOT NULL): Weight capacity in kg
- `cap_volumen` (DECIMAL(10, 2), NOT NULL): Volume capacity in cubic meters
- `consumo_km` (DECIMAL(10, 2), NOT NULL): Fuel consumption per km
- `costo_base_km` (DECIMAL(10, 2), NOT NULL): Base cost per km
- `disponible` (BOOLEAN, NOT NULL, DEFAULT 1): Availability status
- `id_tarifa` (INTEGER, NOT NULL, FOREIGN KEY): Reference to tarifa table
- `created_at` (TIMESTAMP): Record creation timestamp
- `updated_at` (TIMESTAMP): Record last update timestamp

**Indexes:**
- `idx_camion_disponible`: Index on disponible for filtering available trucks
- `idx_camion_tarifa`: Index on id_tarifa for joins
- `idx_camion_chofer`: Index on nom_chofer for driver lookups

**Constraints:**
- `id_tarifa` references `tarifa(id_tarifa)` with RESTRICT on DELETE and CASCADE on UPDATE

**Relationships:**
- Each CAMION must reference one TARIFA
- RESTRICT DELETE: Cannot delete a tarifa if trucks are using it

---

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
- `dominio_camion` (VARCHAR(20), FOREIGN KEY): Reference to camion table
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
- `dominio_camion` references `camion(dominio)` with SET NULL on DELETE and CASCADE on UPDATE

**Relationships:**
- Each TRAMO may reference one CAMION (via dominio_camion)
- SET NULL DELETE: When a camion is deleted, the reference is set to NULL (tramo remains)
- Logical references to RUTA and TRAMO_DEPOSITO (in Rutas microservice)

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
