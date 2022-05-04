package uniandes.isis2304.a12hotelandes.negocio;

import java.sql.Date;

public interface VOConvencion {

	Long getId();

	Long getIdPlanDeConsumo();

	Integer getNumAsistentes();

	Date getFechaInicio();

	Date getFechaFin();

	Integer getCuenta();

	String getEstado();
	
	String toString();

}