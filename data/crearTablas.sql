-- Creación de tablas del proyecto
create sequence hotelandesa12_sequence;

--Entidades básicas

CREATE TABLE HTA_HOTEL
    (ID NUMBER,
    NOMBRE VARCHAR2(50 BYTE),
    UBICACION VARCHAR2(50 BYTE),
    CONSTRAINT HTA_HOTEL_PK PRIMARY KEY (ID));

CREATE TABLE HTA_CLIENTE
    (NOMBRE VARCHAR2(65 BYTE) NOT NULL,
    NUMDOC NUMBER,
    TIPODOC VARCHAR2(4 BYTE),
    CONSTRAINT HTA_CLIENTE_PK PRIMARY KEY (NUMDOC, TIPODOC));
	
	ALTER TABLE HTA_CLIENTE
		ADD CONSTRAINT HTA_TIPO_CORRECTO_TIPODOC_CLIENTE
		CHECK (TIPODOC IN ('CC', 'TI', 'CE'))
    ENABLE;

CREATE TABLE HTA_TIPO_HABITACION
	(ID NUMBER,
	NOMBRE VARCHAR2(50 BYTE),
	DESCRIPCION VARCHAR2(255 BYTE),
	CONSTRAINT HTA_TIPO_HABITACION_PK PRIMARY KEY (ID));

CREATE TABLE HTA_HABITACION
    (ID NUMBER,
	COSTOPORNOCHE NUMBER,
    TIPOHABITACION NUMBER,
	APROVISIONAMIENTO VARCHAR2(255 BYTE),
	MANTENIMIENTO VARCHAR2(1),
    CONSTRAINT HTA_HABITACION_PK PRIMARY KEY (ID));
	
	ALTER TABLE HTA_HABITACION
		ADD CONSTRAINT fk_hta_habitacion_tipoHabitacion
		FOREIGN KEY (TIPOHABITACION)
		REFERENCES HTA_TIPO_HABITACION(ID)
	ENABLE;
	
	ALTER TABLE HTA_HABITACION
		ADD CONSTRAINT ck_mantenimiento_habitacion
		CHECK (MANTENIMIENTO IN ('Y','N'))
	ENABLE;

--Usuarios

CREATE TABLE HTA_ROLES_DE_USUARIO
	(ID NUMBER,
	NOMBRE VARCHAR2(50 BYTE) UNIQUE,
	CONSTRAINT HTA_ROLES_DE_USUARIO_PK PRIMARY KEY (ID));


CREATE TABLE HTA_USUARIO_SISTEMA
    (ID NUMBER,
    NOMBRE VARCHAR2(65 BYTE),
	ROL NUMBER,
    CONSTRAINT HTA_USUARIO_SISTEMA_PK PRIMARY KEY (ID));
	
	ALTER TABLE HTA_USUARIO_SISTEMA
		ADD CONSTRAINT fk_hta_usuario_sistema_rol
		FOREIGN KEY (ROL)
		REFERENCES HTA_ROLES_DE_USUARIO(ID)
	ENABLE;
	
--Reservas

CREATE TABLE HTA_RESERVA_HABITACION
    (ID NUMBER,
	IDHABITACION NUMBER,
	NUMDOCCLIENTE NUMBER,
	TIPODOCCLIENTE VARCHAR2(4 BYTE),
    DIAENTRADA DATE,
    DIASALIDA DATE,
	COMPLETADA VARCHAR2(4 BYTE),
	CUENTA NUMBER,
    CONSTRAINT HTA_RESERVA_PK PRIMARY KEY (ID));

	ALTER TABLE HTA_RESERVA_HABITACION
		ADD CONSTRAINT fk_hta_reserva_habitacion_habitacion
		FOREIGN KEY (IDHABITACION)
		REFERENCES HTA_HABITACION(ID)
	ENABLE;
	
	ALTER TABLE HTA_RESERVA_HABITACION
		ADD CONSTRAINT fk_hta_reserva_habitacion_cliente
		FOREIGN KEY (NUMDOCCLIENTE,TIPODOCCLIENTE)
		REFERENCES HTA_CLIENTE(NUMDOC,TIPODOC)
	ENABLE;

	ALTER TABLE HTA_RESERVA_HABITACION
		ADD CONSTRAINT HTA_TIPO_CORRECTO_TIPODOC_RESERVA
		CHECK (TIPODOCCLIENTE IN ('CC', 'TI', 'CE'))
    ENABLE;
	
	ALTER TABLE HTA_RESERVA_HABITACION
		ADD CONSTRAINT HTA_RESERVA_COMPLETADA_Y_N
		CHECK (COMPLETADA IN ('Y','N'))
	ENABLE;

