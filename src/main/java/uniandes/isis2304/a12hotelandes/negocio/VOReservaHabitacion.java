package uniandes.isis2304.a12hotelandes.negocio;

public interface VOReservaHabitacion {

	Long getId();

	Long getIdHabitacion();

	Integer getNumDocCliente();

	String getTipoDocCliente();

	Integer getPeriodo();

	String getCompletada();

	String toString();

}