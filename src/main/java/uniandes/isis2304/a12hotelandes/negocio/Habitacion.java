package uniandes.isis2304.a12hotelandes.negocio;

public class Habitacion implements VOHabitacion {
	
	public Long id;
	public Integer costoPorNoche;
	public Long tipoHabitacion;
	public String aprovisionamiento;
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Integer getCostoPorNoche() {
		return costoPorNoche;
	}
	public void setCostoPorNoche(Integer costoPorNoche) {
		this.costoPorNoche = costoPorNoche;
	}
	@Override
	public Long getTipoHabitacion() {
		return tipoHabitacion;
	}
	public void setTipoHabitacion(Long tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}
	@Override
	public String getAprovisionamiento() {
		return aprovisionamiento;
	}
	public void setAprovisionamiento(String aprovisionamiento) {
		this.aprovisionamiento = aprovisionamiento;
	}

	public Habitacion() {
		super();
		this.id = 0L;
		this.costoPorNoche = 0;
		this.tipoHabitacion = 0L;
		this.aprovisionamiento = "";
	}
	
	public Habitacion(Long id, Integer costoPorNoche, Long tipoHabitacion, String aprovisionamiento) {
		super();
		this.id = id;
		this.costoPorNoche = costoPorNoche;
		this.tipoHabitacion = tipoHabitacion;
		this.aprovisionamiento = aprovisionamiento;
	}
	@Override
	public String toString() {
		return "Habitacion [id=" + id + ", costoPorNoche=" + costoPorNoche + ", tipoHabitacion=" + tipoHabitacion
				+ ", aprovisionamiento=" + aprovisionamiento + "]";
	}
	
	

}
