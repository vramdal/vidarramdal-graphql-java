package com.vidarramdal.graphql;

import javax.persistence.*;

@Entity
@Table(name = "Varelinje")
public class Varelinje {

    @ManyToOne()
    private Vare vare;

    @Column
    private int antall;

    @Column
    @Id
    private long id;

    @ManyToOne
    private Handlekurv handlekurv;

    public Varelinje() {
    }

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
