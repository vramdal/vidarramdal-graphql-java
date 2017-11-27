package com.vidarramdal.graphql;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.function.Consumer;

class Database {

    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String dbName="webshop";
    private static String connectionURL = "jdbc:derby:" + dbName ;

    private static Database instance;

    private Database() {
    }

    static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> instance.shutdown()));
        }
        return instance;
    }

    void shutdown() {
        try {
            DriverManager.getConnection(
                    connectionURL + ";shutdown=true");
        } catch (SQLException ignore) {
        }
    }

    void init() {
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
            return DriverManager.getConnection(connectionURL + ";create=true");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

