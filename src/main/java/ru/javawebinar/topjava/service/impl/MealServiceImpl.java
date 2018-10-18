package ru.javawebinar.topjava.service.impl;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    public MealServiceImpl(final MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Meal> add(final Meal meal) {
        int userId = SecurityUtil.authUserId();
        meal.setUserId(userId);
        repository.save(meal);
        return repository.getAll(userId);
    }

    @Override
    public List<Meal> remove(final int mealId) {
        repository.delete(mealId);
        return repository.getAll(SecurityUtil.authUserId());
    }

    @Override
    public List<Meal> getAll(final int userId) {
        return repository.getAll(SecurityUtil.authUserId());
    }

    @Override
    public List<Meal> update(final int userId, final int mealId, final Meal newMeal) {
        newMeal.setUserId(SecurityUtil.authUserId());
        repository.delete(mealId);
        repository.save(newMeal);
        return repository.getAll(userId);
    }

}