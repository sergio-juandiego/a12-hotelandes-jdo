package uniandes.isis2304.a12hotelandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.a12hotelandes.negocio.ReservaHabitacion;

public class SQLReservaHabitacion {

	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLReservaHabitacion(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long adicionarReservaHabitacion (PersistenceManager pm, Long idReservaHabitacion, Long idHabitacion, Integer numDocCliente, String tipoDocCliente, Integer periodo,
			String completada) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaReservaHabitacion () + "(id, idhabitacion, numdoccliente, tipodoccliente, periodo, completada) values (?, ?, ?, ?, ?, ?)");
        q.setParameters( idReservaHabitacion,  idHabitacion,  numDocCliente,  tipoDocCliente,  periodo, completada);
        return (long) q.executeUnique();
	}
	
	
	public long eliminarReservaHabitacionPorId (PersistenceManager pm, long idReservaHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReservaHabitacion () + " WHERE id = ?");
        q.setParameters(idReservaHabitacion);
        return (long) q.executeUnique();
	}

	
	public ReservaHabitacion darReservaHabitacionPorId (PersistenceManager pm, long idReservaHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReservaHabitacion () + " WHERE id = ?");
		q.setResultClass(ReservaHabitacion.class);
		q.setParameters(idReservaHabitacion);
		return (ReservaHabitacion) q.executeUnique();
	}

	
	public List<ReservaHabitacion> darReservaHabitaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReservaHabitacion ());
		q.setResultClass(ReservaHabitacion.class);
		return (List<ReservaHabitacion>) q.executeList();
	}
	
	public long cambiarCompletadaReservaHabitacion (PersistenceManager pm, Long idReservaHabitacion, String completada)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaReservaHabitacion () + " SET completada = ? WHERE id = ?");
        q.setParameters(completada, idReservaHabitacion);
        return (long) q.executeUnique();
	}

}