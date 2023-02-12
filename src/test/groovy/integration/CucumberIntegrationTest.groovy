package integration

import io.cucumber.java.Before
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired

import javax.sql.DataSource


@RunWith(Cucumber.class)
@CucumberOptions(features = ['src/test/resources/features']) //CLI runner options
public class CucumberIntegrationTest {

    @Autowired
    private DataSource dataSource;

    @Before
    public void setUp() {
        Liquibase liquibase = null;
        try {
            liquibase = new Liquibase("classpath:db/changelog/db.changelog-master.xml", new ClassLoaderResourceAccessor(), new JdbcConnection(dataSource.getConnection()));
            liquibase.update("test");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (liquibase != null) {
                liquibase.getDatabase().close();
            }
        }
    }
}

