package controller;

import dao.CatDaoImpl;
import service.CatService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main servlet class
 */
@WebServlet("/")
public class CatController extends HttpServlet {
    /**
     * Service instance
     */
    private static CatService CAT_SERVICE = new CatService(new CatDaoImpl());

    public static void setCatService(CatService catService) {
        CAT_SERVICE = catService;
    }

    /**
     * Additional controller methods which contains no logic
     * and just redirect requests/responses
     *
     * @param req  - call to redirect
     * @param resp - call to redirect
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }

    /**
     * Main method which operate servlet logic
     * and calls needed methods in service layer
     *
     * @param req  - incoming http request
     * @param resp - incoming http response
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getServletPath();
        switch (action) {
            case "/new":
                CAT_SERVICE.showNewForm(req, resp);
                break;
            case "/insert":
                CAT_SERVICE.insertCat(req, resp);
                break;
            case "/delete":
                CAT_SERVICE.deleteCat(req, resp);
                break;
            case "/edit":
                CAT_SERVICE.editCat(req, resp);
                break;
            case "/update":
                CAT_SERVICE.updateCat(req, resp);
                break;
            default:
                CAT_SERVICE.listCat(req, resp);
                break;
        }
    }


}