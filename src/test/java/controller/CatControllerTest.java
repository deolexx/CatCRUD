package controller;

import dao.CatDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.CatService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

class CatControllerTest {
    static private HttpServletRequest req;
    static private HttpServletResponse res;
    static private CatService catService;
    static private CatController catController;


    @BeforeEach
    public void setUp() {
        req = mock(HttpServletRequest.class);
        res = mock(HttpServletResponse.class);
        catService = mock(CatService.class);
        catController = mock(CatController.class);
        catController.setCatService(catService);

    }

    @Test
    void doPostMethodCallsDoGet() {
        doCallRealMethod().when(catController).doPost(req, res);
        catController.doGet(req, res);
        verify(catController).doGet(req, res);

    }

    @Test
    void doGetNew() {
        when(req.getRequestDispatcher("jsp/cat-list.jsp")).thenReturn(new RequestDispatcher() {
            @Override
            public void forward(ServletRequest servletRequest, ServletResponse servletResponse) {
            }

            @Override
            public void include(ServletRequest servletRequest, ServletResponse servletResponse) {
            }
        });

        when(req.getServletPath()).thenReturn("new");

        doCallRealMethod().when(catController).doGet(req, res);
        catController.doGet(req, res);
        verify(catService).listCat(req, res);

    }
}