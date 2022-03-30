package uniandes.isis2304.a12hotelandes.negocio;

public class Reserva implements VOReserva {
	
	private Long id;
	private Long periodo;
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Long getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Long periodo) {
		this.periodo = periodo;
	}
	@Override
	public String toString() {
		return "Reserva [id=" + id + ", periodo=" + periodo + "]";
	}
	public Reserva() {
		super();
		this.id = 0L;
		this.periodo = 0L;
	}
	public Reserva(Long id, Long periodo) {
		super();
		this.id = id;
		this.periodo = periodo;
	}
	
	

}
