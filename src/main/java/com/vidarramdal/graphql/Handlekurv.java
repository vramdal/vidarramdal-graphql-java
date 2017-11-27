package com.vidarramdal.graphql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "handlekurv")
public class Handlekurv {

    @OneToMany(mappedBy = "handlekurv")
    private List<Varelinje> varelinjer = new ArrayList<>();

    @Id()
    @Column(name = "id")
    private int id;
    @Column(name = "eier")
    private String eier;

    public Handlekurv() {
    }

    public Handlekurv(int id, String eier) {
        this.id = id;
        this.eier = eier;
    }

    public List<Varelinje> getVarelinjer() {
        return varelinjer;
    }

    public int getId() {
        return id;
    }

    public String getEier() {
        return eier;
    }
}
