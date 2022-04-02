package uniandes.isis2304.a12hotelandes.negocio;

public class Cliente implements VOCliente {

	public String nombre;
	public String tipoDoc;
	public Integer numDoc;
	
	
	@Override
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	@Override
	public Integer getNumDoc() {
		return numDoc;
	}
	public void setNumDoc(Integer numDoc) {
		this.numDoc = numDoc;
	}
	public Cliente() {
		super();
		this.nombre = "";
		this.tipoDoc = "";
		this.numDoc = 0;
	}
	public Cliente(String nombre, String tipoDoc, Integer numDoc) {
		super();
		this.nombre = nombre;
		this.tipoDoc = tipoDoc;
		this.numDoc = numDoc;
	}
	
	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", tipoDoc=" + tipoDoc + ", numDoc=" + numDoc + "]";
	}

	

}