--Servicios

CREATE TABLE HTA_TIPO_SERVICIO
	(ID NUMBER,
	NOMBRE VARCHAR (40 BYTE) UNIQUE,
	CONSTRAINT HTA_TIPO_SERVICIO_PK PRIMARY KEY (ID));
	

CREATE TABLE HTA_SERVICIO
    (ID NUMBER,
    HORAINICIO NUMBER,
	HORAFIN NUMBER,
    CAPACIDAD NUMBER,
	MANTENIMIENTO VARCHAR2(1),
	TIPOSERVICIO NUMBER,
    CONSTRAINT HTA_SERVICIO_PK PRIMARY KEY (ID));
	
	ALTER TABLE HTA_SERVICIO
		ADD CONSTRAINT HTA_SERVICIO_HORAINICIO_CORRECTO
		CHECK (HORAINICIO BETWEEN 0 AND 23)
    ENABLE;

	ALTER TABLE HTA_SERVICIO
		ADD CONSTRAINT HTA_SERVICIO_HORAFIN_CORRECTO
		CHECK (HORAINICIO BETWEEN 0 AND 23)
    ENABLE;

	ALTER TABLE HTA_SERVICIO
		ADD CONSTRAINT ck_mantenimiento_servicio
		CHECK (MANTENIMIENTO IN ('Y', 'N'))
    ENABLE;
	
	ALTER TABLE HTA_SERVICIO
		ADD CONSTRAINT fk_hta_servicio_tipo_servicio
		FOREIGN KEY (TIPOSERVICIO)
		REFERENCES HTA_TIPO_SERVICIO(ID)
	ENABLE;
	
CREATE TABLE HTA_RESERVA_DE_SERVICIO
    (ID NUMBER,
	IDRESERVAHABITACION NUMBER,
    IDSERVICIO NUMBER,
	FECHAINICIO TIMESTAMP,
    FECHAFIN TIMESTAMP,
    CONSTRAINT HTA_RESERVA_DE_SERVICIO_PK PRIMARY KEY (ID));

	
	ALTER TABLE HTA_RESERVA_DE_SERVICIO
		ADD CONSTRAINT fk_hta_reserva_de_servicio_idReserva
		FOREIGN KEY (IDRESERVAHABITACION)
		REFERENCES HTA_RESERVA_HABITACION(ID)
	ENABLE;
	
	ALTER TABLE HTA_RESERVA_DE_SERVICIO
		ADD CONSTRAINT fk_hta_reserva_de_servicio_idServicio
		FOREIGN KEY (IDSERVICIO)
		REFERENCES HTA_SERVICIO(ID)
	ENABLE;

CREATE TABLE HTA_SERVICIO_PISCINA
	(IDSERVICIO NUMBER,
	NOMBRE VARCHAR2(50 BYTE),
	CONSTRAINT HTA_SERVICIO_PISCINA_PK PRIMARY KEY (IDSERVICIO));
	
	ALTER TABLE HTA_SERVICIO_PISCINA 
		ADD CONSTRAINT fk_hta_piscina_idServicio
		FOREIGN KEY (IDSERVICIO)
		REFERENCES HTA_SERVICIO(ID)
	ENABLE;

CREATE TABLE HTA_SERVICIO_GIMNASIO
	(IDSERVICIO NUMBER,
	NOMBRE VARCHAR2(50 BYTE),
	CONSTRAINT HTA_SERVICIO_GIMNASIO_PK PRIMARY KEY (IDSERVICIO));
	
	ALTER TABLE HTA_SERVICIO_GIMNASIO
		ADD CONSTRAINT fk_hta_gimnasio_idServicio
		FOREIGN KEY (IDSERVICIO)
		REFERENCES HTA_SERVICIO(ID)
	ENABLE;

