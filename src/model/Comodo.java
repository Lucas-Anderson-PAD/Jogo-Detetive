package model;

enum Comodo {
    COZINHA("Cozinha"),
    SALA_DE_BAILE("Sala de Baile"),
    JARDIM_DE_INVERNO("Jardim de Inverno"),
    SALA_DE_BILHAR("Sala de Bilhar"),
    BIBLIOTECA("Biblioteca"),
    ESCRITORIO("Escritório"),
    HALL("Hall"),
    SALA_DE_ESTAR("Sala de Estar"),
    SALA_DE_JANTAR("Sala de Jantar");

    private final String nome;

    Comodo(String nome) {
        this.nome = nome;
    }

    String getNome() {
        return nome;
    }
}