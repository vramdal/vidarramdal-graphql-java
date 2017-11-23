package com.vidarramdal.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;

import java.util.Collections;
import java.util.List;

public class VareQuery implements GraphQLResolver<Void> {

    private final VareRepository vareRepository;
    private final HandlekurvRepository handlekurvRepository;

    public VareQuery(VareRepository vareRepository, HandlekurvRepository handlekurvRepository) {
        this.vareRepository = vareRepository;
        this.handlekurvRepository = handlekurvRepository;
    }

    public List<Vare> alleVarer() {
        return vareRepository.getAlleVarer();
    }

    public List<Handlekurv> alleHandlekurver() {
        return handlekurvRepository.getAlleHandlekurver();
    }

    public Handlekurv handlekurv(String id) {
        return handlekurvRepository.getHandlekurvById(Long.parseLong(id));
    }
}
