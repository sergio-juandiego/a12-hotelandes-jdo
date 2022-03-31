package uniandes.isis2304.a12hotelandes.persistencia;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.a12hotelandes.negocio.Cliente;

public class SQLCliente {
	
	private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLCliente(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
	// CRUD
	

	public long adicionarCliente (PersistenceManager pm, String nombre, String tipoDoc, Integer numDoc, Date diaEntrada, Date diaSalida) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCliente () + "(nombre, numdoc, tipodoc, diaentrada, diasalida) values (?, ?, ?, ?, ?)");
		q.setParameters(nombre, numDoc, tipoDoc, diaEntrada, diaSalida);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarClientesPorNombre (PersistenceManager pm, String nombreCliente)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente () + " WHERE nombre = ?");
        q.setParameters(nombreCliente);
        return (long) q.executeUnique();
	}
	
	public long eliminarClientePorNumDoc (PersistenceManager pm, Integer numDoc)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente () + " WHERE numdoc = ?");
        q.setParameters(numDoc);
        return (long) q.executeUnique();
	}

	
	public long eliminarClientePorId (PersistenceManager pm, long idCliente)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente () + " WHERE id = ?");
        q.setParameters(idCliente);
        return (long) q.executeUnique();
	}

	
	public Cliente darClientePorId (PersistenceManager pm, long idCliente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente () + " WHERE id = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(idCliente);
		return (Cliente) q.executeUnique();
	}

	
	public List<Cliente> darClientesPorNumDoc (PersistenceManager pm, Integer numDoc) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente () + " WHERE id = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(numDoc);
		return (List<Cliente>) q.executeUnique();
	}
	
	public List<Cliente> darClientesPorNombre (PersistenceManager pm, String nombreCliente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente () + " WHERE nombre = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(nombreCliente);
		return (List<Cliente>) q.executeList();
	}

	
	public List<Cliente> darClientes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente ());
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}
	
	public long cambiarNombreCliente (PersistenceManager pm, Long idCliente, String nombre)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCliente () + " SET nombre = ? WHERE id = ?");
        q.setParameters(nombre, idCliente);
        return (long) q.executeUnique();
	}
	
}
