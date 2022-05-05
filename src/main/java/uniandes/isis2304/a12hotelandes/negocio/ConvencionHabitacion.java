package uniandes.isis2304.a12hotelandes.negocio;

public class ConvencionHabitacion implements VOConvencionHabitacion {
	public Long idConvencion;
	public Long idReservaHabitacion;
	@Override
	public Long getIdConvencion() {
		return idConvencion;
	}
	public void setIdConvencion(Long idConvencion) {
		this.idConvencion = idConvencion;
	}
	@Override
	public Long getIdReservaHabitacion() {
		return idReservaHabitacion;
	}
	public void setIdReservaHabitacion(Long idReservaHabitacion) {
		this.idReservaHabitacion = idReservaHabitacion;
	}
	@Override
	public String toString() {
		return "ConvencionHabitacion [idConvencion=" + idConvencion + ", idReservaHabitacion=" + idReservaHabitacion
				+ "]";
	}
	public ConvencionHabitacion() {
		super();
		this.idConvencion = 0L;
		this.idReservaHabitacion = 0L;
	}
	
	public ConvencionHabitacion(Long idConvencion, Long idReservaHabitacion) {
		super();
		this.idConvencion = idConvencion;
		this.idReservaHabitacion = idReservaHabitacion;
	}
	
	

}
