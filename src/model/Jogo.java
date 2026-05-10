package model;
import java.util.*;

public class Jogo {

    private List<Jogador> jogadores = new ArrayList<>();
    private EnvelopeConfidencial envelope;
    
    private HashMap<Suspeito, Carta> cartasSuspeitos = new HashMap<>();
    private HashMap<Comodo, Carta> cartasComodos = new HashMap<>();
    private HashMap<Arma, Carta> cartasArmas = new HashMap<>();
    
    private HashMap<Suspeito, Piao> todosOsPeoes = new HashMap<>();
    
    private int indiceJogadorAtual = 0;
  
    private final Tabuleiro tabuleiro = new Tabuleiro();
    private List<Integer> casasAlcancaveisAtuais = new ArrayList<>(); 
    
    private final Dado dado = new Dado(); 
    
    
    
    public Jogo() {
        inicializarCartas();
        inicializarPioes();

        adicionarJogador("Davi" , Suspeito.SRTA_SCARLET);
        adicionarJogador("Yuri" , Suspeito.SRA_PEACOCK);
        adicionarJogador("Lucas", Suspeito.CORONEL_MUSTARD);
        
        prepararJogo();
    }
    
    EnvelopeConfidencial getEnvelope() {
        return envelope;
    }
    
    // DADOS ======================================================== >>>
    
   
    
    public int[] lancarDados() {
        return dado.lancaDados(); 
    	}
    
    public int rolarDadosESomar() {
        int[] resultado = lancarDados();
        return resultado[0] + resultado[1];
    	}
    
    
    
  
    
    
    // ADICIONAR JOGADOR ============================================ >>>
    
    private void adicionarJogador(String nome, Suspeito s) {
        Piao piaoEscolhido = todosOsPeoes.get(s);
        Jogador jogador = new Jogador(nome, piaoEscolhido);
        jogadores.add(jogador);
        System.out.println("Jogador " + nome + " adicionado como " + s.getNome()  );
    }
    
    // INICIAR PEOES ================================================ >>>
    
    private void inicializarPioes() {
        for (Suspeito s : Suspeito.values()) {
            todosOsPeoes.put(s, new Piao(s, 1)); // Colocando todos em 1 inicialmente
        	}
    	}
    
    
    // INICIAR CARTAS ================================================== >>>
    
    private void inicializarCartas() {
        for (Suspeito s : Suspeito.values()) {
            cartasSuspeitos.put(s, new Carta(s.getNome(), TipoCarta.SUSPEITO ));
        }
        for (Comodo c : Comodo.values()) {
            cartasComodos.put(c, new Carta(c.getNome(), TipoCarta.COMODO ));
        }
        for (Arma a : Arma.values()) {
            cartasArmas.put(a, new Carta(a.getNome(), TipoCarta.ARMA ));
        }
    }
    
    // PREPARAR JOGO ================================================ >>>
    
    private void prepararJogo() {
        List<Carta> suspeitos = new ArrayList<>(cartasSuspeitos.values());
        List<Carta> comodos = new ArrayList<>(cartasComodos.values());
        List<Carta> armas = new ArrayList<>(cartasArmas.values());

        
        // Regra 4 ============================== >>>
        Collections.shuffle(suspeitos);
        Collections.shuffle(comodos);
        Collections.shuffle(armas);

        Carta suspeito = suspeitos.remove(0);
        Carta comodo = comodos.remove(0);
        Carta arma = armas.remove(0);
        
        envelope = new EnvelopeConfidencial(suspeito, comodo, arma);
        System.out.println("Enevelope com: Suspeito - " + suspeito.getNome() + " Comodo - " + comodo.getNome() + " Arma - " + arma.getNome()  );
        System.out.println("");
        
        // Regra 5 ============================== >>>
        List<Carta> restantes = new ArrayList<>();
        restantes.addAll(suspeitos);
        restantes.addAll(comodos);
        restantes.addAll(armas);
        Collections.shuffle(restantes);

        distribuirCartas(restantes);
        
        // Regra 6 ============================== >>>
        distribuirBlocosDeNotas();
        
        // Regra 7 ============================== >>>
        definirPrimeiroJogador();
        
    }
    
    
    private void distribuirBlocosDeNotas() {
        for (Jogador jogador : jogadores) {
            jogador.receberBlocoNotas(new BlocoDeNotas());
        	}
    	}

    private void distribuirCartas(List<Carta> cartas) {
        int indiceJogador = 0;
        for (Carta carta : cartas) {
            jogadores.get(indiceJogador).receberCarta(carta);
            System.out.println(jogadores.get(indiceJogador).getNome() + " recebeu " + carta.getNome() );
            indiceJogador++;
            if (indiceJogador >= jogadores.size()) { indiceJogador = 0; }
        	}
        
        System.out.println("");
    	}
    
    
    // TURNOS DOS JOGOS --------------------------------------------------------------------------- >>>
    
    private void definirPrimeiroJogador() {
    	// Se nenhum jogador escolheu a Scarlet então o primeiro jogador é simplesmente o primeiro adicionado
    	
        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).getSuspeito() == Suspeito.SRTA_SCARLET) {
                this.indiceJogadorAtual = i;
                break;
            	} 
        	}
        
        
        System.out.println("Primeiro jogador: " + jogadores.get(this.indiceJogadorAtual).getNome() + " Com " + jogadores.get(this.indiceJogadorAtual).getSuspeito().getNome() );
    	}

    
    public void avancarTurno() {
        this.indiceJogadorAtual = (this.indiceJogadorAtual + 1) % jogadores.size();
    	}

    
    public Jogador getJogadorAtual() {
        return jogadores.get(indiceJogadorAtual);
    	}
    
    
    // TABULEIRO ==================================================== >>>
    
    public List<Integer> mapearCasas(int[] valoresDados) {
        casasAlcancaveisAtuais.clear();
        
        Piao piaoAtual = getJogadorAtual().getPiao();
        
      
        casasAlcancaveisAtuais = tabuleiro.obterDestinosValidos(valoresDados, piaoAtual.getIdCasaAtual());
        
        return casasAlcancaveisAtuais;
    }

    
    public void deslocarPiao(int idCasaDestino) {
        if (!casasAlcancaveisAtuais.contains(idCasaDestino)) {
            throw new IllegalArgumentException("Movimento inválido: A casa não pode ser alcançada nesta jogada.");
        	}
        
        Piao piaoAtual = getJogadorAtual().getPiao();
        
      
        tabuleiro.moverPiao(piaoAtual, idCasaDestino);
        casasAlcancaveisAtuais.clear();
    }
    
    
    
    

    
}

