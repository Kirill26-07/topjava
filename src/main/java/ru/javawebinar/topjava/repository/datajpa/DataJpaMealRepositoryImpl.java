package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DataJpaMealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudMealRepository crudRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew() && get(meal.getId(), userId) == null) {
            meal.setUser(crudUserRepository.getOne(userId));
            return crudRepository.save(meal);
        } else {
            return crudRepository.save(meal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        if (crudRepository.existsById(id)) {
            crudRepository.delete(id, userId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Meal get(int id, int userId) {
        return crudRepository.findByIdAndUserId(id, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.allSorted(userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudRepository.getBetween(startDate, endDate, userId);
    }

}
