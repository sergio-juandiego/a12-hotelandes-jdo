package uniandes.isis2304.a12hotelandes.negocio;

public class SalonReuniones implements VOSalonReuniones {

	private Long idServicio;
	private Long idReserva;
	private Integer horasUso;
	private Integer costoAdicional;
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
	@Override
	public Integer getCostoAdicional() {
		return costoAdicional;
	}
	public void setCostoAdicional(Integer costoAdicional) {
		this.costoAdicional = costoAdicional;
	}
	@Override
	public String toString() {
		return "SalonReuniones [idServicio=" + idServicio + ", idReserva=" + idReserva + ", horasUso=" + horasUso
				+ ", costoAdicional=" + costoAdicional + "]";
	}
	public SalonReuniones(Long idServicio, Long idReserva, Integer horasUso, Integer costoAdicional) {
		super();
		this.idServicio = idServicio;
		this.idReserva = idReserva;
		this.horasUso = horasUso;
		this.costoAdicional = costoAdicional;
	}
	public SalonReuniones() {
		super();
		this.idServicio = 0L;
		this.idReserva = 0L;
		this.horasUso = 0;
		this.costoAdicional = 0;
	}
	
	
	
    
}
