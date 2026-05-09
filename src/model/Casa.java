package model;

import java.util.ArrayList;
import java.util.List;

class Casa {
    private int id;
    private boolean ehComodo;
    private boolean ocupada;
    private List<Integer> vizinhos; // Grafo: IDs das casas adjacentes

    public Casa(int id, boolean ehComodo) {
        this.id = id;
        this.ehComodo = ehComodo;
        this.vizinhos = new ArrayList<>();
    }

    // Conecta esta casa a outra
    public void addVizinho(int idVizinho) {
        this.vizinhos.add(idVizinho);
    }

    public int getId() {
        return id;
    }

    public boolean isComodo() {
        return ehComodo;
    }

    public List<Integer> getVizinhos() {
        return vizinhos;
    }
    
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    // A Mágica da Regra aqui:
    public boolean isBloqueada() {
        // Se for cômodo, NUNCA está bloqueada. Se não for, depende do atributo 'ocupada'.
        if (this.ehComodo) {
            return false;
        }
        return this.ocupada;
    }
}