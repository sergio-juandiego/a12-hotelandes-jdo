package uniandes.isis2304.a12hotelandes.negocio;

public class ServicioSpa implements VOServicioSpa{
	
	private Long idServicio;
	
	private String nombre;
	
	@Override
	public Long getIdServicio() {
		return idServicio;
	}
	
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	
	@Override
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ServicioSpa() {
		super();
		this.idServicio = 0L;
		this.nombre = "";
	}
	
	public ServicioSpa(Long idServicio, String nombre) {
		super();
		this.idServicio = idServicio;
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "Spa [idServicio=" + idServicio +", con el nombre: "+ nombre+ "]";
	}
	
}
