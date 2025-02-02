INSERT INTO usuario (id, nombre, username, password, email, role, deleted)
VALUES
    (nextval('usuario_seq'), 'Juan Pérez', 'juanperez', 'password123', 'juan.perez@example.com', 'USER',  true),
    (nextval('usuario_seq'), 'María ', 'marialopez', 'password456', 'maria.lopez@example.com', 'ADMIN', false),
    (nextval('usuario_seq'), 'María l', 'marialopez', 'password456', 'maria.lopez@example.com', 'ADMIN', false),
    (nextval('usuario_seq'), 'María sfgd', 'marialopez', 'password456', 'maria.lopez@example.com', 'ADMIN', false),
    (nextval('usuario_seq'), 'María aaaa', 'marialopez', 'password456', 'maria.lopez@example.com', 'ADMIN', true),
        (nextval('usuario_seq'), 'Juan Pérez', 'juanperez', 'password123', 'juan.perez@example.com', 'USER', false);



INSERT INTO alumno (id) values (1);
INSERT INTO tecnico (id) values (51);
INSERT INTO personal (id, tipo) values (101, 'PROFESOR');
INSERT INTO personal (id, tipo) values (151, 'PAS');
INSERT INTO tecnico (id) values (201);
INSERT INTO alumno (id) values (251);

INSERT INTO historico_cursos (id, alumno_id, curso_escolar, curso) VALUES (nextval('historico_cursos_seq'), 1, '2019-2020', '1º ESO');
INSERT INTO historico_cursos (id, alumno_id, curso_escolar, curso) VALUES (nextval('historico_cursos_seq'), 251, '2019-2020', '1º ESO');
INSERT INTO historico_cursos (id, alumno_id, curso_escolar, curso) VALUES (nextval('historico_cursos_seq'), 1, '2020-2021', '2º ESO');


