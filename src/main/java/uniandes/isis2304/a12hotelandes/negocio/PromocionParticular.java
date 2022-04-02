package uniandes.isis2304.a12hotelandes.negocio;

public class PromocionParticular implements VOPromocionParticular {

	private Long idPlanDeConsumo;
	private String descripcion;
	@Override
	public Long getIdPlanDeConsumo() {
		return idPlanDeConsumo;
	}
	public void setIdPlanDeConsumo(Long idPlanDeConsumo) {
		this.idPlanDeConsumo = idPlanDeConsumo;
	}
	@Override
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public PromocionParticular() {
		super();
		this.idPlanDeConsumo = 0L;
		this.descripcion = "";
	}
	public PromocionParticular(Long idPlanDeConsumo, String descripcion) {
		super();
		this.idPlanDeConsumo = idPlanDeConsumo;
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "PromocionParticular [idPlanDeConsumo=" + idPlanDeConsumo + ", descripcion=" + descripcion + "]";
	}
	
}
