package uniandes.isis2304.a12hotelandes.negocio;

public class ConvencionServicio implements VOConvencionServicio {
	public Long idConvencion;
	public Long idReservaServicio;
	@Override
	public Long getIdConvencion() {
		return idConvencion;
	}
	public void setIdConvencion(Long idConvencion) {
		this.idConvencion = idConvencion;
	}
	@Override
	public Long getIdReservaServicio() {
		return idReservaServicio;
	}
	public void setIdReservaServicio(Long idReservaServicio) {
		this.idReservaServicio = idReservaServicio;
	}
	@Override
	public String toString() {
		return "ConvencionServicio [idConvencion=" + idConvencion + ", idReservaServicio=" + idReservaServicio + "]";
	}
	public ConvencionServicio() {
		super();
		this.idConvencion = 0L;
		this.idReservaServicio = 0L;
	}
	public ConvencionServicio(Long idConvencion, Long idReservaServicio) {
		super();
		this.idConvencion = idConvencion;
		this.idReservaServicio = idReservaServicio;
	}
	
	

}
