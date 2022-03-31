package uniandes.isis2304.a12hotelandes.negocio;

public class ServicioTienda implements VOServicioTienda {
	
	private Long idServicio;
	private String nombre;
	private String tipoDeTienda;
	
	@Override
	public Long getIdServicio() {
		return idServicio;
	}
	
	@Override
	public String getNombre() {
		return nombre;
	}
	
	public String getTipoDeTienda() {
		return tipoDeTienda;
	}
	
	public ServicioTienda() {
		super();
		this.idServicio = 0L;
		this.nombre = "";
		this.tipoDeTienda = "";
	}
	
	public ServicioTienda(Long idServicio, String nombre, String tipoDeTienda) {
		super();
		this.idServicio = idServicio;
		this.nombre = nombre;
		this.tipoDeTienda = tipoDeTienda;
	}
	
	@Override
	public String toString() {
		return "Tienda [idServicio=" + idServicio +", con el nombre: "+ nombre+ " de tipo "+tipoDeTienda+"]";
	}
	
}
