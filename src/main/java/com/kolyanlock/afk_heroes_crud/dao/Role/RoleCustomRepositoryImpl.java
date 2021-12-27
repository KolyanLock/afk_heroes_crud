package com.kolyanlock.afk_heroes_crud.dao.Role;

import com.kolyanlock.afk_heroes_crud.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
public class RoleCustomRepositoryImpl implements RoleCustomRepository {
    private final EntityManager em;

    @Override
    @Modifying
    public void update(String newTitle, String newDescription, String oldTitle) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Role> update = cb.createCriteriaUpdate(Role.class);

        Root<Role> root = update.from(Role.class);

        update.set("title", newTitle);
        update.set("description", newDescription);
        update.where(cb.equal(root.get("title"), oldTitle));
        em.createQuery(update).executeUpdate();
    }
}
