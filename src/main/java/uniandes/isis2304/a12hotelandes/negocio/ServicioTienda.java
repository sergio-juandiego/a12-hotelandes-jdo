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
	
	@Override
	public String getTipoDeTienda() {
		return tipoDeTienda;
	}
	
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipoDeTienda(String tipoDeTienda) {
		this.tipoDeTienda = tipoDeTienda;
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
