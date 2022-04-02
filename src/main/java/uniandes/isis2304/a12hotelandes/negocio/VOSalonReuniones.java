package uniandes.isis2304.a12hotelandes.negocio;

public interface VOSalonReuniones {

	Long getIdServicio();

	Long getIdReserva();

	Integer getHorasUso();
	
	Integer getCostoBase();

	Integer getCostoAdicional();
	
	String toString();

}