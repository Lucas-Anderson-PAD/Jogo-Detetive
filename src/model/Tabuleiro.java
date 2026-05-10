package model;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

class Tabuleiro {
	
	private Map<Integer, Casa> todasAsCasas;
	
	Tabuleiro(){
		this.todasAsCasas = new HashMap();
		this.gerarTabuleiro();
		}
	
	
	private void gerarTabuleiro() { // Para a primeira iteração, apenas um corredor reto para o tabuleiro
		
		todasAsCasas.put(1, new Casa(1,false));
		todasAsCasas.put(2, new Casa(2,false));
		todasAsCasas.put(3, new Casa(3,false));
		todasAsCasas.put(4, new Casa(4,false));		
		todasAsCasas.put(5, new Casa(5, true));
		
		todasAsCasas.get(1).addVizinho(2);
		
		this.todasAsCasas.get(2).addVizinho(1);
	    this.todasAsCasas.get(2).addVizinho(3);
	    
	    // Casa 3 liga com a 2 e a 4
	    this.todasAsCasas.get(3).addVizinho(2);
	    this.todasAsCasas.get(3).addVizinho(4);
	    
	    // Casa 4 liga com a 3 e a porta do Cômodo (5)
	    this.todasAsCasas.get(4).addVizinho(3);
	    this.todasAsCasas.get(4).addVizinho(5);
	    
	    // Cômodo 5 liga de volta para o corredor 4
	    this.todasAsCasas.get(5).addVizinho(4);
		
	}
	
	
	/**
	 * Inicia a busca pelos destinos válidos após a rolagem dos dados.
	 * Cria as coleções vazias ('destinos' e 'caminho') que serão preenchidas
	 * pela busca recursiva, enviando-as como parâmetro.
	 * * @param valoresDados Array contendo o resultado dos dados.
	 * @param idCasaAtual ID da casa de onde o movimento se inicia.
	 * @return Uma Lista contendo os IDs de todos os destinos finais possíveis.
	 */
	public List<Integer> obterDestinosValidos(int[] valoresDados, int idCasaAtual) {
	    int passosTotais = valoresDados[0] + valoresDados[1];
	    
	    // 1. Criamos as "sacolas" vazias aqui fora
	    Set<Integer> destinos = new HashSet<>(); 
	    List<Integer> caminho = new ArrayList<>();
	    
	    // 2. Mandamos o algoritmo trabalhar preenchendo as sacolas
	    mapearCasasAlcancaveis(passosTotais, idCasaAtual, caminho, destinos);
	    
	    // 3. A sacola 'destinos' agora volta cheia! Devolvemos ela.
	    return new ArrayList<>(destinos);
	}
	
	
	/**
	 * Algoritmo recursivo (backtracking) para explorar as rotas no tabuleiro.
	 * As coleções ('caminho' e 'casasPossiveis') chegam vazias na primeira chamada
	 * e funcionam como "acumuladores" em memória:
	 * - 'caminho': guarda o rastro da rota atual para impedir que o peão ande em círculos.
	 * - 'casasPossiveis': guarda os IDs finais válidos (o uso do Set impede destinos duplicados).
	 * * @param passosRestantes Quantidade de movimentos que o peão ainda pode dar.
	 * @param idCasaAtual O ID da casa analisada neste passo da recursão.
	 * @param caminho Lista com o histórico de casas já pisadas na rota atual.
	 * @param casasPossiveis Coleção que recebe as casas finais encontradas.
	 */
	private void mapearCasasAlcancaveis(int passosRestantes, int idCasaAtual, List<Integer> caminho, Set<Integer> casasPossiveis){
		Casa casaAtual = todasAsCasas.get(idCasaAtual);
		
		if (casaAtual.isComodo()) {
			casasPossiveis.add(idCasaAtual);
			return;
		}
		
		if(passosRestantes == 0) {
			casasPossiveis.add(idCasaAtual);
			return;
		}
		
		caminho.add(casaAtual.getId());
		
		for ( int vizinho : casaAtual.getVizinhos() ) {
			Casa casaVizinha = todasAsCasas.get(vizinho);
			if(!caminho.contains(vizinho) && !casaVizinha.isBloqueada()) {
				mapearCasasAlcancaveis(passosRestantes -1 ,vizinho,caminho,casasPossiveis);
			}
			
		}
		caminho.remove(caminho.size() - 1);		
	
	}
	
	/**
	 * Efetiva a realocação do peão no tabuleiro.
	 * Atualiza a regra de bloqueio: corredores viram bloqueados ou livres, 
	 * enquanto cômodos não sofrem restrição de ocupação.
	 * * @param piao O objeto do peão que será movido.
	 * @param idNovaCasa O ID do destino final escolhido pelo jogador.
	 */
	public void moverPiao(Piao piao ,int idNovaCasa ) {
		Casa casaAntiga = this.todasAsCasas.get(piao.getIdCasaAtual());
		Casa casaNova = this.todasAsCasas.get(idNovaCasa);
		
		if(!casaAntiga.isComodo()) {
			casaAntiga.setOcupada(false);
		}
		if(!casaNova.isComodo()) {
			casaNova.setOcupada(true);
		}
		
		piao.setIdCasaAtual(idNovaCasa); 	
	}

}





