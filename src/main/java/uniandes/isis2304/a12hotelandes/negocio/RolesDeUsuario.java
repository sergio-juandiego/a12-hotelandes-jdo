package uniandes.isis2304.a12hotelandes.negocio;

public class RolesDeUsuario implements VORolesDeUsuario {
	
	public Long id;
	public String nombre;
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
	
	public RolesDeUsuario() {
		super();
		this.id = 0L;
		this.nombre = "";
	}
	
	public RolesDeUsuario(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "RolesDeUsuario [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
	

}
