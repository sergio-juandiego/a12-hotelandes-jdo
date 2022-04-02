package uniandes.isis2304.a12hotelandes.negocio;

public class ServicioPrestamoUtensilios implements VOServicioPrestamoUtensilios {

	private Long idServicio;
	private Long idReserva;
	private Integer recargoPorMalUso;
	@Override
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	@Override
	public Long getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}
	@Override
	public Integer getRecargoPorMalUso() {
		return recargoPorMalUso;
	}
	public void setRecargoPorMalUso(Integer recargoPorMalUso) {
		this.recargoPorMalUso = recargoPorMalUso;
	}
	@Override
	public String toString() {
		return "ServicioPrestamoUtensilios [idServicio=" + idServicio + ", idReserva=" + idReserva
				+ ", recargoPorMalUso=" + recargoPorMalUso + "]";
	}
	public ServicioPrestamoUtensilios() {
		super();
		this.idServicio = 0L;
		this.idReserva = 0L;
		this.recargoPorMalUso = 0;
	}
	public ServicioPrestamoUtensilios(Long idServicio, Long idReserva, Integer recargoPorMalUso) {
		super();
		this.idServicio = idServicio;
		this.idReserva = idReserva;
		this.recargoPorMalUso = recargoPorMalUso;
	}
	
	
}
