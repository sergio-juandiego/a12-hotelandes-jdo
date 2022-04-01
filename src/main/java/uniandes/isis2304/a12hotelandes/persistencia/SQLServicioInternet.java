package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLServicioInternet {
	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLServicioInternet(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long adicionarServicioInternet(PersistenceManager pm, Long idServicio, Long idReserva, Integer numeroDiasUso)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioInternet () + "(idservicio, idreserva, numerodiasuso) values (?, ?, ?)");
        q.setParameters(idServicio, idReserva, numeroDiasUso);
        return (long) q.executeUnique();
		
	}
}
