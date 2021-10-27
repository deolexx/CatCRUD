package controller;

import dao.DaoCat;
import entity.Cat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CatController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DaoCat daoCat = DaoCat.getInstance();
    private static final Logger logger = Logger.getLogger(CatController.class.getName());


    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(req, resp);
                    break;
                case "/insert":
                    insertCat(req, resp);
                    break;
                case "/delete":
                    deleteCat(req, resp);
                    break;
                case "/edit":
                    editCat(req, resp);
                    break;
                case "/update":
                    updateCat(req, resp);
                    break;
                default:
                    listCat(req, resp);
                    break;
            }
        } catch (SQLException e) {
            // For simplicity just Log the Exceptions
            logger.log(Level.SEVERE, "SQL Error", e);
        }


    }

    private void updateCat(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int price = Integer.parseInt(req.getParameter("price"));
        String breed = req.getParameter("breed");
        String seller = req.getParameter("seller");

        Cat cat = new Cat(id, price, breed, seller);
        daoCat.update(cat);
        resp.sendRedirect("list");
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String id = req.getParameter("id");
        Optional<Cat> existingCat = daoCat.find(id);
        // TODO: 27.10.2021 check jsp file location
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/CatForm.jsp");
        dispatcher.forward(req, resp);
    }

    private void deleteCat(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        Cat cat = new Cat(id);
        daoCat.delete(cat);

    }

}