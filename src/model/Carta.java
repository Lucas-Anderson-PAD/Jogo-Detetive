package model;


class Carta {

	
    private String nome;
    private TipoCarta tipo;

    public Carta(String nome, TipoCarta tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public TipoCarta getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return nome;
    }
	
	
	
	
	
	
	
}
