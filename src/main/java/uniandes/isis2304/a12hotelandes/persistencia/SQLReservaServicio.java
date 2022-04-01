package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLReservaServicio {
	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLReservaServicio(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long adicionarReservaServicio(PersistenceManager pm, Long id, Long idServicio, Integer periodo)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaReservaServicio () + "(id, horarioservicio, capacidad, costo) values (?, ?, ?, ?)");
        q.setParameters(id, idServicio, periodo);
        return (long) q.executeUnique();
		
	}
}
