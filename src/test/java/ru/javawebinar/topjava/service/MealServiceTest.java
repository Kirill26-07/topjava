package ru.javawebinar.topjava.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.Month;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    private final Logger logger = LoggerFactory.getLogger(MealServiceTest.class);

    @Autowired
    private MealService service;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Rule
    public final TestName testName = new TestName();

    @Rule
    public final TestWatcher testWatcher = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            testStartTime = System.currentTimeMillis();
        }

        @Override
        protected void finished(Description description) {
            testFinishTime = System.currentTimeMillis();
            logger.info("\nTEST - " + name + ";\n" + "Test time = " + (testFinishTime - testStartTime));
        }
    };

    private String name;
    private Long testStartTime;
    private Long testFinishTime;

    @Test
    public void delete() {
        name = testName.getMethodName();
        service.delete(MEAL1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), MEAL6, MEAL5, MEAL4, MEAL3, MEAL2);
    }

    @Test
    public void deleteNotFound() {
        name = testName.getMethodName();
        exception.expect(NoResultException.class);
        service.delete(MEAL1_ID, 1);
    }

    @Test
    public void save() {
        name = testName.getMethodName();
        Meal created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), created, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void get() {
        name = testName.getMethodName();
        Meal actual = service.get(ADMIN_MEAL_ID, ADMIN_ID);
        assertMatch(actual, ADMIN_MEAL1);
    }

    @Test
    public void getNotFound() {
        name = testName.getMethodName();
        exception.expect(NoResultException.class);
        service.get(MEAL1_ID, ADMIN_ID);
    }

    @Test
    public void update() {
        name = testName.getMethodName();
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(MEAL1_ID, USER_ID), updated);
    }

    @Test
    public void getAll() {
        name = testName.getMethodName();
        assertMatch(service.getAll(USER_ID), MEALS);
    }

    @Test
    public void getBetween() {
        name = testName.getMethodName();
        assertMatch(service.getBetweenDates(
                LocalDate.of(2015, Month.MAY, 30),
                LocalDate.of(2015, Month.MAY, 30), USER_ID), MEAL3, MEAL2, MEAL1);
    }

}