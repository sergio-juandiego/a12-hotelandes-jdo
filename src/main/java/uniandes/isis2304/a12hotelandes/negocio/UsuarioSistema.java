package uniandes.isis2304.a12hotelandes.negocio;

public class UsuarioSistema implements VOUsuarioSistema {
	public Long id;
	public String nombre;
	public Long rol;
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
	public Long getRol() {
		return rol;
	}
	public void setRol(Long rol) {
		this.rol = rol;
	}
	public UsuarioSistema() {
		super();
		this.id = 0L;
		this.nombre = "";
		this.rol = 0L;
	}
	public UsuarioSistema(Long id, String nombre, Long rol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.rol = rol;
	}
	@Override
	public String toString() {
		return "UsuarioSistema [id=" + id + ", nombre=" + nombre + ", rol=" + rol + "]";
	}
	
	
}
