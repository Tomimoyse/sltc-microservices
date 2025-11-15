CREATE TABLE IF NOT EXISTS solicitudes (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  costo_estimatado REAL,
  costo_final REAL,
  tiempo_estimado INTEGER,
  tiempo_real INTEGER,
  estado TEXT,
  id_cliente INTEGER,
  id_contenedor INTEGER
);