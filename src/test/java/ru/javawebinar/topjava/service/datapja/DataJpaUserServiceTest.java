package ru.javawebinar.topjava.service.datapja;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserServiceTest;

import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends UserServiceTest {

    @Autowired
    private MealService mealService;

    @Test
    public void getWithMeals() throws Exception {

        final User user = userService.create(USER);

        List<Meal> meals = MEALS.stream().map(meal -> {
            return mealService.create(meal, user.getId());
        }).collect(Collectors.toList());

        Assert.assertEquals(meals, userService.getWithMeals(user.getId()).getMeals());
        Assert.assertEquals(user, userService.getWithMeals(user.getId()));
    }

    @Test
    public void getWithoutMeals() throws Exception {
        int id = ADMIN_ID + 1;
        String name = "User_without_meals";
        String email = "user_no_Meals@yandex.ru";
        String password = "password";

        User userNoMeals = new User(id, name, email, password, Role.ROLE_USER);
        User savedUser = userService.create(userNoMeals);

        userService.getWithMeals(savedUser.getId()); //todo

    }
}