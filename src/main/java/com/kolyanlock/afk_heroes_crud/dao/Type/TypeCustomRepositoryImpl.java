package com.kolyanlock.afk_heroes_crud.dao.Type;

import com.kolyanlock.afk_heroes_crud.entity.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
public class TypeCustomRepositoryImpl implements TypeCustomRepository {
    private final EntityManager em;

    @Override
    @Modifying
    public void update(String newType, String newDescription, String oldType) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Type> update = cb.createCriteriaUpdate(Type.class);

        Root<Type> root = update.from(Type.class);

        update.set("title", newType);
        update.set("description", newDescription);
        update.where(cb.equal(root.get("title"), oldType));
        em.createQuery(update).executeUpdate();
    }
}
