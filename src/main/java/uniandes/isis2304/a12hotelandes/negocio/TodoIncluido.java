package uniandes.isis2304.a12hotelandes.negocio;

public class TodoIncluido implements VOTodoIncluido {

	private Long idPlanDeConsumo;
	private Long idServicioAsociado;
	private Long idReserva;
	private Integer costoFijoTotal;
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
	@Override
	public Long getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}
	@Override
	public Integer getCostoFijoTotal() {
		return costoFijoTotal;
	}
	public void setCostoFijoTotal(Integer costoFijoTotal) {
		this.costoFijoTotal = costoFijoTotal;
	}
	public TodoIncluido() {
		super();
		this.idPlanDeConsumo = 0L;
		this.idServicioAsociado = 0L;
		this.idReserva = 0L;
		this.costoFijoTotal = 0;
	}
	public TodoIncluido(Long idPlanDeConsumo, Long idServicioAsociado, Long idReserva, Integer costoFijoTotal) {
		super();
		this.idPlanDeConsumo = idPlanDeConsumo;
		this.idServicioAsociado = idServicioAsociado;
		this.idReserva = idReserva;
		this.costoFijoTotal = costoFijoTotal;
	}
	@Override
	public String toString() {
		return "TodoIncluido [idPlanDeConsumo=" + idPlanDeConsumo + ", idServicioAsociado=" + idServicioAsociado
				+ ", idReserva=" + idReserva + ", costoFijoTotal=" + costoFijoTotal + "]";
	}
	
	
	
}
