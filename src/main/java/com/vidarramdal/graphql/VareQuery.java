package com.vidarramdal.graphql;

import io.leangen.graphql.annotations.GraphQLEnvironment;
import io.leangen.graphql.annotations.GraphQLQuery;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

public class VareQuery {

    public VareQuery() {
    }

    @GraphQLQuery
    public List<Vare> alleVarer() {
        Query query = PersistenceUtil.getEntityManager().createQuery("from Vare ");
        //noinspection unchecked
        return query.getResultList();
    }

    @GraphQLQuery
    public List<Handlekurv> alleHandlekurver() {
        Query query = PersistenceUtil.getEntityManager().createQuery("from Handlekurv ");
        //noinspection unchecked
        return query.getResultList();
    }

    @GraphQLQuery
    public Handlekurv handlekurv(String id, @GraphQLEnvironment Set<String> childfields) {
        try {
            EntityManager em = PersistenceUtil.getEntityManager();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Handlekurv> criteria = criteriaBuilder.createQuery(Handlekurv.class);
            Root<Handlekurv> o = criteria.from(Handlekurv.class);
            if (childfields.contains("varelinjer")) {
                o.fetch("varelinjer");
            }
            criteria.select(o);
            if (id != null) {
                criteria.where(criteriaBuilder.equal(o.get("id"), Integer.parseInt(id)));
            }
            return em.createQuery(criteria).getSingleResult();
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }
}
