package uniandes.isis2304.a12hotelandes.negocio;

public class Hotel implements VOHotel {
	
	private Long id;
	private String nombre;
	private String ubicacion;
	
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public Hotel() {
		super();
		this.id = 0L;
		this.nombre = "";
		this.ubicacion = "";
	}
	
	public Hotel(Long id, String nombre, String ubicacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
	}
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", nombre=" + nombre + ", ubicacion=" + ubicacion + "]";
	}
	
	

}
