package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLTipoServicio {
private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLTipoServicio(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	// CRUD

	public long adicionarTipoServicio (PersistenceManager pm, Long idTipoServicio, String nombre) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTipoServicio () + "(id, nombre) values (?, ?)");
		q.setParameters(idTipoServicio, nombre);
		return (long) q.executeUnique();
	} 
}
