package com.vidarramdal.graphql;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Database {

    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private String dbName="webshop";
    private String connectionURL = "jdbc:derby:" + dbName + ";create=true";

    private static Database instance;

    private Database() {
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public  void init() {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("select versjon from system");
                resultSet.next();
                int versjon = resultSet.getInt("versjon");
                if (versjon == 1) {
                    System.out.println("Database finnes allerede");
                    return;
                }
            } catch (Throwable t) {
                System.out.println("Systemtabell finnes ikke, kjører opprettingsscript");
            }
            String createSql = String.join("\n", Files.readAllLines(Paths.get(this.getClass().getResource("/db.sql").toURI()), Charset.defaultCharset()));
            String[] queries = createSql.split("\\;");
            for (String query : queries) {
                if (query.trim().isEmpty()) {
                    continue;
                }
                System.out.println("query = " + query);
                try  (Statement statement = connection.createStatement()){
                    boolean result = statement.execute(query);
                    System.out.println("result = " + result);
                }
            }
        }  catch (Throwable e)  {
            e.printStackTrace();
        }
    }

    private Connection getConnection()  {
        try {
            return DriverManager.getConnection(connectionURL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private interface ResultSetConsumer extends Consumer<ResultSet> {
        default void consume(ResultSet resultSet) {
            try {
                this.accept(resultSet);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private <T> void executeQuery(Consumer<PreparedStatement> setParameters, String sql, Consumer<ResultSet> resultSetConsumer) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            setParameters.accept(statement);
            ResultSet resultSet = statement.executeQuery();
            resultSetConsumer.accept(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> List<T> select(String sql, Consumer<PreparedStatement> statementPreparator, Function<ResultSet, T> factory) {
        List<T> result = new ArrayList<T>();
        this.executeQuery(statementPreparator, sql, resultSet -> {
            try {
                while (resultSet.next()) {
                    result.add(factory.apply(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return result;
    }

    <T> T getById(int id, String table, Function<ResultSet, T> factory) {
        List<T> list = this.select("select * from " + table + " where id = ? ",
                preparedStatement -> {
                    try {
                        preparedStatement.setInt(1, id);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                },
                factory);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    <T> List<T> getAll(String table, Function<ResultSet, T> factory) {
        return this.select("select * from " + table, preparedStatement -> {}, factory);
    }

}

