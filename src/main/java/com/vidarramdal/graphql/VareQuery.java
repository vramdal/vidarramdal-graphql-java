package com.vidarramdal.graphql;

import io.leangen.graphql.annotations.GraphQLQuery;

import java.util.List;

public class VareQuery {

    private final VareRepository vareRepository;
    private final HandlekurvRepository handlekurvRepository;

    public VareQuery(VareRepository vareRepository, HandlekurvRepository handlekurvRepository) {
        this.vareRepository = vareRepository;
        this.handlekurvRepository = handlekurvRepository;
    }

    @GraphQLQuery
    public List<Vare> alleVarer() {
        return vareRepository.getAlleVarer();
    }

    @GraphQLQuery
    public List<Handlekurv> alleHandlekurver() {
        return handlekurvRepository.getAlleHandlekurver();
    }

    @GraphQLQuery
    public Handlekurv handlekurv(String id) {
        return handlekurvRepository.getHandlekurvById(Integer.parseInt(id));
    }
}
