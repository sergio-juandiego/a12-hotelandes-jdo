package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


public class SQLConvencionServicio {
	
	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;
	

	public SQLConvencionServicio(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}


	public Long agregarConvencionServicio(PersistenceManager pm, Long idConvencion, Long id) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaConvencionServicio () + "(idConvencion, idReservaServicio) values (?, ?)");
        q.setParameters(idConvencion, id);
        return (Long) q.executeUnique();
	}


	public Long eliminarServiciosPorIdConvencion(PersistenceManager pm, Long id) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaConvencionServicio () + " WHERE IDCONVENCION = ?");
        q.setParameters(id);
        return (Long) q.executeUnique();
	}

}
