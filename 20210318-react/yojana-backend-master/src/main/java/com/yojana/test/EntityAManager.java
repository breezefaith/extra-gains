package com.yojana.test;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Dependent
public class EntityAManager implements Serializable {
    @PersistenceContext(unitName = "comp4911-pms-rest-jpa")
    EntityManager em;

    public EntityA find(int id) {
        return em.find(EntityA.class, id);
    }

    public List<EntityA> getAll() {
        TypedQuery<EntityA> q = em.createQuery("SELECT c FROM EntityA c", EntityA.class);
        List<EntityA> creds = q.getResultList();
        return creds;
    }
}
