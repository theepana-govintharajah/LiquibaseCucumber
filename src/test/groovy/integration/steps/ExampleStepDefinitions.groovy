package integration.steps

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.springframework.beans.factory.annotation.Autowired

import javax.sql.DataSource
import java.beans.Statement
import java.sql.ResultSet

public class ExampleStepDefinitions {

    @Autowired
    private DataSource dataSource;

    @Given("^the database is initialized")
    public void the_database_is_initialized() throws Exception {
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

    @When("^data is inserted into the database")
    public void data_is_inserted_into_the_database() throws Exception {

            // Insert sample data into the database using JDBC
        String sql = "INSERT INTO users (username, email) VALUES (?, ?)";

            statement.setString(1, "user1");
            statement.setString(2, "user1@example.com");
            statement.executeUpdate();


    }

    @Then("^the data can be retrieved from the database")
    public void the_data_can_be_retrieved_from_the_database() throws Exception {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM example_table");

            // Verify that the data retrieved from the database is as expected
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("Retrieved data: id=" + id + ", name=" + name);
            }

    }

}
