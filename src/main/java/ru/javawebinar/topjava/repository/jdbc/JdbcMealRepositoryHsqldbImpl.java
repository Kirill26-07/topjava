package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Profile(Profiles.HSQL_DB)
@Repository
public class JdbcMealRepositoryHsqldbImpl extends JdbcMealRepositoryImpl {

    protected Timestamp convert(LocalDateTime dateTime) {
        return Timestamp.valueOf(dateTime);
    }
}
