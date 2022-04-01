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

import org.apache.log4j.LogManager;
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
	private static Logger log = LogManager.getLogger(A12HotelAndes.class.getName());
	
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

	
	public List<VOHabitacion> darVOHabitaciones ()
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
	 * 			Métodos para manejar ROLES_DE_USUARIO
	 *****************************************************************/
	
	public RolesDeUsuario adicionarRolesDeUsuario(String nombre) {
		log.info ("Adicionando tipoHabitacion: " + nombre);
        RolesDeUsuario tipoHabitacion = pp.adicionarRolesDeUsuario (nombre);
        log.info ("Adicionando tipoHabitacion: " + tipoHabitacion);
        return tipoHabitacion;
	} // TODO CAMBIAR atributos
	
	public long eliminarRolesDeUsuarioPorNombre (String nombre)
	{
        log.info ("Eliminando tipoHabitacion por nombre: " + nombre);
        long resp = pp.eliminarRolesDeUsuarioPorNombre (nombre);
        log.info ("Eliminando tipoHabitacion: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public long eliminarRolesDeUsuarioPorId (long idRolesDeUsuario)
	{
        log.info ("Eliminando tipoHabitacion por id: " + idRolesDeUsuario);
        long resp = pp.eliminarRolesDeUsuarioPorId (idRolesDeUsuario);
        log.info ("Eliminando tipoHabitacion: " + resp);
        return resp;
	}

	
	public List<RolesDeUsuario> darRolesDeUsuarioes ()
	{
        log.info ("Listando RolesDeUsuarioes");
        List<RolesDeUsuario> tipoHabitaciones = pp.darRolesDeUsuarios ();	
        log.info ("Listando RolesDeUsuarioes: " + tipoHabitaciones.size() + " tipoHabitaciones existentes");
        return tipoHabitaciones;
	}

	
	public List<VORolesDeUsuario> darVORolesDeUsuarios ()
	{
		log.info ("Generando los VO de RolesDeUsuarios");
		List<VORolesDeUsuario> voRolesDeUsuarioes = new LinkedList<VORolesDeUsuario> ();
		for (RolesDeUsuario tipoHabitacion: pp.darRolesDeUsuarios ())
		{
			voRolesDeUsuarioes.add (tipoHabitacion);
		}
		log.info ("Generando los VO de RolesDeUsuarioes: " + voRolesDeUsuarioes.size () + " tipoHabitaciones existentes");
		return voRolesDeUsuarioes;
	}

	/* ****************************************************************
	 * 			Métodos para manejar USUARIO_SISTEMA
	 *****************************************************************/
	
	public UsuarioSistema adicionarUsuarioSistema(String nombre, Long rol) {
		log.info ("Adicionando usuarioSistema: " + nombre);
        UsuarioSistema usuarioSistema = pp.adicionarUsuarioSistema ( nombre,  rol);
        log.info ("Adicionando usuarioSistema: " + usuarioSistema);
        return usuarioSistema;
	}
	
	public long eliminarUsuarioSistemaPorNombre (String nombre)
	{
        log.info ("Eliminando usuarioSistema por nombre: " + nombre);
        long resp = pp.eliminarUsuarioSistemaPorNombre (nombre);
        log.info ("Eliminando usuarioSistema: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public long eliminarUsuarioSistemaPorId (long idUsuarioSistema)
	{
        log.info ("Eliminando usuarioSistema por id: " + idUsuarioSistema);
        long resp = pp.eliminarUsuarioSistemaPorId (idUsuarioSistema);
        log.info ("Eliminando usuarioSistema: " + resp);
        return resp;
	}
	
	
	public long cambiarNombreUsuarioSistema(Long idUsuarioSistema, String nombre) {
		log.info ("Actualizando usuarioSistema por id: " + idUsuarioSistema);
        long resp = pp.cambiarNombreUsuarioSistema(idUsuarioSistema, nombre);
        log.info ("Actualizado usuarioSistema: " + resp);
        return resp;
	}
	
	
	public List<UsuarioSistema> darUsuariosSistema()
	{
        log.info ("Listando UsuarioSistemaes");
        List<UsuarioSistema> usuarioSistemaes = pp.darUsuariosSistema ();	
        log.info ("Listando UsuarioSistemaes: " + usuarioSistemaes.size() + " usuarioSistemaes existentes");
        return usuarioSistemaes;
	}

	
	public List<VOUsuarioSistema> darVOUsuarioSistemas ()
	{
		log.info ("Generando los VO de UsuarioSistemas");
		List<VOUsuarioSistema> voUsuarioSistemaes = new LinkedList<VOUsuarioSistema> ();
		for (UsuarioSistema usuarioSistema: pp.darUsuariosSistema ())
		{
			voUsuarioSistemaes.add (usuarioSistema);
		}
		log.info ("Generando los VO de UsuarioSistemaes: " + voUsuarioSistemaes.size () + " usuarioSistemaes existentes");
		return voUsuarioSistemaes;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar RESERVA_HABITACION
	 *****************************************************************/
	
	public ReservaHabitacion adicionarReservaHabitacion(Long idHabitacion, Integer numDocCliente, String tipoDocCliente, Integer periodo,
			String completada) {
		log.info ("Adicionando ReservaHabitacion: ");
        ReservaHabitacion ReservaHabitacion = pp.adicionarReservaHabitacion ( idHabitacion,  numDocCliente,  tipoDocCliente,  periodo,
    			completada);
        log.info ("Adicionando ReservaHabitacion: " + ReservaHabitacion);
        return ReservaHabitacion;
	}
	
	
	
	public long eliminarReservaHabitacionPorId (long idReservaHabitacion)
	{
        log.info ("Eliminando ReservaHabitacion por id: " + idReservaHabitacion);
        long resp = pp.eliminarReservaHabitacionPorId (idReservaHabitacion);
        log.info ("Eliminando ReservaHabitacion: " + resp);
        return resp;
	}
	
	
	public long cambiarCompletadaReservaHabitacion(Long idReservaHabitacion, String completada) {
		log.info ("Actualizando ReservaHabitacion por id: " + idReservaHabitacion);
        long resp = pp.cambiarCompletadaReservaHabitacion(idReservaHabitacion, completada);
        log.info ("Actualizado ReservaHabitacion: " + resp);
        return resp;
	}
	
	
	public List<ReservaHabitacion> darReservaHabitaciones ()
	{
        log.info ("Listando ReservaHabitaciones");
        List<ReservaHabitacion> ReservaHabitaciones = pp.darReservaHabitaciones ();	
        log.info ("Listando ReservaHabitaciones: " + ReservaHabitaciones.size() + " ReservaHabitaciones existentes");
        return ReservaHabitaciones;
	}

	
	public List<VOReservaHabitacion> darVOReservaHabitaciones ()
	{
		log.info ("Generando los VO de ReservaHabitacions");
		List<VOReservaHabitacion> voReservaHabitaciones = new LinkedList<VOReservaHabitacion> ();
		for (ReservaHabitacion ReservaHabitacion: pp.darReservaHabitaciones ())
		{
			voReservaHabitaciones.add (ReservaHabitacion);
		}
		log.info ("Generando los VO de ReservaHabitaciones: " + voReservaHabitaciones.size () + " ReservaHabitaciones existentes");
		return voReservaHabitaciones;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar SERVICIO
	 *****************************************************************/
	public Servicio adicionarServicio(String horarioServicio, Integer capacidad, Integer costo) {
		log.info ("Adicionando Servicio: ");
        Servicio Servicio = pp.adicionarServicio (horarioServicio, capacidad, costo);
        log.info ("Adicionando Servicio: " + Servicio);
        return Servicio;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar RESERVA SERVICIO
	 *****************************************************************/
	public ReservaServicio adicionarReservaServicio(Long idServicio, Integer periodo) {
		log.info ("Adicionando Servicio: ");
        ReservaServicio ReservaServicio = pp.adicionarReservaServicio (idServicio, periodo);
        log.info ("Adicionando Servicio: " + ReservaServicio);
        return ReservaServicio;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar SERVICIO_PISCINA
	 *****************************************************************/
	public ServicioPiscina adicionarServicioPiscina(Long idServicio, String nombre) {
		log.info ("Adicionando Servicio: ");
		ServicioPiscina ServicioPiscina = pp.adicionarServicioPiscina (idServicio, nombre);
        log.info ("Adicionando Servicio: " + ServicioPiscina);
        return ServicioPiscina;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar SERVICIO_GIMNASIO
	 *****************************************************************/
	public ServicioGimnasio adicionarServicioGimnasio(Long idServicio, String nombre) {
		log.info ("Adicionando Servicio: ");
		ServicioGimnasio ServicioGimnasio = pp.adicionarServicioGimnasio (idServicio, nombre);
        log.info ("Adicionando Servicio: " + ServicioGimnasio);
        return ServicioGimnasio;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar SERVICIO_INTERNET
	 *****************************************************************/
	public ServicioInternet adicionarServicioInternet(Long idServicio, Long idReserva, Integer numeroDiasUso) {
		log.info ("Adicionando Servicio: ");
		ServicioInternet ServicioInternet = pp.adicionarServicioInternet(idServicio, idReserva, numeroDiasUso);
        log.info ("Adicionando Servicio: " + ServicioInternet);
        return ServicioInternet;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar SERVICIO_BAR
	 *****************************************************************/
	public ServicioBar adicionarServicioBar(Long idServicio, String nombre) {
		log.info ("Adicionando Servicio: ");
		ServicioBar ServicioBar = pp.adicionarServicioBar (idServicio, nombre);
        log.info ("Adicionando Servicio: " + ServicioBar);
        return ServicioBar;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar SERVICIO_Restaurante
	 *****************************************************************/
	public ServicioRestaurante adicionarServicioRestaurante(Long idServicio, String nombre, Integer capacidad, String estilo) {
		log.info ("Adicionando Servicio: ");
		ServicioRestaurante ServicioRestaurante = pp.adicionarServicioRestaurante (idServicio, nombre, capacidad, estilo);
        log.info ("Adicionando Servicio: " + ServicioRestaurante);
        return ServicioRestaurante;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar SERVICIO_SUPERMERCADO
	 *****************************************************************/
	public ServicioSupermercado adicionarServicioSupermercado(Long idServicio, String nombre) {
		log.info ("Adicionando Servicio: ");
		ServicioSupermercado ServicioSupermercado = pp.adicionarServicioSupermercado (idServicio, nombre);
        log.info ("Adicionando Servicio: " + ServicioSupermercado);
        return ServicioSupermercado;
	}
	
	/* **************************************************************** SERGIO
	 * 			Métodos para manejar Servicio Tienda
	 *****************************************************************/
	
	public ServicioTienda agregarServicioTienda(String nombre, String tipoDeTienda) {
		log.info ("Agregando ServicioTienda: " + nombre);
        ServicioTienda tienda = pp.agregarServicioTienda (nombre,tipoDeTienda);
        log.info ("Agregando Tienda: " + tienda);
        return tienda;
	} // TODO CAMBIAR atributos
	
	public long eliminarServicioTiendaPorNombre (String nombre)
	{
        log.info ("Eliminando ServicioTienda por nombre: " + nombre);
        long resp = pp.eliminarRolesDeUsuarioPorNombre (nombre);
        log.info ("Eliminando tienda: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public long eliminarServicioTiendaPorId (long idServicioTienda)
	{
        log.info ("Eliminando Servicio Tienda por id: " + idServicioTienda);
        long resp = pp.eliminarServicioTiendaPorId (idServicioTienda);
        log.info ("Eliminando servicio tienda: " + resp);
        return resp;
	}

	
	public List<ServicioTienda> darTiendas ()
	{
        log.info ("Listando Servicios Tienda");
        List<ServicioTienda> serviciosTienda = pp.darTiendas ();	
        log.info ("Listando RolesDeUsuarioes: " + serviciosTienda.size() + " tipoHabitaciones existentes");
        return serviciosTienda;
	}

	
	public List<VOServicioTienda> darVOServiciosTienda()
	{
		log.info ("Generando los VO de Servicios tienda");
		List<VOServicioTienda> voServiciosTienda = new LinkedList<VOServicioTienda> ();
		for (ServicioTienda servicioTienda: pp.darTiendas ())
		{
			voServiciosTienda.add(servicioTienda);
		}
		log.info ("Generando los VO de RolesDeUsuarioes: " + voServiciosTienda.size () + " tipoHabitaciones existentes");
		return voServiciosTienda;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar Servicio Spa
	 *****************************************************************/
	
	public ServicioSpa agregarServicioSpa(String nombre) {
		log.info ("Agregando ServicioSpa: " + nombre);
        ServicioSpa Spa = pp.agregarSpa (nombre);
        log.info ("Agregando Spa: " + Spa);
        return Spa;
	} // TODO CAMBIAR atributos
	
	public long eliminarServicioSpaPorNombre (String nombre)
	{
        log.info ("Eliminando ServicioSpa por nombre: " + nombre);
        long resp = pp.eliminarRolesDeUsuarioPorNombre (nombre);
        log.info ("Eliminando Spa: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public long eliminarServicioSpaPorId (long idServicioSpa)
	{
        log.info ("Eliminando Servicio Spa por id: " + idServicioSpa);
        long resp = pp.eliminarSpaPorId (idServicioSpa);
        log.info ("Eliminando servicio Spa: " + resp);
        return resp;
	}

	
	public List<ServicioSpa> darSpas ()
	{
        log.info ("Listando Servicios Spa");
        List<ServicioSpa> serviciosSpa = pp.darSpas ();	
        log.info ("Listando RolesDeUsuarioes: " + serviciosSpa.size() + " tipoHabitaciones existentes");
        return serviciosSpa;
	}

	
	public List<VOServicioSpa> darVOServiciosSpa()
	{
		log.info ("Generando los VO de Servicios Spa");
		List<VOServicioSpa> voServiciosSpa = new LinkedList<VOServicioSpa> ();
		for (ServicioSpa servicioSpa: pp.darSpas ())
		{
			voServiciosSpa.add(servicioSpa);
		}
		log.info ("Generando los VO de RolesDeUsuarioes: " + voServiciosSpa.size () + " tipoHabitaciones existentes");
		return voServiciosSpa;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar Servicio LPE
	 *****************************************************************/
	
	public ServicioLPE agregarServicioLPE(Long idReserva, String tipoPrenda, Integer numPrendas) {
		log.info ("Agregando ServicioLPE a la reserva: " + idReserva);
        ServicioLPE LPE = pp.agregarLPE (idReserva, tipoPrenda, numPrendas);
        log.info ("Agregando lavado planchado embolado: " + LPE);
        return LPE;
	} // TODO CAMBIAR atributos
	
	public List<ServicioLPE> darLPEs ()
	{
        log.info ("Listando Servicios Spa");
        List<ServicioLPE> serviciosLPE = pp.darLPEs ();	
        log.info ("Listando LPEs: " + serviciosLPE.size());
        return serviciosLPE;
	}

	
	public List<VOServicioLavadoPlanchadoEmbolado> darVOServiciosLPE()
	{
		log.info ("Generando los VO de Servicios Spa");
		List<VOServicioLavadoPlanchadoEmbolado> voServiciosLPE = new LinkedList<VOServicioLavadoPlanchadoEmbolado> ();
		for (ServicioLPE servicioLPE: pp.darLPEs ())
		{
			voServiciosLPE.add(servicioLPE);
		}
		log.info ("Generando los VO de LPEs: " + voServiciosLPE.size ());
		return voServiciosLPE;
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
