package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLServicioRestaurante {
	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLServicioRestaurante(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long adicionarServicioRestaurante(PersistenceManager pm, Long idServicio, String nombre, String estilo)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioRestaurante () + "(idservicio, nombreestilo) values (?, ?, ?)");
        q.setParameters(idServicio, nombre, estilo);
        return (long) q.executeUnique();
		
	}

}
