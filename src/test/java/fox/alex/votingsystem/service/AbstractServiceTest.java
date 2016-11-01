package fox.alex.votingsystem.service;

import fox.alex.votingsystem.utils.JpaUtil;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

import static fox.alex.votingsystem.Profiles.ACTIVE_DB;
import static fox.alex.votingsystem.Profiles.DB_IMPLEMENTATION;

/**
 * Created by fox on 24.08.16.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"), executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles({ACTIVE_DB, DB_IMPLEMENTATION})
abstract public class AbstractServiceTest {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected JpaUtil jpaUtil;

    @After
    public void tearDown() throws Exception {
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        private void logInfo(Description description, long nanos) {
            log.info(String.format("+++ Test %s spent %d microseconds",
                    description.getMethodName(), TimeUnit.NANOSECONDS.toMicros(nanos)));
        }

        @Override
        protected void finished(long nanos, Description description) {
            logInfo(description, nanos);
        }
    };
}
