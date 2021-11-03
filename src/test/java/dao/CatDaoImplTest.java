package dao;

import entity.Cat;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Testcontainers
class CatDaoImplTest {


    @Test
    void findTestCase() {
        CatDaoImpl catDaoImpl = Mockito.mock(CatDaoImpl.class);
        Mockito.when(catDaoImpl.find("1")).thenReturn(java.util.Optional.of(new Cat(1, "breed", "seller")));
        assertNotNull(catDaoImpl.find("1"));
    }

    @Test
    void findAllTestCase() {
        CatDaoImpl catDaoImpl = mock(CatDaoImpl.class);
        List<Cat> cats = Arrays.asList(new Cat(), new Cat());
        when(catDaoImpl.findAll()).thenReturn(cats);
        assertEquals(catDaoImpl.findAll().size(), 2);
    }

    @Test
    void save() {

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}