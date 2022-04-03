
--Sentencias SQL para poblar las tablas

-- Hotel

INSERT INTO HTA_HOTEL (id, nombre, ubicacion) values (1, 'Marriot', 'Cartagena');
INSERT INTO HTA_HOTEL (id, nombre, ubicacion) values (2, 'Decameron', 'Santa Marta');
INSERT INTO HTA_HOTEL (id, nombre, ubicacion) values (3, 'ESTELAR', 'Paipa');

-- Cliente

INSERT INTO HTA_CLIENTE (nombre, numdoc,tipodoc) values ('Juan Diego Pardo', 1000654218, 'CC');

INSERT INTO HTA_CLIENTE (nombre, numdoc,tipodoc) values('Sergio Yepes', 1000985283, 'CC');

INSERT INTO HTA_CLIENTE (nombre, numdoc,tipodoc) values ('Paula Hernandez', 1000416291, 'CE');

--Tipos de Habitacion

INSERT INTO HTA_TIPO_HABITACION(id, nombre, descripcion) values (4, 'Suite Presidencial','Para 10 personas. 8 habitaciones,7 banos, sala y comedor' );
INSERT INTO HTA_TIPO_HABITACION(id, nombre, descripcion) values (5, 'suite, familiar','para 6 a 8 personas, con comedor y cocina');
INSERT INTO HTA_TIPO_HABITACION(id, nombre, descripcion) values (6, 'doble', 'para 2 o 3 personas, con o sin jacuzzi');
INSERT INTO HTA_TIPO_HABITACION(id, nombre, descripcion) values (7, 'sencilla', 'para 1 persona');

-- Roles de usuario

INSERT INTO HTA_ROLES_DE_USUARIO(id, nombre) values (8, 'Administrador');
INSERT INTO HTA_ROLES_DE_USUARIO(id, nombre) values (9, 'Gerente');
INSERT INTO HTA_ROLES_DE_USUARIO(id, nombre) values (10, 'Recepcionista');
INSERT INTO HTA_ROLES_DE_USUARIO(id, nombre) values (11, 'Empleado');
INSERT INTO HTA_ROLES_DE_USUARIO(id, nombre) values (12, 'Cliente');

-- Habitaciones 

INSERT INTO HTA_HABITACION (id, costopornoche, tipohabitacion, aprovisionamiento) values (13, 2000, 4, 'Nevera llena, Whisky Tequila y Ginebra. Televisor 4K 1080');
INSERT INTO HTA_HABITACION (id, costopornoche, tipohabitacion, aprovisionamiento) values (14, 2000, 4, 'Nevera llena, Whisky Tequila y Ginebra. Televisor 4K 1080');
INSERT INTO HTA_HABITACION (id, costopornoche, tipohabitacion, aprovisionamiento) values (15, 1200, 5, 'Nevera llena, golosinas y televisor familiar');
INSERT INTO HTA_HABITACION (id, costopornoche, tipohabitacion, aprovisionamiento) values (16, 1300, 5, 'Nevera llena, golosinas y televisor familiar');
INSERT INTO HTA_HABITACION (id, costopornoche, tipohabitacion, aprovisionamiento) values (17, 700, 6, 'Nevera con bebidas, condones');
INSERT INTO HTA_HABITACION (id, costopornoche, tipohabitacion, aprovisionamiento) values (18, 750, 6, 'Nevera con bebidas, Condones');
INSERT INTO HTA_HABITACION (id, costopornoche, tipohabitacion, aprovisionamiento) values (19, 300, 7, 'Televisor basico');

--Usuarios

INSERT INTO HTA_USUARIO_SISTEMA(id, nombre, rol) values (20, 'Juan Yepes', 8);
INSERT INTO HTA_USUARIO_SISTEMA(id, nombre, rol) values (21, 'Sergio Pardo', 8);
INSERT INTO HTA_USUARIO_SISTEMA(id, nombre, rol) values (22, 'Juan Rulfo', 9);
INSERT INTO HTA_USUARIO_SISTEMA(id, nombre, rol) values (23, 'Pedro Paramo', 10);
INSERT INTO HTA_USUARIO_SISTEMA(id, nombre, rol) values (24, 'Nicki Minaj', 11);

-- Reservas de Habitacion

