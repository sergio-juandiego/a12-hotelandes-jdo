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

package uniandes.isis2304.a12hotelandes.persistencia;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.a12hotelandes.negocio.Cliente;
import uniandes.isis2304.a12hotelandes.negocio.Habitacion;
import uniandes.isis2304.a12hotelandes.negocio.Hotel;
import uniandes.isis2304.a12hotelandes.negocio.RolesDeUsuario;
import uniandes.isis2304.a12hotelandes.negocio.ServicioTienda;
import uniandes.isis2304.a12hotelandes.negocio.TipoHabitacion;

/**
 * Clase para el manejador de persistencia del proyecto Parranderos
 * Traduce la información entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para comunicarse de manera correcta
 * con la base de datos
 * Se apoya en las clases SQLHotel, SQLBebedor, SQLBebida, SQLGustan, SQLSirven, SQLTipoBebida y SQLVisitan, que son 
 * las que realizan el acceso a la base de datos
 * 
 * @author Germán Bravo
 */
public class PersistenciaA12HotelAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaA12HotelAndes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaA12HotelAndes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	 */
	private SQLUtil sqlUtil;
	
	/**
	 * Atributo para el acceso a la tabla HTA_HOTEL de la base de datos
	 */
	private SQLHotel sqlHotel;
	private SQLCliente sqlCliente;
	private SQLTipoHabitacion sqlTipoHabitacion;
	private SQLHabitacion sqlHabitacion;
	private SQLRolesDeUsuario sqlRolesDeUsuario;
	private SQLServicioTienda sqlServicioTienda;
	
	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaA12HotelAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("A12HotelAndes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("hotelandesa12");
		tablas.add ("HTA_HOTEL");
		tablas.add ("HTA_CLIENTE");
		tablas.add ("HTA_TIPO_HABITACION");
		tablas.add ("HTA_HABITACION");
		tablas.add ("HTA_ROLES_DE_USUARIO");
		tablas.add ("HTA_USUARIO_SISTEMA");
		tablas.add ("HTA_RESERVA_HABITACION");
		tablas.add ("HTA_SERVICIO");
		tablas.add ("HTA_RESERVA_DE_SERVICIO");
		tablas.add ("HTA_SERVICIO_PISCINA");
		tablas.add ("HTA_SERVICIO_GIMNASIO");
		tablas.add ("HTA_SERVICIO_INTERNET");
		tablas.add ("HTA_SERVICIO_BAR");
		tablas.add ("HTA_SERVICIO_RESTAURANTE");
		tablas.add ("HTA_SERVICIO_SUPERMERCADO");
		tablas.add ("HTA_SERVICIO_TIENDA");
		tablas.add ("HTA_SERVICIO_SPA");
		tablas.add ("HTA_SERVICIO_LAVADO_PLANCHADO_EMBOLADO");
		tablas.add ("HTA_PRESTAMO_UTENSILIOS");
		tablas.add ("HTA_SALON_REUNIONES");
		tablas.add ("HTA_SALON_CONFERENCIAS");
		tablas.add ("HTA_PRODUCTO");
		tablas.add ("HTA_CONSUMO_SERVICIO");
		tablas.add ("HTA_PLAN_DE_CONSUMO");
		tablas.add ("HTA_LARGA_ESTADIA");
		tablas.add ("HTA_TIEMPO_COMPARTIDO");
		tablas.add ("HTA_TODO_INCLUIDO");
		tablas.add ("HTA_PRODUCTOS_TODO_INCLUIDO");
		tablas.add ("HTA_PROMOCION_PARTICULAR");
}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaA12HotelAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaA12HotelAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaA12HotelAndes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaA12HotelAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaA12HotelAndes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		
		sqlHotel = new SQLHotel(this);
		sqlCliente = new SQLCliente(this);
		sqlTipoHabitacion = new SQLTipoHabitacion(this);
		sqlHabitacion = new SQLHabitacion(this);
		sqlRolesDeUsuario = new SQLRolesDeUsuario(this);
		sqlServicioTienda = new SQLServicioTienda(this);
		
		// TODO Crear todas las clases
		
		sqlUtil = new SQLUtil(this);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	public String darSeqA12HotelAndes ()
	{
		return tablas.get (0);
	}
	
	public String darTablaHotel()
	{
		return tablas.get (1);
	}

	public String darTablaCliente() 
	{
		return tablas.get(2);
	}
	
	public String darTablaTipoHabitacion() 
	{
		return tablas.get(3);
	}
	
	public String darTablaHabitacion() {
		return tablas.get(4);
	}
	
	public String darTablaRolesDeUsuario() {
		return tablas.get(5);
	}

	
	public String darTablaServicioTienda() {
		return tablas.get(15);
	}
	
	/**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}


	
	/* ****************************************************************
	 * 			Métodos para manejar los HOTELES
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla BAR
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del bar en la ciudad (Mayor que 0)
	 * @return El objeto Hotel adicionado. null si ocurre alguna Excepción
	 */
	public Hotel adicionarHotel(String nombre, String ubicacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idHotel = nextval (); // TODO en los ids de clientes y de personas hay que poner el que definimos en las reglas
            long tuplasInsertadas = sqlHotel.adicionarHotel(pm, idHotel, nombre, ubicacion);
            tx.commit();

            log.trace ("Inserción de Hotel: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Hotel (idHotel, nombre, ubicacion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarHotelPorNombre (String nombreHotel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHotel.eliminarHotelesPorNombre(pm, nombreHotel);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarHotelPorId (long idHotel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHotel.eliminarHotelPorId (pm, idHotel);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long cambiarUbicacionHotel (long idHotel, String ubicacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHotel.cambiarUbicacionHotel (pm, idHotel, ubicacion);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Hotel> darHoteles ()
	{
		return sqlHotel.darHoteles (pmf.getPersistenceManager());
	}
 
	
	public List<Hotel> darHotelesPorNombre (String nombreHotel)
	{
		return sqlHotel.darHotelesPorNombre (pmf.getPersistenceManager(), nombreHotel);
	}
 
	
	public Hotel darHotelPorId (long idHotel)
	{
		return sqlHotel.darHotelPorId (pmf.getPersistenceManager(), idHotel);
	}
 
	
	/* ****************************************************************
	 * 			Métodos para manejar los CLIENTES
	 *****************************************************************/
	

	public Cliente adicionarCliente(String nombreCliente, String tipoDoc, Integer numDoc, Date diaEntrada, Date diaSalida) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
        	tx.begin();
            long tuplasInsertadas = sqlCliente.adicionarCliente(pm, nombreCliente, tipoDoc, numDoc, diaEntrada, diaSalida);
            tx.commit();

            log.trace ("Inserción de Cliente: " + nombreCliente + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Cliente (nombreCliente,  tipoDoc,  numDoc,  diaEntrada,  diaSalida);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarClientePorNombre (String nombreCliente) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCliente.eliminarClientesPorNombre(pm, nombreCliente);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	public long eliminarClientePorNumDoc(Integer numDoc) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCliente.eliminarClientePorNumDoc (pm, numDoc);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long cambiarNombreCliente (long idCliente, String nuevoNombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCliente.cambiarNombreCliente (pm, idCliente, nuevoNombre);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Cliente> darClientes ()
	{
		return sqlCliente.darClientes (pmf.getPersistenceManager());
	}
 
	
	public List<Cliente> darClientesPorNombre (String nombreCliente)
	{
		return sqlCliente.darClientesPorNombre (pmf.getPersistenceManager(), nombreCliente);
	}
 
	
	public Cliente darClientePorId (long idCliente)
	{
		return sqlCliente.darClientePorId (pmf.getPersistenceManager(), idCliente);
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los TIPO_HABITACION
	 *****************************************************************/
	

	public TipoHabitacion adicionarTipoHabitacion(String nombre, String descripcion) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
        	tx.begin();
            long idTipoHabitacion = nextval(); 
            long tuplasInsertadas = sqlTipoHabitacion.adicionarTipoHabitacion(pm, idTipoHabitacion, nombre, descripcion);
            tx.commit();

            log.trace ("Inserción de TipoHabitacion: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new TipoHabitacion (idTipoHabitacion, nombre, descripcion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarTipoHabitacionPorNombre (String nombreTipoHabitacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlTipoHabitacion.eliminarTipoHabitacionesPorNombre(pm, nombreTipoHabitacion);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarTipoHabitacionPorId (long idTipoHabitacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlTipoHabitacion.eliminarTipoHabitacionPorId (pm, idTipoHabitacion);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	

	
	public long cambiarDescripcionTipoHabitacion (long idTipoHabitacion, String nuevaDescripcion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlTipoHabitacion.cambiarDescripcionTipoHabitacion (pm, idTipoHabitacion, nuevaDescripcion);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<TipoHabitacion> darTipoHabitaciones ()
	{
		return sqlTipoHabitacion.darTipoHabitaciones (pmf.getPersistenceManager());
	}
 
	
	public List<TipoHabitacion> darTipoHabitacionesPorNombre (String nombreTipoHabitacion)
	{
		return sqlTipoHabitacion.darTipoHabitacionesPorNombre (pmf.getPersistenceManager(), nombreTipoHabitacion);
	}
 
	
	public TipoHabitacion darTipoHabitacionPorId (long idTipoHabitacion)
	{
		return sqlTipoHabitacion.darTipoHabitacionPorId (pmf.getPersistenceManager(), idTipoHabitacion);
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los HABITACION
	 *****************************************************************/
	

	public Habitacion adicionarHabitacion(Integer costoPorNoche, Integer cuenta, Long tipoHabitacion, String aprovisionamiento) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
        	tx.begin();
            long idHabitacion = nextval(); 
            long tuplasInsertadas = sqlHabitacion.adicionarHabitacion(pm, idHabitacion, costoPorNoche,  cuenta,  tipoHabitacion, aprovisionamiento);
            tx.commit();

            log.trace ("Inserción de Habitacion de tipo: " + tipoHabitacion + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Habitacion (idHabitacion,  costoPorNoche,  cuenta,  tipoHabitacion,  aprovisionamiento);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarHabitacionPorId (long idHabitacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacion.eliminarHabitacionPorId (pm, idHabitacion);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	

	
	public long cambiarAprovisionamientoHabitacion (long idHabitacion, String nuevoAprov) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacion.cambiarAprovisionamientonHabitacion(pm, idHabitacion, nuevoAprov);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	} // TODO cambiar

	
	public List<Habitacion> darHabitaciones ()
	{
		return sqlHabitacion.darHabitaciones (pmf.getPersistenceManager());
	}
 
	
	public Habitacion darHabitacionPorId (long idHabitacion)
	{
		return sqlHabitacion.darHabitacionPorId (pmf.getPersistenceManager(), idHabitacion);
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar los ROLES_DE_USUARIO
	 *****************************************************************/
	

	public RolesDeUsuario adicionarRolesDeUsuario(String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
        	tx.begin();
            long idRolesDeUsuario = nextval(); 
            long tuplasInsertadas = sqlRolesDeUsuario.adicionarRolesDeUsuario(pm, idRolesDeUsuario, nombre);
            tx.commit();

            log.trace ("Inserción de RolesDeUsuario: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new RolesDeUsuario(idRolesDeUsuario, nombre);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarRolesDeUsuarioPorNombre (String nombreRolesDeUsuario) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlRolesDeUsuario.eliminarRolesDeUsuarioPorNombre(pm, nombreRolesDeUsuario);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarRolesDeUsuarioPorId (long idRolesDeUsuario) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlRolesDeUsuario.eliminarRolesDeUsuarioPorId (pm, idRolesDeUsuario);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	

	

	
	public List<RolesDeUsuario> darRolesDeUsuarios ()
	{
		return sqlRolesDeUsuario.darRolesDeUsuarios (pmf.getPersistenceManager());
	}

	
	public RolesDeUsuario darRolesDeUsuarioPorId (long idRolesDeUsuario)
	{
		return sqlRolesDeUsuario.darRolesDeUsuarioPorId (pmf.getPersistenceManager(), idRolesDeUsuario);
	}

	
	
	/* ****************************************************************
	 * 			Métodos para manejar el Servicio Tienda
	 *****************************************************************/
	

	public ServicioTienda agregarServicioTienda(String nombre, String tipoDeTienda) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
        	tx.begin();
            long idServicio = nextval(); 
            long tuplasInsertadas = sqlServicioTienda.agregarServicioTienda(pm, idServicio, nombre, tipoDeTienda);
            tx.commit();

            log.trace ("Inserción de tienda: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new ServicioTienda(idServicio, nombre, tipoDeTienda);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarServicioTiendaPorNombre (String nombreTienda) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicioTienda.eliminarServicioTiendaPorNombre(pm, nombreTienda);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarServicioTiendaPorId (long idServicioTienda) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicioTienda.eliminarServicioTiendaPorId (pm, idServicioTienda);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	

	

	
	public List<ServicioTienda> darTiendas ()
	{
		return sqlServicioTienda.darTiendas (pmf.getPersistenceManager());
	}

	
	public ServicioTienda darServicioTiendaPorId (long idServicioTienda)
	{
		return sqlServicioTienda.darTiendaPorId (pmf.getPersistenceManager(), idServicioTienda);
	}

	
	
	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Parranderos
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarA12HotelAndes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarA12HotelAndes (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1, -1, -1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}

 }
