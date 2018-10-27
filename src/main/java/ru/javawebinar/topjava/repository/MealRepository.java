package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {
    // null if updated meal do not belong dto userId
    Meal save(Meal meal, Integer userId);

    // false if meal do not belong dto userId
    boolean delete(Integer id, Integer userId);

    // null if meal do not belong dto userId
    Meal get(Integer id, Integer userId);

    // ORDERED dateTime desc
    List<Meal> getAll(Integer userId);

    // ORDERED dateTime desc
    List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, Integer userId);
}
