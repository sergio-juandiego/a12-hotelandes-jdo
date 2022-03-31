package uniandes.isis2304.a12hotelandes.negocio;

import java.sql.Date;

public interface VOServicioLavadoPlanchadoEmbolado {

	Long getIdServicio();
	
	Long getIdReserva();
	
	String getTipoPrenda();
	
	Integer getNumPrendas();

	String toString();
	
}
