-- ================================================================
-- Database Schema: DataBase_Tarifas
-- Description: Tarifa table for the Tarifas microservice
-- ================================================================

-- Drop table if it exists (for clean re-initialization)
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
