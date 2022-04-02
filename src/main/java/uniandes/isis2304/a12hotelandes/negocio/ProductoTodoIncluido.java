package uniandes.isis2304.a12hotelandes.negocio;

public class ProductoTodoIncluido implements VOProductoTodoIncluido {

	
	private Long idPlanDeConsumo;
	private Long idProductoAsociado;
	private Double descuento;
	@Override
	public Long getIdPlanDeConsumo() {
		return idPlanDeConsumo;
	}
	public void setIdPlanDeConsumo(Long idPlanDeConsumo) {
		this.idPlanDeConsumo = idPlanDeConsumo;
	}
	@Override
	public Long getIdProductoAsociado() {
		return idProductoAsociado;
	}
	public void setIdProductoAsociado(Long idProductoAsociado) {
		this.idProductoAsociado = idProductoAsociado;
	}
	@Override
	public Double getDescuento() {
		return descuento;
	}
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	public ProductoTodoIncluido() {
		super();
		this.idPlanDeConsumo = 0L;
		this.idProductoAsociado = 0L;
		this.descuento = 0.0;
	}
	public ProductoTodoIncluido(Long idPlanDeConsumo, Long idProductoAsociado, Double descuento) {
		super();
		this.idPlanDeConsumo = idPlanDeConsumo;
		this.idProductoAsociado = idProductoAsociado;
		this.descuento = descuento;
	}
	@Override
	public String toString() {
		return "ProductoTodoIncluido [idPlanDeConsumo=" + idPlanDeConsumo + ", idProductoAsociado=" + idProductoAsociado
				+ ", descuento=" + descuento + "]";
	}
	
}
