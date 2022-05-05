package uniandes.isis2304.a12hotelandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.a12hotelandes.negocio.Habitacion;

public class SQLHabitacion {

	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLHabitacion(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long adicionarHabitacion (PersistenceManager pm, long idHabitacion, Integer costoPorNoche, Long tipoHabitacion, String aprovisionamiento) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabitacion () + "(id, costopornoche, tipohabitacion, aprovisionamiento) values (?, ?, ?, ?)");
        q.setParameters( idHabitacion,  costoPorNoche, tipoHabitacion,  aprovisionamiento);
        return (long) q.executeUnique();
	}
	
	
	public long eliminarHabitacionPorId (PersistenceManager pm, long idHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacion () + " WHERE id = ?");
        q.setParameters(idHabitacion);
        return (long) q.executeUnique();
	}

	
	public Habitacion darHabitacionPorId (PersistenceManager pm, long idHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion () + " WHERE id = ?");
		q.setResultClass(Habitacion.class);
		q.setParameters(idHabitacion);
		return (Habitacion) q.executeUnique();
	}

	
	public List<Habitacion> darHabitaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion ());
		q.setResultClass(Habitacion.class);
		return (List<Habitacion>) q.executeList();
	}
	
	public long cambiarAprovisionamientonHabitacion (PersistenceManager pm, Long idHabitacion, String aprovisionamiento)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaHabitacion () + " SET aprovisionamiento = ? WHERE id = ?");
        q.setParameters(aprovisionamiento, idHabitacion);
        return (long) q.executeUnique();
	}

	public List<Habitacion> darHabitacionesPorTipo(PersistenceManager pm, Long tipo) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion () + " WHERE TIPOHABITACION = ?");
		q.setParameters(tipo);
		q.setResultClass(Habitacion.class);
		return (List<Habitacion>) q.executeList();
	}

}
