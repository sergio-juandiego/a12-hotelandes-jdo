package uniandes.isis2304.a12hotelandes.persistencia;

import java.sql.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLConvencion {
	
	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLConvencion(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long agregarConvencion (PersistenceManager pm, Long id, Long idPlanDeConsumo, Integer numAsistentes, Date diaEntradaDate,
			Date diaSalidaDate, Integer cuenta, String estado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaConvencion () + "(id, idPlanDeConsumo, numAsistentes, fechaInicio, fechaFin, cuenta, estado) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, idPlanDeConsumo, numAsistentes, diaEntradaDate,
        		diaSalidaDate, cuenta, estado);
        return (long) q.executeUnique();
	}
	
}
