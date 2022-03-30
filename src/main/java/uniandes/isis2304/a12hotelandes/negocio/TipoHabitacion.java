package uniandes.isis2304.a12hotelandes.negocio;

public class TipoHabitacion implements VOTipoHabitacion {
	
	public Long id;
	public String nombre;
	public String descripcion;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "TipoHabitacion [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
	public TipoHabitacion() {
		super();
		this.id = 0L;
		this.nombre = "";
		this.descripcion = "";
	}
	
	public TipoHabitacion(Long id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
}
