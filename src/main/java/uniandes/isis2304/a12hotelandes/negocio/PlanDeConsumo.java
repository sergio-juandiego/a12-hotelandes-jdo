package uniandes.isis2304.a12hotelandes.negocio;

public class PlanDeConsumo implements VOPlanDeConsumo {

	private Long id;
	private String tipo;
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public PlanDeConsumo() {
		super();
		this.id = 0L;
		this.tipo = "";
	}
	public PlanDeConsumo(Long id, String tipo) {
		super();
		this.id = id;
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "PlanDeConsumo [id=" + id + ", tipo=" + tipo + "]";
	}
	
}
