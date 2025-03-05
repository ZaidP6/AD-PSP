-- ADMIN
INSERT INTO usuario (uuid, username, email, password, role, enabled, activation_token, secret_key, created_at) VALUES ('fbb7168c-3fff-4013-9479-174a5da630ac', 'adminNuevo', 'adminNuevo@email.com', '{bcrypt}$2a$10$GN2sqMDOIN8K.Blxgnk1sO4lYD2UQyXXoA8dODL9AhnbmFEqNGGm6', 'ADMIN', true, NULL, '2QFVO7MFGVYMGUD6', NOW());
-- USER
INSERT INTO usuario (uuid, username, email, password, role, enabled, activation_token, secret_key, created_at) VALUES ('cdd1f9e1-0acc-4b7c-9e72-dd8a833397c4', 'user', 'user@example.com','{bcrypt}$2a$12$dh0sWUWSkeNzlIUNA0M.je3l//9zDtBRBoSk.ljRvEXaqTJOmFEEG','USER', true, NULL, 'KKGBFWOWRYGPYM7D', '2024-02-23');


INSERT INTO lugar (uuid, tipo, nombre, pais, provincia_estado, ciudad, coordenadas, fecha_construccion) VALUES ('e30b4a7e-1234-4c1d-8c7f-79e2a10e1a67', 'Catedral', 'La Giralda', 'España', 'Sevilla', 'Sevilla', '37.3879,-5.9926', '1198-01-01');
INSERT INTO lugar (uuid, tipo, nombre, pais, provincia_estado, ciudad, coordenadas, fecha_construccion) VALUES ('a12b3c4d-5678-90ef-abc1-23456789def0', 'Torre', 'Torre del Oro', 'España', 'Sevilla', 'Sevilla', '37.3826,-5.9964', '1221-01-01');
INSERT INTO lugar (uuid, tipo, nombre, pais, provincia_estado, ciudad, coordenadas, fecha_construccion) VALUES ('b21c3d4e-6789-01fa-bcd2-34567890f123', 'Estadio', 'Ramón Sánchez-Pizjuán', 'España', 'Sevilla', 'Sevilla', '37.3840,-5.9700', '1958-09-07');

INSERT INTO estado_lugar (uuid, estado, fecha_inicio, fecha_fin, lugar_uuid) VALUES ('1e5d9c0f-9a7b-4d6b-924c-3c7e1e28a5f4', 'Accesible_Completamente', '2025-01-01', NULL, 'e30b4a7e-1234-4c1d-8c7f-79e2a10e1a67');
INSERT INTO estado_lugar (uuid, estado, fecha_inicio, fecha_fin, lugar_uuid) VALUES ('2a3b4c5d-6789-0123-4567-890abcdef123', 'Accesible_Completamente', '2025-01-01', NULL, 'a12b3c4d-5678-90ef-abc1-23456789def0');
INSERT INTO estado_lugar (uuid, estado, fecha_inicio, fecha_fin, lugar_uuid) VALUES ('3b4c5d6e-7890-1234-5678-90abcdef1234', 'Visible', '2025-01-01', NULL, 'b21c3d4e-6789-01fa-bcd2-34567890f123');
