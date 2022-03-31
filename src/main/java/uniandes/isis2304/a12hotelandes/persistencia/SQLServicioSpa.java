package uniandes.isis2304.a12hotelandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.a12hotelandes.negocio.ServicioSpa;

public class SQLServicioSpa {

private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;
	
	public SQLServicioSpa(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long agregarSpa(PersistenceManager pm, long idServicio, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioSpa () + "(idServicio, nombre, tipoDeSpa) values (?, ?, ?)");
        q.setParameters(idServicio, nombre);
        return (long) q.executeUnique();
	}
	
	
	public long eliminarSpaPorNombre (PersistenceManager pm, String nombreSpa)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicioSpa() + " WHERE nombre = ?");
        q.setParameters(nombreSpa);
        return (long) q.executeUnique();
	}

	
	public long eliminarSpaPorId (PersistenceManager pm, long idServicio)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicioSpa() + " WHERE idServicio = ?");
        q.setParameters(idServicio);
        return (long) q.executeUnique();
	}

	
	public ServicioSpa darSpaPorId (PersistenceManager pm, long idServicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioSpa () + " WHERE idServicio = ?");
		q.setResultClass(ServicioSpa.class);
		q.setParameters(idServicio);
		return (ServicioSpa) q.executeUnique();
	}

	
	public List<ServicioSpa> darSpasPorNombre (PersistenceManager pm, String nombreSpa) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioSpa () + " WHERE nombre = ?");
		q.setResultClass(ServicioSpa.class);
		q.setParameters(nombreSpa);
		return (List<ServicioSpa>) q.executeList();
	}

	
	public List<ServicioSpa> darSpas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioSpa ());
		q.setResultClass(ServicioSpa.class);
		return (List<ServicioSpa>) q.executeList();
	}
}
