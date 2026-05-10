package model;

class EnvelopeConfidencial {

    private Carta suspeito;
    private Carta comodo;
    private Carta arma;

    public EnvelopeConfidencial(Carta suspeito, Carta comodo, Carta arma) {
        this.suspeito = suspeito;
        this.comodo = comodo;
        this.arma = arma;
    }

    public boolean verificarSolucao(
            Carta suspeito,
            Carta comodo,
            Carta arma
    ) {
        return this.suspeito.equals(suspeito)
            && this.comodo.equals(comodo)
            && this.arma.equals(arma);
    }

    
    public Carta getCartaSuspeito() { return this.suspeito; }
    public Carta getCartaArma() { return this.arma; }
    public Carta getCartaComodo() { return this.comodo; }
    
    
    
}
