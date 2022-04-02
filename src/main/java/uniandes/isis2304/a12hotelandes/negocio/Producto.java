package uniandes.isis2304.a12hotelandes.negocio;

public class Producto implements VOProducto {

	private Long idProducto;
	private Long idServicio;
	private Integer costo;
	@Override
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	@Override
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	@Override
	public Integer getCosto() {
		return costo;
	}
	public void setCosto(Integer costo) {
		this.costo = costo;
	}
	public Producto() {
		super();
		this.idProducto = 0L;
		this.idServicio = 0L;
		this.costo = 0;
	}
	public Producto(Long idProducto, Long idServicio, Integer costo) {
		super();
		this.idProducto = idProducto;
		this.idServicio = idServicio;
		this.costo = costo;
	}
	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", idServicio=" + idServicio + ", costo=" + costo + "]";
	}
	
	
	
}
