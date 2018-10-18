package ru.javawebinar.topjava.web.controller.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.dto.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.*;

@Controller
public class MealRestController {

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealWithExceed> addMeal(final Meal meal) {
        return getWithExceeded(service.add(meal), DEFAULT_CALORIES_PER_DAY);
    }

    public void removeMeal(final int mealId) {
        service.remove(mealId);
    }

    public List<MealWithExceed> getAllMeals(final int userId) {
        return getWithExceeded(service.getAll(userId), DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealWithExceed> updateMeal(final int userId, final int mealId, final Meal newMeal) {
        return getWithExceeded(service.update(userId, mealId, newMeal), DEFAULT_CALORIES_PER_DAY);
    }

}