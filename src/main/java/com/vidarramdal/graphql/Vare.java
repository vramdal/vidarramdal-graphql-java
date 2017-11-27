package com.vidarramdal.graphql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Vare")
public class Vare {

    @Id
    @Column
    private int id;

    @Column
    private String navn;

    public Vare() {
    }

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
