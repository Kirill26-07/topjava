package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMealsUtil {

    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> filteredUserMeal = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredUserMeal.forEach(System.out::println);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(final List<UserMeal> mealList, final LocalTime startTime, final LocalTime endTime, final int caloriesPerDay) {
        List<UserMealWithExceed> filteredList = new ArrayList<>();
        for (UserMeal userMel : mealList) {
            if (TimeUtil.isBetween(userMel.getDateTime().toLocalTime(), startTime, endTime)) {
                filteredList.add(new UserMealWithExceed(userMel.getDateTime(), userMel.getDescription(), userMel.getCalories(), !(userMel.getCalories() <= caloriesPerDay)));
            }
        }
        return filteredList;
    }

}
