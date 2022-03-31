package uniandes.isis2304.a12hotelandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.a12hotelandes.negocio.UsuarioSistema;

public class SQLUsuarioSistema {
	
	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLUsuarioSistema(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	// CRUD

	public long adicionarUsuarioSistema (PersistenceManager pm, Long idUsuarioSistema, String nombre, Long rol) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuarioSistema () + "(id, nombre, rol) values (?, ?, ?)");
		q.setParameters(idUsuarioSistema, nombre, rol);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarUsuariosSistemaPorNombre (PersistenceManager pm, String nombreUsuarioSistema)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuarioSistema () + " WHERE nombre = ?");
        q.setParameters(nombreUsuarioSistema);
        return (long) q.executeUnique();
	}

	
	public long eliminarUsuarioSistemaPorId (PersistenceManager pm, long idUsuarioSistema)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuarioSistema () + " WHERE id = ?");
        q.setParameters(idUsuarioSistema);
        return (long) q.executeUnique();
	}

	
	public UsuarioSistema darUsuarioSistemaPorId (PersistenceManager pm, long idUsuarioSistema) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuarioSistema () + " WHERE id = ?");
		q.setResultClass(UsuarioSistema.class);
		q.setParameters(idUsuarioSistema);
		return (UsuarioSistema) q.executeUnique();
	}

	
	public List<UsuarioSistema> darUsuariosSistemaPorNombre (PersistenceManager pm, String nombreUsuarioSistema) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuarioSistema () + " WHERE nombre = ?");
		q.setResultClass(UsuarioSistema.class);
		q.setParameters(nombreUsuarioSistema);
		return (List<UsuarioSistema>) q.executeList();
	}

	
	public List<UsuarioSistema> darUsuariosSistema (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuarioSistema ());
		q.setResultClass(UsuarioSistema.class);
		return (List<UsuarioSistema>) q.executeList();
	}
	
	public long cambiarNombreUsuarioSistema (PersistenceManager pm, Long idUsuarioSistema, String nombre)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaUsuarioSistema () + " SET nombre = ? WHERE id = ?");
        q.setParameters(nombre, idUsuarioSistema);
        return (long) q.executeUnique();
	}

}
