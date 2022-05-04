package uniandes.isis2304.a12hotelandes.negocio;

import java.sql.Date;

public class Convencion implements VOConvencion {
	public Long id;
	public Long idPlanDeConsumo;
	public Integer numAsistentes;
	public Date fechaInicio;
	public Date fechaFin;
	public Integer cuenta;
	public String estado;
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Long getIdPlanDeConsumo() {
		return idPlanDeConsumo;
	}
	public void setIdPlanDeConsumo(Long idPlanDeConsumo) {
		this.idPlanDeConsumo = idPlanDeConsumo;
	}
	@Override
	public Integer getNumAsistentes() {
		return numAsistentes;
	}
	public void setNumAsistentes(Integer numAsistentes) {
		this.numAsistentes = numAsistentes;
	}
	@Override
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	@Override
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	@Override
	public Integer getCuenta() {
		return cuenta;
	}
	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}
	@Override
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Convencion [id=" + id + ", idPlanDeConsumo=" + idPlanDeConsumo + ", numAsistentes=" + numAsistentes
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", cuenta=" + cuenta + ", estado="
				+ estado + "]";
	}
	public Convencion() {
		super();
		this.id = 0L;
		this.idPlanDeConsumo = 0L;
		this.numAsistentes = 0;
		this.fechaInicio =  new Date (0);
		this.fechaFin =  new Date (0);
		this.cuenta = 0;
		this.estado = "";
	}
	public Convencion(Long id, Long idPlanDeConsumo, Integer numAsistentes, Date fechaInicio, Date fechaFin,
			Integer cuenta, String estado) {
		super();
		this.id = id;
		this.idPlanDeConsumo = idPlanDeConsumo;
		this.numAsistentes = numAsistentes;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.cuenta = cuenta;
		this.estado = estado;
	}
}
