package ru.javawebinar.topjava.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class MealWithExceed {

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;

}
