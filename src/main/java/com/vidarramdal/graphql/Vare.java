package com.vidarramdal.graphql;

public class Vare {

    private final int id;
    private final String navn;


    public Vare(int id, String navn) {
        this.id = id;
        this.navn = navn;
    }

    public int getId() {
        return id;
    }

    public String getNavn() {
        return navn;
    }
}
