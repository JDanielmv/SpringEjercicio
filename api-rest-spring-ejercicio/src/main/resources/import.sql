INSERT INTO clientes(nombre,apellido,empresa,puesto,cp,provincia,telefono,fechaNacimiento) VALUES ("Jose","Garcia","INDRA","junior","48902","Bizkaia",65475324,"1996-08-17");
INSERT INTO clientes(nombre,apellido,empresa,puesto,cp,provincia,telefono,fechaNacimiento) VALUES ("Juan","Perez","INDRA","junior","48902","Bizkaia",654345324,"1996-08-17");
INSERT INTO clientes(nombre,apellido,empresa,puesto,cp,provincia,telefono,fechaNacimiento) VALUES ("Mara","Otero","INDRA","junior","48902","Bizkaia",654647532,"1996-08-17");
INSERT INTO clientes(nombre,apellido,empresa,puesto,cp,provincia,telefono,fechaNacimiento) VALUES ("David","Guiterrez","INDRA","junior","48902","Bizkaia",654245324,"1996-08-17");
INSERT INTO clientes(nombre,apellido,empresa,puesto,cp,provincia,telefono,fechaNacimiento) VALUES ("Hector","Bilbao","INDRA","junior","48902","Bizkaia",654689324,"1996-08-17");

INSERT INTO articulos(nombre,descripcion,precio,unidad,stockseg) VALUES ("pasta de dientes1", "te deja aliento fresco",1,100,200);
INSERT INTO articulos(nombre,descripcion,precio,unidad,stockseg) VALUES ("pasta de dientes2", "te deja aliento fresco",1,50,260);
INSERT INTO articulos(nombre,descripcion,precio,unidad,stockseg) VALUES ("pasta de dientes3", "te deja aliento fresco",1,150,240);
INSERT INTO articulos(nombre,descripcion,precio,unidad,stockseg) VALUES ("pasta de dientes4", "te deja aliento fresco",1,140,100);


INSERT INTO compras(cod_cliente,cod_articulo,fecha,unidades) VALUES (1,1,"2022-02-11",3);
INSERT INTO compras(cod_cliente,cod_articulo,fecha,unidades) VALUES (2,2,"2022-04-11",4);
INSERT INTO compras(cod_cliente,cod_articulo,fecha,unidades) VALUES (3,3,"2022-05-11",6);
INSERT INTO compras(cod_cliente,cod_articulo,fecha,unidades) VALUES (4,4,"2022-06-11",5);