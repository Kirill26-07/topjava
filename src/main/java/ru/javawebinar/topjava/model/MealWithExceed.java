package ru.javawebinar.topjava.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MealWithExceed {

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;

    public MealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

}
