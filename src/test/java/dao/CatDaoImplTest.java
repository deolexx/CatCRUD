package dao;

import entity.Cat;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class CatDaoImplTest {


    private static PostgreSQLContainer postgreSQLContainer;

    private static Connection connection = null;
    private static final CatDaoImpl catDao = new CatDaoImpl();


    @BeforeAll
    public static void init() {

        postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:13-alpine")
                .withExposedPorts(5432);
        postgreSQLContainer
                .withDatabaseName("test")
                .withUsername("test")
                .withPassword("test")
                .withInitScript("create_tables.sql");
        postgreSQLContainer.start();
        assertTrue(postgreSQLContainer.isRunning());
        System.out.println(postgreSQLContainer.getJdbcUrl());
        System.out.println(postgreSQLContainer.getUsername());
        System.out.println(postgreSQLContainer.getPassword());

        try {
            connection = DriverManager.getConnection(postgreSQLContainer.getJdbcUrl(), postgreSQLContainer.getUsername(), postgreSQLContainer.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        catDao.setConnection(connection);
    }


    @Test
    @SneakyThrows
    void findInDBByID() {
        String breed = "test", seller_name = "test", seller_phone = "test";
        int price = 100;
        Cat cat = new Cat(price, breed, seller_name, seller_phone);
        assertTrue(catDao.save(cat));
        Optional<Cat> cat1 = catDao.find("1");
        cat1.ifPresent(cat2 -> assertEquals(1, cat2.getId()));
    }

    @Test
    void findAllCatsInsideDB() {
        List<Cat> cats = catDao.findAll();
        assertFalse(cats.isEmpty());
        assertEquals(2, cats.size());
    }

    @Test
    @SneakyThrows
    void isNewCatSavedInDataBase() {

        String breed = "test", seller_name = "test", seller_phone = "test";
        int price = 100;
        Cat cat = new Cat(price, breed, seller_name, seller_phone);
        assertTrue(postgreSQLContainer.isRunning());

        assertTrue(catDao.save(cat));


    }

    @Test
    @SneakyThrows
    void updateCatInsideDB() {
        String breed = "test", seller_name = "test", seller_phone = "test";
        int price = 100, cat_id = 2;
        Cat cat = new Cat(cat_id, price, breed, seller_name, seller_phone);
        catDao.save(cat);
        assertTrue(catDao.update(cat));

    }

    @Test
    @SneakyThrows
    void deleteCatFromDB() {
        String breed = "test", seller_name = "test", seller_phone = "test";
        int price = 100, cat_id = 1;
        Cat cat = new Cat(cat_id, price, breed, seller_name, seller_phone);
        catDao.save(cat);
        assertTrue(catDao.delete(cat));
    }
}