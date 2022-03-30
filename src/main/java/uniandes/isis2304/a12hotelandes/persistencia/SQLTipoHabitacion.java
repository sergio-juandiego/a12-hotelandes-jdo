package uniandes.isis2304.a12hotelandes.persistencia;

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.a12hotelandes.negocio.TipoHabitacion;

public class SQLTipoHabitacion {

	
private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLTipoHabitacion(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	// CRUD

	public long adicionarTipoHabitacion (PersistenceManager pm, Long idTipoHabitacion, String nombre, String descripcion) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTipoHabitacion () + "(id, nombre, descripcion) values (?, ?, ?)");
		q.setParameters(idTipoHabitacion, nombre, descripcion);
		return (long) q.executeUnique();
	} // no esta haciendo este query por alguna razón que desconozco
	
	
	public long eliminarTipoHabitacionesPorNombre (PersistenceManager pm, String nombreTipoHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoHabitacion () + " WHERE nombre = ?");
        q.setParameters(nombreTipoHabitacion);
        return (long) q.executeUnique();
	}

	
	public long eliminarTipoHabitacionPorId (PersistenceManager pm, long idTipoHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoHabitacion () + " WHERE id = ?");
        q.setParameters(idTipoHabitacion);
        return (long) q.executeUnique();
	}

	
	public TipoHabitacion darTipoHabitacionPorId (PersistenceManager pm, long idTipoHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoHabitacion () + " WHERE id = ?");
		q.setResultClass(TipoHabitacion.class);
		q.setParameters(idTipoHabitacion);
		return (TipoHabitacion) q.executeUnique();
	}

	
	public List<TipoHabitacion> darTipoHabitacionesPorNumDoc (PersistenceManager pm, Integer numDoc) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoHabitacion () + " WHERE id = ?");
		q.setResultClass(TipoHabitacion.class);
		q.setParameters(numDoc);
		return (List<TipoHabitacion>) q.executeUnique();
	}
	
	public List<TipoHabitacion> darTipoHabitacionesPorNombre (PersistenceManager pm, String nombreTipoHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoHabitacion () + " WHERE nombre = ?");
		q.setResultClass(TipoHabitacion.class);
		q.setParameters(nombreTipoHabitacion);
		return (List<TipoHabitacion>) q.executeList();
	}

	
	public List<TipoHabitacion> darTipoHabitaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoHabitacion ());
		q.setResultClass(TipoHabitacion.class);
		return (List<TipoHabitacion>) q.executeList();
	}
	
	public long cambiarDescripcionTipoHabitacion (PersistenceManager pm, Long idTipoHabitacion, String descripcion)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaTipoHabitacion () + " SET nombre = ? WHERE id = ?");
        q.setParameters(descripcion, idTipoHabitacion);
        return (long) q.executeUnique();
	}

}

