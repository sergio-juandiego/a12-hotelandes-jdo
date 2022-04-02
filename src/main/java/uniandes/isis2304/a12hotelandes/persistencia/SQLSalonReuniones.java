package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLSalonReuniones {

private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLSalonReuniones(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long agregarSalonReuniones(PersistenceManager pm, Long idServicio, Long idReserva, Integer horasUso, Integer costoAdicional)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSalonReuniones() + "(idservicio, idreserva, horas_uso, costo_adicional) values (?, ?, ?,?)");
        q.setParameters(idServicio, idReserva,horasUso,costoAdicional);
        return (long) q.executeUnique();
		
	}
}