INSERT INTO HTA_RESERVA_HABITACION(id, idhabitacion, numdoccliente, tipodoccliente, diaentrada, diasalida, completada, cuenta) values (25,13,1000654218,'CC',(DATE'2016-04-12'),(DATE'2016-04-17'),'Y',0);
INSERT INTO HTA_RESERVA_HABITACION(id, idhabitacion, numdoccliente, tipodoccliente, diaentrada, diasalida, completada, cuenta) values (26,16,1000985283,'CC',(DATE'2021-11-25'),(DATE'2021-12-03'),'Y',0);
INSERT INTO HTA_RESERVA_HABITACION(id, idhabitacion, numdoccliente, tipodoccliente, diaentrada, diasalida, completada, cuenta) values (27,18,1000416291,'CE',(DATE'2020-08-05'),(DATE'2020-08-12'),'N',0);
INSERT INTO HTA_RESERVA_HABITACION(id, idhabitacion, numdoccliente, tipodoccliente, diaentrada, diasalida, completada, cuenta) values (65,15,1000985283,'CC',(DATE'2020-12-28'),(DATE'2020-01-04'),'Y',0);


-- Servicios

INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (28,10,20,50);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (29,10,20,20);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (30,10,20,45);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (31,10,22,20);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (32,10,22,15);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (33,10,22,40);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (34,0,23,0);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (35,0,23,0);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (36,0,23,0);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (37,17,2,120);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (38,14,23,50);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (39,21,5,200);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (40,10,22,70);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (41,10,23,80);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (42,10,22,50);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (43,10,0,200);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (44,10,0,150);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (45,10,0,130);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (46,10,20,30);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (47,10,19,15);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (48,10,9,20);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (49,10,20,10);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (50,10,20,10);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (51,10,20,10);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (52,10,17,0);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (53,10,17,0);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (54,10,17,0);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (55,10,18,0);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (56,10,18,0);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (57,10,18,0);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (58,7,0,12);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (59,7,0,12);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (60,7,0,12);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (61,6,18,200);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (62,6,18,3);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (63,6,18,400);
INSERT INTO HTA_SERVICIO(id, horainicio, horafin, capacidad) values (64,10,20,20);

-- Servicio Piscina

INSERT INTO HTA_SERVICIO_PISCINA(idservicio, nombre) values (28, 'Piscina Hard Rock');
INSERT INTO HTA_SERVICIO_PISCINA(idservicio, nombre) values (29, 'Lago Azul');
INSERT INTO HTA_SERVICIO_PISCINA(idservicio, nombre) values (30, 'Piscina Playa');

-- Servicio Gimnasio

INSERT INTO HTA_SERVICIO_GIMNASIO(idservicio, nombre) values (31, 'Gimnasio BodyTech');
INSERT INTO HTA_SERVICIO_GIMNASIO(idservicio, nombre) values (32, 'HardBody');
INSERT INTO HTA_SERVICIO_GIMNASIO(idservicio, nombre) values (33, 'Planet Fitness');

--Servicio Internet

INSERT INTO HTA_SERVICIO_INTERNET(idservicio, idreserva, numerodiasuso, costo) values (34, 25, 1,50);
INSERT INTO HTA_SERVICIO_INTERNET(idservicio, idreserva, numerodiasuso, costo) values (35, 26, 6,50);
INSERT INTO HTA_SERVICIO_INTERNET(idservicio, idreserva, numerodiasuso, costo) values (36, 27, 4,50);

-- Servicio Bar 

INSERT INTO HTA_SERVICIO_BAR(idservicio, nombre) values (37, 'Hard Rock');
INSERT INTO HTA_SERVICIO_BAR(idservicio, nombre) values (38, 'Cantina del desamor');
INSERT INTO HTA_SERVICIO_BAR(idservicio, nombre) values (39, 'Farra pa los locos');

-- Servicio Restaurante

INSERT INTO HTA_SERVICIO_RESTAURANTE(idservicio, nombre, estilo) values (40,'Wok','Oriental');
INSERT INTO HTA_SERVICIO_RESTAURANTE(idservicio, nombre, estilo) values (41,'Don Jediondo','Colombiana');
INSERT INTO HTA_SERVICIO_RESTAURANTE(idservicio, nombre, estilo) values (42,'Archies','Italiana');

-- Servicio Supermercado

INSERT INTO HTA_SERVICIO_SUPERMERCADO(idServicio,nombre) values (43,'Maritos Market');
INSERT INTO HTA_SERVICIO_SUPERMERCADO(idServicio,nombre) values (44,'Carulla');
INSERT INTO HTA_SERVICIO_SUPERMERCADO(idServicio,nombre) values (45,'Carrefour');

