package uniandes.isis2304.a12hotelandes.negocio;

import java.sql.Date;

public interface VOReservaHabitacion {

	Long getId();

	Long getIdHabitacion();

	Integer getNumDocCliente();

	String getTipoDocCliente();

	Date getDiaEntrada();

	Date getDiaSalida();

	String getCompletada();

	Integer getCuenta();

	String toString();

}