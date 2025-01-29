INSERT INTO usuario (id, nombre, username, password, email, role)
VALUES
    (nextval('usuario_seq'), 'Juan Pérez', 'juanperez', 'password123', 'juan.perez@example.com', 'USER'),
    (nextval('usuario_seq'), 'María López', 'marialopez', 'password456', 'maria.lopez@example.com', 'ADMIN');

INSERT INTO alumno (id) values (1);
