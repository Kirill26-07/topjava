package ru.javawebinar.topjava.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {

    private final BeanPropertyRowMapper<Meal> rowMapper = BeanPropertyRowMapper.newInstance(Meal.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert simpleJdbcInsert;

    private static final String UPDATE_MEAL_QUARRY = "UPDATE meal SET date=:date, description=:description, calories=:calories WHERE id=:id";
    private static final String GET_MEAL_QUARRY = "SELECT date, description, calories FROM meal JOIN user_meals um on meal.id = um.meal_id JOIN users on um.user_id = users.id WHERE users.id =? AND meal.id =?";
    private static final String GET_ALL_MEAL_QUARRY = "SELECT date, description, calories FROM meal JOIN user_meals um on meal.id = um.meal_id JOIN users on um.user_id = users.id WHERE users.id =?";

    @Autowired
    public JdbcMealRepositoryImpl(final JdbcTemplate jdbcTemplate, final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("meal")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Meal save(Meal meal, Integer userId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("date", meal.getDate())
                .addValue("description", meal.getDescription())
                .addValue("calories", meal.getCalories());

        if (get(meal.getId(), userId) == null) {
            simpleJdbcInsert.executeAndReturnKey(mapSqlParameterSource);
        } else {
           namedParameterJdbcTemplate.update(UPDATE_MEAL_QUARRY, mapSqlParameterSource);
        }
        return meal;
    }

    @Override
    public boolean delete(Integer id, Integer userId) {
        return false;
    }

    @Override
    public Meal get(Integer id, Integer userId) {
        if (id == null) {
            return null;
        }
        return jdbcTemplate.queryForObject(GET_MEAL_QUARRY,rowMapper, userId, id);
    }

    @Override
    public List<Meal> getAll(Integer userId) {
        return jdbcTemplate.query(GET_ALL_MEAL_QUARRY, rowMapper, userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, Integer userId) {
        return null;
    }

}
