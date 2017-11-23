package com.vidarramdal.graphql;

import java.util.ArrayList;
import java.util.List;

public class VareRepository {

    private final List<Vare> varer = new ArrayList<>();

    public VareRepository() {
        varer.add(new Vare(1, "Brunost"));
        varer.add(new Vare(2, "Melblanding"));
        varer.add(new Vare(3, "Blomkålsuppe"));
        varer.add(new Vare(4, "Makrell i tomat"));
        varer.add(new Vare(5, "Melkesjokolade"));
    }

    public List<Vare> getAlleVarer() {
        return varer;
    }

    public void lagreVare(Vare vare) {
        varer.add(vare);
    }
}
