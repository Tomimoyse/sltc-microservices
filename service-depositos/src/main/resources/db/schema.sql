-- ================================================================
-- Database Schema: DataBase_Depositos
-- Description: Deposito table for the Depositos microservice
-- ================================================================

-- Drop table if it exists (for clean re-initialization)
DROP TABLE IF EXISTS deposito;

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
