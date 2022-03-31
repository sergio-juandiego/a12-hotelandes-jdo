package uniandes.isis2304.a12hotelandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.a12hotelandes.negocio.RolesDeUsuario;

public class SQLRolesDeUsuario {
private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLRolesDeUsuario(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	public long adicionarRolesDeUsuario (PersistenceManager pm, long idRolesDeUsuario, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaRolesDeUsuario () + "(id, nombre) values (?, ?)");
        q.setParameters( idRolesDeUsuario,  nombre);
        return (long) q.executeUnique();
	}
	
	
	public long eliminarRolesDeUsuarioPorId (PersistenceManager pm, long idRolesDeUsuario)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRolesDeUsuario () + " WHERE id = ?");
        q.setParameters(idRolesDeUsuario);
        return (long) q.executeUnique();
	}
	
	public long eliminarRolesDeUsuarioPorNombre (PersistenceManager pm, String nombre)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRolesDeUsuario () + " WHERE nombre = ?");
        q.setParameters(nombre);
        return (long) q.executeUnique();
	}

	
	public RolesDeUsuario darRolesDeUsuarioPorId (PersistenceManager pm, long idRolesDeUsuario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRolesDeUsuario () + " WHERE id = ?");
		q.setResultClass(RolesDeUsuario.class);
		q.setParameters(idRolesDeUsuario);
		return (RolesDeUsuario) q.executeUnique();
	}

	
	public List<RolesDeUsuario> darRolesDeUsuarios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRolesDeUsuario ());
		q.setResultClass(RolesDeUsuario.class);
		return (List<RolesDeUsuario>) q.executeList();
	}


}