-- Servicio Tienda

INSERT INTO HTA_SERVICIO_TIENDA(idServicio, nombre, tipoDeTienda) values (46,'Ralph Polo Luaren','Ropa');
INSERT INTO HTA_SERVICIO_TIENDA(idServicio, nombre, tipoDeTienda) values (47,'Sephora','Boutique');
INSERT INTO HTA_SERVICIO_TIENDA(idServicio, nombre, tipoDeTienda) values (48,'Mi Colombia Bella','Artesanias');

-- Servicio SPA

INSERT INTO HTA_SERVICIO_SPA(idServicio,nombre) values (49,'Relajacion Maxima');
INSERT INTO HTA_SERVICIO_SPA(idServicio,nombre) values (50,'Final Feliz');
INSERT INTO HTA_SERVICIO_SPA(idServicio,nombre) values (51,'Rocas del Oriente');

-- Servicio LPE
INSERT INTO HTA_SERVICIO_LAVADO_PLANCHADO_EMBOLADO(idServicio, idReserva, tipoPrenda, numeroPrendas) values (52, 65, 'Algodon', 3);
INSERT INTO HTA_SERVICIO_LAVADO_PLANCHADO_EMBOLADO(idServicio, idReserva, tipoPrenda, numeroPrendas) values (53, 65, 'Seda', 1);
INSERT INTO HTA_SERVICIO_LAVADO_PLANCHADO_EMBOLADO(idServicio, idReserva, tipoPrenda, numeroPrendas) values (54, 26, 'Lana', 4);

-- SERVICIO PRESTAMO DE UTENSILIOS

INSERT INTO HTA_PRESTAMO_UTENSILIOS(idservicio, idreserva, recargoPorMalUso) values (55, 25, 0);
INSERT INTO HTA_PRESTAMO_UTENSILIOS(idservicio, idreserva, recargoPorMalUso) values (56, 26, 200);
INSERT INTO HTA_PRESTAMO_UTENSILIOS(idservicio, idreserva, recargoPorMalUso) values (57, 65, 0);

-- SERVICIO SALON REUNIONES

INSERT INTO HTA_SALON_REUNIONES(idservicio, idreserva, horas_uso, costobase, costo_adicional) values (58, 25, 3,300,300);
INSERT INTO HTA_SALON_REUNIONES(idservicio, idreserva, horas_uso, costobase, costo_adicional) values (59, 25, 1,200,0);
INSERT INTO HTA_SALON_REUNIONES(idservicio, idreserva, horas_uso, costobase, costo_adicional) values (60, 26, 6,400,1000);

-- SERVICIO SALON CONFERENCIAS
INSERT INTO HTA_SALON_CONFERENCIAS(idservicio, idreserva, horas_uso, costo) values (61, 27,7,500);
INSERT INTO HTA_SALON_CONFERENCIAS(idservicio, idreserva, horas_uso, costo) values (62, 25,12,700);
INSERT INTO HTA_SALON_CONFERENCIAS(idservicio, idreserva, horas_uso, costo) values (63, 26,2,1000);

-- RESERVA SERVICIO

INSERT INTO HTA_RESERVA_DE_SERVICIO(idReserva,idServicio, fechaInicio,fechaFin) values (25, 50, TIMESTAMP'2016-04-13 16:00:00',TIMESTAMP'2016-04-13 17:00:00');
INSERT INTO HTA_RESERVA_DE_SERVICIO(idReserva,idServicio, fechaInicio,fechaFin) values (65, 40, TIMESTAMP'2020-12-30 16:00:00',TIMESTAMP'2020-12-31 2:00:00');
INSERT INTO HTA_RESERVA_DE_SERVICIO(idReserva,idServicio, fechaInicio,fechaFin) values (26, 31, TIMESTAMP'2021-12-01 11:00:00',TIMESTAMP'2021-12-01 12:00:00');

-- PRODUCTO 

INSERT INTO HTA_PRODUCTO(idproducto,idservicio,costo) values (66,37,20);
INSERT INTO HTA_PRODUCTO(idproducto,idservicio,costo) values (67,40,35);
INSERT INTO HTA_PRODUCTO(idproducto,idservicio,costo) values (68,46,40);
INSERT INTO HTA_PRODUCTO(idproducto,idservicio,costo) values (69,43,5);
INSERT INTO HTA_PRODUCTO(idproducto,idservicio,costo) values (70,49,100);
INSERT INTO HTA_PRODUCTO(idproducto,idservicio,costo) values (71,55,0);


