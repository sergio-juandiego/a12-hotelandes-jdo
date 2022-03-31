package uniandes.isis2304.a12hotelandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.a12hotelandes.negocio.ServicioTienda;

public class SQLServicioTienda {

private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;
	
	public SQLServicioTienda(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long agregarServicioTienda(PersistenceManager pm, long idServicio, String nombre, String tipoDeTienda) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioTienda () + "(idServicio, nombre, tipoDeTienda) values (?, ?, ?)");
        q.setParameters(idServicio, nombre, tipoDeTienda);
        return (long) q.executeUnique();
	}
	
	
	public long eliminarServicioTiendaPorNombre (PersistenceManager pm, String nombreTienda)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicioTienda() + " WHERE nombre = ?");
        q.setParameters(nombreTienda);
        return (long) q.executeUnique();
	}

	
	public long eliminarServicioTiendaPorId (PersistenceManager pm, long idServicio)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicioTienda() + " WHERE id = ?");
        q.setParameters(idServicio);
        return (long) q.executeUnique();
	}

	
	public ServicioTienda darTiendaPorId (PersistenceManager pm, long idServicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioTienda () + " WHERE id = ?");
		q.setResultClass(ServicioTienda.class);
		q.setParameters(idServicio);
		return (ServicioTienda) q.executeUnique();
	}

	
	public List<ServicioTienda> darTiendasPorNombre (PersistenceManager pm, String nombreTienda) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioTienda () + " WHERE nombre = ?");
		q.setResultClass(ServicioTienda.class);
		q.setParameters(nombreTienda);
		return (List<ServicioTienda>) q.executeList();
	}

	
	public List<ServicioTienda> darTiendas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioTienda ());
		q.setResultClass(ServicioTienda.class);
		return (List<ServicioTienda>) q.executeList();
	}
	
}
