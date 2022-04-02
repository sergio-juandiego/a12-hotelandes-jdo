package uniandes.isis2304.a12hotelandes.negocio;

public class ServicioInternet implements VOServicioInternet {
	
	public Long idServicio;
	public Long idReserva;
	public Integer numeroDiasUso;
	public Integer costo;
	@Override
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	@Override
	public Long getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}
	@Override
	public Integer getNumeroDiasUso() {
		return numeroDiasUso;
	}
	@Override
	public Integer getCosto() {
		return costo;
	}
	public void setCosto(Integer costo) {
		this.costo = costo;
	}
	public void setNumeroDiasUso(Integer numeroDiasUso) {
		this.numeroDiasUso = numeroDiasUso;
	}
	public ServicioInternet() {
		super();
		this.idServicio = 0L;
		this.idReserva = 0L;
		this.numeroDiasUso = 0;
		this.costo = 0;
	}
	public ServicioInternet(Long idServicio, Long idReserva, Integer numeroDiasUso) {
		super();
		this.idServicio = idServicio;
		this.idReserva = idReserva;
		this.numeroDiasUso = numeroDiasUso;
		this.costo = costo;
	}
	@Override
	public String toString() {
		return "ServicioInternet [idServicio=" + idServicio + ", idReserva=" + idReserva + ", numeroDiasUso="
				+ numeroDiasUso + ", costo=" + costo + "]";
	}	

}
