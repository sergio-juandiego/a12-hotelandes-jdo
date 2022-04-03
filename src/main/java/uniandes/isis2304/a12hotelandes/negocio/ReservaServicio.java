package uniandes.isis2304.a12hotelandes.negocio;

import java.sql.Timestamp;

public class ReservaServicio implements VOReservaServicio {
	
	public Long id;
	Long idReserva;
	public Long idServicio;
	public Timestamp horaInicio;
	public Timestamp horaFin;
	
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	@Override
	public Timestamp getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Timestamp horaInicio) {
		this.horaInicio = horaInicio;
	}
	@Override
	public Timestamp getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Timestamp horaFin) {
		this.horaFin = horaFin;
	}
	@Override
	public Long getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}
	public ReservaServicio() {
		super();
		this.id = 0L;
		this.idServicio = 0L;
		this.horaInicio = new Timestamp(0);
		this.horaFin = new Timestamp(0);
	}
	public ReservaServicio(Long id, Long idServicio, Timestamp horaInicio, Timestamp horaFin) {
		super();
		this.id = id;
		this.idServicio = idServicio;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	@Override
	public String toString() {
		return "ReservaServicio [id=" + id + ", idServicio=" + idServicio + ", horaInicio=" + horaInicio + ", horaFin="
				+ horaFin + "]";
	}	

}
