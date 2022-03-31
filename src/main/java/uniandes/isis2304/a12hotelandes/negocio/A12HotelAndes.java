/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.a12hotelandes.negocio;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;


import uniandes.isis2304.a12hotelandes.persistencia.PersistenciaA12HotelAndes;

/**
 * Clase principal del negocio
 * Sarisface todos los requerimientos funcionales del negocio
 *
 * @author Germán Bravo
 */
public class A12HotelAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(A12HotelAndes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaA12HotelAndes pp;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public A12HotelAndes ()
	{
		pp = PersistenciaA12HotelAndes.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public A12HotelAndes (JsonObject tableConfig)
	{
		pp = PersistenciaA12HotelAndes.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}

	/* ****************************************************************
	 * 			Métodos para manejar los HOTELES
	 *****************************************************************/

	/**
	 * 
	 * @param nombre
	 * @param presupuesto
	 * @param ubicacion
	 * @return Hotel
	 */
	public Hotel adicionarHotel (String nombre, String ubicacion)
	{
        log.info ("Adicionando hotel: " + nombre);
        Hotel hotel = pp.adicionarHotel (nombre, ubicacion);
        log.info ("Adicionando hotel: " + hotel);
        return hotel;
	}
	

	/**
	 * @param nombre
	 */
	public long eliminarHotelPorNombre (String nombre)
	{
        log.info ("Eliminando hotel por nombre: " + nombre);
        long resp = pp.eliminarHotelPorNombre (nombre);
        log.info ("Eliminando hotel: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public long eliminarHotelPorId (long idHotel)
	{
        log.info ("Eliminando hotel por id: " + idHotel);
        long resp = pp.eliminarHotelPorId (idHotel);
        log.info ("Eliminando hotel: " + resp);
        return resp;
	}
	
	public long cambiarUbicacionHotel(Long idHotel, String nuevaUbicacion) {
		log.info ("Actualizando hotel por id: " + idHotel);
        long resp = pp.cambiarUbicacionHotel(idHotel, nuevaUbicacion);
        log.info ("Actualizado hotel: " + resp);
        return resp;
	}
	
	
	public List<Hotel> darHoteles ()
	{
        log.info ("Listando Hoteles");
        List<Hotel> hoteles = pp.darHoteles ();	
        log.info ("Listando Hoteles: " + hoteles.size() + " hoteles existentes");
        return hoteles;
	}

	
	public List<VOHotel> darVOHoteles ()
	{
		log.info ("Generando los VO de Hoteles");
		List<VOHotel> voHoteles = new LinkedList<VOHotel> ();
		for (Hotel hotel: pp.darHoteles ())
		{
			voHoteles.add (hotel);
		}
		log.info ("Generando los VO de Hoteles: " + voHoteles.size () + " hoteles existentes");
		return voHoteles;
	}

	/* ****************************************************************
	 * 			Métodos para manejar CLIENTE
	 *****************************************************************/
	
	public Cliente adicionarCliente(String nombreCliente, String tipoDoc, Integer numDoc, Date diaEntrada, Date diaSalida) {
		log.info ("Adicionando cliente: " + nombreCliente);
        Cliente cliente = pp.adicionarCliente ( nombreCliente, tipoDoc, numDoc,  diaEntrada,  diaSalida);
        log.info ("Adicionando cliente: " + cliente);
        return cliente;
	}
	
	public long eliminarClientePorNombre (String nombre)
	{
        log.info ("Eliminando cliente por nombre: " + nombre);
        long resp = pp.eliminarClientePorNombre (nombre);
        log.info ("Eliminando cliente: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public long eliminarClientePorNumDoc (Integer numDoc)
	{
        log.info ("Eliminando cliente por nombre: " + numDoc);
        long resp = pp.eliminarClientePorNumDoc (numDoc);
        log.info ("Eliminando cliente: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public long cambiarNombreCliente(Long idCliente, String nuevaUbicacion) {
		log.info ("Actualizando cliente por id: " + idCliente);
        long resp = pp.cambiarNombreCliente(idCliente, nuevaUbicacion);
        log.info ("Actualizado cliente: " + resp);
        return resp;
	}
	
	
	public List<Cliente> darClientees ()
	{
        log.info ("Listando Clientees");
        List<Cliente> clientees = pp.darClientes ();	
        log.info ("Listando Clientees: " + clientees.size() + " clientees existentes");
        return clientees;
	}

	
	public List<VOCliente> darVOClientes ()
	{
		log.info ("Generando los VO de Clientes");
		List<VOCliente> voClientees = new LinkedList<VOCliente> ();
		for (Cliente cliente: pp.darClientes ())
		{
			voClientees.add (cliente);
		}
		log.info ("Generando los VO de Clientees: " + voClientees.size () + " clientees existentes");
		return voClientees;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar TIPO_HABITACION
	 *****************************************************************/
	
	public TipoHabitacion adicionarTipoHabitacion(String nombre, String descripcion) {
		log.info ("Adicionando tipoHabitacion: " + nombre);
        TipoHabitacion tipoHabitacion = pp.adicionarTipoHabitacion ( nombre,  descripcion);
        log.info ("Adicionando tipoHabitacion: " + tipoHabitacion);
        return tipoHabitacion;
	}
	
	public long eliminarTipoHabitacionPorNombre (String nombre)
	{
        log.info ("Eliminando tipoHabitacion por nombre: " + nombre);
        long resp = pp.eliminarTipoHabitacionPorNombre (nombre);
        log.info ("Eliminando tipoHabitacion: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public long eliminarTipoHabitacionPorId (long idTipoHabitacion)
	{
        log.info ("Eliminando tipoHabitacion por id: " + idTipoHabitacion);
        long resp = pp.eliminarTipoHabitacionPorId (idTipoHabitacion);
        log.info ("Eliminando tipoHabitacion: " + resp);
        return resp;
	}
	
	
	public long cambiarDescripcionTipoHabitacion(Long idTipoHabitacion, String nuevaDescripcion) {
		log.info ("Actualizando tipoHabitacion por id: " + idTipoHabitacion);
        long resp = pp.cambiarDescripcionTipoHabitacion(idTipoHabitacion, nuevaDescripcion);
        log.info ("Actualizado tipoHabitacion: " + resp);
        return resp;
	}
	
	
	public List<TipoHabitacion> darTipoHabitaciones ()
	{
        log.info ("Listando TipoHabitaciones");
        List<TipoHabitacion> tipoHabitaciones = pp.darTipoHabitaciones ();	
        log.info ("Listando TipoHabitaciones: " + tipoHabitaciones.size() + " tipoHabitaciones existentes");
        return tipoHabitaciones;
	}

	
	public List<VOTipoHabitacion> darVOTipoHabitacions ()
	{
		log.info ("Generando los VO de TipoHabitacions");
		List<VOTipoHabitacion> voTipoHabitaciones = new LinkedList<VOTipoHabitacion> ();
		for (TipoHabitacion tipoHabitacion: pp.darTipoHabitaciones ())
		{
			voTipoHabitaciones.add (tipoHabitacion);
		}
		log.info ("Generando los VO de TipoHabitaciones: " + voTipoHabitaciones.size () + " tipoHabitaciones existentes");
		return voTipoHabitaciones;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar HABITACION
	 *****************************************************************/
	
	public Habitacion adicionarHabitacion(Integer costoPorNoche, Integer cuenta, Long tipoHabitacion, String aprovisionamiento) {
		log.info ("Adicionando Habitacion: ");
        Habitacion Habitacion = pp.adicionarHabitacion ( costoPorNoche,  cuenta,  tipoHabitacion,  aprovisionamiento);
        log.info ("Adicionando Habitacion: " + Habitacion);
        return Habitacion;
	}
	
	
	
	public long eliminarHabitacionPorId (long idHabitacion)
	{
        log.info ("Eliminando Habitacion por id: " + idHabitacion);
        long resp = pp.eliminarHabitacionPorId (idHabitacion);
        log.info ("Eliminando Habitacion: " + resp);
        return resp;
	}
	
	
	public long cambiarAprovisionamientoHabitacion(Long idHabitacion, String nuevoAprov) {
		log.info ("Actualizando Habitacion por id: " + idHabitacion);
        long resp = pp.cambiarAprovisionamientoHabitacion(idHabitacion, nuevoAprov);
        log.info ("Actualizado Habitacion: " + resp);
        return resp;
	}
	
	
	public List<Habitacion> darHabitaciones ()
	{
        log.info ("Listando Habitaciones");
        List<Habitacion> Habitaciones = pp.darHabitaciones ();	
        log.info ("Listando Habitaciones: " + Habitaciones.size() + " Habitaciones existentes");
        return Habitaciones;
	}

	
	public List<VOHabitacion> darVOHabitacions ()
	{
		log.info ("Generando los VO de Habitacions");
		List<VOHabitacion> voHabitaciones = new LinkedList<VOHabitacion> ();
		for (Habitacion Habitacion: pp.darHabitaciones ())
		{
			voHabitaciones.add (Habitacion);
		}
		log.info ("Generando los VO de Habitaciones: " + voHabitaciones.size () + " Habitaciones existentes");
		return voHabitaciones;
	}
	

	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/


	public long [] limpiarA12HotelAndes ()
	{
        log.info ("Limpiando la BD de A12HotelAndes");
        long [] borrrados = pp.limpiarA12HotelAndes();	
        log.info ("Limpiando la BD de A12HotelAndes: Listo!");
        return borrrados;
	}

	
}
