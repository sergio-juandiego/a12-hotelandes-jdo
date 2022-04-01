package uniandes.isis2304.a12hotelandes.negocio;

public class ServicioInternet implements VOServicioInternet {
	
	public Long idServicio;
	public Long idReserva;
	public Integer numeroDiasUso;
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
	public void setNumeroDiasUso(Integer numeroDiasUso) {
		this.numeroDiasUso = numeroDiasUso;
	}
	public ServicioInternet() {
		super();
		this.idServicio = 0L;
		this.idReserva = 0L;
		this.numeroDiasUso = 0;
	}
	public ServicioInternet(Long idServicio, Long idReserva, Integer numeroDiasUso) {
		super();
		this.idServicio = idServicio;
		this.idReserva = idReserva;
		this.numeroDiasUso = numeroDiasUso;
	}
	@Override
	public String toString() {
		return "ServicioInternet [idServicio=" + idServicio + ", idReserva=" + idReserva + ", numeroDiasUso="
				+ numeroDiasUso + "]";
	}
	
	

}
