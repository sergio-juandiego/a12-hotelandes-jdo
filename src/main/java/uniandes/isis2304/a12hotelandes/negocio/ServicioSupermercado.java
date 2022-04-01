package uniandes.isis2304.a12hotelandes.negocio;

public class ServicioSupermercado implements VOServicioSupermercado {
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
	public ServicioSupermercado() {
		super();
		this.idServicio = 0L;
		this.nombre = "";
	}
	public ServicioSupermercado(Long idServicio, String nombre) {
		super();
		this.idServicio = idServicio;
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "ServicioSupermercado [idServicio=" + idServicio + ", nombre=" + nombre + "]";
	}
	
	
}
