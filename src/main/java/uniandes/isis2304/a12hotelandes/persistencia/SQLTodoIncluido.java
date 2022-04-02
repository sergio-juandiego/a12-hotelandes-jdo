package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLTodoIncluido {

private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLTodoIncluido(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long agregarTodoIncluido(PersistenceManager pm,Long idPlanDeConsumo, Long idServicioAsociado, Long idReserva, Integer costo)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTodoIncluido() + "(idPlanDeConsumo,idServicioAsociado,idReserva,costofijototal) values (?, ?, ?, ?)");
        q.setParameters(idPlanDeConsumo,idServicioAsociado,idReserva,costo);
        return (long) q.executeUnique();
		
	}
}
