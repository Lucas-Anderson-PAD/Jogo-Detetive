package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;


public class JogadorTest {

    private Jogador jogador;
    private Piao piaoMock;
    
    /**
     * Configuração inicial executada antes de cada teste.
     * Garante um estado limpo e isolado para as validações.
     */
    @Before
    public void setUp() {
        // Assumindo que Piao recebe um Suspeito no construtor
        piaoMock = new Piao(Suspeito.SRTA_SCARLET, 1); 
        jogador = new Jogador("Davi", piaoMock);
    }

    /**
     * Verifica se o construtor inicializa corretamente os atributos básicos.
     * Testa o nome, o pião associado e se a lista de cartas não é nula.
     */
    @Test
    public void testConstrutorEInicializacao() {
        assertEquals("O nome do jogador deve ser 'Davi'", "Davi", jogador.getNome());
        assertNotNull("O pião não deve ser nulo", jogador.getPiao());
        assertEquals("O pião retornado deve ser o mesmo passado no construtor", piaoMock, jogador.getPiao());
        assertNotNull("A lista de cartas deve ser inicializada", jogador.getCartas());
        assertTrue("A lista de cartas deve estar vazia ao inicializar", jogador.getCartas().isEmpty());
    }

    /**
     * Verifica se o método getSuspeito() recupera corretamente o personagem
     * a partir do pião associado ao jogador.
     */
    @Test
    public void testGetSuspeito() {
        assertEquals("O suspeito deve ser a SRTA_SCARLET", Suspeito.SRTA_SCARLET, jogador.getSuspeito());
    }

    /**
     * Verifica o comportamento de recebimento e listagem de cartas.
     * Testa se a carta é adicionada corretamente à lista interna do jogador.
     */
    @Test
    public void testReceberEGetCartas() {
        // Assumindo um construtor simples para Carta
        Carta cartaArma = new Carta("Corda", TipoCarta.ARMA); 
        
        jogador.receberCarta(cartaArma);
        List<Carta> cartasDoJogador = jogador.getCartas();
        
        assertEquals("A lista de cartas deve ter tamanho 1", 1, cartasDoJogador.size());
        assertTrue("A lista de cartas deve conter a carta 'Corda'", cartasDoJogador.contains(cartaArma));
    }

    /**
     * Verifica a associação do bloco de notas ao jogador.
     * Testa o método receberBlocoNotas e o getBlocoNotas correspondente.
     */
    @Test
    public void testReceberEGetBlocoNotas() {
        BlocoDeNotas bloco = new BlocoDeNotas();
        
        assertNull("O bloco de notas deve ser nulo inicialmente", jogador.getBlocoNotas());
        
        jogador.receberBlocoNotas(bloco);
        
        assertNotNull("O bloco de notas não deve ser nulo após ser recebido", jogador.getBlocoNotas());
        assertEquals("O bloco de notas retornado deve ser a mesma instância fornecida", bloco, jogador.getBlocoNotas());
    }

    /**
     * Verifica o funcionamento do setter e getter de nome.
     */
    @Test
    public void testSetEGetNome() {
        jogador.setNome("Yuri");
        assertEquals("O nome do jogador deve ter sido alterado para 'Yuri'", "Yuri", jogador.getNome());
    }
    
    
    
    
    
    
    
    
    
    
    
    
}

















