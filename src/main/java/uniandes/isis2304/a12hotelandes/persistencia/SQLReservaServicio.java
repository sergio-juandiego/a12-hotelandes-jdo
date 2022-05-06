package uniandes.isis2304.a12hotelandes.persistencia;

import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLReservaServicio {
	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLReservaServicio(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long adicionarReservaServicio(PersistenceManager pm, Long id,Long idReserva, Long idServicio, Timestamp horaInicio, Timestamp horaFin)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaReservaServicio () + "(id,IDRESERVAHABITACION,idservicio,fechainicio,fechafin) values (?, ?, ?, ?,?)");
        q.setParameters(id,idReserva, idServicio, horaInicio, horaFin);
        return (long) q.executeUnique();
		
	}
}
