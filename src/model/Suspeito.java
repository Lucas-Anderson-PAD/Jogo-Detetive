package model;

enum Suspeito {
    CORONEL_MUSTARD("Coronel Mustard"),
    SRTA_SCARLET("Srta. Scarlet"),
    PROFESSOR_PLUM("Professor Plum"),
    REVERENDO_GREEN("Reverendo Green"),
    SRA_WHITE("Sra. White"),
    SRA_PEACOCK("Sra. Peacock");

    private final String nome;


    Suspeito(String nome) { this.nome = nome; }
    String getNome() { return nome; }
    
} 