package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudMealRepository repository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setUser(crudUserRepository.getOne(userId));
            return repository.save(meal);
        } else {
            Meal m = repository.getOne(meal.getId());
            if (m.getUser().getId() == userId) {
                meal.setUser(crudUserRepository.getOne(userId));
                return repository.save(meal);
            } else {
                return null;
            }
        }
    }

    @Transactional
    @Override
    public boolean delete(int id, int userId) {
        return repository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.findByIdAndUserId(id, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.findByUserIdOrderByDateTimeDesc(userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return repository.findByDateTimeIsBetweenAndUserIdOrderByDateTimeDesc(startDate, endDate, userId);
    }
}
