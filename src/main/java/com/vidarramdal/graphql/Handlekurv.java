package com.vidarramdal.graphql;

import java.util.ArrayList;
import java.util.List;

public class Handlekurv {

    private final List<Varelinje> varelinjer = new ArrayList<>();
    private final int id;
    private final String eier;

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
