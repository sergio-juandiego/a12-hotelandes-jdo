
-- Mostrar el dinero recolectado por servicios en cada habitacion durante el periodo de tiempo y en el año corrido

SELECT H.ID ID_HABITACION,SUM(P.COSTO* CS.CANTIDAD) CONSUMO
FROM HTA_CONSUMO_SERVICIO CS, HTA_RESERVA_HABITACION RH, HTA_HABITACION H, HTA_PRODUCTO P
WHERE RH.IDHABITACION = H.ID 
    AND RH.ID = CS.IDRESERVA
	AND CS.IDPRODUCTO = P.IDPRODUCTO
	AND RH.DIAENTRADA > (DATE'FECHAINICIO')
    AND RH.DIASALIDA < (DATE'FECHAFIN')
GROUP BY H.ID;

-- Mostrar los 20 servicios más populares

SELECT CS.IDSERVICIO, COUNT(DISTINCT CS.IDFACTURA)NUM_CONUMOS
FROM HTA_CONSUMO_SERVICIO CS, HTA_RESERVA_HABITACION RH
WHERE RH.ID = CS.IDRESERVA
	AND RH.DIAENTRADA > (DATE'FECHAINICIO')
    AND RH.DIASALIDA < (DATE'FECHAFIN')
GROUP BY IDSERVICIO
ORDER BY COUNT(DISTINCT IDFACTURA) DESC,IDSERVICIO;

-- Mostrar el índice de ocupación de cada una de las habitaciones del hotel

SELECT H.ID, COUNT(RH.ID) NUM_RESERVAS
FROM HTA_RESERVA_HABITACION RH, HTA_HABITACION H
WHERE RH.IDHABITACION = H.ID 
    AND RH.DIAENTRADA > (DATE'FECHAINICIO')
    AND RH.DIASALIDA < (DATE'FECHAFIN')
GROUP BY H.ID
ORDER BY H.ID;

--Mostrar los servicios que cumplen con cierta característica

SELECT *
FROM HTA_SERVICIO 
WHERE CAPACIDAD < 'CAPACIDAD MAXIMA'
	AND CAPCIDAD > 'CAPACIDAD MINIMA';

SELECT *
FROM HTA_SERVICIO S 
WHERE HORAINICIO> 'HORAINICIO'
	AND HORAFIN < 'HORAFIN'


-- Mostrar el consumo en HotelAndes por un usuario dado en un rango de fechas indicado

SELECT *
FROM HTA_CONSUMO_SERVICIO CS, HTA_RESERVA_HABITACION RH, HTA_CLIENTE C
WHERE RH.ID = CS.IDRESERVA
	AND RH.NUMDOCCLIENTE = C.NUMDOC
	AND RH.TIPODOCCLIENTE = C.TIPODOC
	AND RH.DIAENTRADA > (DATE'FECHAINICIO')
	AND RH.DIASALIDA < (DATE'FECHAFIN')
	AND C.TIPODOC = 'TIPODOC'
	AND C.NUMDOC = 'NUMDOC';

-- RF6 Analizar operacion de hotelandes

-- TIPO HABITACION

-- Por semana 
-- 	maximos
SELECT *
	FROM
	(SELECT to_number(to_char(to_date(HR.DIAENTRADA),'WW')) SEMANA, COUNT(H.TIPOHABITACION) NUM_RESERVAS
	FROM HTA_RESERVA_HABITACION HR, HTA_HABITACION H
	WHERE H.TIPOHABITACION = 'TIPOHAB'
	GROUP BY to_number(to_char(to_date(HR.DIAENTRADA),'WW'))
	ORDER BY NUM_RESERVAS DESC)
	FETCH NEXT 3 ROWS ONLY;
	
-- 	minimos
SELECT *
	FROM
	(SELECT to_number(to_char(to_date(HR.DIAENTRADA),'WW')) SEMANA, COUNT(H.TIPOHABITACION) NUM_RESERVAS
	FROM HTA_RESERVA_HABITACION HR, HTA_HABITACION H
	WHERE H.TIPOHABITACION = 'TIPOHAB'
	GROUP BY to_number(to_char(to_date(HR.DIAENTRADA),'WW'))
	ORDER BY NUM_RESERVAS ASC)
	FETCH NEXT 3 ROWS ONLY;

-- Por mes
-- 	maximos
SELECT *
	FROM
	(SELECT EXTRACT(MONTH FROM RH.DIAENTRADA) MES , COUNT(H.TIPOHABITACION) NUM_RESERVAS
	FROM HTA_RESERVA_HABITACION RH, HTA_HABITACION H
	WHERE H.TIPOHABITACION = 'TIPOHAB'
	GROUP BY EXTRACT(MONTH FROM RH.DIAENTRADA)
	ORDER BY NUM_RESERVAS DESC)
	FETCH NEXT 3 ROWS ONLY;

