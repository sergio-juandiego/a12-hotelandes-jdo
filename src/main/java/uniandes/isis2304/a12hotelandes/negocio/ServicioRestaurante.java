package uniandes.isis2304.a12hotelandes.negocio;

public class ServicioRestaurante implements VOServicioRestaurante {
	public Long idServicio;
	public String nombre;
	public Integer capacidad;
	public String estilo;
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
	@Override
	public Integer getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	@Override
	public String getEstilo() {
		return estilo;
	}
	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	public ServicioRestaurante() {
		super();
		this.idServicio = 0L;
		this.nombre = "";
		this.capacidad = 0;
		this.estilo = "";
	}
	public ServicioRestaurante(Long idServicio, String nombre, Integer capacidad, String estilo) {
		super();
		this.idServicio = idServicio;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.estilo = estilo;
	}
	@Override
	public String toString() {
		return "ServicioRestaurante [idServicio=" + idServicio + ", nombre=" + nombre + ", capacidad=" + capacidad
				+ ", estilo=" + estilo + "]";
	}
	
	

}
