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

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLUtil
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaA12HotelAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaA12HotelAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLUtil (PersistenciaA12HotelAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqA12HotelAndes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	
	public long [] limpiarA12HotelAndes (PersistenceManager pm)
	{
        
		Query qHotel = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel());
        Query qCliente = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente());
        Query qTipoHabitacion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoHabitacion());
        Query qHabitacion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacion());
        Query qRolesDeUsuario = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRolesDeUsuario());
        Query qUsuarioSistema = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuarioSistema());
        Query qReservaHabitacion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReservaHabitacion());
        Query qServicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicio());
        Query qReservaDeServicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReservaServicio());
        Query qPlanDeConsumo = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPlanDeConsumo());
        
        
        
        
        long hotelEliminados = (long) qHotel.executeUnique ();
        long clienteEliminados = (long) qCliente.executeUnique ();
        long planesDeConsumoEliminados = (long) qPlanDeConsumo.executeUnique ();
        long tipoHabitacionEliminados = (long) qTipoHabitacion.executeUnique ();
        return new long[] {hotelEliminados, clienteEliminados, planesDeConsumoEliminados, tipoHabitacionEliminados};
	}
}
