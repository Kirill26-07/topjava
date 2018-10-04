package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MealsUtil {
    public static void main(String[] args) {
        List<Meal> mealList = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<MealWithExceed> filteredUserMeal = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        filteredUserMeal.forEach(System.out::println);
    }

    public static List<MealWithExceed>  getFilteredWithExceeded(final List<Meal> mealList, final LocalTime startTime, final LocalTime endTime, final int caloriesPerDay) {
        return mealList.stream()
                .collect(Collectors.groupingBy(Meal::getLocalDate)).values()
                .stream()
                .flatMap(dailyMeal -> {
                    boolean isExceeded = dailyMeal.stream().mapToInt(Meal::getCalories).sum() > caloriesPerDay;
                    return dailyMeal.stream()
                            .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                            .map(meal -> new MealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), isExceeded));
                })
        .collect(Collectors.toList());
    }

}