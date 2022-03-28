create sequence hotelandesa12_sequence;

-- Creación de tablas del proyecto

CREATE TABLE HTA_HOTEL
    (ID NUMBER,
    NOMBRE VARCHAR2(255 BYTE),
    UBICACION VARCHAR2(255 BYTE),
    CONSTRAINT HTA_HOTEL_PK PRIMARY KEY (ID));

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_RESERVA
    (ID NUMBER,
    PERIODO NUMBER,
    CONSTRAINT HTA_RESERVA_PK PRIMARY KEY (ID));

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_SERVICIO
    (ID NUMBER,
    HORARIOSERVICIO VARCHAR2(20 BYTE),
    CAPACIDAD NUMBER,
	COSTO NUMBER,
    CONSTRAINT HTA_SERVICIO_PK PRIMARY KEY (ID));

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_PRODUCTO
    (ID NUMBER,
	COSTO NUMBER,
    CONSTRAINT HTA_PRODUCTO_PK PRIMARY KEY (ID));

-- Creación de tabla CLIENTE

CREATE TABLE HTA_CLIENTE
    (NOMBRE VARCHAR2(255 BYTE) NOT NULL,
    NUMDOC NUMBER,
    TIPODOC VARCHAR2(255 BYTE),
    DIAENTRADA DATE,
    DIASALIDA DATE,
    CONSTRAINT HTA_CLIENTE_PK PRIMARY KEY (NUMDOC, TIPODOC));

ALTER TABLE HTA_CLIENTE
    ADD CONSTRAINT TIPO_CORRECTO_TIPODOC
    CHECK (TIPODOC IN ('CC', 'TI', 'CE'))
    ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_HABITACION
    (COSTOPORNOCHE NUMBER,
    CUENTA NUMBER,
    ID NUMBER,
	APROVISIONAMIENTO VARCHAR2(255 BYTE),
    CONSTRAINT HTA_HABITACION_PK PRIMARY KEY (ID));

-- Creación de tabla CADENA HOTELERA
 
CREATE TABLE HTA_CADENAHOTELERA
    (ID NUMBER,
    NOMBRE VARCHAR2(255 BYTE),
    CONSTRAINT HTA_CADENAHOTELERA_PK PRIMARY KEY (ID));


-- Creación de tablas CARRITO COMPRA
 
CREATE TABLE HTA_CARRITO_COMPRA_SUPERMERCADO
    (IDFACTURA NUMBER,
	IDPRODUCTO NUMBER,
	UNIDADES NUMBER,
    CONSTRAINT HTA_CARRITO_COMPRA_SUPERMERCADO_PK PRIMARY KEY (IDFACTURA,IDPRODUCTO));

ALTER TABLE HTA_CARRITO_COMPRA_SUPERMERCADO
ADD CONSTRAINT fk_hta_carrito_compra_idProducto
    FOREIGN KEY (IDPRODUCTO)
    REFERENCES HTA_PRODUCTO(ID)
ENABLE;

-- Creación de tabla CLIENTE RESTAURANTE
 
CREATE TABLE HTA_CLIENTE_RESTAURANTE
    (IDSERVICIO NUMBER,
	IDRESERVA NUMBER,
	CUENTA NUMBER,
    CONSTRAINT HTA_CLIENTE_RESTAURANTE_PK PRIMARY KEY (IDSERVICIO,IDRESERVA));

ALTER TABLE HTA_CLIENTE_RESTAURANTE
ADD CONSTRAINT fk_hta_cliente_restaurante_idServicio
    FOREIGN KEY (IDSERVICIO)
    REFERENCES HTA_SERVICIO(ID)
ENABLE;

ALTER TABLE HTA_CLIENTE_RESTAURANTE
ADD CONSTRAINT fk_hta_cliente_restaurante_idReserva
    FOREIGN KEY (IDRESERVA)
    REFERENCES HTA_RESERVA(ID)
ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_CLIENTE_SPA
    (IDSERVICIO NUMBER,
	IDRESERVA NUMBER,
	CUENTA NUMBER,
    CONSTRAINT HTA_CLIENTE_SPA_PK PRIMARY KEY (IDSERVICIO,IDRESERVA));

ALTER TABLE HTA_CLIENTE_SPA
ADD CONSTRAINT fk_hta_cliente_spa_idServicio
    FOREIGN KEY (IDSERVICIO)
    REFERENCES HTA_SERVICIO(ID)
