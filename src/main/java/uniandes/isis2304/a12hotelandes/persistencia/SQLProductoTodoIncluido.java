package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLProductoTodoIncluido {

private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLProductoTodoIncluido(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long agregarProductoTodoIncluido(PersistenceManager pm,Long idPlanDeConsumo, Long idProductoAsociado, Double descuento)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductoTodoIncluido() + "(idPlanDeConsumo,idServicioAsociado,idReserva,costofijototal) values (?, ?, ?, ?)");
        q.setParameters(idPlanDeConsumo,idProductoAsociado,descuento);
        return (long) q.executeUnique();
		
	}
	
}
