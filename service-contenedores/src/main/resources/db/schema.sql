-- ================================================================
-- Database Schema: DataBase_Contenedores
-- Description: Contenedor table for the Contenedores microservice
-- ================================================================

-- Drop table if it exists (for clean re-initialization)
DROP TABLE IF EXISTS contenedor;

-- ================================================================
-- Table: CONTENEDOR
-- Description: Stores container information
-- ================================================================
CREATE TABLE IF NOT EXISTS contenedor (
    id_contenedor INTEGER PRIMARY KEY AUTOINCREMENT,
    peso DECIMAL(10, 2) NOT NULL,
    volumen DECIMAL(10, 2) NOT NULL,
    estado VARCHAR(50) NOT NULL CHECK(estado IN ('DISPONIBLE', 'EN_TRANSITO', 'ENTREGADO', 'PENDIENTE')),
    id_cliente INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Index on id_cliente for faster lookups by customer
CREATE INDEX IF NOT EXISTS idx_contenedor_cliente ON contenedor(id_cliente);

-- Index on estado for filtering by status
CREATE INDEX IF NOT EXISTS idx_contenedor_estado ON contenedor(estado);
