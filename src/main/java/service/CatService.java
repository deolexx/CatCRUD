package service;

import dao.CatDaoImpl;
import entity.Cat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CatService {
    private final CatDaoImpl catDaoImpl;

    public CatService(CatDaoImpl catDaoImpl) {
        this.catDaoImpl = catDaoImpl;
    }


    /**
     * Find cat by id, and if cat exists, redirect to
     * cat edit form
     *
     * @param req  - HttpServletRequest
     * @param resp - HttpServletResponse
     */
    public void editCat(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        Optional<Cat> existingCat;

        existingCat = catDaoImpl.find(id);


        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/cat-form.jsp");
        existingCat.ifPresent(c -> req.setAttribute("cat", c));
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show all cats found
     *
     * @param req  - HttpServletRequest
     * @param resp - HttpServletResponse
     */
    public void listCat(HttpServletRequest req, HttpServletResponse resp) {
        List<Cat> listCat;
        listCat = catDaoImpl.findAll();
        req.setAttribute("listCat", listCat);

        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/cat-list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates info of an existing cat
     *
     * @param req  - HttpServletRequest
     * @param resp - HttpServletResponse
     */
    public void updateCat(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        int price = Integer.parseInt(req.getParameter("price"));
        String breed = req.getParameter("breed");
        String seller_name = req.getParameter("seller_name");
        String seller_phone = req.getParameter("seller_phone");

        Cat cat = new Cat(id, price, breed, seller_name, seller_phone);
        try {
            catDaoImpl.update(cat);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            resp.sendRedirect("list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows cat edit form
     *
     * @param req  - HttpServletRequest
     * @param resp - HttpServletResponse
     */
    public void showNewForm(HttpServletRequest req, HttpServletResponse resp) {

        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/cat-form.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteCat(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Cat cat = new Cat(id);
        try {
            catDaoImpl.delete(cat);

            resp.sendRedirect("list");


        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create new cat
     *
     * @param req  - HttpServletRequest
     * @param resp - HttpServletResponse
     */
    public void insertCat(HttpServletRequest req, HttpServletResponse resp) {
        int price = Integer.parseInt(req.getParameter("price"));
        String breed = req.getParameter("breed");
        String seller_name = req.getParameter("seller_name");
        String seller_phone = req.getParameter("seller_phone");
        Cat newCat = new Cat(price, breed, seller_name, seller_phone);
        try {
            catDaoImpl.save(newCat);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            resp.sendRedirect("list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
