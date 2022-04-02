package uniandes.isis2304.a12hotelandes.negocio;

public class TiempoCompartido implements VOTiempoCompartido {

	private Long idPlanDeConsumo;
	private Long idServicioAsociado;
	@Override
	public Long getIdPlanDeConsumo() {
		return idPlanDeConsumo;
	}
	public void setIdPlanDeConsumo(Long idPlanDeConsumo) {
		this.idPlanDeConsumo = idPlanDeConsumo;
	}
	@Override
	public Long getIdServicioAsociado() {
		return idServicioAsociado;
	}
	public void setIdServicioAsociado(Long idServicioAsociado) {
		this.idServicioAsociado = idServicioAsociado;
	}
	public TiempoCompartido() {
		super();
		this.idPlanDeConsumo = 0L;
		this.idServicioAsociado = 0L;
	}
	public TiempoCompartido(Long idPlanDeConsumo, Long idServicioAsociado) {
		super();
		this.idPlanDeConsumo = idPlanDeConsumo;
		this.idServicioAsociado = idServicioAsociado;
	}
	
	@Override
	public String toString() {
		return "TiempoCompartido [idPlanDeConsumo=" + idPlanDeConsumo + ", idServicioAsociado=" + idServicioAsociado
				+ "]";
	}
	
	
}
