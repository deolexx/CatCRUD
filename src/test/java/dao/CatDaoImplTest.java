package dao;

import entity.Cat;
import lombok.SneakyThrows;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Testcontainers
class CatDaoImplTest {


    private static PostgreSQLContainer postgreSQLContainer;

    private static Connection connection=null;
    private static CatDaoImpl catDao = new CatDaoImpl();


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
    void findTestCase() {

    }

    @Test
    void findAllTestCase() {
        CatDaoImpl catDaoImpl = mock(CatDaoImpl.class);
        List<Cat> cats = Arrays.asList(new Cat(), new Cat());
        when(catDaoImpl.findAll()).thenReturn(cats);
        assertEquals(catDaoImpl.findAll().size(), 2);
    }

    @Test
    @SneakyThrows
    void isNewCatSavedInDataBase() {

        String breed="test", seller_name="test",seller_phone="test";
        int price=100,seller_id;
        Cat cat = new Cat(price,breed,seller_name,seller_phone);
        assertTrue(postgreSQLContainer.isRunning());

        assertTrue(catDao.save(cat));


    }

    @Test
    @SneakyThrows
    void update() {
        String breed="test", seller_name="test",seller_phone="test";
        int price=100,seller_id,cat_id=1;
        Cat cat = new Cat(price,breed,seller_name,seller_phone);
        catDao.save(cat);
        assertTrue(catDao.update(cat));

    }

    @Test
    @SneakyThrows
    void delete() {
        String breed="test", seller_name="test",seller_phone="test";
        int price=100,seller_id,cat_id=1;
        Cat cat = new Cat(cat_id,price,breed,seller_name,seller_phone);
        catDao.save(cat);
        assertTrue(catDao.delete(cat));
    }
}