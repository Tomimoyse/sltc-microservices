# Database: DataBase_Clientes

## Description
This database stores customer (cliente) and container (contenedor) information for the Clientes microservice.

## Tables

### CLIENTE
Stores customer information.

**Columns:**
- `id_cliente` (INTEGER, PRIMARY KEY): Unique customer identifier
- `nombre` (VARCHAR(100), NOT NULL): Customer first name
- `apellido` (VARCHAR(100), NOT NULL): Customer last name
- `dni` (VARCHAR(20), NOT NULL, UNIQUE): National identification number
- `telefono` (VARCHAR(20)): Phone number
- `email` (VARCHAR(100)): Email address
- `direccion` (VARCHAR(255)): Physical address
- `created_at` (TIMESTAMP): Record creation timestamp
- `updated_at` (TIMESTAMP): Record last update timestamp

**Indexes:**
- `idx_cliente_dni`: Index on DNI for faster lookups
- `idx_cliente_email`: Index on email for faster lookups

**Constraints:**
- DNI must be unique across all customers

---

### CONTENEDOR
Stores container information associated with clients.

**Columns:**
- `id_contenedor` (INTEGER, PRIMARY KEY): Unique container identifier
- `peso` (DECIMAL(10, 2), NOT NULL): Container weight in kg
- `volumen` (DECIMAL(10, 2), NOT NULL): Container volume in cubic meters
- `estado` (VARCHAR(50), NOT NULL): Container status (DISPONIBLE, EN_TRANSITO, ENTREGADO, PENDIENTE)
- `id_cliente` (INTEGER, NOT NULL, FOREIGN KEY): Reference to cliente table
- `created_at` (TIMESTAMP): Record creation timestamp
- `updated_at` (TIMESTAMP): Record last update timestamp

**Indexes:**
- `idx_contenedor_cliente`: Index on id_cliente for faster joins and lookups
- `idx_contenedor_estado`: Index on estado for filtering by status

**Constraints:**
- `estado` must be one of: DISPONIBLE, EN_TRANSITO, ENTREGADO, PENDIENTE
- `id_cliente` references `cliente(id_cliente)` with CASCADE on DELETE and UPDATE

**Relationships:**
- Each CONTENEDOR belongs to one CLIENTE
- CASCADE DELETE: When a cliente is deleted, all associated contenedores are also deleted

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
