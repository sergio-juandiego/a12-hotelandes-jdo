package uniandes.isis2304.a12hotelandes.negocio;

public class ServicioPiscina implements VOServicioPiscina {
	public Long idServicio;
	public String nombre;
	
	@Override
	public String toString() {
		return "ServicioPiscina [idServicio=" + idServicio + ", nombre=" + nombre + "]";
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

	public ServicioPiscina() {
		super();
		this.idServicio = 0L;
		this.nombre = "";
	}
	
	public ServicioPiscina(Long idServicio, String nombre) {
		super();
		this.idServicio = idServicio;
		this.nombre = nombre;
	}
	
	

}
