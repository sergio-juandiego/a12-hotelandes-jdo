package uniandes.isis2304.a12hotelandes.negocio;

public class Servicio implements VOServicio {
	public Long id;
	public Integer horaInicio;
	public Integer horaFin;
	public Integer capacidad;

	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Integer getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Integer horaInicio) {
		this.horaInicio = horaInicio;
	}
	@Override
	public Integer getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Integer horaFin) {
		this.horaFin = horaFin;
	}
	@Override
	public Integer getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}


	public Servicio(Long id, Integer horaInicio, Integer horaFin, Integer capacidad) {
		super();
		this.id = id;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.capacidad = capacidad;
	}
	public Servicio() {
		super();
		this.id = 0L;
		this.horaInicio = 0;
		this.horaFin = 0;
		this.capacidad = 0;
	}
	@Override
	public String toString() {
		return "Servicio [id=" + id + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", capacidad=" + capacidad
				+ "]";
	}
	
}
