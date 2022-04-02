package uniandes.isis2304.a12hotelandes.negocio;

public class LargaEstadia implements VOLargaEstadia {

	private Long idPlanDeConsumo;
	private Double descuento;
	private Long idHotel;
	private String tiempoEstadia;
	@Override
	public Long getIdPlanDeConsumo() {
		return idPlanDeConsumo;
	}
	public void setIdPlanDeConsumo(Long idPlanDeConsumo) {
		this.idPlanDeConsumo = idPlanDeConsumo;
	}
	@Override
	public Double getDescuento() {
		return descuento;
	}
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	@Override
	public Long getIdHotel() {
		return idHotel;
	}
	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}
	@Override
	public String getTiempoEstadia() {
		return tiempoEstadia;
	}
	public void setTiempoEstadia(String tiempoEstadia) {
		this.tiempoEstadia = tiempoEstadia;
	}
	public LargaEstadia() {
		super();
		this.idPlanDeConsumo = 0L;
		this.descuento = 0.0;
		this.idHotel = 0L;
		this.tiempoEstadia = "";
	}
	public LargaEstadia(Long idPlanDeConsumo, Double descuento, Long idHotel, String tiempoEstadia) {
		super();
		this.idPlanDeConsumo = idPlanDeConsumo;
		this.descuento = descuento;
		this.idHotel = idHotel;
		this.tiempoEstadia = tiempoEstadia;
	}
	@Override
	public String toString() {
		return "LargaEstadia [idPlanDeConsumo=" + idPlanDeConsumo + ", descuento=" + descuento + ", idHotel=" + idHotel
				+ ", tiempoEstadia=" + tiempoEstadia + "]";
	}
	
}
