package model;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;



public class CasaTest {

    @Test
    public void testeCriacaoDeCasa() {
        Casa casaNormal = new Casa(1, false); 

        assertFalse(casaNormal.isComodo());
    }
    
    @Test
    public void testeComodoNuncaFicaBloqueado() {
        Casa comodo = new Casa(10, true);

        
        comodo.setOcupada(true);

        // Verificação (Assert): 
        assertFalse(comodo.isBloqueada());
    }
    
    @Test
    public void testeCasaNormalFicaBloqueada() {
        Casa casaNormal = new Casa(5, false);

        casaNormal.setOcupada(true);

        assertTrue(casaNormal.isBloqueada());
    }
    
    @Test
    public void testeAddVizinho() {
        // 1. Preparação
        Casa casa = new Casa(1, false);

        // 2. Ação
        casa.addVizinho(2); // Adicionando a casa de ID 2 como vizinha da casa 1
        
        List<Integer> vizinhos = new ArrayList<>();
        vizinhos = casa.getVizinhos();
        		
        assertTrue(vizinhos.contains(2));
    }
}