package uniandes.isis2304.a12hotelandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLServicioPrestamoUtensilios {

	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLServicioPrestamoUtensilios(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long adicionarServicioPrestamosUtensilios(PersistenceManager pm, Long idServicio, Long idReserva, Integer recargoPorMalUso)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioPrestamoUtensilios() + "(idservicio, idreserva, recargoPorMalUso) values (?, ?, ?)");
        q.setParameters(idServicio, idReserva, recargoPorMalUso);
        return (long) q.executeUnique();
		
	}
	
}
