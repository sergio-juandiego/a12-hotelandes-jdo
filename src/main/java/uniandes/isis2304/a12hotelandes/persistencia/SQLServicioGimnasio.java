package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLServicioGimnasio {
	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLServicioGimnasio(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long adicionarServicioGimnasio(PersistenceManager pm, Long idServicio, String nombre)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioGimnasio () + "(idservicio, nombre) values (?, ?)");
        q.setParameters(idServicio, nombre);
        return (long) q.executeUnique();
		
	}
}
