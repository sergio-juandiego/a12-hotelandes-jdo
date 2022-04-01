package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLServicio {
	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLServicio(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long adicionarServicio(PersistenceManager pm, Long id, String horarioServicio, Integer capacidad, Integer costo)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicio () + "(id, horarioservicio, capacidad, costo) values (?, ?, ?, ?)");
        q.setParameters(id, horarioServicio, capacidad, costo);
        return (long) q.executeUnique();
		
	}
}
