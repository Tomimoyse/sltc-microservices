-- ================================================================
-- Database Schema: DataBase_Camiones
-- Description: Camion, Tarifa, Deposito, and Tramo tables for the Camiones microservice
-- ================================================================

-- Drop tables if they exist (for clean re-initialization)
DROP TABLE IF EXISTS tramo;
DROP TABLE IF EXISTS camion;
DROP TABLE IF EXISTS deposito;
DROP TABLE IF EXISTS tarifa;

-- ================================================================
-- Table: TARIFA
-- Description: Stores pricing tariffs for different truck types
-- ================================================================
CREATE TABLE IF NOT EXISTS tarifa (
    id_tarifa INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo_camion VARCHAR(50) NOT NULL,
    costo_km DECIMAL(10, 2) NOT NULL,
    valor_combustible DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Index on tipo_camion for faster lookups
CREATE INDEX IF NOT EXISTS idx_tarifa_tipo ON tarifa(tipo_camion);

-- ================================================================
-- Table: DEPOSITO
-- Description: Stores warehouse/depot information
-- ================================================================
CREATE TABLE IF NOT EXISTS deposito (
    id_deposito INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    latitud DECIMAL(10, 8),
    longitud DECIMAL(11, 8),
    costo_diario DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Index on nombre for faster lookups
CREATE INDEX IF NOT EXISTS idx_deposito_nombre ON deposito(nombre);

-- Index on geographic coordinates for location-based queries
CREATE INDEX IF NOT EXISTS idx_deposito_coords ON deposito(latitud, longitud);

-- ================================================================
-- Table: CAMION
-- Description: Stores truck and driver information
-- ================================================================
CREATE TABLE IF NOT EXISTS camion (
    dominio VARCHAR(20) PRIMARY KEY,
    nom_chofer VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    cap_peso DECIMAL(10, 2) NOT NULL,
    cap_volumen DECIMAL(10, 2) NOT NULL,
    consumo_km DECIMAL(10, 2) NOT NULL,
    costo_base_km DECIMAL(10, 2) NOT NULL,
    disponible BOOLEAN NOT NULL DEFAULT 1,
    id_tarifa INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_tarifa) REFERENCES tarifa(id_tarifa) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Index on disponible for filtering available trucks
CREATE INDEX IF NOT EXISTS idx_camion_disponible ON camion(disponible);

-- Index on id_tarifa for joins
CREATE INDEX IF NOT EXISTS idx_camion_tarifa ON camion(id_tarifa);

-- Index on nom_chofer for driver lookups
CREATE INDEX IF NOT EXISTS idx_camion_chofer ON camion(nom_chofer);

-- ================================================================
-- Table: TRAMO
-- Description: Stores route segment information
-- ================================================================
CREATE TABLE IF NOT EXISTS tramo (
    id_tramo INTEGER PRIMARY KEY AUTOINCREMENT,
    origen VARCHAR(255) NOT NULL,
    destino VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL CHECK(tipo IN ('DIRECTO', 'CON_PARADAS', 'INTERNACIONAL')),
    estado VARCHAR(50) NOT NULL CHECK(estado IN ('PLANIFICADO', 'EN_CURSO', 'COMPLETADO', 'CANCELADO')),
    costo_aprox DECIMAL(10, 2),
    costo_real DECIMAL(10, 2),
    fecha_inicio TIMESTAMP,
    fecha_fin TIMESTAMP,
    dominio_camion VARCHAR(20),
    id_ruta INTEGER,
    id_tramo_dep INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (dominio_camion) REFERENCES camion(dominio) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Index on estado for filtering by status
CREATE INDEX IF NOT EXISTS idx_tramo_estado ON tramo(estado);

-- Index on dominio_camion for truck lookups
CREATE INDEX IF NOT EXISTS idx_tramo_camion ON tramo(dominio_camion);

-- Index on id_ruta for route lookups
CREATE INDEX IF NOT EXISTS idx_tramo_ruta ON tramo(id_ruta);

-- Index on id_tramo_dep for tramo-deposito relationships
CREATE INDEX IF NOT EXISTS idx_tramo_tramo_dep ON tramo(id_tramo_dep);

-- Index on fecha_inicio for time-based queries
CREATE INDEX IF NOT EXISTS idx_tramo_fecha_inicio ON tramo(fecha_inicio);

-- Index on fecha_fin for time-based queries
CREATE INDEX IF NOT EXISTS idx_tramo_fecha_fin ON tramo(fecha_fin);
