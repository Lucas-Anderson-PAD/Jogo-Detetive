package model;
import java.util.ArrayList;
import java.util.List;

class Jogador {

	private Piao piao; 
    private String nome;
    private List<Carta> cartas;
    private BlocoDeNotas blocoNotas;
    

    public Jogador(String nome, Piao piao) {
        this.nome = nome;
        this.piao = piao;
        
        this.cartas = new ArrayList<>();
    }

    
    
    Piao getPiao() {
        return piao;
    	}
    
    Suspeito getSuspeito() {
        return piao.getPersonagem();
    	}
    
    
    
    
    
    
    
    public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public BlocoDeNotas getBlocoNotas() {
		return blocoNotas;
	}



	public void setBlocoNotas(BlocoDeNotas blocoNotas) {
		this.blocoNotas = blocoNotas;
	}



	public void receberCarta(Carta carta) {
        cartas.add(carta);
    }

    
    
    public List<Carta> getCartas() {
        return cartas;
    }

    
    

    void receberBlocoNotas(BlocoDeNotas bloco) {
        this.blocoNotas = bloco;
    }
    
    
 
    
    
    
}