-- 	minimos
SELECT *
	FROM
	(SELECT EXTRACT(MONTH FROM RH.DIAENTRADA) MES , COUNT(H.TIPOHABITACION) NUM_RESERVAS
	FROM HTA_RESERVA_HABITACION RH, HTA_HABITACION H
	WHERE H.TIPOHABITACION = 4
	GROUP BY EXTRACT(MONTH FROM RH.DIAENTRADA)
	ORDER BY NUM_RESERVAS ASC)
	FETCH NEXT 3 ROWS ONLY

-- TIPO SERVICIO...


-- RFC7 Encontrar los buenos clientes

-- Estadías mayores a 2 semanas
SELECT C1.TIPODOC, C1.NUMDOC, C1.NOMBRE
FROM 
	(SELECT C.TIPODOC, C.NUMDOC, C.NOMBRE, SUM(TRUNC(RH.DIASALIDA)-TRUNC(RH.DIAENTRADA)) dias
	FROM HTA_CLIENTE C, HTA_RESERVA_HABITACION RH
	WHERE RH.NUMDOCCLIENTE = C.NUMDOC
		AND RH.TIPODOCCLIENTE = C.TIPODOC
	GROUP BY C.TIPODOC,C.NUMDOC, C.NOMBRE) C1
WHERE dias > 14;

-- Gastos superiores a 15 millones

SELECT C.TIPODOC, C.NUMDOC, C.NOMBRE
FROM 
	(SELECT C.TIPODOC, C.NUMDOC, C.NOMBRE, SUM(RH.CUENTA) GASTO
	FROM HTA_CLIENTE C, HTA_RESERVA_HABITACION RH
	WHERE RH.NUMDOCCLIENTE = C.NUMDOC
		AND RH.TIPODOCCLIENTE = C.TIPODOC
		AND (TRUNC(SYSDATE)-TRUNC(DIAENTRADA))<365
	GROUP BY C.TIPODOC, C.NUMDOC, C.NOMBRE) C
WHERE C.GASTO > 15000000;

-- Buenos clientes 

SELECT
FROM (
	(SELECT C1.TIPODOC, C1.NUMDOC, C1.NOMBRE
	FROM 
		(SELECT C.TIPODOC, C.NUMDOC, C.NOMBRE, SUM(TRUNC(RH.DIASALIDA)-TRUNC(RH.DIAENTRADA)) dias
		FROM HTA_CLIENTE C, HTA_RESERVA_HABITACION RH
		WHERE RH.NUMDOCCLIENTE = C.NUMDOC
			AND RH.TIPODOCCLIENTE = C.TIPODOC
		GROUP BY C.TIPODOC,C.NUMDOC, C.NOMBRE) C1
	WHERE dias > 14)
	UNION
	(SELECT C.TIPODOC, C.NUMDOC, C.NOMBRE
	FROM 
		(SELECT C.TIPODOC, C.NUMDOC, C.NOMBRE, SUM(RH.CUENTA) GASTO
		FROM HTA_CLIENTE C, HTA_RESERVA_HABITACION RH
		WHERE RH.NUMDOCCLIENTE = C.NUMDOC
			AND RH.TIPODOCCLIENTE = C.TIPODOC
			AND (TRUNC(SYSDATE)-TRUNC(DIAENTRADA))<365
		GROUP BY C.TIPODOC, C.NUMDOC, C.NOMBRE) C
	WHERE C.GASTO > 15000000))
	
-- RF8 Encontrar servicios con baja demanda

-- Escoger y agrupar por semanas del último año

SELECT S.ID, TRUNC(RS.FECHAINICIO,'IW') WEEK, COUNT(RS.IDSERVICIO) RESERVAS
    FROM HTA_SERVICIO S, HTA_RESERVA_DE_SERVICIO RS
    WHERE S.ID = RS.IDSERVICIO
        AND (TRUNC(SYSDATE)-TRUNC(RS.FECHAINICIO))<365
    GROUP BY S.ID, TRUNC(RS.FECHAINICIO,'IW')
	
-- Servicios con baja demanda 

SELECT ID
FROM(
SELECT A.ID, AVG(RESERVAS) PROM_SEMANAL
FROM
    (SELECT S.ID, TRUNC(RS.FECHAINICIO,'IW') WEEK, COUNT(RS.IDSERVICIO) RESERVAS
    FROM HTA_SERVICIO S, HTA_RESERVA_DE_SERVICIO RS
    WHERE S.ID = RS.IDSERVICIO
        AND (TRUNC(SYSDATE)-TRUNC(RS.FECHAINICIO))<365
    GROUP BY S.ID, TRUNC(RS.FECHAINICIO,'IW'))A
GROUP BY A.ID)
WHERE PROM_SEMANAL < 3;