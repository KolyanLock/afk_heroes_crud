package com.kolyanlock.afk_heroes_crud.dao.Fraction;

import com.kolyanlock.afk_heroes_crud.entity.Fraction;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
public class FractionCustomRepositoryImpl implements FractionCustomRepository{
    private final EntityManager em;

    @Override
    @Modifying
    public void update(String newTitle, String newDescription, String oldTitle) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Fraction> update = cb.createCriteriaUpdate(Fraction.class);

        Root<Fraction> root = update.from(Fraction.class);

        update.set("title", newTitle);
        update.set("description", newDescription);
        update.where(cb.equal(root.get("title"), oldTitle));
        em.createQuery(update).executeUpdate();
    }
}
