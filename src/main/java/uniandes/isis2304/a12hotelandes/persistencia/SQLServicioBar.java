package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLServicioBar {
	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLServicioBar(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long adicionarServicioBar(PersistenceManager pm, Long idServicio, String nombre)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioBar () + "(idservicio, nombre) values (?, ?)");
        q.setParameters(idServicio, nombre);
        return (long) q.executeUnique();
		
	}
}
