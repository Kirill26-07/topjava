package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InMamoryMealRepository {

    private final Set<Meal> mealSet = new HashSet<>();
    private final List<MealWithExceed> mealWithExceeds = new ArrayList<>();



}
