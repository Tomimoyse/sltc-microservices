-- ================================================================
-- Database Schema: DataBase_Clientes
-- Description: Cliente and Contenedor tables for the Clientes microservice
-- ================================================================

-- Drop tables if they exist (for clean re-initialization)
DROP TABLE IF EXISTS contenedor;
DROP TABLE IF EXISTS cliente;

-- ================================================================
-- Table: CLIENTE
-- Description: Stores customer information
-- ================================================================
CREATE TABLE IF NOT EXISTS cliente (
    id_cliente INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    dni VARCHAR(20) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Index on DNI for faster lookups
CREATE INDEX IF NOT EXISTS idx_cliente_dni ON cliente(dni);

-- Index on email for faster lookups
CREATE INDEX IF NOT EXISTS idx_cliente_email ON cliente(email);

-- ================================================================
-- Table: CONTENEDOR
-- Description: Stores container information associated with clients
-- ================================================================
CREATE TABLE IF NOT EXISTS contenedor (
    id_contenedor INTEGER PRIMARY KEY AUTOINCREMENT,
    peso DECIMAL(10, 2) NOT NULL,
    volumen DECIMAL(10, 2) NOT NULL,
    estado VARCHAR(50) NOT NULL CHECK(estado IN ('DISPONIBLE', 'EN_TRANSITO', 'ENTREGADO', 'PENDIENTE')),
    id_cliente INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Index on id_cliente for faster joins and lookups
CREATE INDEX IF NOT EXISTS idx_contenedor_cliente ON contenedor(id_cliente);

-- Index on estado for filtering by status
CREATE INDEX IF NOT EXISTS idx_contenedor_estado ON contenedor(estado);
