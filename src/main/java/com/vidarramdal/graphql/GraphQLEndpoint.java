package com.vidarramdal.graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import javax.servlet.annotation.WebServlet;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import org.jetbrains.annotations.NotNull;


@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    @NotNull
    private static GraphQLSchema buildSchema() {
        VareRepository vareRepository = new VareRepository();
        HandlekurvRepository handlekurvRepository = new HandlekurvRepository();
        return SchemaParser.newParser()
                .file("schema.graphqls") //parse the schema file created earlier
                .resolvers(new VareQuery(vareRepository, handlekurvRepository))
                .dictionary("VareQuery", VareQuery.class)
                .build()
                .makeExecutableSchema();
    }
}