CREATE TABLE HTA_SERVICIO_INTERNET
    (IDSERVICIO NUMBER,
	IDRESERVA NUMBER,
	NUMERODIASUSO NUMBER,
	COSTO NUMBER,
    CONSTRAINT HTA_INTERNET_PK PRIMARY KEY (IDSERVICIO,IDRESERVA));
	
	ALTER TABLE HTA_SERVICIO_INTERNET
		ADD CONSTRAINT fk_hta_internet_idServicio
		FOREIGN KEY (IDSERVICIO)
		REFERENCES HTA_SERVICIO(ID)
	ENABLE;

	ALTER TABLE HTA_SERVICIO_INTERNET
		ADD CONSTRAINT fk_hta_internet_idReserva
		FOREIGN KEY (IDRESERVA)
		REFERENCES HTA_RESERVA_HABITACION(ID)
	ENABLE;
	
CREATE TABLE HTA_SERVICIO_BAR
    (IDSERVICIO NUMBER,
    NOMBRE VARCHAR2(50 BYTE),
    CONSTRAINT HTA_BAR_PK PRIMARY KEY (IDSERVICIO));

	ALTER TABLE HTA_SERVICIO_BAR
		ADD CONSTRAINT fk_hta_bar_idServicio
		FOREIGN KEY (IDSERVICIO)
		REFERENCES HTA_SERVICIO(ID)
    ENABLE;

CREATE TABLE HTA_SERVICIO_RESTAURANTE
    (IDSERVICIO NUMBER,
	NOMBRE VARCHAR2(50 BYTE),
	ESTILO VARCHAR2(50 BYTE),
    CONSTRAINT HTA_RESTAURANTE_PK PRIMARY KEY (IDSERVICIO));

	ALTER TABLE HTA_SERVICIO_RESTAURANTE
		ADD CONSTRAINT fk_hta_restaurante_idServicio
		FOREIGN KEY (IDSERVICIO)
		REFERENCES HTA_SERVICIO(ID)
    ENABLE;

CREATE TABLE HTA_SERVICIO_SUPERMERCADO
    (IDSERVICIO NUMBER,
	NOMBRE VARCHAR2(50),
    CONSTRAINT HTA_SUPERMERCADO_PK PRIMARY KEY (IDSERVICIO));

	ALTER TABLE HTA_SERVICIO_SUPERMERCADO
		ADD CONSTRAINT fk_hta_supermercado_idServicio
		FOREIGN KEY (IDSERVICIO)
		REFERENCES HTA_SERVICIO(ID)
    ENABLE;

CREATE TABLE HTA_SERVICIO_TIENDA
    (IDSERVICIO NUMBER,
	NOMBRE VARCHAR2(50 BYTE),
	TIPODETIENDA VARCHAR2(50 BYTE),
    CONSTRAINT HTA_TIENDA_PK PRIMARY KEY (IDSERVICIO));
	
	ALTER TABLE HTA_SERVICIO_TIENDA
		ADD CONSTRAINT fk_hta_tienda_idServicio
		FOREIGN KEY (IDSERVICIO)
		REFERENCES HTA_SERVICIO(ID)
    ENABLE;

CREATE TABLE HTA_SERVICIO_SPA
    (IDSERVICIO NUMBER,
	NOMBRE VARCHAR2(50 BYTE),
    CONSTRAINT HTA_SPA_PK PRIMARY KEY (IDSERVICIO));
	
	ALTER TABLE HTA_SERVICIO_SPA
		ADD CONSTRAINT fk_hta_spa_idServicio
		FOREIGN KEY (IDSERVICIO)
		REFERENCES HTA_SERVICIO(ID)
    ENABLE;
	
CREATE TABLE HTA_SERVICIO_LAVADO_PLANCHADO_EMBOLADO
    (IDSERVICIO NUMBER,
    IDRESERVA NUMBER,
    TIPOPRENDA VARCHAR2(50 BYTE),
    NUMEROPRENDAS NUMBER,
    CONSTRAINT HTA_SERVICIO_LAVADO_PLANCHADO_EMBOLADO_PK PRIMARY KEY (IDSERVICIO, IDRESERVA));

	ALTER TABLE HTA_SERVICIO_LAVADO_PLANCHADO_EMBOLADO
		ADD CONSTRAINT fk_hta_lavado_planchado_embolado_idServicio
		FOREIGN KEY (IDSERVICIO)
		REFERENCES HTA_SERVICIO(ID)
	ENABLE;

	ALTER TABLE HTA_SERVICIO_LAVADO_PLANCHADO_EMBOLADO
		ADD CONSTRAINT fk_hta_lavado_planchado_embolado_idReserva
		FOREIGN KEY (IDRESERVA)
		REFERENCES HTA_RESERVA_HABITACION(ID)
    ENABLE;

