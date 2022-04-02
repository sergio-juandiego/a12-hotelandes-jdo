package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLProducto {

private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLProducto(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long agregarProducto(PersistenceManager pm,Long idProducto, Long idServicio, Integer costo)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProducto() + "(idProducto,idservicio,costo) values (?, ?, ?)");
        q.setParameters(idProducto,idServicio,costo);
        return (long) q.executeUnique();
		
	}
	
}
