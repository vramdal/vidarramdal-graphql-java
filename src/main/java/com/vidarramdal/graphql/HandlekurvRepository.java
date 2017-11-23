package com.vidarramdal.graphql;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HandlekurvRepository {
    private final Function<ResultSet, Handlekurv> handlekurvFactory = resultSet -> {
        try {
            return new Handlekurv(resultSet.getInt("id"), resultSet.getString("eier"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };
    private List<Handlekurv> handlekurver = new ArrayList<>();
    private int varelinjeIdSequence = 0;

    public HandlekurvRepository() {
/*
        Handlekurv gudbrand = new Handlekurv(1, "Gudbrand");
        leggVareIHandlekurv(gudbrand, lagVarelinje(getVare(0)));
        leggVareIHandlekurv(gudbrand, lagVarelinje(getVare(1)));
        this.handlekurver.add(gudbrand);
        Handlekurv frank = new Handlekurv(2, "Frank");
        leggVareIHandlekurv(frank, lagVarelinje(getVare(1)));
        leggVareIHandlekurv(frank, lagVarelinje(getVare(2)));
        this.handlekurver.add(frank);
        Handlekurv bjarne = new Handlekurv(3, "Bjarne");
        leggVareIHandlekurv(bjarne, lagVarelinje(getVare(2)));
        leggVareIHandlekurv(bjarne, lagVarelinje(getVare(3)));
        leggVareIHandlekurv(bjarne, lagVarelinje(getVare(4)));
        this.handlekurver.add(bjarne);
        Handlekurv kaare = new Handlekurv(4, "Kåre");
        leggVareIHandlekurv(kaare, lagVarelinje(getVare(4)));
        this.handlekurver.add(kaare);
*/
    }

    @NotNull
    private Varelinje lagVarelinje(Vare vare) {
        return new Varelinje(vare, new Double(Math.ceil(Math.random() * 10)).intValue(), varelinjeIdSequence++);
    }

    private boolean leggVareIHandlekurv(Handlekurv handlekurv, Varelinje varelinje) {
        return handlekurv.getVarelinjer().add(varelinje);
    }

    public List<Handlekurv> getAlleHandlekurver() {
        return Database.getInstance().getAll("handlekurv", handlekurvFactory);
    }

    public Handlekurv getHandlekurvById(int id) {
        //return this.handlekurver.stream().filter(handlekurv -> handlekurv.getId() == id).findFirst().orElse(null);
        return Database.getInstance().getById(id, "handlekurv", handlekurvFactory);
    }
}
