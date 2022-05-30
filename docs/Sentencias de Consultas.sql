-- ITERACION 2 --

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

-- ITERACION 3 --

-- RF6 Analizar operacion de hotelandes

-- TIPO HABITACION

-- Por semana 
-- 	maximos
SELECT *
FROM
	(SELECT T.NOMBRE, to_number(to_char(to_date(HR.DIAENTRADA),'WW')) SEMANA, COUNT(H.TIPOHABITACION) NUM_USOS
	FROM HTA_RESERVA_HABITACION HR, HTA_HABITACION H, HTA_TIPO_HABITACION T
	WHERE H.TIPOHABITACION = 'TIPOHAB'
		AND T.ID = H.TIPOHABITACION
	GROUP BY T.NOMBRE, to_number(to_char(to_date(HR.DIAENTRADA),'WW'))
	ORDER BY NUM_USOS DESC)
FETCH NEXT 3 ROWS ONLY;
	
-- 	minimos
SELECT *
FROM
	(SELECT T.NOMBRE, to_number(to_char(to_date(HR.DIAENTRADA),'WW')) SEMANA, COUNT(H.TIPOHABITACION) NUM_USOS
	FROM HTA_RESERVA_HABITACION HR, HTA_HABITACION H, HTA_TIPO_HABITACION T
	WHERE H.TIPOHABITACION = 'TIPOHAB'
		AND T.ID = H.TIPOHABITACION
	GROUP BY T.NOMBRE, to_number(to_char(to_date(HR.DIAENTRADA),'WW'))
	ORDER BY NUM_USOS ASC)
FETCH NEXT 3 ROWS ONLY;

-- Por mes
-- 	maximos
SELECT *
FROM
	(SELECT T.NOMBRE, EXTRACT(MONTH FROM RH.DIAENTRADA) MES , COUNT(H.TIPOHABITACION) NUM_USOS
	FROM HTA_RESERVA_HABITACION RH, HTA_HABITACION H, HTA_TIPO_HABITACION T
	WHERE H.TIPOHABITACION = 'TIPOHAB'
		AND H.TIPOHABITACION = T.ID
	GROUP BY T.NOMBRE, EXTRACT(MONTH FROM RH.DIAENTRADA)
	ORDER BY NUM_USOS DESC)
FETCH NEXT 3 ROWS ONLY;

-- 	minimos
SELECT *
FROM
	(SELECT T.NOMBRE, EXTRACT(MONTH FROM RH.DIAENTRADA) MES , COUNT(H.TIPOHABITACION) NUM_USOS
	FROM HTA_RESERVA_HABITACION RH, HTA_HABITACION H, HTA_TIPO_HABITACION T
	WHERE H.TIPOHABITACION = 'TIPOHAB'
		AND H.TIPOHABITACION = T.ID
	GROUP BY T.NOMBRE, EXTRACT(MONTH FROM RH.DIAENTRADA)
	ORDER BY NUM_USOS ASC)
FETCH NEXT 3 ROWS ONLY;

-- TIPO SERVICIO

-- Por semana 
-- 	maximos
SELECT * 
FROM (
	SELECT T.NOMBRE, to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW')) SEMANA, COUNT(S.TIPOSERVICIO) NUM_USOS
	FROM HTA_RESERVA_DE_SERVICIO RS, HTA_SERVICIO S, HTA_TIPO_SERVICIO T
	WHERE S.TIPOSERVICIO = T.ID
		AND RS.IDSERVICIO = S.ID
		AND T.ID = 'TIPOSERVICIO'
	GROUP BY T.NOMBRE, to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW'))
	ORDER BY NUM_USOS DESC)
FETCH NEXT 3 ROWS ONLY;

-- minimos
SELECT * 
FROM (
	SELECT T.NOMBRE, to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW')) SEMANA, COUNT(S.TIPOSERVICIO) NUM_USOS
	FROM HTA_RESERVA_DE_SERVICIO RS, HTA_SERVICIO S, HTA_TIPO_SERVICIO T
	WHERE S.TIPOSERVICIO = T.ID
		AND RS.IDSERVICIO = S.ID
		AND T.ID = 'TIPOSERVICIO'
	GROUP BY T.NOMBRE, to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW'))
	ORDER BY NUM_USOS ASC)
