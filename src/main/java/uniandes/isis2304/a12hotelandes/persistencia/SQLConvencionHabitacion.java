package uniandes.isis2304.a12hotelandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.a12hotelandes.negocio.ConvencionHabitacion;
import uniandes.isis2304.a12hotelandes.negocio.VOConvencionHabitacion;


public class SQLConvencionHabitacion {
	
	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;
	

	public SQLConvencionHabitacion(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}


	public Long agregarConvencionHabitacion(PersistenceManager pm, Long idConvencion, Long id) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaConvencionHabitacion () + "(idConvencion, idReservaHabitacion) values (?, ?)");
        q.setParameters(idConvencion, id);
        return (Long) q.executeUnique();
	}


	public Long eliminarHabitacionesPorIdConvencion(PersistenceManager pm, Long id) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaConvencionHabitacion () + " WHERE IDCONVENCION = ?");
        q.setParameters(id);
        return (Long) q.executeUnique();
	}


	public List<VOConvencionHabitacion> darReservasPorIdConvencion(PersistenceManager pm, Long id) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaConvencionHabitacion () + " WHERE IDCONVENCION = ?");
        q.setParameters(id);
        return (List<VOConvencionHabitacion>) q.executeList();
	}

}
