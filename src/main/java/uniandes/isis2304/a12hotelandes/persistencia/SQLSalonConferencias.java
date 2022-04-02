package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLSalonConferencias {

private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLSalonConferencias(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long agregarSalonConferencias(PersistenceManager pm, Long idServicio, Long idReserva, Integer horasUso, Integer costo)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSalonConferencias() + "(idservicio, idreserva, horas_uso, costo) values (?, ?, ?, ?)");
        q.setParameters(idServicio, idReserva,horasUso, costo);
        return (long) q.executeUnique();
		
	}
	
}