FETCH NEXT 3 ROWS ONLY;

--Por mes
-- maximos
SELECT * 
FROM (
	SELECT T.NOMBRE, EXTRACT(MONTH FROM to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY'))) MES, COUNT(S.TIPOSERVICIO) NUM_USOS
	FROM HTA_RESERVA_DE_SERVICIO RS, HTA_SERVICIO S, HTA_TIPO_SERVICIO T
	WHERE S.TIPOSERVICIO = T.ID
		AND RS.IDSERVICIO = S.ID
		AND T.ID = 'TIPOSERVICIO'
	GROUP BY T.NOMBRE, EXTRACT(MONTH FROM to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')))
	ORDER BY NUM_USOS DESC)
FETCH NEXT 3 ROWS ONLY;

-- minimos
SELECT * 
FROM (
	SELECT T.NOMBRE, EXTRACT(MONTH FROM to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY'))) MES, COUNT(S.TIPOSERVICIO) NUM_USOS
	FROM HTA_RESERVA_DE_SERVICIO RS, HTA_SERVICIO S, HTA_TIPO_SERVICIO T
	WHERE S.TIPOSERVICIO = T.ID
		AND RS.IDSERVICIO = S.ID
		AND T.ID = 'TIPOSERVICIO'
	GROUP BY T.NOMBRE, EXTRACT(MONTH FROM to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')))
	ORDER BY NUM_USOS ASC)
FETCH NEXT 3 ROWS ONLY;


-- RFC7 Encontrar los buenos clientes

-- Estadías mayores a 2 semanas
SELECT C1.TIPODOC, C1.NUMDOC, C1.NOMBRE
FROM 
	(SELECT C.TIPODOC, C.NUMDOC, C.NOMBRE, SUM(TRUNC(RH.DIASALIDA)-TRUNC(RH.DIAENTRADA)) dias
	FROM HTA_CLIENTE C, HTA_RESERVA_HABITACION RH
	WHERE RH.NUMDOCCLIENTE = C.NUMDOC
		AND RH.TIPODOCCLIENTE = C.TIPODOC
		AND (TRUNC(SYSDATE)-(DIAENTRADA))<365
	GROUP BY C.TIPODOC,C.NUMDOC, C.NOMBRE) C1
WHERE dias > 14

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

SELECT *
FROM (
	(SELECT C1.TIPODOC, C1.NUMDOC, C1.NOMBRE
	FROM 
		(SELECT C.TIPODOC, C.NUMDOC, C.NOMBRE, SUM(TRUNC(RH.DIASALIDA)-TRUNC(RH.DIAENTRADA)) dias
		FROM HTA_CLIENTE C, HTA_RESERVA_HABITACION RH
		WHERE RH.NUMDOCCLIENTE = C.NUMDOC
			AND RH.TIPODOCCLIENTE = C.TIPODOC
			AND (TRUNC(SYSDATE)-(DIAENTRADA))<365
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
	WHERE C.GASTO > 15000000));
	
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

-- ITERACION 4 --

-- RFC 9 Consultar consumo en HotelAndes

-- Consumo del servicio especificado entre el rango de fechas

SELECT C.TIPODOC, C.NUMDOC, C.NOMBRE, RH.DIAENTRADA, COUNT(CS.IDRESERVA) NUM_RESERVAS
FROM HTA_CONSUMO_SERVICIO CS, HTA_CLIENTE C, HTA_RESERVA_HABITACION RH
WHERE CS.IDRESERVA = RH.ID
	AND RH.NUMDOCCLIENTE = C.NUMDOC
	AND RH.TIPODOCCLIENTE = C.TIPODOC
	AND RH.DIAENTRADA > ('FECHAINICIAL')
	AND RH.DIASALIDA < ('FECHAFINAL')
	AND CS.IDSERVICIO = ('ID')
GROUP BY C.TIPODOC, C.NUMDOC, C.NOMBRE, RH.DIAENTRADA

-- FILTRADO
SELECT P.TIPODOC, P.NUMDOC, P.NOMBRE
FROM(
	SELECT C.TIPODOC, C.NUMDOC, C.NOMBRE, RH.DIAENTRADA, COUNT(CS.IDRESERVA) NUM_RESERVAS
	FROM HTA_CONSUMO_SERVICIO CS, HTA_CLIENTE C, HTA_RESERVA_HABITACION RH	
	WHERE CS.IDRESERVA = RH.ID
		AND RH.NUMDOCCLIENTE = C.NUMDOC
		AND RH.TIPODOCCLIENTE = C.TIPODOC
		AND RH.DIAENTRADA > ('FECHAINICIAL')
		AND RH.DIASALIDA < ('FECHAFINAL')
		AND CS.IDSERVICIO = ('ID')
	GROUP BY C.TIPODOC, C.NUMDOC, C.NOMBRE, RH.DIAENTRADA) P

WHERE P.TIPODOC = ('TD')
WHERE P.NUMDOC = ('NUM')
WHERE P.NOMBRE = ('NOM')
WHERE P.DIAENTRADA = ('DIA')
WHERE NUM_RESERVAS = ('NUM')

AND P.TIPODOC = ('TD')
AND P.NUMDOC = ('NUM')
AND P.NOMBRE = ('NOM')
AND P.DIAENTRADA = ('DIA')
AND NUM_RESERVAS = ('NUM')

ORDER BY P.NUMDOC ASC
ORDER BY P.TIPODOC
ORDER BY P.NOMBRE
ORDER BY NUM_RESERVAS
ORDER BY P.DIAENTRADA

-- RFC 10 Clientes que no consumieron un servicio en un rango de fechas

SELECT DISTINCT P.TIPODOC, P.NUMDOC, P.NOMBRE
FROM (
	SELECT C.TIPODOC, C.NUMDOC, C.NOMBRE, RH.DIAENTRADA, RH.DIASALIDA
	FROM HTA_CLIENTE C, HTA_RESERVA_HABITACION RH
	WHERE RH.NUMDOCCLIENTE = C.NUMDOC
		AND RH.TIPODOCCLIENTE = C.TIPODOC
		AND (C.TIPODOC, C.NUMDOC, C.NOMBRE, RH.DIAENTRADA, RH.DIASALIDA) NOT IN (
		SELECT C.TIPODOC, C.NUMDOC, C.NOMBRE, RH.DIAENTRADA, RH.DIASALIDA 
		FROM HTA_CONSUMO_SERVICIO CS, HTA_CLIENTE C, HTA_RESERVA_HABITACION RH	
		WHERE CS.IDRESERVA = RH.ID
			AND RH.NUMDOCCLIENTE = C.NUMDOC
			AND RH.TIPODOCCLIENTE = C.TIPODOC
			AND RH.DIAENTRADA > ('DIAINICIO')
			AND RH.DIASALIDA < ('DIAFIN')
			AND CS.IDSERVICIO = 55
		GROUP BY C.TIPODOC, C.NUMDOC, C.NOMBRE, RH.DIAENTRADA,RH.DIASALIDA)) P

WHERE P.TIPODOC = ('TD')
WHERE P.NUMDOC = ('NUM')
WHERE P.NOMBRE = ('NOM')

AND P.TIPODOC = ('TD')
AND P.NUMDOC = ('NUM')
AND P.NOMBRE = ('NOM')

ORDER BY P.NUMDOC ASC
ORDER BY P.TIPODOC
ORDER BY P.NOMBRE
ORDER BY P.DIAENTRADA

-- Alojados durante el periodo 

AND P.DIASALIDA < ('DIAFIN')
AND P.DIAENTRADA > ('DIAINICIO')

-- NO alojados durante el periodo

AND (P.DIAENTRADA > ('DIAFIN') OR P.DIASALIDA < ('DIAINICIO'))


-- RFC 11 Consultar funcionamiento

-- Servicio más consumido

-- Reservas por semana 

SELECT to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW')) SEMANA, S.ID, COUNT(S.ID) NUM_RESERVAS
    FROM  HTA_RESERVA_DE_SERVICIO RS, HTA_SERVICIO S
    WHERE S.ID = RS.IDSERVICIO
    GROUP BY S.ID, to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW'))
ORDER BY SEMANA;

-- Consumos por semana

SELECT to_number(to_char(to_date(RH.DIAENTRADA),'WW')) SEMANA, S.ID, COUNT(S.ID) NUM_CONSUMOS
    FROM  HTA_CONSUMO_SERVICIO CS, HTA_SERVICIO S, HTA_RESERVA_HABITACION RH
    WHERE S.ID = CS.IDSERVICIO
        AND CS.IDRESERVA = RH.ID
    GROUP BY S.ID, to_number(to_char(to_date(RH.DIAENTRADA),'WW'))
ORDER BY SEMANA;
	
-- Suma entre consumos y reservas escogiendo el mayor por semana
SELECT SEMANA, ID ID_MAYOR, NUM_RESERVAS
FROM
    (SELECT ROW_NUMBER() OVER
    (PARTITION BY SEMANA  ORDER BY NUM_RESERVAS DESC) ROW_NUM, HC.*
    FROM(
    SELECT * 
    FROM( 
        SELECT *
        FROM(
            SELECT to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW')) SEMANA, S.ID, COUNT(S.ID) NUM_RESERVAS
            FROM  HTA_RESERVA_DE_SERVICIO RS, HTA_SERVICIO S
            WHERE S.ID = RS.IDSERVICIO
            GROUP BY S.ID, to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW')))
            UNION
            (SELECT to_number(to_char(to_date(RH.DIAENTRADA),'WW')) SEMANA, S.ID, COUNT(S.ID) NUM_RESERVAS
            FROM  HTA_CONSUMO_SERVICIO CS, HTA_SERVICIO S, HTA_RESERVA_HABITACION RH
            WHERE S.ID = CS.IDSERVICIO
                AND CS.IDRESERVA = RH.ID
            GROUP BY S.ID, to_number(to_char(to_date(RH.DIAENTRADA),'WW'))))
    ORDER BY SEMANA, NUM_RESERVAS DESC)HC) 
WHERE ROW_NUM = 1

-- Suma entre consumos y reservas escogiendo el menor por semana
SELECT SEMANA, ID ID_MENOR, NUM_RESERVAS
FROM
    (SELECT ROW_NUMBER() OVER
    (PARTITION BY SEMANA  ORDER BY NUM_RESERVAS ASC) ROW_NUM, HC.*
    FROM(
    SELECT * 
    FROM( 
        SELECT *
        FROM(
            SELECT to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW')) SEMANA, S.ID, COUNT(S.ID) NUM_RESERVAS
            FROM  HTA_RESERVA_DE_SERVICIO RS, HTA_SERVICIO S
            WHERE S.ID = RS.IDSERVICIO
            GROUP BY S.ID, to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW')))
            UNION
            (SELECT to_number(to_char(to_date(RH.DIAENTRADA),'WW')) SEMANA, S.ID, COUNT(S.ID) NUM_RESERVAS
            FROM  HTA_CONSUMO_SERVICIO CS, HTA_SERVICIO S, HTA_RESERVA_HABITACION RH
            WHERE S.ID = CS.IDSERVICIO
                AND CS.IDRESERVA = RH.ID
            GROUP BY S.ID, to_number(to_char(to_date(RH.DIAENTRADA),'WW'))))
    ORDER BY SEMANA, NUM_RESERVAS ASC)HC) 
WHERE ROW_NUM = 1

-- Servicio mayor y menor por semana

SELECT A.SEMANA, ID_MENOR, A.NUM_RESERVAS NUM_RES_MENOR, ID_MAYOR, B.NUM_RESERVAS NUM_RES_MAYOR
FROM
(SELECT SEMANA, ID ID_MENOR, NUM_RESERVAS
FROM
    (SELECT ROW_NUMBER() OVER
    (PARTITION BY SEMANA  ORDER BY NUM_RESERVAS ASC) ROW_NUM, HC.*
    FROM(
    SELECT * 
    FROM( 
        SELECT *
        FROM(
            SELECT to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW')) SEMANA, S.ID, COUNT(S.ID) NUM_RESERVAS
            FROM  HTA_RESERVA_DE_SERVICIO RS, HTA_SERVICIO S
            WHERE S.ID = RS.IDSERVICIO
            GROUP BY S.ID, to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW')))
            UNION
            (SELECT to_number(to_char(to_date(RH.DIAENTRADA),'WW')) SEMANA, S.ID, COUNT(S.ID) NUM_RESERVAS
            FROM  HTA_CONSUMO_SERVICIO CS, HTA_SERVICIO S, HTA_RESERVA_HABITACION RH
            WHERE S.ID = CS.IDSERVICIO
                AND CS.IDRESERVA = RH.ID
            GROUP BY S.ID, to_number(to_char(to_date(RH.DIAENTRADA),'WW'))))
    ORDER BY SEMANA, NUM_RESERVAS ASC)HC) 
WHERE ROW_NUM = 1)A
FULL JOIN 
(SELECT SEMANA, ID ID_MAYOR, NUM_RESERVAS
FROM
    (SELECT ROW_NUMBER() OVER
    (PARTITION BY SEMANA  ORDER BY NUM_RESERVAS DESC) ROW_NUM, HC.*
    FROM(
    SELECT * 
    FROM( 
        SELECT *
        FROM(
            SELECT to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW')) SEMANA, S.ID, COUNT(S.ID) NUM_RESERVAS
            FROM  HTA_RESERVA_DE_SERVICIO RS, HTA_SERVICIO S
            WHERE S.ID = RS.IDSERVICIO
            GROUP BY S.ID, to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW')))
            UNION
            (SELECT to_number(to_char(to_date(RH.DIAENTRADA),'WW')) SEMANA, S.ID, COUNT(S.ID) NUM_RESERVAS
            FROM  HTA_CONSUMO_SERVICIO CS, HTA_SERVICIO S, HTA_RESERVA_HABITACION RH
            WHERE S.ID = CS.IDSERVICIO
                AND CS.IDRESERVA = RH.ID
            GROUP BY S.ID, to_number(to_char(to_date(RH.DIAENTRADA),'WW'))))
    ORDER BY SEMANA, NUM_RESERVAS DESC)HC) 
WHERE ROW_NUM = 1)B
ON A.SEMANA = B.SEMANA

-- Habitaciones más solicitadas por semana

SELECT SEMANA, IDHABITACION, NUM_RESERVAS
FROM
(SELECT ROW_NUMBER() OVER
    (PARTITION BY SEMANA  ORDER BY NUM_RESERVAS DESC) ROW_NUM, HC.*
    FROM
(SELECT to_number(to_char(to_date(RH.DIAENTRADA),'WW')) SEMANA, IDHABITACION, COUNT(IDHABITACION) NUM_RESERVAS
FROM HTA_RESERVA_HABITACION RH
GROUP BY to_number(to_char(to_date(RH.DIAENTRADA),'WW')), IDHABITACION
ORDER BY SEMANA, NUM_RESERVAS DESC) HC)
WHERE ROW_NUM = 1

-- Habitaciones menos solicitadas por semana

SELECT SEMANA, IDHABITACION, NUM_RESERVAS
FROM
(SELECT ROW_NUMBER() OVER
    (PARTITION BY SEMANA  ORDER BY NUM_RESERVAS ASC) ROW_NUM, HC.*
    FROM
(SELECT to_number(to_char(to_date(RH.DIAENTRADA),'WW')) SEMANA, IDHABITACION, COUNT(IDHABITACION) NUM_RESERVAS
FROM HTA_RESERVA_HABITACION RH
GROUP BY to_number(to_char(to_date(RH.DIAENTRADA),'WW')), IDHABITACION
ORDER BY SEMANA, NUM_RESERVAS ASC) HC)
WHERE ROW_NUM = 1

--Todo junto

SELECT E.SEMANA, ID_SER_MENOR, NUM_SER_MENOR, ID_SER_MAYOR, NUM_SER_MAYOR, ID_HAB_MENOR, NUM_RES_MENOR, ID_HAB_MAYOR, NUM_RES_MAYOR
FROM 
	(SELECT A.SEMANA, ID_MENOR ID_SER_MENOR, A.NUM_RESERVAS NUM_SER_MENOR, ID_MAYOR ID_SER_MAYOR, B.NUM_RESERVAS NUM_SER_MAYOR
	FROM
		(SELECT SEMANA, ID ID_MENOR, NUM_RESERVAS
		FROM
		(SELECT ROW_NUMBER() OVER
		(PARTITION BY SEMANA  ORDER BY NUM_RESERVAS ASC) ROW_NUM, HC.*
		FROM(
			SELECT * 
			FROM( 
				SELECT *
				FROM(
					SELECT to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW')) SEMANA, S.ID, COUNT(S.ID) NUM_RESERVAS
					FROM  HTA_RESERVA_DE_SERVICIO RS, HTA_SERVICIO S
					WHERE S.ID = RS.IDSERVICIO
					GROUP BY S.ID, to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW')))
				UNION
					(SELECT to_number(to_char(to_date(RH.DIAENTRADA),'WW')) SEMANA, S.ID, COUNT(S.ID) NUM_RESERVAS
					FROM  HTA_CONSUMO_SERVICIO CS, HTA_SERVICIO S, HTA_RESERVA_HABITACION RH
					WHERE S.ID = CS.IDSERVICIO
					AND CS.IDRESERVA = RH.ID
					GROUP BY S.ID, to_number(to_char(to_date(RH.DIAENTRADA),'WW'))))
				ORDER BY SEMANA, NUM_RESERVAS ASC)HC) 
			WHERE ROW_NUM = 1)A
	FULL JOIN 
	(SELECT SEMANA, ID ID_MAYOR, NUM_RESERVAS
	FROM
		(SELECT ROW_NUMBER() OVER
		(PARTITION BY SEMANA  ORDER BY NUM_RESERVAS DESC) ROW_NUM, HC.*
		FROM(
			SELECT * 
		FROM( 
			SELECT *
			FROM(
				SELECT to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW')) SEMANA, S.ID, COUNT(S.ID) NUM_RESERVAS
				FROM  HTA_RESERVA_DE_SERVICIO RS, HTA_SERVICIO S
				WHERE S.ID = RS.IDSERVICIO
				GROUP BY S.ID, to_number(to_char(to_date(to_char(cast(RS.FECHAINICIO as date),'DD-MM-YYYY')),'WW')))
            UNION
				(SELECT to_number(to_char(to_date(RH.DIAENTRADA),'WW')) SEMANA, S.ID, COUNT(S.ID) NUM_RESERVAS
				FROM  HTA_CONSUMO_SERVICIO CS, HTA_SERVICIO S, HTA_RESERVA_HABITACION RH
				WHERE S.ID = CS.IDSERVICIO
					AND CS.IDRESERVA = RH.ID
				GROUP BY S.ID, to_number(to_char(to_date(RH.DIAENTRADA),'WW'))))
			ORDER BY SEMANA, NUM_RESERVAS DESC)HC) 
		WHERE ROW_NUM = 1)B
	ON A.SEMANA = B.SEMANA)E
FULL JOIN
	(SELECT C.SEMANA, C.IDHABITACION ID_HAB_MENOR, C.NUM_RESERVAS NUM_RES_MENOR, D.IDHABITACION ID_HAB_MAYOR, D.NUM_RESERVAS NUM_RES_MAYOR
	FROM 
		(SELECT SEMANA, IDHABITACION, NUM_RESERVAS
		FROM
		(SELECT ROW_NUMBER() OVER
		(PARTITION BY SEMANA  ORDER BY NUM_RESERVAS ASC) ROW_NUM, HC.*
		FROM
			(SELECT to_number(to_char(to_date(RH.DIAENTRADA),'WW')) SEMANA, IDHABITACION, COUNT(IDHABITACION) NUM_RESERVAS
			FROM HTA_RESERVA_HABITACION RH
			GROUP BY to_number(to_char(to_date(RH.DIAENTRADA),'WW')), IDHABITACION
			ORDER BY SEMANA, NUM_RESERVAS ASC) HC) 
		WHERE ROW_NUM = 1) C
	FULL JOIN 
		(SELECT SEMANA, IDHABITACION, NUM_RESERVAS
		FROM
		(SELECT ROW_NUMBER() OVER
		(PARTITION BY SEMANA  ORDER BY NUM_RESERVAS DESC) ROW_NUM, HC.*
		FROM
			(SELECT to_number(to_char(to_date(RH.DIAENTRADA),'WW')) SEMANA, IDHABITACION, COUNT(IDHABITACION) NUM_RESERVAS
			FROM HTA_RESERVA_HABITACION RH
			GROUP BY to_number(to_char(to_date(RH.DIAENTRADA),'WW')), IDHABITACION
			ORDER BY SEMANA, NUM_RESERVAS DESC) HC)
		WHERE ROW_NUM = 1) D
	ON D.SEMANA = C.SEMANA) F
ON E.SEMANA = F.SEMANA
ORDER BY SEMANA;


-- RFC 12 Consultar los buenos clientes

-- Realizan estancias cada 3 meses

SELECT C.TIPODOC, C.NUMDOC, C.NOMBRE, count(trunc(rh.diaentrada, 'Q')) suma_visitas_trimestrales
FROM HTA_CLIENTE C, HTA_RESERVA_HABITACION RH
WHERE RH.NUMDOCCLIENTE = C.NUMDOC
    AND RH.TIPODOCCLIENTE = C.TIPODOC
    GROUP BY C.TIPODOC,C.NUMDOC, C.NOMBRE
    having count(trunc(rh.diaentrada, 'Q')) >= 3; 

--siempre consumen por lo menos un servicio costoso 
--(Entiéndase como costoso, por ejemplo, con un precio mayor a $300.000.oo). Solo tener en cuenta los servicios que si cuestan

select C.TIPODOC, C.NUMDOC, C.NOMBRE
from 
    (select p.id
    from hta_servicio p, HTA_SERVICIO_INTERNET i
    where (p.tiposervicio = 3) 
        and (p.id = i.idServicio)
        and (i.costo >= 300)
    union all
    select p.id
    from hta_servicio p, HTA_SALON_REUNIONES sr
    where (p.tiposervicio = 11) 
        and (p.id = sr.idServicio)
        and (sr.costobase+sr.costo_adicional >= 300)
    union all
    select p.id
    from hta_servicio p, HTA_SALON_CONFERENCIAS sc
    where (p.tiposervicio = 12) 
        and (p.id = sc.idServicio)
        and (sc.costo >= 300)) a,
    HTA_CLIENTE C,
    HTA_CONSUMO_SERVICIO co,
    HTA_RESERVA_HABITACION RH
WHERE RH.NUMDOCCLIENTE = C.NUMDOC
    AND RH.TIPODOCCLIENTE = C.TIPODOC
    and rh.id = co.idreserva
    and co.idservicio = a.id;


-- Aquellos que en cada estancia consumen servicios de SPA o de salones de reuniones con duración mayor a 4 horas.

select C.TIPODOC, C.NUMDOC, C.NOMBRE
from HTA_SALON_REUNIONES sr, HTA_CLIENTE C,
    HTA_CONSUMO_SERVICIO co,
    HTA_RESERVA_HABITACION RH 
where RH.NUMDOCCLIENTE = C.NUMDOC
    and RH.TIPODOCCLIENTE = C.TIPODOC
    and rh.id = co.idreserva
    and co.idservicio = sr.idservicio
    and sr.horas_uso > 4;
