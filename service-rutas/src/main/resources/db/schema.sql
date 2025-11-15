-- ================================================================
-- Database Schema: DataBase_Rutas
-- Description: Ruta and Tramo_Deposito tables for the Rutas microservice
-- ================================================================

-- Drop tables if they exist (for clean re-initialization)
DROP TABLE IF EXISTS tramo_deposito;
DROP TABLE IF EXISTS ruta;

-- ================================================================
-- Table: RUTA
-- Description: Stores route information
-- ================================================================
CREATE TABLE IF NOT EXISTS ruta (
    id_ruta INTEGER PRIMARY KEY AUTOINCREMENT,
    cant_tramos INTEGER NOT NULL DEFAULT 0,
    cant_depositos INTEGER NOT NULL DEFAULT 0,
    id_solicitud INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Index on id_solicitud for faster lookups by request
CREATE INDEX IF NOT EXISTS idx_ruta_solicitud ON ruta(id_solicitud);

-- ================================================================
-- Table: TRAMO_DEPOSITO
-- Description: Stores the relationship between tramos and depositos
-- ================================================================
CREATE TABLE IF NOT EXISTS tramo_deposito (
    id_tramo_dep INTEGER PRIMARY KEY AUTOINCREMENT,
    id_deposito INTEGER NOT NULL,
    id_tramo INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Index on id_deposito for faster lookups
CREATE INDEX IF NOT EXISTS idx_tramo_deposito_deposito ON tramo_deposito(id_deposito);

-- Index on id_tramo for faster lookups
CREATE INDEX IF NOT EXISTS idx_tramo_deposito_tramo ON tramo_deposito(id_tramo);

-- Compound index for unique tramo-deposito combinations
CREATE UNIQUE INDEX IF NOT EXISTS idx_tramo_deposito_unique ON tramo_deposito(id_tramo, id_deposito);
