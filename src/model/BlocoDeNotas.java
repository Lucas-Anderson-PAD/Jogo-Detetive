package model;

import java.util.HashMap;

class BlocoDeNotas {
    // Uso obrigatório de HashMap 
    private HashMap<String, Boolean> anotacoesSuspeitos;
    private HashMap<String, Boolean> anotacoesArmas;
    private HashMap<String, Boolean> anotacoesComodos;

    BlocoDeNotas() {
        anotacoesSuspeitos = new HashMap<>();
        anotacoesArmas = new HashMap<>();
        anotacoesComodos = new HashMap<>();

        // Inicializa todas as anotações como falsas (não riscadas)
        for (Suspeito s : Suspeito.values()) {
            anotacoesSuspeitos.put(s.getNome(), false);
        }
        for (Arma a : Arma.values()) {
            anotacoesArmas.put(a.getNome(), false);
        }
        for (Comodo c : Comodo.values()) {
            anotacoesComodos.put(c.getNome(), false);
        }
    }

    // Métodos para o jogador interagir com sua folha
    void riscarSuspeito(String nome) {
        anotacoesSuspeitos.put(nome, true);
    }

    void riscarArma(String nome) {
        anotacoesArmas.put(nome, true);
    }

    void riscarComodo(String nome) {
        anotacoesComodos.put(nome, true);
    }
    
    
    
    boolean isSuspeitoRiscado(String nome) {
        return anotacoesSuspeitos.getOrDefault(nome, false);
    }

    boolean isArmaRiscada(String nome) {
        return anotacoesArmas.getOrDefault(nome, false);
    }

    boolean isComodoRiscado(String nome) {
        return anotacoesComodos.getOrDefault(nome, false);
    }
    
    
    
    
    
    
}