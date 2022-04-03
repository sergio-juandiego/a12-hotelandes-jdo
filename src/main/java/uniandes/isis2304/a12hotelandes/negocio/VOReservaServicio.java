package uniandes.isis2304.a12hotelandes.negocio;

import java.sql.Timestamp;

public interface VOReservaServicio {

	Long getId();
	
	Long getIdReserva();

	Long getIdServicio();

	Timestamp getHoraInicio();

	Timestamp getHoraFin();

	String toString();

}