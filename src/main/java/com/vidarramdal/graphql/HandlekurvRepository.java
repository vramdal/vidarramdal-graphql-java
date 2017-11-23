package com.vidarramdal.graphql;

import java.util.ArrayList;
import java.util.List;

public class HandlekurvRepository {
    private List<Handlekurv> handlekurver = new ArrayList<>();

    public HandlekurvRepository() {
        this.handlekurver.add(new Handlekurv(1, "Gudbrand"));
        this.handlekurver.add(new Handlekurv(2, "Frank"));
        this.handlekurver.add(new Handlekurv(3, "Bjarne"));
        this.handlekurver.add(new Handlekurv(4, "Kåre"));
    }

    public List<Handlekurv> getAlleHandlekurver() {
        return handlekurver;
    }

    public Handlekurv getHandlekurvById(long id) {
        return this.handlekurver.stream().filter(handlekurv -> handlekurv.getId() == id).findFirst().orElse(null);
    }
}
