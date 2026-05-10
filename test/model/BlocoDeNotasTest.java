package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BlocoDeNotasTest {

    private BlocoDeNotas bloco;

    /**
     * Configuração inicial executada antes de cada teste.
     * Garante que cada teste inicie com uma folha de anotações em branco.
     */
    @Before
    public void setUp() {
        bloco = new BlocoDeNotas();
    }

    /**
     * Verifica se o construtor inicializa corretamente os HashMaps,
     * garantindo que nenhuma carta comece "riscada" (true) por acidente.
     */
    @Test
    public void testInicializacao() {
        // Pega um valor de exemplo de cada Enum para garantir que começaram como falsos
        String nomeSuspeitoExemplo = Suspeito.SRTA_SCARLET.getNome();
        String nomeArmaExemplo = Arma.CORDA.getNome();
        String nomeComodoExemplo = Comodo.COZINHA.getNome();

        assertFalse("O suspeito não deve estar riscado no início", bloco.isSuspeitoRiscado(nomeSuspeitoExemplo));
        assertFalse("A arma não deve estar riscada no início", bloco.isArmaRiscada(nomeArmaExemplo));
        assertFalse("O cômodo não deve estar riscado no início", bloco.isComodoRiscado(nomeComodoExemplo));
    }

    /**
     * Verifica se o método riscarSuspeito altera o valor booleano
     * correspondente no HashMap para true.
     */
    @Test
    public void testRiscarSuspeito() {
        String suspeitoAlvo = Suspeito.CORONEL_MUSTARD.getNome();
        
        // Verifica o estado antes da ação
        assertFalse("O Coronel Mustard não deve estar riscado antes da chamada", bloco.isSuspeitoRiscado(suspeitoAlvo));
        
        // Executa a ação
        bloco.riscarSuspeito(suspeitoAlvo);
        
        // Verifica o estado após a ação
        assertTrue("O Coronel Mustard deve estar riscado após a chamada", bloco.isSuspeitoRiscado(suspeitoAlvo));
    }

    /**
     * Verifica se o método riscarArma altera o valor booleano
     * correspondente no HashMap para true.
     */
    @Test
    public void testRiscarArma() {
        String armaAlvo = Arma.REVOLVER.getNome();
        
        // Executa a ação
        bloco.riscarArma(armaAlvo);
        
        // Verifica o estado após a ação
        assertTrue("O Revólver deve estar riscado após a chamada", bloco.isArmaRiscada(armaAlvo));
        
        // Garante que outra arma aleatória não foi afetada
        assertFalse("A Corda não deve ter sido riscada por engano", bloco.isArmaRiscada(Arma.CORDA.getNome()));
    }

    /**
     * Verifica se o método riscarComodo altera o valor booleano
     * correspondente no HashMap para true.
     */
    @Test
    public void testRiscarComodo() {
        String comodoAlvo = Comodo.BIBLIOTECA.getNome();
        
        // Executa a ação
        bloco.riscarComodo(comodoAlvo);
        
        // Verifica o estado após a ação
        assertTrue("A Biblioteca deve estar riscada após a chamada", bloco.isComodoRiscado(comodoAlvo));
    }
}