package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLLargaEstadia {

private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLLargaEstadia(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long agregarLargaEstadia(PersistenceManager pm,Long idPlanDeConsumo, Double descuento, Long idHotel, String tiempoEstadia)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaLargaEstadia() + "(idPlanDeConsumo,descuento, idHotel, tiempoEstadia) values (?, ?, ?,?)");
        q.setParameters(idPlanDeConsumo,descuento,idHotel,tiempoEstadia);
        return (long) q.executeUnique();
		
	}
}
