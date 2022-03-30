package uniandes.isis2304.a12hotelandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.a12hotelandes.negocio.Hotel;
import uniandes.isis2304.a12hotelandes.negocio.HotelesDeCadenaHotelera;

public class SQLHotelesDeCadenaHotelera {
private final static String SQL = PersistenciaA12HotelAndes.SQL;
	
	private PersistenciaA12HotelAndes pp;

	public SQLHotelesDeCadenaHotelera(PersistenciaA12HotelAndes pp) {
		super();
		this.pp = pp;
	}
	
}