CREATE TABLE HTA_PRESTAMO_UTENSILIOS
    (IDSERVICIO NUMBER,
	IDRESERVA NUMBER,
	RECARGOPORMALUSO NUMBER,
    CONSTRAINT HTA_PRESTAMO_UTENSILIOS_PK PRIMARY KEY (IDSERVICIO,IDRESERVA));

	ALTER TABLE HTA_PRESTAMO_UTENSILIOS
		ADD CONSTRAINT fk_hta_prestamo_utensilios_idServicio
		FOREIGN KEY (IDSERVICIO)
		REFERENCES HTA_SERVICIO(ID)
	ENABLE;

	ALTER TABLE HTA_PRESTAMO_UTENSILIOS
		ADD CONSTRAINT fk_hta_prestamo_utensilios_idReserva
		FOREIGN KEY (IDRESERVA)
		REFERENCES HTA_RESERVA_HABITACION(ID)
	ENABLE;

CREATE TABLE HTA_SALON_REUNIONES
    (IDSERVICIO NUMBER,
    IDRESERVA NUMBER,
    HORAS_USO NUMBER,
	COSTOBASE NUMBER,
    COSTO_ADICIONAL NUMBER,
    CONSTRAINT HTA_SALON_REUNIONES_PK PRIMARY KEY (IDSERVICIO, IDRESERVA));

	ALTER TABLE HTA_SALON_REUNIONES
		ADD CONSTRAINT fk_hta_salon_reuniones_idServicio
		FOREIGN KEY (IDSERVICIO)
		REFERENCES HTA_SERVICIO(ID)
    ENABLE;

	ALTER TABLE HTA_SALON_REUNIONES
		ADD CONSTRAINT fk_hta_salon_reuniones_idReserva
		FOREIGN KEY (IDRESERVA)
		REFERENCES HTA_RESERVA_HABITACION(ID)
    ENABLE;

CREATE TABLE HTA_SALON_CONFERENCIAS
    (IDSERVICIO NUMBER,
    IDRESERVA NUMBER,
    HORAS_USO NUMBER,
	COSTO NUMBER,
    CONSTRAINT HTA_SALON_CONFERENCIAS_PK PRIMARY KEY (IDSERVICIO, IDRESERVA));

	ALTER TABLE HTA_SALON_CONFERENCIAS
		ADD CONSTRAINT fk_hta_salon_conferencias_idServicio
		FOREIGN KEY (IDSERVICIO)
		REFERENCES HTA_SERVICIO(ID)
    ENABLE;

	ALTER TABLE HTA_SALON_CONFERENCIAS
		ADD CONSTRAINT fk_hta_salon_conferencias_idReserva
		FOREIGN KEY (IDRESERVA)
		REFERENCES HTA_RESERVA_HABITACION(ID)
    ENABLE;	

-- Productos

CREATE TABLE HTA_PRODUCTO
	(IDPRODUCTO NUMBER,
	IDSERVICIO NUMBER,
	COSTO NUMBER,
	CONSTRAINT HTA_PRODUCTO_PK PRIMARY KEY (IDSERVICIO,IDPRODUCTO));
	
	ALTER TABLE HTA_PRODUCTO
		ADD CONSTRAINT fk_hta_producto_idServicio
		FOREIGN KEY (IDSERVICIO)
		REFERENCES HTA_SERVICIO(ID)
    ENABLE;

-- Consumos
 
