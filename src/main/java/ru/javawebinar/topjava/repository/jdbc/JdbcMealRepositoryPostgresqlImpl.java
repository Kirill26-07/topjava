package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;

import java.time.LocalDateTime;

@Profile(Profiles.POSTGRES_DB)
@Repository
public class JdbcMealRepositoryPostgresqlImpl extends JdbcMealRepositoryImpl {

    @Override
    protected Object convert(LocalDateTime dateTime) {
        return dateTime;
    }

}
