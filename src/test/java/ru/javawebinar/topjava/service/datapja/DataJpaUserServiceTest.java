package ru.javawebinar.topjava.service.datapja;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserServiceTest;

import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.UserTestData.USER;

@ActiveProfiles(value = {Profiles.POSTGRES_DB, Profiles.DATAJPA})
public class DataJpaUserServiceTest extends UserServiceTest {

    @Autowired
    private MealService mealService;

    @Test
    public void getWithMeals() throws Exception {

        final User user = service.create(USER);

        List<Meal> meals = MEALS.stream().map(meal -> {
            return mealService.create(meal, user.getId());
        }).collect(Collectors.toList());

        Assert.assertEquals(meals, service.getWithMeals(user.getId()).getMeals());
        Assert.assertEquals(user, service.getWithMeals(user.getId()));
    }
}