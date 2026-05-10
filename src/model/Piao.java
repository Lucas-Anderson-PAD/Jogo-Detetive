package model;



class Piao {
	
	private Suspeito personagem;
	private int IdCasaAtual;
	
	public Piao(Suspeito personagem, int idCasaInicial) {
		this.personagem = personagem;
		this.IdCasaAtual = idCasaInicial;
	}
	
	public int getIdCasaAtual() {
		return IdCasaAtual;
	}
	
	public void setIdCasaAtual(int novoIdCasaAtual) {
		this.IdCasaAtual = novoIdCasaAtual;
	}
	public Suspeito getPersonagem() {
		return personagem;
	}
	
	
	
	

}
