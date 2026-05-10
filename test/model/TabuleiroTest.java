package model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class TabuleiroTest {

    @Test
    public void testarMapeamentoParaCorredor() {
        Tabuleiro tab = new Tabuleiro();
        int[] dados = {1, 2}; // Total: 3 passos
        
        List<Integer> destinos = tab.obterDestinosValidos(dados, 1);
        
        // Verifica se a lista tem tamanho 1 e se o destino exato é a casa 4
        assertEquals(1, destinos.size());
        assertTrue(destinos.contains(4));
    }

    @Test
    public void testarParadaNoComodo() {
        Tabuleiro tab = new Tabuleiro();
        int[] dados = {3, 3}; // Total: 6 passos
        
        // Caminho do mini-tabuleiro: 1 -> 2 -> 3 -> 4 -> 5 (Cômodo)
        List<Integer> destinos = tab.obterDestinosValidos(dados, 1);
        
        // Verifica se parou no cômodo (casa 5), ignorando os passos excedentes
        assertTrue(destinos.contains(5));
    }
    
    
    
    
    
}