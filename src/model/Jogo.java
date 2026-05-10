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
    private List<Integer> casasAlcancaveisAtuais = new ArrayList<>(); // Usando sua lista de Integers
    
    private final Dado dado = new Dado(); // Instância única da sua classe Dado
    
    public Jogo() {
        inicializarCartas();
        inicializarPeoes();

        adicionarJogador("Davi" , Suspeito.SRTA_SCARLET);
        adicionarJogador("Yuri" , Suspeito.SRA_PEACOCK);
        adicionarJogador("Lucas", Suspeito.CORONEL_MUSTARD);
        
        prepararJogo();
    }
    
    // DADOS ======================================================== >>>
    
    public int[] lancarDados() {
        // Usa o método que você já criou na sua classe
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
    }
    
    // INICIAR PEOES ================================================ >>>
    
    private void inicializarPeoes() {
        for (Suspeito s : Suspeito.values()) {
            // Inicializa na casa 1 por padrão (ajuste para a casa de início real do tabuleiro depois)
            todosOsPeoes.put(s, new Piao(s, 1));
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

        Collections.shuffle(suspeitos);
        Collections.shuffle(comodos);
        Collections.shuffle(armas);

        Carta suspeito = suspeitos.remove(0);
        Carta comodo = comodos.remove(0);
        Carta arma = armas.remove(0);

        envelope = new EnvelopeConfidencial(suspeito, comodo, arma);

        List<Carta> restantes = new ArrayList<>();
        restantes.addAll(suspeitos);
        restantes.addAll(comodos);
        restantes.addAll(armas);

        Collections.shuffle(restantes);

        distribuirCartas(restantes);
        distribuirBlocosDeNotas();
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
            indiceJogador++;
            if (indiceJogador >= jogadores.size()) { indiceJogador = 0; }
        }
    }
    
    // TURNOS DOS JOGOS --------------------------------------------------------------------------- >>>
    
    private void definirPrimeiroJogador() {
        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).getSuspeito() == Suspeito.SRTA_SCARLET) {
                this.indiceJogadorAtual = i;
                break;
            }
        }
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
        
        // Aciona a sua API de tabuleiro para buscar os IDs
        casasAlcancaveisAtuais = tabuleiro.obterDestinosValidos(valoresDados, piaoAtual.getIdCasaAtual());
        
        return casasAlcancaveisAtuais;
    }

    public void deslocarPiao(int idCasaDestino) {
        if (!casasAlcancaveisAtuais.contains(idCasaDestino)) {
            throw new IllegalArgumentException("Movimento inválido: A casa não pode ser alcançada nesta jogada.");
        }
        
        Piao piaoAtual = getJogadorAtual().getPiao();
        
        // Efetiva a realocação chamando o seu método
        tabuleiro.moverPiao(piaoAtual, idCasaDestino);
        casasAlcancaveisAtuais.clear();
    }
    
    public static void main(String[] args) {
        Jogo partida = new Jogo();
        System.out.println("--- PREPARAÇÃO DO JOGO ---");
        System.out.println("Jogador inicial : " + partida.getJogadorAtual().getNome());
        
        System.out.println("\n--- CARTAS DISTRIBUÍDAS ---");
        for(Jogador j : partida.jogadores) {
            System.out.println(j.getNome() + " (" + j.getSuspeito().getNome() + ") recebeu as cartas: " + j.getCartas());
        }
        
        System.out.println("\n--- TESTE DE MOVIMENTAÇÃO ---");
        int[] dados = partida.lancarDados();
        System.out.println("O jogador rolou: " + dados[0] + " e " + dados[1]);
        
        List<Integer> destinos = partida.mapearCasas(dados);
        System.out.println("IDs das casas alcançáveis: " + destinos);
    }
    
}