ENABLE;

ALTER TABLE HTA_CLIENTE_SPA
ADD CONSTRAINT fk_hta_cliente_spa_idReserva
    FOREIGN KEY (IDRESERVA)
    REFERENCES HTA_RESERVA(ID)
ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_CLIENTE_TIENDA
    (IDSERVICIO NUMBER,
	IDRESERVA NUMBER,
	CUENTA NUMBER,
    CONSTRAINT HTA_CLIENTE_TIENDA_PK PRIMARY KEY (IDSERVICIO,IDRESERVA));

ALTER TABLE HTA_CLIENTE_TIENDA
ADD CONSTRAINT fk_hta_cliente_tienda_idServicio
    FOREIGN KEY (IDSERVICIO)
    REFERENCES HTA_SERVICIO(ID)
ENABLE;

ALTER TABLE HTA_CLIENTE_TIENDA
ADD CONSTRAINT fk_hta_cliente_tienda_idReserva
    FOREIGN KEY (IDRESERVA)
    REFERENCES HTA_RESERVA(ID)
ENABLE;


-- Creación de tablas del proyecto
 
CREATE TABLE HTA_CLIENTE_SUPERMERCADO
    (IDSERVICIO NUMBER,
    IDRESERVA NUMBER,
    IDFACTURA NUMBER,
    CONSTRAINT HTA_CLIENTE_SUPERMERCADO_PK PRIMARY KEY (IDSERVICIO, IDRESERVA));

ALTER TABLE HTA_CLIENTE_SUPERMERCADO
    ADD CONSTRAINT fk_hta_cliente_supermercado_idServicio
    FOREIGN KEY (IDSERVICIO)
    REFERENCES HTA_SERVICIO(ID)
    ENABLE;

ALTER TABLE HTA_CLIENTE_SUPERMERCADO
    ADD CONSTRAINT fk_hta_cliente_supermercado_idReserva
    FOREIGN KEY (IDRESERVA)
    REFERENCES HTA_RESERVA(ID)
    ENABLE;

-- Creación de tabla bar
 
CREATE TABLE HTA_BAR
    (IDSERVICIO NUMBER,
    IDRESERVA NUMBER,
    IDFACTURA NUMBER,
    CONSTRAINT HTA_BAR_PK PRIMARY KEY (IDSERVICIO, IDRESERVA));

ALTER TABLE HTA_BAR
    ADD CONSTRAINT fk_hta_bar_idServicio
    FOREIGN KEY (IDSERVICIO)
    REFERENCES HTA_SERVICIO(ID)
    ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_PRODUCTO_BAR
    (IDSERVICIO NUMBER,
    IDPRODUCTO NUMBER,
    COSTO NUMBER,
    CONSTRAINT HTA_PRODUCTO_BAR_PK PRIMARY KEY (IDSERVICIO, IDPRODUCTO));

ALTER TABLE HTA_PRODUCTO_BAR
    ADD CONSTRAINT fk_hta_producto_bar_idServicio
    FOREIGN KEY (IDSERVICIO)
    REFERENCES HTA_SERVICIO(ID)
    ENABLE;

ALTER TABLE HTA_PRODUCTO_BAR
    ADD CONSTRAINT fk_hta_producto_bar_idProducto
    FOREIGN KEY (IDPRODUCTO)
    REFERENCES HTA_PRODUCTO(ID)
    ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_CONSUMO_BAR
    (IDFACTURA NUMBER,
	IDSERVICIOBAR NUMBER,
	IDPRODUCTO NUMBER,
	CANTIDAD NUMBER,
    CONSTRAINT HTA_CONSUMO_BAR_PK PRIMARY KEY (IDFACTURA,IDPRODUCTO));

ALTER TABLE HTA_CONSUMO_BAR
ADD CONSTRAINT fk_hta_consumo_bar_idProductoBar
    FOREIGN KEY (IDSERVICIOBAR,IDPRODUCTO)
    REFERENCES HTA_PRODUCTO_BAR(IDSERVICIO, IDPRODUCTO)
ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_PLAN_DE_CONSUMO
    (ID NUMBER,
    TIPO VARCHAR2(255 BYTE),
    TIEMPOESTADIA VARCHAR2(255 BYTE),
    CONSTRAINT HTA_PLANDECONSUMO_PK PRIMARY KEY (ID));

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_DESCUENTOS_TIEMPO_COMPARTIDO
    (IDSERVICIOASOCIADO NUMBER,
    IDPRODUCTOASOCIADO NUMBER,
    DESCUENTO BINARY_FLOAT,
    CONSTRAINT HTA_DESCUENTOS_TIEMPO_COMPARTIDO_PK PRIMARY KEY (IDSERVICIOASOCIADO, IDPRODUCTOASOCIADO));

