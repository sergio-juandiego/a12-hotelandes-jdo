package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLPromocionParticular {

private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLPromocionParticular(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long agregarPromocionParticular(PersistenceManager pm,Long idPlanDeConsumo, String descripcion)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocionParticular() + "(idPlanDeConsumo,descripcion) values (?, ?)");
        q.setParameters(idPlanDeConsumo,descripcion);
        return (long) q.executeUnique();
		
	}
	
}
