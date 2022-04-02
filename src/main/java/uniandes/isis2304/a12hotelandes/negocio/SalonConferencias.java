package uniandes.isis2304.a12hotelandes.negocio;

public class SalonConferencias implements VOSalonConferencias {
	
	private Long idServicio;
	private Long idReserva;
	private Integer horasUso;
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
	public Integer getHorasUso() {
		return horasUso;
	}
	public void setHorasUso(Integer horasUso) {
		this.horasUso = horasUso;
	}
	public SalonConferencias(Long idServicio, Long idReserva, Integer horasUso) {
		super();
		this.idServicio = idServicio;
		this.idReserva = idReserva;
		this.horasUso = horasUso;
	}
	
	public SalonConferencias() {
		super();
		this.idServicio = 0L;
		this.idReserva = 0L;
		this.horasUso = 0;
	}
	@Override
	public String toString() {
		return "SalonConferencias [idServicio=" + idServicio + ", idReserva=" + idReserva + ", horasUso=" + horasUso
				+ "]";
	}	
	
	
}
