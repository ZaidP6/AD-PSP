-- Tabla Estacion
CREATE TABLE Estacion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL
);

-- Tabla Bicicleta
CREATE TABLE Bicicleta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    estado BOOLEAN NOT NULL,
    estacion_id BIGINT,
    FOREIGN KEY (estacion_id) REFERENCES Estacion(id) ON DELETE SET NULL
);

-- Tabla Uso
CREATE TABLE Uso (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_inicio TIMESTAMP NOT NULL,
    fecha_fin TIMESTAMP,
    bicicleta_id BIGINT,
    FOREIGN KEY (bicicleta_id) REFERENCES Bicicleta(id) ON DELETE CASCADE
);

-- Insertar datos en la tabla Estacion
INSERT INTO Estacion (nombre, direccion) VALUES ('Estación Centro', 'Calle Mayor, 1'),
INSERT INTO Estacion (nombre, direccion) VALUES ('Estación Norte', 'Avenida del Norte, 12'),
INSERT INTO Estacion (nombre, direccion) VALUES ('Estación Sur', 'Carretera Nacional, 45');

-- Insertar datos en la tabla Bicicleta
INSERT INTO Bicicleta (marca, modelo, estado, estacion_id) VALUES ('Orbea', 'MX 50', TRUE, 1),
INSERT INTO Bicicleta (marca, modelo, estado, estacion_id) VALUES ('Specialized', 'Rockhopper', TRUE, 1),
INSERT INTO Bicicleta (marca, modelo, estado, estacion_id) VALUES ('Trek', 'Marlin 7', FALSE, 2),
INSERT INTO Bicicleta (marca, modelo, estado, estacion_id) VALUES ('Giant', 'Talon 4', TRUE, 3),
INSERT INTO Bicicleta (marca, modelo, estado, estacion_id) VALUES( 'Cannondale', 'Trail 8', TRUE, 3);

-- Insertar datos en la tabla Uso
INSERT INTO Uso (fecha_inicio, fecha_fin, bicicleta_id) VALUES ('2024-01-01 10:00:00', '2024-01-01 12:00:00', 1),
INSERT INTO Uso (fecha_inicio, fecha_fin, bicicleta_id) VALUES ('2024-01-02 14:00:00', '2024-01-02 16:30:00', 1),
INSERT INTO Uso (fecha_inicio, fecha_fin, bicicleta_id) VALUES ('2024-01-03 09:30:00', NULL, 2),
INSERT INTO Uso (fecha_inicio, fecha_fin, bicicleta_id) VALUES ('2024-01-04 15:00:00', '2024-01-04 16:00:00', 3),
INSERT INTO Uso (fecha_inicio, fecha_fin, bicicleta_id) VALUES ('2024-01-05 10:00:00', NULL, 4);
