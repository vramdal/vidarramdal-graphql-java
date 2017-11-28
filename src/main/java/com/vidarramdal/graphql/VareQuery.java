package com.vidarramdal.graphql;

import io.leangen.graphql.annotations.GraphQLEnvironment;
import io.leangen.graphql.annotations.GraphQLQuery;

import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Handlekurv handlekurv(String id, @GraphQLEnvironment Set<String> wantedFields) {
        try {
            EntityManager em = PersistenceUtil.getEntityManager();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Handlekurv> criteria = criteriaBuilder.createQuery(Handlekurv.class);
            Root<Handlekurv> o = criteria.from(Handlekurv.class);
            Set<Field> joins = new HashSet<>();
            List<String> fields = Arrays.stream(Handlekurv.class.getFields()).map(Field::getName).collect(Collectors.toList());
            for (String wantedFieldName : wantedFields) {
                if (!fields.contains(wantedFieldName)) {
                    continue;
                }
                Field field = Handlekurv.class.getField(wantedFieldName);
                if (field.getAnnotation(OneToMany.class) != null) {
                    joins.add(field);
                }
            }
            for (Field join : joins) {
                o.fetch(join.getName());
            }
            criteria.select(o);
            if (id != null) {
                criteria.where(criteriaBuilder.equal(o.get("id"), Integer.parseInt(id)));
            }
            return em.createQuery(criteria).getSingleResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
