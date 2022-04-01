package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLServicioPiscina {
	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLServicioPiscina(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long adicionarServicioPiscina(PersistenceManager pm, Long idServicio, String nombre)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioPiscina () + "(idservicio, nombre) values (?, ?)");
        q.setParameters(idServicio, nombre);
        return (long) q.executeUnique();
		
	}
}
