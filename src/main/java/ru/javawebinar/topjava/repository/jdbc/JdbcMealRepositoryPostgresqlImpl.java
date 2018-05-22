package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;

import javax.sql.DataSource;

@Profile(Profiles.POSTGRES_DB)
@Repository
public class JdbcMealRepositoryPostgresqlImpl extends JdbcMealRepositoryImpl {

    @Autowired
    public JdbcMealRepositoryPostgresqlImpl(DataSource dataSource,
                                            JdbcTemplate jdbcTemplate,
                                            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        init(dataSource, jdbcTemplate, namedParameterJdbcTemplate);
    }
}
