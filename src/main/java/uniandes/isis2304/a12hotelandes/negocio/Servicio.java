package uniandes.isis2304.a12hotelandes.negocio;

public class Servicio implements VOServicio {
	public Long id;
	public String horarioServicio;
	public Integer capacidad;
	public Integer costo;
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String getHorarioServicio() {
		return horarioServicio;
	}
	public void setHorarioServicio(String horarioServicio) {
		this.horarioServicio = horarioServicio;
	}
	@Override
	public Integer getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	@Override
	public Integer getCosto() {
		return costo;
	}
	public void setCosto(Integer costo) {
		this.costo = costo;
	}
	public Servicio() {
		super();
		this.id = 0L;
		this.horarioServicio = "";
		this.capacidad = 0;
		this.costo = 0;
	}
	public Servicio(Long id, String horarioServicio, Integer capacidad, Integer costo) {
		super();
		this.id = id;
		this.horarioServicio = horarioServicio;
		this.capacidad = capacidad;
		this.costo = costo;
	}
	@Override
	public String toString() {
		return "Servicio [id=" + id + ", horarioServicio=" + horarioServicio + ", capacidad=" + capacidad + ", costo="
				+ costo + "]";
	}
	
	
}
