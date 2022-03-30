package uniandes.isis2304.a12hotelandes.negocio;

public class HotelesDeCadenaHotelera implements VOHotelesDeCadenaHotelera{
	
	private Long idHotel;
	private Long idCadena;
	public Long getIdHotel() {
		return idHotel;
	}
	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}
	public Long getIdCadena() {
		return idCadena;
	}
	public void setIdCadena(Long idCadena) {
		this.idCadena = idCadena;
	}
	
	public HotelesDeCadenaHotelera() {
		super();
		this.idHotel = 0L;
		this.idCadena = 0L;
	}
	
	public HotelesDeCadenaHotelera(Long idHotel, Long idCadena) {
		super();
		this.idHotel = idHotel;
		this.idCadena = idCadena;
	}
	@Override
	public String toString() {
		return "Hotel [idHotel=" + idHotel + " de la cadena " + idCadena+"]";
	}
	
}
