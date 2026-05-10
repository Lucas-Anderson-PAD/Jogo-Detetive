package model;

import java.util.ArrayList;
import java.util.List;

class Casa {
    private int id;
    private String nomeComodo; 
    private boolean ehComodo;
    private Integer idPassagemSecreta; 
    private boolean ocupada;
    private List<Integer> vizinhos; // Grafo: IDs das casas adjacentes
    
    public Casa(int id, boolean ehComodo) {
        this.id = id;
        this.ehComodo = ehComodo;
        this.vizinhos = new ArrayList<>();
    }

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
    
    public boolean temPassagemSecreta() {
    	return this.idPassagemSecreta !=null;
    }
    
    public Integer getIdPassagemSecreta() {
    	return this.idPassagemSecreta;
    }
    
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public boolean isBloqueada() {
        if (this.ehComodo) {
            return false;
        }
        return this.ocupada;
    }
}