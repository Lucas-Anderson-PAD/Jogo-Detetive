package model;


//Sem o modificador "public"
enum Arma {
 CORDA("Corda"),
 CANO_DE_CHUMBO("Cano de Chumbo"),
 FACA("Faca"),
 CHAVE_INGLESA("Chave Inglesa"),
 CASTICAL("Castiçal"),
 REVOLVER("Revólver");

 private final String nome;

 Arma(String nome) {
     this.nome = nome;
 }

 String getNome() {
     return nome;
 }
}
