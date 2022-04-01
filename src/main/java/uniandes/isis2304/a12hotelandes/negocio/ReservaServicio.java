package uniandes.isis2304.a12hotelandes.negocio;

public class ReservaServicio implements VOReservaServicio {
	
	public Long id;
	public Long idServicio;
	public Integer periodo;
	
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

	
	@Override
	public Integer getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	
	
	public ReservaServicio() {
		super();
		this.id = 0L;
		this.idServicio = 0L;
		this.periodo = 0;
	}
	public ReservaServicio(Long id, Long idServicio, Integer periodo) {
		super();
		this.id = id;
		this.idServicio = idServicio;
		this.periodo = periodo;
	}
	@Override
	public String toString() {
		return "ReservaServicio [id=" + id + ", idServicio=" + idServicio + ", periodo=" + periodo + "]";
	}
	
	

	

}
