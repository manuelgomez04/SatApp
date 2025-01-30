insert into incidencia(id,fecha,titulo,descripcion,estado,urgencia) values(nextval('incidencia_seq'),'1943-02-10','AA','AAAAAAA','ABIERTA',true)
insert into incidencia(id,fecha,titulo,descripcion,estado,urgencia) values(nextval('incidencia_seq'),'2024-02-10','ABA','BAAAAAAAB','PENDIENTE',false)

insert into nota(id,contenido,autor,fecha,incidencia_id) values (nextval('nota_seq'), 'AAAAAAAAB','Jose','2025-01-29',currval('incidencia_seq'))
insert into nota(id,contenido,autor,fecha,incidencia_id) values (nextval('nota_seq'), 'AAAAAAAAB','Pepe','2025-01-29',currval('incidencia_seq'))
insert into nota(id,contenido,autor,fecha,incidencia_id) values (nextval('nota_seq'), 'AAAAAAAAB','Paco','2025-01-29',currval('incidencia_seq'))
insert into nota(id,contenido,autor,fecha,incidencia_id) values (nextval('nota_seq'), 'AAAAAAAAB','Fran','2025-01-29',currval('incidencia_seq'))