ALTER TABLE HTA_DESCUENTOS_TIEMPO_COMPARTIDO
    ADD CONSTRAINT fk_hta_descuentos_tiempo_compartido_idServicioAsociado
    FOREIGN KEY (IDSERVICIOASOCIADO)
    REFERENCES HTA_PLAN_DE_CONSUMO(ID)
    ENABLE;

ALTER TABLE HTA_DESCUENTOS_TIEMPO_COMPARTIDO
    ADD CONSTRAINT fk_hta_descuentos_tiempo_compartido_idProducto
    FOREIGN KEY (IDPRODUCTOASOCIADO)
    REFERENCES HTA_PRODUCTO(ID)
    ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_EQUIPO_SALON_REUNIONES
    (IDSERVICIO NUMBER,
	IDEQUIPO NUMBER,
	COSTO NUMBER,
    CONSTRAINT HTA_EQUIPO_SALON_REUNIONES_PK PRIMARY KEY (IDSERVICIO,IDEQUIPO));

ALTER TABLE HTA_EQUIPO_SALON_REUNIONES
ADD CONSTRAINT fk_hta_equipo_salon_reuniones_idServicio
    FOREIGN KEY (IDSERVICIO)
    REFERENCES HTA_SERVICIO(ID)
ENABLE;


-- Creación de tablas del proyecto
 
CREATE TABLE HTA_HABITACION_DE_CLIENTE
    (IDHABITACION NUMBER,
    NUMDOCCLIENTE NUMBER,
	TIPODOCCLIENTE VARCHAR2(255 BYTE),
    CONSTRAINT HTA_HABITACION_DE_CLIENTE_PK PRIMARY KEY (IDHABITACION, NUMDOCCLIENTE, TIPODOCCLIENTE));

ALTER TABLE HTA_HABITACION_DE_CLIENTE
    ADD CONSTRAINT HTA_TIPO_CORRECTO_TIPODOC_HABITACION
    CHECK (TIPODOCCLIENTE IN ('CC', 'TI', 'CE'))
    ENABLE;

ALTER TABLE HTA_HABITACION_DE_CLIENTE
    ADD CONSTRAINT fk_hta_habitacion_de_cliente_cliente_numdoc_tipodoc
    FOREIGN KEY (NUMDOCCLIENTE, TIPODOCCLIENTE)
    REFERENCES HTA_CLIENTE(NUMDOC, TIPODOC)
    ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_HABITACIONES_DE_HOTEL
    (IDHABITACION NUMBER,
    IDHOTEL NUMBER,
    CONSTRAINT HTA_HABITACIONES_DE_HOTEL_PK PRIMARY KEY (IDHABITACION,IDHOTEL));

ALTER TABLE HTA_HABITACIONES_DE_HOTEL
    ADD CONSTRAINT fk_hta_habitaciones_de_hotel_idHabitacion
    FOREIGN KEY (IDHABITACION)
    REFERENCES HTA_HABITACION(ID)
    ENABLE;

ALTER TABLE HTA_HABITACIONES_DE_HOTEL
    ADD CONSTRAINT fk_hta_habitaciones_de_hotel_idHotel
    FOREIGN KEY (IDHOTEL)
    REFERENCES HTA_HOTEL(ID)
    ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_HOTELES_DE_CADENA_HOTELERA
    (IDCADENA NUMBER,
	IDHOTEL NUMBER,
    CONSTRAINT HTA_HOTELES_DE_CADENA_HOTELERA_PK PRIMARY KEY (IDCADENA,IDHOTEL));

ALTER TABLE HTA_HOTELES_DE_CADENA_HOTELERA
ADD CONSTRAINT fk_hta_hoteles_cadena_hotelera_idCadena
    FOREIGN KEY (IDCADENA)
    REFERENCES HTA_CADENAHOTELERA(ID)
ENABLE;
	
