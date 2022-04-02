package uniandes.isis2304.a12hotelandes.negocio;

public class ConsumoServicio implements VOConsumoServicio {

	private Long idFactura;
	private Long idReserva;
	private Long idServicio;
	private Long idProducto;
	private Integer cantidad;
	@Override
	public Long getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}
	@Override
	public Long getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}
	@Override
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	@Override
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	@Override
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public ConsumoServicio() {
		super();
		this.idFactura = 0L;
		this.idReserva = 0L;
		this.idServicio = 0L;
		this.idProducto = 0L;
		this.cantidad = 0;
	}
	public ConsumoServicio(Long idFactura, Long idReserva, Long idServicio, Long idProducto, Integer cantidad) {
		super();
		this.idFactura = idFactura;
		this.idReserva = idReserva;
		this.idServicio = idServicio;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "ConsumoServicio [idFactura=" + idFactura + ", idReserva=" + idReserva + ", idServicio=" + idServicio
				+ ", idProducto=" + idProducto + ", cantidad=" + cantidad + "]";
	}
	
	
}
