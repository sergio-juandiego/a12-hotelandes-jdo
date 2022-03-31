package uniandes.isis2304.a12hotelandes.negocio;

public class ReservaHabitacion implements VOReservaHabitacion {
	
	public Long id;
	public Long idHabitacion;
	public Integer numDocCliente;
	public String tipoDocCliente;
	public Integer periodo;
	public String completada;
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
	public Integer getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	@Override
	public String getCompletada() {
		return completada;
	}
	public void setCompletada(String completada) {
		this.completada = completada;
	}
	
	public ReservaHabitacion() {
		super();
		this.id = 0L;
		this.idHabitacion = 0L;
		this.numDocCliente = 0;
		this.tipoDocCliente = "";
		this.periodo = 0;
		this.completada = "";
	}
	
	public ReservaHabitacion(Long id, Long idHabitacion, Integer numDocCliente, String tipoDocCliente, Integer periodo,
			String completada) {
		super();
		this.id = id;
		this.idHabitacion = idHabitacion;
		this.numDocCliente = numDocCliente;
		this.tipoDocCliente = tipoDocCliente;
		this.periodo = periodo;
		this.completada = completada;
	}
	@Override
	public String toString() {
		return "ReservaHabitacion [id=" + id + ", idHabitacion=" + idHabitacion + ", numDocCliente=" + numDocCliente
				+ ", tipoDocCliente=" + tipoDocCliente + ", periodo=" + periodo + ", completada=" + completada + "]";
	}
	
	

}