CREATE TABLE HTA_CONSUMO_SERVICIO
	(IDFACTURA NUMBER,
	IDRESERVA NUMBER, 
	IDSERVICIO NUMBER,
	IDPRODUCTO NUMBER,
	CANTIDAD NUMBER,
	CONSTRAINT HTA_CONSUMO_SERVICIO_PK PRIMARY KEY (IDFACTURA,IDPRODUCTO));
	
	ALTER TABLE HTA_CONSUMO_SERVICIO
		ADD CONSTRAINT fk_hta_consumo_servicio_idReserva
		FOREIGN KEY (IDRESERVA)
		REFERENCES HTA_RESERVA_HABITACION(ID)
	ENABLE;
	
	ALTER TABLE HTA_CONSUMO_SERVICIO
		ADD CONSTRAINT fk_hta_consumo_servicio_idProducto
		FOREIGN KEY (IDSERVICIO,IDPRODUCTO)
		REFERENCES HTA_PRODUCTO(IDSERVICIO, IDPRODUCTO)
	ENABLE;
	
	ALTER TABLE HTA_CONSUMO_SERVICIO
		ADD CONSTRAINT fk_hta_consumo_servicio_idServicio
		FOREIGN KEY (IDSERVICIO)
		REFERENCES HTA_SERVICIO(ID)
	ENABLE;

-- PLANES DE CONSUMO

CREATE TABLE HTA_PLAN_DE_CONSUMO
    (ID NUMBER,
    TIPO VARCHAR2(255 BYTE),
    CONSTRAINT HTA_PLANDECONSUMO_PK PRIMARY KEY (ID));
	
CREATE TABLE HTA_LARGA_ESTADIA
    (IDPLANDECONSUMO NUMBER,
    DESCUENTO BINARY_FLOAT,
    IDHOTEL NUMBER,
	TIEMPOESTADIA VARCHAR2(50 BYTE),
    CONSTRAINT HTA_LARGA_ESTADIA_PK PRIMARY KEY (IDPLANDECONSUMO));

	ALTER TABLE HTA_LARGA_ESTADIA
		ADD CONSTRAINT fk_hta_larga_estadia_idHotel
		FOREIGN KEY (IDHOTEL)
		REFERENCES HTA_HOTEL(ID)
    ENABLE;
	
CREATE TABLE HTA_TIEMPO_COMPARTIDO
    (IDPLANDECONSUMO NUMBER,
	IDSERVICIOASOCIADO NUMBER,
    CONSTRAINT HTA_TIEMPO_COMPARTIDO_PK PRIMARY KEY (IDPLANDECONSUMO));

	ALTER TABLE HTA_TIEMPO_COMPARTIDO
		ADD CONSTRAINT fk_hta_tiempo_compartido_idPlanDeConsumo
		FOREIGN KEY (IDPLANDECONSUMO)
		REFERENCES HTA_PLAN_DE_CONSUMO(ID)
	ENABLE;
	
	ALTER TABLE HTA_TIEMPO_COMPARTIDO
		ADD CONSTRAINT fk_hta_tiempo_compartido_idServicioAsociado
		FOREIGN KEY (IDSERVICIOASOCIADO)
		REFERENCES HTA_SERVICIO(ID)
	ENABLE;	

CREATE TABLE HTA_TODO_INCLUIDO
    (IDPLANDECONSUMO NUMBER,
	IDSERVICIOASOCIADO NUMBER,
	IDRESERVA NUMBER,
	COSTOFIJOTOTAL NUMBER,
    CONSTRAINT HTA_TODO_INCLUIDO_PK PRIMARY KEY (IDPLANDECONSUMO));

	ALTER TABLE HTA_TODO_INCLUIDO
		ADD CONSTRAINT fk_hta_todo_incluido_idPlanDeConsumo
		FOREIGN KEY (IDPLANDECONSUMO)
		REFERENCES HTA_PLAN_DE_CONSUMO(ID)
	ENABLE;
	
	ALTER TABLE HTA_TODO_INCLUIDO
		ADD CONSTRAINT fk_hta_todo_incluido_idServicioAsociado
		FOREIGN KEY (IDSERVICIOASOCIADO)
		REFERENCES HTA_SERVICIO(ID)
	ENABLE;
	
	ALTER TABLE HTA_TODO_INCLUIDO
		ADD CONSTRAINT fk_hta_todo_incluido_idReserva
		FOREIGN KEY (IDRESERVA)
		REFERENCES HTA_RESERVA_HABITACION(ID)
	ENABLE;
	
