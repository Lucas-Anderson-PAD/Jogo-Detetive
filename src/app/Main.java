package app;

import model.Jogo; // Importa apenas a API pública do Model
import java.util.*;

public class Main {
	
	
	
    public static void main(String[] args) {
        System.out.println("Iniciando simulação do Model (1ª Iteração)...");
        System.out.println("");
        
        // Instancia 
        Jogo meuJogo = new Jogo(); 
         
        
        // Testa o lançamento virtual de dados
        int[] dados = meuJogo.lancarDados();
        System.out.println("Valores dos dados: " + dados[0] + " e " + dados[1]);
        
       
        
    }
    
    
    
    
    
    
    
    
    
}



