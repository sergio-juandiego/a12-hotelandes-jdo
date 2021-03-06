package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.a12hotelandes.negocio.Servicio;
import uniandes.isis2304.a12hotelandes.negocio.VOServicio;

public class SQLServicio {
	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLServicio(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long adicionarServicio(PersistenceManager pm, Long id, Integer horaInicio, Integer horaFin, Integer capacidad, String mantenimiento, Long tipoServicio)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicio () + "(id, horainicio, horafin, capacidad, mantenimiento, tiposervicio) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, horaInicio, horaFin, capacidad, mantenimiento, tipoServicio);
        return (long) q.executeUnique();
	}
	
	public VOServicio darServicioPorId (PersistenceManager pm, long idServicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicio () + " WHERE id = ?");
		q.setResultClass(Servicio.class);
		q.setParameters(idServicio);
		return (VOServicio) q.executeUnique();
	}
	
	public long cambiarMantenimientoServicio(PersistenceManager pm, Long idServicio, String estado) {
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaServicio () + " SET mantenimiento = ? WHERE id = ?");
        q.setParameters(estado, idServicio);
        return (long) q.executeUnique();
	}
}
