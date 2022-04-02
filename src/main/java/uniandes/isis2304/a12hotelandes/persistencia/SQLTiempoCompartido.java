package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLTiempoCompartido {
	
private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLTiempoCompartido(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long agregarTiempoCompartido(PersistenceManager pm,Long idPlanDeConsumo, Long idServicioAsociado)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTiempoCompartido() + "(idPlanDeConsumo,idServicioAsociado) values (?, ?");
        q.setParameters(idPlanDeConsumo,idServicioAsociado);
        return (long) q.executeUnique();
		
	}
	
}
