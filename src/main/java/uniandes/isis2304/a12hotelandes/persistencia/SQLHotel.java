package uniandes.isis2304.a12hotelandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.a12hotelandes.negocio.Hotel;

public class SQLHotel {
	
	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLHotel(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long adicionarHotel (PersistenceManager pm, long idHotel, String nombre, String ubicacion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHotel () + "(id, nombre, ubicacion) values (?, ?, ?)");
        q.setParameters(idHotel, nombre, ubicacion);
        return (long) q.executeUnique();
	}
	
	
	public long eliminarHotelesPorNombre (PersistenceManager pm, String nombreHotel)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel () + " WHERE nombre = ?");
        q.setParameters(nombreHotel);
        return (long) q.executeUnique();
	}

	
	public long eliminarHotelPorId (PersistenceManager pm, long idHotel)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel () + " WHERE id = ?");
        q.setParameters(idHotel);
        return (long) q.executeUnique();
	}

	
	public Hotel darHotelPorId (PersistenceManager pm, long idHotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel () + " WHERE id = ?");
		q.setResultClass(Hotel.class);
		q.setParameters(idHotel);
		return (Hotel) q.executeUnique();
	}

	
	public List<Hotel> darHotelesPorNombre (PersistenceManager pm, String nombreHotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel () + " WHERE nombre = ?");
		q.setResultClass(Hotel.class);
		q.setParameters(nombreHotel);
		return (List<Hotel>) q.executeList();
	}

	
	public List<Hotel> darHoteles (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel ());
		q.setResultClass(Hotel.class);
		return (List<Hotel>) q.executeList();
	}
	
	public long cambiarUbicacionHotel (PersistenceManager pm, Long idHotel, String ubicacion)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaHotel () + " SET ubicacion = ? WHERE id = ?");
        q.setParameters(ubicacion, idHotel);
        return (long) q.executeUnique();
	}
}
