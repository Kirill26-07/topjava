package ru.javawebinar.topjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class UserMealWithExceed {

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;

}
