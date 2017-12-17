package ru.javawebinar.topjava.service.jdbc;

import org.junit.Assume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import java.util.Arrays;

import static ru.javawebinar.topjava.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {

    @Autowired
    Environment environment;

    @Override
    public void setUp() throws Exception {
        getCacheManager().getCache("users").clear();
    }

    @Override
    public void testValidation() throws Exception {
        Assume.assumeTrue(!Arrays.asList(environment.getActiveProfiles()).contains("jdbc"));
        super.testValidation();
    }
}