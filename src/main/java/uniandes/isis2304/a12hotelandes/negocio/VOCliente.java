package uniandes.isis2304.a12hotelandes.negocio;

import java.sql.Date;

public interface VOCliente {

	String getNombre();

	Integer getNumDoc();

	Date getDiaEntrada();

	Date getDiaSalida();

	String toString();
	
	String getTipoDoc();

}