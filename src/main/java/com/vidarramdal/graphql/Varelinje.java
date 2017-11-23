package com.vidarramdal.graphql;

public class Varelinje {

    private final Vare vare;
    private final int antall;
    private final long id;

    public Varelinje(Vare vare, int antall, long id) {
        this.vare = vare;
        this.antall = antall;
        this.id = id;
    }

    public Vare getVare() {
        return vare;
    }

    public int getAntall() {
        return antall;
    }

    public long getId() {
        return id;
    }
}
