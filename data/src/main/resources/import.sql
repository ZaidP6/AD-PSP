INSERT INTO categorias(id, nombre) VALUES(1, 'Alimentación');
INSERT INTO categorias(id, nombre) VALUES(2, 'Electrónica');
INSERT INTO categorias(id, nombre) VALUES(3, 'Bricomanía');
ALTER SEQUENCE categorias_seq RESTART WITH 53;

INSERT INTO productos(id, nombre_producto, descripcion, precio, categoria_id) VALUES (1, 'Alcaparras', 'Illo unas alcaparras qué más quieres churra????', 3.12, 1);
INSERT INTO productos(id, nombre_producto, descripcion, precio, categoria_id) VALUES (2, 'Ordenador', 'Illo un ordenador to wapo pa jugar al counter estraik ese tío', 800, 2);
INSERT INTO productos(id, nombre_producto, descripcion, precio, categoria_id) VALUES (3, 'Mesa', 'Illo una mesa to wapa del carajo colega', 800, 3);
ALTER SEQUENCE productos_seq RESTART WITH 53;