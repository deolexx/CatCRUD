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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class CatControllerTest {
    static private HttpServletRequest req;
    static private HttpServletResponse res;
    static private CatService catService;
    static private CatDaoImpl catDao;
    static private CatController catController;


    @BeforeEach
    public void setUp() {
        req = mock(HttpServletRequest.class);
        res = mock(HttpServletResponse.class);
        catDao = mock(CatDaoImpl.class);
        catService = mock(CatService.class);
        catController = mock(CatController.class);


    }

    @Test
    void doPost() {
        catController.doPost(req,res);

    }

    @Test
    void doGet() {
        when(req.getRequestDispatcher("jsp/cat-form.jsp")).thenReturn(new RequestDispatcher() {
            @Override
            public void forward(ServletRequest servletRequest, ServletResponse servletResponse) {

            }

            @Override
            public void include(ServletRequest servletRequest, ServletResponse servletResponse) {

            }
        });

        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("price")).thenReturn("100");
        when(req.getParameter("breed")).thenReturn("Siam");
        when(req.getParameter("seller_name")).thenReturn("Test");
        when(req.getParameter("seller_phone")).thenReturn("test");

        CatController catController = new CatController();
        catController.doGet(req,res);



    }
}