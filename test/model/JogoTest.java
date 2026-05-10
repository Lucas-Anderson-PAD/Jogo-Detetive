package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class JogoTest {

    private Jogo jogo;

    /**
     * Instancia um novo Jogo antes de cada teste.
     * Como o construtor do Jogo já executa toda a preparação (Regras 4, 5, 6 e 7),
     * o estado inicial já estará pronto para ser testado.
     */
    @Before
    public void setUp() {
        jogo = new Jogo();
    }

    /**
     * Verifica a Regra 7: A Srta. Scarlet deve ser sempre a primeira a jogar.
     * Também aproveita para verificar as Regras 5 e 6 no jogador atual.
     */
    @Test
    public void testPreparacaoEPrimeiroJogador() {
        Jogador jogadorAtual = jogo.getJogadorAtual();

        // Verifica a Regra 7
        assertNotNull("O jogador atual não pode ser nulo", jogadorAtual);
        assertEquals("A Srta. Scarlet (Davi) deve ser a primeira a jogar", 
                     Suspeito.SRTA_SCARLET, jogadorAtual.getSuspeito());
        assertEquals("O nome do primeiro jogador deve ser Davi", "Davi", jogadorAtual.getNome());

        // Verifica a Regra 6 (Bloco de notas distribuído)
        assertNotNull("O jogador deve ter recebido um bloco de notas", jogadorAtual.getBlocoNotas());

        // Verifica a Regra 5 (Cartas distribuídas)
        assertFalse("O jogador deve ter recebido cartas na mão", jogadorAtual.getCartas().isEmpty());
    }

    /**
     * Verifica a Regra 4: O envelope confidencial deve conter exatamente
     * uma carta de Suspeito, uma de Cômodo e uma de Arma.
     * (Requer a criação do método getEnvelope() na classe Jogo).
     */
    @Test
    public void testEnvelopeConfidencial() {
        EnvelopeConfidencial envelope = jogo.getEnvelope();
        assertNotNull("O envelope confidencial deve ter sido criado", envelope);
        
        // Assumindo que sua classe EnvelopeConfidencial tenha getters para as cartas
        assertNotNull("O envelope deve ter uma carta de suspeito", envelope.getCartaSuspeito());
        assertNotNull("O envelope deve ter uma carta de arma", envelope.getCartaArma());
        assertNotNull("O envelope deve ter uma carta de cômodo", envelope.getCartaComodo());
    }

    /**
     * Verifica a Regra 7 (continuação): O jogo segue para o próximo jogador.
     */
    @Test
    public void testAvancarTurno() {
        // Primeiro turno: Davi (Scarlet)
        assertEquals("Davi", jogo.getJogadorAtual().getNome());

        // Avança o turno
        jogo.avancarTurno();
        // Segundo turno: Yuri (Peacock)
        assertEquals("Yuri", jogo.getJogadorAtual().getNome());

        // Avança o turno
        jogo.avancarTurno();
        // Terceiro turno: Lucas (Mustard)
        assertEquals("Lucas", jogo.getJogadorAtual().getNome());

        // Avança o turno (deve voltar para o primeiro - loop circular)
        jogo.avancarTurno();
        assertEquals("Davi", jogo.getJogadorAtual().getNome());
    }

    /**
     * Verifica a etapa de lançamento virtual dos dados.
     * Testa se o retorno está dentro dos limites de dois dados convencionais (1 a 6).
     */
    @Test
    public void testLancarDados() {
        int[] resultado = jogo.lancarDados();
        
        assertEquals("O array de dados deve conter 2 valores", 2, resultado.length);
        
        assertTrue("O valor do dado 1 deve ser no mínimo 1", resultado[0] >= 1);
        assertTrue("O valor do dado 1 deve ser no máximo 6", resultado[0] <= 6);
        
        assertTrue("O valor do dado 2 deve ser no mínimo 1", resultado[1] >= 1);
        assertTrue("O valor do dado 2 deve ser no máximo 6", resultado[1] <= 6);
    }
    
    /**
     * Verifica se o método de somar os dados está funcionando corretamente.
     */
    @Test
    public void testRolarDadosESomar() {
        int soma = jogo.rolarDadosESomar();
        assertTrue("A soma de 2 dados deve ser no mínimo 2", soma >= 2);
        assertTrue("A soma de 2 dados deve ser no máximo 12", soma <= 12);
    }

    /**
     * Verifica a Regra 4 de Movimentação: Lançamento de exceção caso o jogador 
     * tente mover para uma casa que não foi previamente mapeada (inválida).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeslocarPiaoInvalido() {
        // Tenta mover o peão sem ter chamado o mapearCasas (lista de alcançáveis vazia)
        // O ID 999 é fictício e certamente não está na lista.
        jogo.deslocarPiao(999);
    }
}