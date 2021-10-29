package controller;

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

    private static final CatService CAT_SERVICE = new CatService();


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }

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