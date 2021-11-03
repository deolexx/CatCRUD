package service;

import dao.CatDaoImpl;
import entity.Cat;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.mockito.Mockito.*;

class CatServiceTest {


    static private HttpServletRequest req;
    static private HttpServletResponse res;
    static private CatService catService;
    static private CatDaoImpl catDao;


    @BeforeEach
    public void setUp() {
        req = mock(HttpServletRequest.class);
        res = mock(HttpServletResponse.class);
        catDao = mock(CatDaoImpl.class);
        catService = new CatService(catDao);


    }


    @Test
    @SneakyThrows
    void WhenEditCatCalledThenFindCatTriggered() {
        when(catDao.find("1")).thenReturn(Optional.of(new Cat()));

        when(req.getRequestDispatcher("jsp/cat-form.jsp")).thenReturn(new RequestDispatcher() {
            @Override
            public void forward(ServletRequest servletRequest, ServletResponse servletResponse) {
            }

            @Override
            public void include(ServletRequest servletRequest, ServletResponse servletResponse) {

            }
        });
        catService.editCat(req, res);


        verify(catDao).find(null);
    }

    @Test
    void whenListCatUsedThenCatDaoFindAllTriggered() {
        when(req.getRequestDispatcher("jsp/cat-list.jsp")).thenReturn(new RequestDispatcher() {
            @Override
            public void forward(ServletRequest servletRequest, ServletResponse servletResponse) {
            }

            @Override
            public void include(ServletRequest servletRequest, ServletResponse servletResponse) {

            }
        });
    catService.listCat(req,res);

    verify(catDao).findAll();
    }

    @Test
    @SneakyThrows
    void whenUpdateCatUsedCatDaoUpdateTriggered() {
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("price")).thenReturn("100");
        when(req.getParameter("breed")).thenReturn("Siam");
        when(req.getParameter("seller_name")).thenReturn("Test");
        when(req.getParameter("seller_phone")).thenReturn("test");
        catService.updateCat(req, res);
        verify(catDao).update(any(Cat.class));
    }

    @Test
    void whenShowNewFormCalledPageRedirectedToCatFormJsp() {


        when(req.getRequestDispatcher("jsp/cat-form.jsp")).thenReturn(new RequestDispatcher() {
            @Override
            public void forward(ServletRequest servletRequest, ServletResponse servletResponse) {

            }

            @Override
            public void include(ServletRequest servletRequest, ServletResponse servletResponse) {

            }
        });


    }

    @Test
    @SneakyThrows
    void whenDeleteCatUsedCatDaoDeleteTriggered() {
        when(req.getParameter("id")).thenReturn("1");
        catService.deleteCat(req, res);
        verify(catDao).delete(any(Cat.class));
    }






    @Test
    @SneakyThrows
    void whenInsertCatCalledNewCatSavedInDb() {

        when(req.getParameter("price")).thenReturn("100");
        when(req.getParameter("breed")).thenReturn("Siam");
        when(req.getParameter("seller_name")).thenReturn("Test");
        when(req.getParameter("seller_phone")).thenReturn("test");
        catService.insertCat(req, res);
        verify(catDao).save(any(Cat.class));


    }
}