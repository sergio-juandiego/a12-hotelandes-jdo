package uniandes.isis2304.a12hotelandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.a12hotelandes.negocio.ServicioLPE;

public class SQLServicioLPE {
	
private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;
	
	public SQLServicioLPE(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long agregarLPE(PersistenceManager pm, long idServicio, long idReserva, String tipoPrenda, Integer numPrendas) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioLPE () + "(idServicio, idReserva, tipoPrenda, numeroPrendas) values (?, ?, ?,?)");
        q.setParameters(idServicio, idReserva, tipoPrenda, numPrendas);
        return (long) q.executeUnique();
	}
	
	public ServicioLPE darLPEPorId (PersistenceManager pm, long idServicio, long idReserva) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioLPE () + " WHERE idServicio = ? AND idReserva = ?");
		q.setResultClass(ServicioLPE.class);
		q.setParameters(idServicio,idReserva);
		return (ServicioLPE) q.executeUnique();
	}

	
	public List<ServicioLPE> darLPEsPorTipoNumPrendas (PersistenceManager pm, String tipoPrenda, Integer numPrendas) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioLPE () + " WHERE tipoprenda = ? and numeroprendas = ?");
		q.setResultClass(ServicioLPE.class);
		q.setParameters(tipoPrenda,numPrendas);
		return (List<ServicioLPE>) q.executeList();
	}

	
	public List<ServicioLPE> darLPEs (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioLPE ());
		q.setResultClass(ServicioLPE.class);
		return (List<ServicioLPE>) q.executeList();
	}
	
}