ALTER TABLE HTA_HOTELES_DE_CADENA_HOTELERA
ADD CONSTRAINT fk_hta_hoteles_cadena_hotelera_idHotel
    FOREIGN KEY (IDHOTEL)
    REFERENCES HTA_HOTEL(ID)
ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_INTERNET
    (IDHABITACION NUMBER,
	IDRESERVA NUMBER,
	NUMERODIASUSO NUMBER,
    CONSTRAINT HTA_INTERNET_PK PRIMARY KEY (IDRESERVA));

ALTER TABLE HTA_INTERNET
ADD CONSTRAINT fk_hta_internet_idHabitacion
    FOREIGN KEY (IDHABITACION)
    REFERENCES HTA_HABITACION(ID)
ENABLE;

ALTER TABLE HTA_INTERNET
ADD CONSTRAINT fk_hta_internet_idReserva
    FOREIGN KEY (IDRESERVA)
    REFERENCES HTA_RESERVA(ID)
ENABLE;
-- Creación de tablas del proyecto
 
CREATE TABLE HTA_LARGA_ESTADIA
    (IDPLANDECONSUMO NUMBER,
    DESCUENTO BINARY_FLOAT, -- REVISAR
    IDHOTEL NUMBER,
    CONSTRAINT HTA_LARGA_ESTADIA_PK PRIMARY KEY (IDPLANDECONSUMO));

ALTER TABLE HTA_LARGA_ESTADIA
    ADD CONSTRAINT fk_hta_larga_estadia_idHotel
    FOREIGN KEY (IDHOTEL)
    REFERENCES HTA_HOTEL(ID)
    ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_LAVADO_PLANCHADO_EMBOLADO
    (IDSERVICIO NUMBER,
    IDRESERVA NUMBER,
    TIPOPRENDA VARCHAR2(255 BYTE),
    NUMEROPRENDAS NUMBER,
    CUENTA NUMBER,
    CONSTRAINT HTA_LAVADO_PLANCHADO_EMBOLADO_PK PRIMARY KEY (IDSERVICIO, IDRESERVA));

ALTER TABLE HTA_LAVADO_PLANCHADO_EMBOLADO
    ADD CONSTRAINT fk_hta_lavado_planchado_embolado_idServicio
    FOREIGN KEY (IDSERVICIO)
    REFERENCES HTA_SERVICIO(ID)
    ENABLE;

ALTER TABLE HTA_LAVADO_PLANCHADO_EMBOLADO
    ADD CONSTRAINT fk_hta_lavado_planchado_embolado_idReserva
    FOREIGN KEY (IDRESERVA)
    REFERENCES HTA_RESERVA(ID)
    ENABLE;

-- Creación de tablas del proyecto
 
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
    REFERENCES HTA_RESERVA(ID)
ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_PRODUCTO_DE_SERVICIO
    (IDPRODUCTO NUMBER,
	IDSERVICIO NUMBER,
    CONSTRAINT HTA_PRODUCTO_DE_SERVICIO_PK PRIMARY KEY (IDPRODUCTO,IDSERVICIO));

ALTER TABLE HTA_PRODUCTO_DE_SERVICIO
ADD CONSTRAINT fk_hta_producto_de_servicio_idProducto
    FOREIGN KEY (IDPRODUCTO)
    REFERENCES HTA_PRODUCTO(ID)
ENABLE;
	
ALTER TABLE HTA_PRODUCTO_DE_SERVICIO
ADD CONSTRAINT fk_hta_producto_de_servicio_idServicio
    FOREIGN KEY (IDSERVICIO)
    REFERENCES HTA_SERVICIO(ID)
ENABLE;

-- Creación de tablas del proyecto

CREATE TABLE HTA_PRODUCTO_DE_TIENDA
    (IDSERVICIO NUMBER,
    IDPRODUCTO NUMBER,
    COSTO NUMBER,
    CONSTRAINT HTA_PRODUCTO_DE_TIENDA_PK PRIMARY KEY (IDSERVICIO, IDPRODUCTO));

ALTER TABLE HTA_PRODUCTO_DE_TIENDA
    ADD CONSTRAINT fk_hta_producto_de_tienda_idServicio
    FOREIGN KEY (IDSERVICIO)
    REFERENCES HTA_SERVICIO(ID)
    ENABLE;

