package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CartaTest {

    private Carta cartaSuspeito;
    private Carta cartaArma;

    /**
     * Configuração inicial executada antes de cada teste.
     * Instancia cartas de tipos diferentes para garantir a cobertura.
     */
    @Before
    public void setUp() {
        // Assumindo que TipoCarta possui as constantes SUSPEITO e ARMA
        cartaSuspeito = new Carta("Coronel Mustard", TipoCarta.SUSPEITO);
        cartaArma = new Carta("Corda", TipoCarta.ARMA);
    }

    /**
     * Verifica se o construtor atribui corretamente os valores de nome e tipo,
     * e se os métodos getNome() e getTipo() os recuperam sem alterações.
     */
    @Test
    public void testConstrutorEGetters() {
        // Validações para a carta de Suspeito
        assertEquals("O nome da carta deve ser 'Coronel Mustard'", "Coronel Mustard", cartaSuspeito.getNome());
        assertEquals("O tipo da carta deve ser SUSPEITO", TipoCarta.SUSPEITO, cartaSuspeito.getTipo());
        
        // Validações para a carta de Arma
        assertEquals("O nome da carta deve ser 'Corda'", "Corda", cartaArma.getNome());
        assertEquals("O tipo da carta deve ser ARMA", TipoCarta.ARMA, cartaArma.getTipo());
    }

    /**
     * Verifica se o método toString() foi sobrescrito corretamente para
     * retornar apenas a string correspondente ao nome da carta.
     */
    @Test
    public void testToString() {
        assertEquals("O método toString deve retornar o nome 'Coronel Mustard'", "Coronel Mustard", cartaSuspeito.toString());
        assertEquals("O método toString deve retornar o nome 'Corda'", "Corda", cartaArma.toString());
    }
}