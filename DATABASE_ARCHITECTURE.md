# Database Architecture - SLTC Microservices

## Overview
This document describes the complete database architecture for the SLTC microservices system. Each microservice has its own independent database following the microservices pattern.

## Microservices and their Databases

### 1. Service-Clientes (DataBase_Clientes)
**Location:** `service-clientes/src/main/resources/db/schema.sql`

**Tables:**
- `CLIENTE`: Customer information
- `CONTENEDOR`: Container information associated with customers

**Documentation:** `service-clientes/database/README.md`

---

### 2. Service-Solicitudes (DataBase_Solicitudes)
**Location:** `service-solicitudes/src/main/resources/db/schema.sql`

**Tables:**
- `SOLICITUD`: Request/order information

**Documentation:** `service-solicitudes/database/README.md`

---

### 3. Service-Rutas (DataBase_Rutas)
**Location:** `service-rutas/src/main/resources/db/schema.sql`

**Tables:**
- `RUTA`: Route information
- `TRAMO_DEPOSITO`: Relationship between route segments and warehouses

**Documentation:** `service-rutas/database/README.md`

---

### 4. Service-Camiones (DataBase_Camiones)
**Location:** `service-camiones/src/main/resources/db/schema.sql`

**Tables:**
- `TARIFA`: Pricing tariffs for truck types
- `DEPOSITO`: Warehouse/depot information
- `CAMION`: Truck and driver information
- `TRAMO`: Route segment information

**Documentation:** `service-camiones/database/README.md`

---

### 5. Service-Depositos (DataBase_Depositos)
**Location:** `service-depositos/src/main/resources/db/schema.sql`

**Tables:**
- `DEPOSITO`: Warehouse/depot information

**Documentation:** `service-depositos/database/README.md`

---

### 6. Service-Contenedores (DataBase_Contenedores)
**Location:** `service-contenedores/src/main/resources/db/schema.sql`

**Tables:**
- `CONTENEDOR`: Container information

**Documentation:** `service-contenedores/database/README.md`

---

### 7. Service-Tarifas (DataBase_Tarifas)
**Location:** `service-tarifas/src/main/resources/db/schema.sql`

**Tables:**
- `TARIFA`: Pricing tariffs

**Documentation:** `service-tarifas/database/README.md`

---

### 8. Service-Tramos (DataBase_Tramos)
**Location:** `service-tramos/src/main/resources/db/schema.sql`

**Tables:**
- `TRAMO`: Route segment information

**Documentation:** `service-tramos/database/README.md`

---

## Database Relationships

### Physical Foreign Keys (Within Microservice)
These are enforced by the database:

1. **service-clientes:**
   - `CONTENEDOR.id_cliente` → `CLIENTE.id_cliente` (CASCADE DELETE/UPDATE)

2. **service-camiones:**
   - `CAMION.id_tarifa` → `TARIFA.id_tarifa` (RESTRICT DELETE, CASCADE UPDATE)
   - `TRAMO.dominio_camion` → `CAMION.dominio` (SET NULL DELETE, CASCADE UPDATE)

### Logical Foreign Keys (Cross-Microservice)
These are not enforced by database but maintained by application logic:

1. **service-solicitudes → service-clientes:**
   - `SOLICITUD.id_cliente` → `CLIENTE.id_cliente`
   - `SOLICITUD.id_contenedor` → `CONTENEDOR.id_contenedor`

2. **service-rutas → service-solicitudes:**
   - `RUTA.id_solicitud` → `SOLICITUD.id_solicitud`

3. **service-rutas → service-depositos:**
   - `TRAMO_DEPOSITO.id_deposito` → `DEPOSITO.id_deposito`

4. **service-rutas → service-tramos:**
   - `TRAMO_DEPOSITO.id_tramo` → `TRAMO.id_tramo`

5. **service-camiones → service-rutas:**
   - `TRAMO.id_ruta` → `RUTA.id_ruta`
   - `TRAMO.id_tramo_dep` → `TRAMO_DEPOSITO.id_tramo_dep`

