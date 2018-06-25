package ru.javawebinar.topjava.service.datapja;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends UserServiceTest {

    @Test
    public void getWithMeals() throws Exception {
        User u = userService.getWithMeals(USER_ID);
        Assert.assertEquals(MEALS, u.getMeals());
    }

    @Test
    public void getWithoutMeals() throws Exception {
        final User user = new User();
        user.setName("newUser");
        user.setEmail("1@1.1");
        user.setPassword("123Qwe");
        User newUser = userService.create(user);
        Assert.assertEquals(userService.getWithMeals(newUser.getId()).getMeals().size(), 0);
    }
}