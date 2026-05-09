package model;

import java.util.Random;

class Dado {
	
	private Random rand;
	
	public Dado() {
        this.rand = new Random();
    }
	
	public int[] lancaDados() {
		int dado1 = rand.nextInt(6)+1;
		int dado2 = rand.nextInt(6)+1;
		int[] dados = new int[]{dado1,dado2};
		
		return dados;
	}
	
	public void imprimeDados(int[] dados) {
		for(int dado : dados) {
		System.out.print(dado);
		}
	}
}
