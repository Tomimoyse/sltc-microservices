-- ================================================================
-- Database Schema: DataBase_Solicitudes
-- Description: Solicitud table for the Solicitudes microservice
-- ================================================================

-- Drop table if it exists (for clean re-initialization)
DROP TABLE IF EXISTS solicitud;

-- ================================================================
-- Table: SOLICITUD
-- Description: Stores request/order information
-- ================================================================
CREATE TABLE IF NOT EXISTS solicitud (
    id_solicitud INTEGER PRIMARY KEY AUTOINCREMENT,
    costo_estimado DECIMAL(10, 2),
    tiempo_estimado INTEGER,
    costo_final DECIMAL(10, 2),
    tiempo_real INTEGER,
    estado VARCHAR(50) NOT NULL CHECK(estado IN ('PENDIENTE', 'EN_PROCESO', 'COMPLETADA', 'CANCELADA')),
    id_cliente INTEGER NOT NULL,
    id_contenedor INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Index on id_cliente for faster lookups by customer
CREATE INDEX IF NOT EXISTS idx_solicitud_cliente ON solicitud(id_cliente);

-- Index on id_contenedor for faster lookups by container
CREATE INDEX IF NOT EXISTS idx_solicitud_contenedor ON solicitud(id_contenedor);

-- Index on estado for filtering by status
CREATE INDEX IF NOT EXISTS idx_solicitud_estado ON solicitud(estado);

-- Index on created_at for time-based queries
CREATE INDEX IF NOT EXISTS idx_solicitud_created_at ON solicitud(created_at);
