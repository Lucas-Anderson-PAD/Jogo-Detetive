package model;

import static org.junit.Assert.*;
import org.junit.Test;

public class DadoTest {

    @Test
    public void testeLancaDadosRetornaDoisValores() {
        Dado dado = new Dado();
        int[] resultado = dado.lancaDados();
        
        // Verifica se o array tem exatamente 2 posições
        assertEquals(2, resultado.length);
    }

    @Test
    public void testeValoresDosDadosEstaoNoIntervaloCorreto() {
        Dado dado = new Dado();
        
        // nunca saia do intervalo de 1 a 6
        for (int i = 0; i < 59; i++) {
            int[] resultado = dado.lancaDados();
            
            for (int valor : resultado) {
                assertTrue("O valor do dado deve ser maior ou igual a 1", valor >= 1);
                assertTrue("O valor do dado deve ser menor ou igual a 6", valor <= 6);
            }
        }
    }
}