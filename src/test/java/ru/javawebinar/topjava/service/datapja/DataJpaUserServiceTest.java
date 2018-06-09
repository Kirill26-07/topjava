package ru.javawebinar.topjava.service.datapja;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

@ActiveProfiles(value = {Profiles.POSTGRES_DB, Profiles.DATAJPA})
public class DataJpaUserServiceTest extends UserServiceTest {
}