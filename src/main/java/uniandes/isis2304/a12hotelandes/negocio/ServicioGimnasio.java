package uniandes.isis2304.a12hotelandes.negocio;

public class ServicioGimnasio implements VOServicioGimnasio {
	
	public Long idServicio;
	public String nombre;
	
	@Override
	public String toString() {
		return "ServicioGimnasio [idServicio=" + idServicio + ", nombre=" + nombre + "]";
	}

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

	public ServicioGimnasio() {
		super();
		this.idServicio = 0L;
		this.nombre = "";
	}
	
	public ServicioGimnasio(Long idServicio, String nombre) {
		super();
		this.idServicio = idServicio;
		this.nombre = nombre;
	}
	

}