ALTER TABLE HTA_PRODUCTO_DE_TIENDA
    ADD CONSTRAINT fk_hta_producto_de_tienda_idProducto
    FOREIGN KEY (IDPRODUCTO)
    REFERENCES HTA_PRODUCTO(ID)
    ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_PRODUCTO_RESTAURANTE
    (IDSERVICIO NUMBER,
    IDPRODUCTO NUMBER,
    COSTO NUMBER,
    CONSTRAINT HTA_PRODUCTO_RESTAURANTE_PK PRIMARY KEY (IDSERVICIO, IDPRODUCTO));

ALTER TABLE HTA_PRODUCTO_RESTAURANTE
    ADD CONSTRAINT fk_hta_producto_restaurante_idServicio
    FOREIGN KEY (IDSERVICIO)
    REFERENCES HTA_SERVICIO(ID)
    ENABLE;

ALTER TABLE HTA_PRODUCTO_RESTAURANTE
    ADD CONSTRAINT fk_hta_producto_restaurante_idProducto
    FOREIGN KEY (IDPRODUCTO)
    REFERENCES HTA_PRODUCTO(ID)
    ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_TODO_INCLUIDO
    (IDPLANDECONSUMO NUMBER,
	IDSERVICIOASOCIADO NUMBER,
	CUENTAHABITACION NUMBER,
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
ADD CONSTRAINT fk_hta_todo_incluido_cuentaHabitacion
	FOREIGN KEY (CUENTAHABITACION)
	REFERENCES HTA_HABITACION(ID)
ENABLE;

-- Creación de tablas del proyecto
 
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
    FOREIGN KEY (IDPRODUCTOASOCIADO)
    REFERENCES HTA_PRODUCTO(ID)
    ENABLE;


-- Creación de tablas del proyecto
 -- #TODO TIENE UN CAMBIO CON RESPECTO A LA TABLA ORIGINAL
 -- NO DEBERÍAMOS INCLUIR EL ID DE LA HABITACIÓN?
 
CREATE TABLE HTA_RESERVA_DE_HABITACION
    (ID NUMBER,
    NUMDOCCLIENTE NUMBER,
    TIPODOCCLIENTE VARCHAR2(255 BYTE),
    DIAENTRADA DATE,
    DIASALIDA DATE,
    CONSTRAINT HTA_RESERVA_DE_HABITACION_PK PRIMARY KEY (ID));

ALTER TABLE HTA_RESERVA_DE_HABITACION
    ADD CONSTRAINT HTA_TIPO_CORRECTO_TIPODOC_RESERVA
    CHECK (TIPODOCCLIENTE IN ('CC', 'TI', 'CE'))
    ENABLE;

ALTER TABLE HTA_RESERVA_DE_HABITACION
    ADD CONSTRAINT fk_hta_reserva_de_habitacion_cliente_numdoc_tipodoc
    FOREIGN KEY (NUMDOCCLIENTE, TIPODOCCLIENTE)
    REFERENCES HTA_CLIENTE(NUMDOC,TIPODOC)
    ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_RESERVA_DE_SERVICIO
    (IDRESERVA NUMBER,
	NUMDOCCLIENTE NUMBER,
    TIPODOCCLIENTE VARCHAR2(255 BYTE),
    SERVICIO NUMBER,
	PERIODO VARCHAR2 (255 BYTE),
    CONSTRAINT HTA_RESERVA_DE_SERVICIO_PK PRIMARY KEY (IDRESERVA));

ALTER TABLE HTA_RESERVA_DE_SERVICIO
ADD CONSTRAINT HTA_TIPO_CORRECTO_TIPODOC_RESERVA_SERVICIO
    CHECK (TIPODOCCLIENTE IN ('CC', 'TI', 'CE'))
ENABLE;
	
ALTER TABLE HTA_RESERVA_DE_SERVICIO
ADD CONSTRAINT fk_hta_reserva_de_servicio_cliente_numdoc_tipodoc
    FOREIGN KEY (NUMDOCCLIENTE,TIPODOCCLIENTE)
    REFERENCES HTA_CLIENTE(NUMDOC,TIPODOC)
ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_RESTAURANTE
    (IDSERVICIO NUMBER,
	CAPACIDAD NUMBER,
	ESTILO VARCHAR2(255 BYTE),
    CONSTRAINT HTA_RESTAURANTE_PK PRIMARY KEY (IDSERVICIO));

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_SALON_REUNIONES
    (IDSERVICIO NUMBER,
    IDRESERVA NUMBER,
    HORAS_USO NUMBER,
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
    REFERENCES HTA_RESERVA(ID)
    ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_SALON_CONFERENCIAS
    (IDSERVICIO NUMBER,
    IDRESERVA NUMBER,
    HORAS_USO NUMBER,
    CONSTRAINT HTA_SALON_CONFERENCIAS_PK PRIMARY KEY (IDSERVICIO, IDRESERVA));

ALTER TABLE HTA_SALON_CONFERENCIAS
    ADD CONSTRAINT fk_hta_salon_conferencias_idServicio
    FOREIGN KEY (IDSERVICIO)
    REFERENCES HTA_SERVICIO(ID)
    ENABLE;

ALTER TABLE HTA_SALON_CONFERENCIAS
    ADD CONSTRAINT fk_hta_salon_conferencias_idReserva
    FOREIGN KEY (IDRESERVA)
    REFERENCES HTA_RESERVA(ID)
    ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_SERVICIOS_DE_HOTEL
    (IDHOTEL NUMBER,
	IDSERVICIO NUMBER,
    CONSTRAINT HTA_SERVICIOS_DE_HOTEL_PK PRIMARY KEY (IDHOTEL,IDSERVICIO));

ALTER TABLE HTA_SERVICIOS_DE_HOTEL
ADD CONSTRAINT fk_hta_servicios_de_hotel_idHotel
    FOREIGN KEY (IDHOTEL)
    REFERENCES HTA_HOTEL(ID)
ENABLE;

ALTER TABLE HTA_SERVICIOS_DE_HOTEL
ADD CONSTRAINT fk_hta_servicios_de_hotel_idServicio
    FOREIGN KEY (IDSERVICIO)
    REFERENCES HTA_SERVICIO(ID)
ENABLE;

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_SERVICIOS_SPA
    (IDSERVICIO NUMBER,
    IDSERVICIOSPA NUMBER,
    COSTO NUMBER,
    CONSTRAINT HTA_SERVICIOS_SPA_PK PRIMARY KEY (IDSERVICIO, IDSERVICIOSPA));

ALTER TABLE HTA_SERVICIOS_SPA
    ADD CONSTRAINT fk_hta_servicios_spa_idServicio
    FOREIGN KEY (IDSERVICIO)
    REFERENCES HTA_SERVICIO(ID)
    ENABLE;

ALTER TABLE HTA_SERVICIOS_SPA
    ADD CONSTRAINT fk_hta_servicios_spa_idProducto
    FOREIGN KEY (IDSERVICIOSPA)
    REFERENCES HTA_PRODUCTO(ID)
    ENABLE;

-- Creación de tablas del proyecto
 
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

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_TIENDA
    (IDSERVICIO NUMBER,
	TIPODETIENDA VARCHAR2(255 BYTE),
    CONSTRAINT HTA_TIENDA_PK PRIMARY KEY (IDSERVICIO));
	

-- Creación de tablas del proyecto
 
CREATE TABLE HTA_USUARIOSISTEMA
    (ID NUMBER,
    NOMBRE VARCHAR2(255 BYTE),
    CONSTRAINT HTA_USUARIOSISTEMA_PK PRIMARY KEY (ID));


-- Creación de tablas del proyecto
 
CREATE TABLE HTA_USUARIOS_SISTEMA_HOTEL
    (IDCADENAHOTELERA NUMBER,
    IDUSUARIO NUMBER,
    CONSTRAINT HTA_USUARIOS_SISTEMA_HOTEL_PK PRIMARY KEY (IDCADENAHOTELERA, IDUSUARIO));

ALTER TABLE HTA_USUARIOS_SISTEMA_HOTEL
    ADD CONSTRAINT fk_hta_usuarios_sistema_hotel_idCadenaHotelera
    FOREIGN KEY (IDCADENAHOTELERA)
    REFERENCES HTA_CADENAHOTELERA(ID)
    ENABLE;

ALTER TABLE HTA_USUARIOS_SISTEMA_HOTEL
    ADD CONSTRAINT fk_hta_usuarios_sistema_hotel_idUsuarioSistema
    FOREIGN KEY (IDUSUARIO)
    REFERENCES HTA_USUARIOSISTEMA(ID)
    ENABLE;

COMMIT;