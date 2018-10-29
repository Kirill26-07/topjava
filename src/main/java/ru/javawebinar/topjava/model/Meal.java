package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal extends AbstractBaseEntity {

    private LocalDateTime date;

    private String description;

    private int calories;

    public Meal() {
    }

    public Meal(final LocalDateTime date, final String description, final int calories) {
        this(null, date, description, calories);
    }

    public Meal(final Integer id, final LocalDateTime date, final String description, final int calories) {
        super(id);
        this.date = date;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getLocalDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return date.toLocalDate();
    }

    public LocalTime getTime() {
        return date.toLocalTime();
    }

    public void setDate(final LocalDateTime date) {
        this.date = date;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setCalories(final int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }

}