CREATE TABLE HTA_PRODUCTOS_TODO_INCLUIDO
    (IDPLANDECONSUMO NUMBER,
    IDPRODUCTOASOCIADO NUMBER,
    DESCUENTO BINARY_FLOAT,
    CONSTRAINT HTA_PRODUCTOS_TODO_INCLUIDO_PK PRIMARY KEY (IDPLANDECONSUMO, IDPRODUCTOASOCIADO));

	ALTER TABLE HTA_PRODUCTOS_TODO_INCLUIDO
		ADD CONSTRAINT fk_hta_productos_todo_incluido_idServicioAsociado
		FOREIGN KEY (IDPLANDECONSUMO)
		REFERENCES HTA_TODO_INCLUIDO(IDPLANDECONSUMO)
    ENABLE;

	ALTER TABLE HTA_PRODUCTOS_TODO_INCLUIDO
		ADD CONSTRAINT fk_hta_productos_todo_incluido_idProducto
		FOREIGN KEY (IDPLANDECONSUMO,IDPRODUCTOASOCIADO)
		REFERENCES HTA_PRODUCTO(IDSERVICIO,IDPRODUCTO)
    ENABLE;
	
CREATE TABLE HTA_PROMOCION_PARTICULAR
	(IDPLANDECONSUMO NUMBER,
	DESCRIPCION VARCHAR2(255 BYTE),
	CONSTRAINT HTA_PROMOCION_PARTICULAR_PK PRIMARY KEY (IDPLANDECONSUMO));
	
	ALTER TABLE HTA_PROMOCION_PARTICULAR
		ADD CONSTRAINT fk_hta_promocionParticular_idPlanDeConsumo
		FOREIGN KEY (IDPLANDECONSUMO)
		REFERENCES HTA_PLAN_DE_CONSUMO(ID)
	ENABLE;
    
CREATE TABLE HTA_CONVENCION
	(ID NUMBER,
	IDPLANDECONSUMO NUMBER,
	NUMASISTENTES NUMBER,
	FECHAINICIO DATE,
	FECHAFIN DATE,
	CUENTA NUMBER,
	ESTADO VARCHAR2(10),
	CONSTRAINT HTA_CONVENCION_PK PRIMARY KEY (ID));
	
	ALTER TABLE HTA_CONVENCION
		ADD CONSTRAINT HTA_CONVENCION_NUMASISTENTES
		CHECK (NUMASISTENTES BETWEEN 50 AND 10000)
	ENABLE;
	
	ALTER TABLE HTA_CONVENCION
		ADD CONSTRAINT HTA_CONVENCION_ESTADO
		CHECK (ESTADO IN ('Iniciada','Cerrada'))
	ENABLE;

CREATE TABLE HTA_CONVENCION_SERVICIO
	(IDCONVENCION NUMBER,
	IDRESERVASERVICIO NUMBER,
	CONSTRAINT HTA_CONVENCION_SERVICIO_PK PRIMARY KEY(IDCONVENCION,IDRESERVASERVICIO));
	
	ALTER TABLE HTA_CONVENCION_SERVICIO
		ADD CONSTRAINT fk_hta_convencion_servicio_idServicio
		FOREIGN KEY (IDRESERVASERVICIO)
		REFERENCES HTA_RESERVA_DE_SERVICIO(ID)
	ENABLE;
	
	ALTER TABLE HTA_CONVENCION_SERVICIO
		ADD CONSTRAINT fk_hta_convencion_servicio_idConvencion
		FOREIGN KEY (IDCONVENCION)
		REFERENCES HTA_CONVENCION(ID)
	ENABLE;

CREATE TABLE HTA_CONVENCION_HABITACION
	(IDCONVENCION NUMBER,
	IDRESERVAHABITACION NUMBER,
	CONSTRAINT HTA_CONVENCION_HABITACION_PK PRIMARY KEY(IDCONVENCION,IDRESERVAHABITACION));
	
	ALTER TABLE HTA_CONVENCION_HABITACION
		ADD CONSTRAINT fk_hta_convencion_habitacion_idHabitacion
		FOREIGN KEY (IDRESERVAHABITACION)
		REFERENCES HTA_RESERVA_HABITACION(ID)
	ENABLE;
	
	ALTER TABLE HTA_CONVENCION_HABITACION
		ADD CONSTRAINT fk_hta_convencion_habitacion_idConvencion
		FOREIGN KEY (IDCONVENCION)
		REFERENCES HTA_CONVENCION(ID)
	ENABLE;

COMMIT;