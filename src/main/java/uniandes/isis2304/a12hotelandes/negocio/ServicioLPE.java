package uniandes.isis2304.a12hotelandes.negocio;

public class ServicioLPE implements VOServicioLavadoPlanchadoEmbolado{

	/* ****************************************************************
	 * 			Servicio Lavado Planchado Embolado
	 *****************************************************************/
	
	private Long idServicio;
	private Long idReserva;
	private String tipoPrenda;
	private Integer numPrendas;
	
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
	public String getTipoPrenda() {
		return tipoPrenda;
	}
	public void setTipoPrenda(String tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
	}
	@Override
	public Integer getNumPrendas() {
		return numPrendas;
	}
	public void setNumPrendas(Integer numPrendas) {
		this.numPrendas = numPrendas;
	}

	@Override
	public String toString() {
		return "Lavado Planchado Embolado[idServicio=" + idServicio +", asociado a la reserva: "+ idReserva +" por "+numPrendas+" de tipo "+tipoPrenda+ "]";
	}
	
	public ServicioLPE() {
		super();
		this.idServicio = 0L;
		this.idReserva = 0L;
		this.tipoPrenda = "";
		this.numPrendas = 0;
	}
	
	public ServicioLPE(Long idServicio, Long idReserva, String tipoPrenda, Integer numPrendas) {
		super();
		this.idServicio = idServicio;
		this.idReserva = idReserva;
		this.tipoPrenda = tipoPrenda;
		this.numPrendas = numPrendas;
	}
	
	
}
