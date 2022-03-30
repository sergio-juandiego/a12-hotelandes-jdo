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
	
	public VOCliente adicionarCliente(String nombreCliente, Integer numDoc, Date diaEntrada, Date diaSalida) {
		log.info ("Adicionando cliente: " + nombreCliente);
        Cliente cliente = pp.adicionarCliente ( nombreCliente,  numDoc,  diaEntrada,  diaSalida);
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
	
	
	public long eliminarClientePorId (long idCliente)
	{
        log.info ("Eliminando cliente por id: " + idCliente);
        long resp = pp.eliminarClientePorId (idCliente);
        log.info ("Eliminando cliente: " + resp);
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

	
	public List<VOCliente> darVOClientees ()
	{
		log.info ("Generando los VO de Clientees");
		List<VOCliente> voClientees = new LinkedList<VOCliente> ();
		for (Cliente cliente: pp.darClientes ())
		{
			voClientees.add (cliente);
		}
		log.info ("Generando los VO de Clientees: " + voClientees.size () + " clientees existentes");
		return voClientees;
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
