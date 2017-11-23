package com.vidarramdal.graphql;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class VareRepository {

    private final List<Vare> varer = new ArrayList<>();

    private static final VareRepository instance = new VareRepository();
    private final Function<ResultSet, Vare> vareFactory = resultSet -> {
        try {
            return new Vare(resultSet.getInt("id"), resultSet.getString("navn"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    public static VareRepository getInstance() {
        return instance;
    }

    private VareRepository() {
    }

    public List<Vare> getAlleVarer() {
        return Database.getInstance().getAll("vare", vareFactory);
    }

    public Vare getVareById(int id) {
        return Database.getInstance().getById(id, "vare", vareFactory);
    }

    public void lagreVare(Vare vare) {
        varer.add(vare);
    }
}
