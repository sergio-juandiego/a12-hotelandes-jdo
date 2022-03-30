package uniandes.isis2304.a12hotelandes.negocio;

import java.sql.Date;

public class Cliente implements VOCliente {
	
	public Long id;
	public String nombre;
	public String tipoDoc;
	public Integer numDoc;
	public Date diaEntrada;
	public Date diaSalida;
	
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
	@Override
	public Date getDiaEntrada() {
		return diaEntrada;
	}
	public void setDiaEntrada(Date diaEntrada) {
		this.diaEntrada = diaEntrada;
	}
	@Override
	public Date getDiaSalida() {
		return diaSalida;
	}
	public void setDiaSalida(Date diaSalida) {
		this.diaSalida = diaSalida;
	}
	public Cliente() {
		super();
		this.id = 0L;
		this.nombre = "";
		this.tipoDoc = "";
		this.numDoc = 0;
		this.diaEntrada = new Date (0);
		this.diaSalida = new Date (0);
	}
	public Cliente(Long id, String nombre, String tipoDoc, Integer numDoc, Date diaEntrada, Date diaSalida) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipoDoc = tipoDoc;
		this.numDoc = numDoc;
		this.diaEntrada = diaEntrada;
		this.diaSalida = diaSalida;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", numDoc=" + numDoc + ", diaEntrada=" + diaEntrada
				+ ", diaSalida=" + diaSalida + "]";
	}
	
	

}
