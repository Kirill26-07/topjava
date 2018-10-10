package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryMealRepository implements Storage {

    private final ConcurrentMap<Long, Meal> meals = new ConcurrentHashMap<>();

    private static final int CALORIES_PER_DAY = 2000;

    private AtomicLong counter = new AtomicLong();

    public InMemoryMealRepository() {
                meals.put(0L, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
                meals.put(1L, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
                meals.put(2L, new Meal(LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин", 500));
                meals.put(3L, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
                meals.put(4L, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
                meals.put(5L, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public List<MealWithExceed> addMeal(final String description, final int calories) {
        meals.put(counter.incrementAndGet(), new Meal(LocalDateTime.now(), description, calories));
        return getAllMealWithExceeded();
    }

    @Override
    public List<MealWithExceed> deleteMeal(final long id) {
        meals.remove(id);
        return getAllMealWithExceeded();
    }

    public List<MealWithExceed> getAllMealWithExceeded() {
         return MealsUtil.getMealWithExceeded(meals, CALORIES_PER_DAY);
    }

}
