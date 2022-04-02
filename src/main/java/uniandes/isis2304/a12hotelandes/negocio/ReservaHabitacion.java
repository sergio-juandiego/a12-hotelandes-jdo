package uniandes.isis2304.a12hotelandes.negocio;

import java.sql.Date;

public class ReservaHabitacion implements VOReservaHabitacion {
	
	public Long id;
	public Long idHabitacion;
	public Integer numDocCliente;
	public String tipoDocCliente;
	public Date diaEntrada;
	public Date diaSalida;
	public String completada;
	public Integer cuenta;
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Long getIdHabitacion() {
		return idHabitacion;
	}
	public void setIdHabitacion(Long idHabitacion) {
		this.idHabitacion = idHabitacion;
	}
	@Override
	public Integer getNumDocCliente() {
		return numDocCliente;
	}
	public void setNumDocCliente(Integer numDocCliente) {
		this.numDocCliente = numDocCliente;
	}
	@Override
	public String getTipoDocCliente() {
		return tipoDocCliente;
	}
	public void setTipoDocCliente(String tipoDocCliente) {
		this.tipoDocCliente = tipoDocCliente;
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
	@Override
	public String getCompletada() {
		return completada;
	}
	public void setCompletada(String completada) {
		this.completada = completada;
	}
	@Override
	public Integer getCuenta() {
		return cuenta;
	}
	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}
	public ReservaHabitacion() {
		super();
		this.id = 0L;
		this.idHabitacion = 0L;
		this.numDocCliente = 0;
		this.tipoDocCliente = "";
		this.diaEntrada = new Date (0);
		this.diaSalida = new Date (0);
		this.completada = "";
		this.cuenta = 0;
	}
	public ReservaHabitacion(Long id, Long idHabitacion, Integer numDocCliente, String tipoDocCliente, Date diaEntrada,
			Date diaSalida, String completada, Integer cuenta) {
		super();
		this.id = id;
		this.idHabitacion = idHabitacion;
		this.numDocCliente = numDocCliente;
		this.tipoDocCliente = tipoDocCliente;
		this.diaEntrada = diaEntrada;
		this.diaSalida = diaSalida;
		this.completada = completada;
		this.cuenta = cuenta;
	}
	@Override
	public String toString() {
		return "ReservaHabitacion [id=" + id + ", idHabitacion=" + idHabitacion + ", numDocCliente=" + numDocCliente
				+ ", tipoDocCliente=" + tipoDocCliente + ", diaEntrada=" + diaEntrada + ", diaSalida=" + diaSalida
				+ ", completada=" + completada + ", cuenta=" + cuenta + "]";
	}	

}
