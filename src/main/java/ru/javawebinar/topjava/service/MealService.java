package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {

    List<Meal> add(Meal meal);

    List<Meal> remove(int mealId);

    List<Meal> getAll(int userId);

    List<Meal> update(int userId, int mealId, Meal newMeal);

}