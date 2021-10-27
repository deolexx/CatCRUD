package controller;

import dao.CatDaoImpl;
import entity.Cat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/")
public class CatController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CatDaoImpl catDaoImpl = CatDaoImpl.getInstance();
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

    private void editCat(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String id = req.getParameter("id");
        Optional<Cat> existingCat = catDaoImpl.find(id);
        // TODO: 27.10.2021 check address
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/cat-form.jsp");
        existingCat.ifPresent(c -> req.setAttribute("cat", c));
        dispatcher.forward(req, resp);
    }

    private void listCat(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<Cat> listCat = catDaoImpl.findAll();
        req.setAttribute("listCat", listCat);
        // TODO: 27.10.2021 check this adress
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/cat-list.jsp");
        dispatcher.forward(req, resp);
    }

    private void updateCat(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int price = Integer.parseInt(req.getParameter("price"));
        String breed = req.getParameter("breed");
        String seller = req.getParameter("seller");

        Cat cat = new Cat(id, price, breed, seller);
        catDaoImpl.update(cat);
        resp.sendRedirect("list");
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {


        // TODO: 27.10.2021 check jsp file location
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/cat-form.jsp");
        dispatcher.forward(req, resp);
    }

    private void deleteCat(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Cat cat = new Cat(id);
        catDaoImpl.delete(cat);
        resp.sendRedirect("list");

    }


    private void insertCat(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int price = Integer.parseInt(req.getParameter("price"));
        String breed = req.getParameter("breed");
        String seller = req.getParameter("seller");
        Cat newCat = new Cat(price, breed, seller);
        catDaoImpl.save(newCat);
        resp.sendRedirect("list");
    }
}