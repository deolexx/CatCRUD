package dao;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;
@Testcontainers
class JDBCPostgresConnectionTest {

    JDBCPostgresConnection connection = new JDBCPostgresConnection();

    PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer();
    @Container
    private final PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer()
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");


    @Test
    void run_Container()  {
        assertTrue(postgresqlContainer.isRunning());
        assertTrue(postgresqlContainer.isRunning());

    }


    @Test

    void getConnection() {
        assertNotNull(connection);
    }
}