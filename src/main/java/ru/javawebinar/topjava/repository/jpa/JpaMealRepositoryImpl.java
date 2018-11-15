package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(final Meal meal, final int userId) {
        meal.setUser(em.getReference(User.class, userId));
        em.persist(meal);
        return meal;
    }

    @Override
    @Transactional
    public Meal update(final Meal meal, final int userId) {
        meal.setUser(em.getReference(User.class, userId));
        em.merge(meal);
        return meal;
    }

    @Override
    @Transactional
    public boolean delete(final int id, final int userId) {
        final Meal meal = get(id, userId);
        em.remove(meal);
        return true;
    }

    @Override
    public Meal get(final int id, final int userId) {
        return em.createNamedQuery(Meal.GET, Meal.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Override
    public List<Meal> getAll(final int userId) {
        return em.createNamedQuery(Meal.GET_ALL, Meal.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetween(final LocalDateTime startDate, final LocalDateTime endDate, final int userId) {
        return em.createNamedQuery(Meal.BETWEEN, Meal.class)
                .setParameter("startDate", startDate)
                .setParameter("andDate", endDate)
                .setParameter("userId", userId)
                .getResultList();
    }

}