6. **service-contenedores → service-clientes:**
   - `CONTENEDOR.id_cliente` → `CLIENTE.id_cliente`

7. **service-tramos → service-camiones:**
   - `TRAMO.dominio_camion` → `CAMION.dominio`

8. **service-tramos → service-rutas:**
   - `TRAMO.id_ruta` → `RUTA.id_ruta`
   - `TRAMO.id_tramo_dep` → `TRAMO_DEPOSITO.id_tramo_dep`

---

## Common Schema Features

### Timestamps
All tables include:
- `created_at`: Automatic timestamp on record creation
- `updated_at`: Automatic timestamp on record update

### Indexes
Strategic indexes have been created on:
- Primary keys (automatic)
- Foreign keys for faster joins
- Status/state fields for filtering
- Frequently queried fields (DNI, email, nombre, etc.)
- Date fields for time-based queries
- Geographic coordinates for location-based searches

### Constraints

#### CHECK Constraints
Used for enum-like fields:
- `CONTENEDOR.estado`: DISPONIBLE, EN_TRANSITO, ENTREGADO, PENDIENTE
- `SOLICITUD.estado`: PENDIENTE, EN_PROCESO, COMPLETADA, CANCELADA
- `TRAMO.tipo`: DIRECTO, CON_PARADAS, INTERNACIONAL
- `TRAMO.estado`: PLANIFICADO, EN_CURSO, COMPLETADO, CANCELADO

#### UNIQUE Constraints
- `CLIENTE.dni`: Each DNI must be unique
- `TRAMO_DEPOSITO(id_tramo, id_deposito)`: Prevents duplicate tramo-deposito relationships

---

## Database Technology

**Current:** SQLite
- Suitable for development and testing
- Embedded database, no separate server required
- ACID compliant
- File-based storage

**Future Migration:** PostgreSQL/MySQL
- All SQL is written to be compatible with standard SQL
- Migration path available for production environments
- Foreign key constraints are properly defined for relational databases

---

## Schema Initialization

Each microservice is configured to automatically initialize its schema on startup:

```yaml
spring:
  sql:
    init:
      mode: always
      schema-locations: classpath:db/schema.sql
```

**Note:** For production, change `mode` to `never` and use proper database migration tools (Flyway, Liquibase).

---

## Data Flow Example

1. **Customer creates a request:**
   - `CLIENTE` record exists in service-clientes
   - `CONTENEDOR` record exists in service-clientes
   - `SOLICITUD` created in service-solicitudes (references cliente and contenedor)

2. **Route is planned:**
   - `RUTA` created in service-rutas (references solicitud)
   - Multiple `TRAMO` records created in service-camiones
   - `TRAMO_DEPOSITO` relationships created in service-rutas

3. **Trucks are assigned:**
   - `CAMION` records exist in service-camiones
   - `TARIFA` applied to calculate costs
   - `TRAMO.dominio_camion` updated with truck assignment

4. **Execution:**
   - `TRAMO.estado` updated as segments complete
   - `SOLICITUD.tiempo_real` and `costo_final` updated
   - `DEPOSITO.costo_diario` applied for storage time

---

## Maintenance and Best Practices

1. **Backup Strategy:**
   - Regular backups of each microservice database
   - Transaction logs for point-in-time recovery

2. **Monitoring:**
   - Track query performance on indexed fields
   - Monitor database size growth
   - Alert on constraint violations

3. **Optimization:**
   - Regularly analyze query patterns
   - Add indexes as needed based on actual usage
   - Consider partitioning for large tables (fecha_inicio, fecha_fin)

4. **Data Integrity:**
   - Validate cross-microservice references at application level
   - Implement eventual consistency patterns
   - Use saga pattern for distributed transactions

---

## Version History

- **v1.0.0** (2025-11-15): Initial database schema implementation
  - All tables created with proper structure
  - Indexes added for performance
  - Constraints and validations implemented
  - Documentation completed
