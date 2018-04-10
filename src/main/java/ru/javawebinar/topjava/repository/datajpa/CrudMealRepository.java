package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    List<Meal> findByUserIdOrderByIdDesc(int userId);

    Meal findByIdAndUserId(int id, int userId);

    List<Meal> findByDateTimeIsBetweenAndUserIdOrderByIdDesc(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
