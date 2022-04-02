package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLConsumoServicio {

private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLConsumoServicio(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long agregarConsumoServicio(PersistenceManager pm,Long idFactura, Long idReserva, Long idServicio, Long idProducto, Integer cantidad)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaConsumoServicio() + "(idFactura, idReserva,idservicio,idProducto,cantidad) values (?, ?, ?, ?, ?)");
        q.setParameters(idFactura,idReserva,idServicio,idProducto,cantidad);
        return (long) q.executeUnique();
		
	}
	
}