-- CONSUMO SERVICIO 

INSERT INTO HTA_CONSUMO_SERVICIO(idfactura,idreserva,idservicio,idproducto,cantidad) values (100,25,37,66,5);
INSERT INTO HTA_CONSUMO_SERVICIO(idfactura,idreserva,idservicio,idproducto,cantidad) values (101,25,40,67,2);
INSERT INTO HTA_CONSUMO_SERVICIO(idfactura,idreserva,idservicio,idproducto,cantidad) values (102,26,49,70,2);
INSERT INTO HTA_CONSUMO_SERVICIO(idfactura,idreserva,idservicio,idproducto,cantidad) values (103,65,46,68,1);
INSERT INTO HTA_CONSUMO_SERVICIO(idfactura,idreserva,idservicio,idproducto,cantidad) values (104,26,55,71,2);
INSERT INTO HTA_CONSUMO_SERVICIO(idfactura,idreserva,idservicio,idproducto,cantidad) values (105,65,43,69,10);

"""
-- PLAN DE CONSUMO

INSERT INTO HTA_PLAN_DE_CONSUMO(id,tipo) values (72,);
INSERT INTO HTA_PLAN_DE_CONSUMO(id,tipo) values (73,);
INSERT INTO HTA_PLAN_DE_CONSUMO(id,tipo) values (74,);
INSERT INTO HTA_PLAN_DE_CONSUMO(id,tipo) values (75,);
INSERT INTO HTA_PLAN_DE_CONSUMO(id,tipo) values (76,);
INSERT INTO HTA_PLAN_DE_CONSUMO(id,tipo) values (77,);
INSERT INTO HTA_PLAN_DE_CONSUMO(id,tipo) values (78,);
INSERT INTO HTA_PLAN_DE_CONSUMO(id,tipo) values (79,);
INSERT INTO HTA_PLAN_DE_CONSUMO(id,tipo) values (80,);
INSERT INTO HTA_PLAN_DE_CONSUMO(id,tipo) values (81,);
INSERT INTO HTA_PLAN_DE_CONSUMO(id,tipo) values (82,);
INSERT INTO HTA_PLAN_DE_CONSUMO(id,tipo) values (83,);


-- PLAN LARGA ESTADIA

INSERT INTO HTA_LARGA_ESTADIA(idplandeconsumo,descuento,idhotel,tiempoestadia) values ();
INSERT INTO HTA_LARGA_ESTADIA(idplandeconsumo,descuento,idhotel,tiempoestadia) values ();
INSERT INTO HTA_LARGA_ESTADIA(idplandeconsumo,descuento,idhotel,tiempoestadia) values ();

-- PLAN TIEMPO COMPARTIDO

INSERT INTO HTA_TIEMPO_COMPARTIDO(idplandeconsumo,idservicioasociado) values ();
INSERT INTO HTA_TIEMPO_COMPARTIDO(idplandeconsumo,idservicioasociado) values ();
INSERT INTO HTA_TIEMPO_COMPARTIDO(idplandeconsumo,idservicioasociado) values ();

-- PLAN TODO INCLUIDO

INSERT INTO HTA_TODO_INCLUIDO(idplandeconsumo,idservicioasociado,idreserva,costofijototal) values ();
INSERT INTO HTA_TODO_INCLUIDO(idplandeconsumo,idservicioasociado,idreserva,costofijototal) values ();
INSERT INTO HTA_TODO_INCLUIDO(idplandeconsumo,idservicioasociado,idreserva,costofijototal) values ();

-- PRODUCTO TODO INCLUIDO

INSERT INTO HTA_PRODUCTOS_TODO_INCLUIDO(idplandeconsumo,idproductoasociado,descuento) values ();
INSERT INTO HTA_PRODUCTOS_TODO_INCLUIDO(idplandeconsumo,idproductoasociado,descuento) values ();
INSERT INTO HTA_PRODUCTOS_TODO_INCLUIDO(idplandeconsumo,idproductoasociado,descuento) values ();

-- PROMOCION PARTICULAR

INSERT INTO HTA_PROMOCION_PARTICULAR(idplandeconsumo,descripcion) values ();
INSERT INTO HTA_PROMOCION_PARTICULAR(idplandeconsumo,descripcion) values ();
INSERT INTO HTA_PROMOCION_PARTICULAR(idplandeconsumo,descripcion) values ();
"""




