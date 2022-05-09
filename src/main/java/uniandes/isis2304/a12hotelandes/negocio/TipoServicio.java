package uniandes.isis2304.a12hotelandes.negocio;

public class TipoServicio implements VOTipoServicio {

	private Long id;
	private String nombre;
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
	public TipoServicio() {
		super();
		this.id = 0L;
		this.nombre = "";
	}
	@Override
	public String toString() {
		return "TipoServicio [id=" + id + ", nombre=" + nombre + "]";
	}
	public TipoServicio(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
}
