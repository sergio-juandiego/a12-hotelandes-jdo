package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLPlanDeConsumo {

private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLPlanDeConsumo(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long agregarPlanDeConsumo(PersistenceManager pm,Long id, String tipo)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPlanDeConsumo() + "(id,tipo) values (?, ?)");
        q.setParameters(id,tipo);
        return (long) q.executeUnique();
		
	}
	
}
