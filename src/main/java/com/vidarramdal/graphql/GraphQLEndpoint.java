package com.vidarramdal.graphql;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import io.leangen.graphql.GraphQLSchemaGenerator;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    private final Database database;

    public GraphQLEndpoint() {
        super(buildSchema());
        database = Database.getInstance();
        database.init();
    }

    @NotNull
    private static GraphQLSchema buildSchema() {
        VareRepository vareRepository = VareRepository.getInstance();
        HandlekurvRepository handlekurvRepository = new HandlekurvRepository();
        VareQuery query = new VareQuery(vareRepository, handlekurvRepository);
        return new GraphQLSchemaGenerator()
                .withOperationsFromSingleton(query)
                .generate();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}