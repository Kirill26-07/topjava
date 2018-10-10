package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.MealWithExceed;
import java.util.List;

public interface Storage {

    List<MealWithExceed> addMeal(String description, int calories);

    List<MealWithExceed> deleteMeal(long id);

    List<MealWithExceed> getAllMealWithExceeded();

}
