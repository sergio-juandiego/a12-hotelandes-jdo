package uniandes.isis2304.a12hotelandes.negocio;

public class ServicioBar implements VOServicioBar {
	public Long idServicio;
	public String nombre;
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
	public ServicioBar() {
		super();
		this.idServicio = 0L;
		this.nombre = "";
	}
	public ServicioBar(Long idServicio, String nombre) {
		super();
		this.idServicio = idServicio;
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "ServicioBar [idServicio=" + idServicio + ", nombre=" + nombre + "]";
	}
	
	
}
