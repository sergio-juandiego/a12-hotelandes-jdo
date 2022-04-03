package uniandes.isis2304.a12hotelandes.persistencia;

import java.sql.Date;
import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLConsultas {

private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLConsultas(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public Long consultarIngresos(PersistenceManager pm, Date inicio, Date fin)
	{
		Query q = pm.newQuery(SQL, "SELECT H.ID ID_HABITACION, SUM(P.COSTO*CS.CANTIDAD) FROM " + pp.darTablaConsumoServicio() + " CS"
				+ ", "+pp.darTablaReservaHabitacion()+" RH, "+pp.darTablaHabitacion()+" H, "+pp.darTablaProducto()+" P WHERE RH.IDHABITACION = H.ID "
						+ "AND RH.ID = CS.IDRESERVA AND CS.IDPRODUCTO = P.IDPRODUCTO AND RH.FECHAINICIO > ? AND RH.FECHAFIN < ? GROUP BY H.ID;");
        q.setParameters(inicio,fin);
        return (long) q.executeUnique();
		
	}
	
	public Long consultarServiciosPopulares(PersistenceManager pm, Date inicio, Date fin)
	{
		Query q = pm.newQuery(SQL, "SELECT CS.IDSERVICIO, COUNT(DISTINCT IDFACTURA) NUM_CONSUMOS FROM "+pp.darTablaConsumoServicio()+" CS, " +
				pp.darTablaReservaHabitacion()+"RH WHERE RH.ID = CS.IDRESERVA AND RH.FECHAINICIO > ? AND RH.FECHAFIN < ? GROUP BY CS.IDSERVICIO ORDER BY COUNT(DISTINCT IDFACTURA) DESC, IDSERVICIO;");
        q.setParameters(inicio,fin);
        return (long) q.executeUnique();
		
	}
	
	public Long consultarIndiceOcupacion(PersistenceManager pm, Date inicio, Date fin)
	{
		Query q = pm.newQuery(SQL, "SELECT H.ID, COUNT(RH.ID) NUM_RESERVAS FROM "+pp.darTablaReservaHabitacion()+" RH, " + pp.darTablaHabitacion()+
				" H WHERE RH.IDHABITACION = H.ID AND RH.FECHAINICIO > ? AND RH.FECHA FIN < ? GROUP BY H.ID ORDER BY H.ID;");
        q.setParameters(inicio,fin);
        return (long) q.executeUnique();
	}
	
	public Long consultarServiciosPorCosto(PersistenceManager pm, Integer costoMenor, Integer costoMayor)
	{
		Query q = pm.newQuery(SQL, "SELECT * "+pp.darTablaServicio()+" CS WHERE COSTO > ? AND COSTO < ?;");
        q.setParameters(costoMenor,costoMayor);
        return (long) q.executeUnique();
		
	}
	
	public Long consultarServiciosPorFecha(PersistenceManager pm, Date inicio, Date fin)
	{
		Query q = pm.newQuery(SQL, "SELECT * "+pp.darTablaServicio()+" CS WHERE FECHAINICIO > ? AND FECHAFIN < ?;");
        q.setParameters(inicio,fin);
        return (long) q.executeUnique();
		
	}
	
	public Long consultarConsumoCliente(PersistenceManager pm, Date inicio, Date fin, String tipoDoc, Integer numDoc)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaConsumoServicio()+" CS, "+pp.darTablaReservaHabitacion()+" RH, "+pp.darTablaCliente()+
				"WHERE RH.ID = CS.IDRESERVA\r\n"
				+ "	AND RH.NUMDOCCLIENTE = C.NUMDOC\r\n"
				+ "	AND RH.TIPODOCCLIENTE = C.TIPODOC\r\n"
				+ "	AND RH.DIAENTRADA > 'FECHAINICIO'\r\n"
				+ "	AND RH.DIASALIDA < 'FECHASALIDA'\r\n"
				+ "	AND C.TIPODOC = 'TIPODOC'\r\n"
				+ "	AND C.NUMDOC = 'NUMDOC';;");
        q.setParameters(inicio,fin);
        return (long) q.executeUnique();
		
	}
	
}
