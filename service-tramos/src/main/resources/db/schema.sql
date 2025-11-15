-- ================================================================
-- Database Schema: DataBase_Tramos
-- Description: Tramo table for the Tramos microservice
-- ================================================================

-- Drop table if it exists (for clean re-initialization)
DROP TABLE IF EXISTS tramo;

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
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
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
