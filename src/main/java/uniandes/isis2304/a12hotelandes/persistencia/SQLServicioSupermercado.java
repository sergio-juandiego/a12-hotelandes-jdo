package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLServicioSupermercado {
	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLServicioSupermercado(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long adicionarServicioSupermercado(PersistenceManager pm, Long idServicio, String nombre)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioSupermercado () + "(idservicio, nombre) values (?, ?)");
        q.setParameters(idServicio, nombre);
        return (long) q.executeUnique();
		
	}

}
