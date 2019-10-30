package fr.dalichamp.romain.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class welcome the user on the Home Page
 */
public class HomeController extends HttpServlet {
    private static final String HOME_PAGE = "/jsp/home.jsp";

    /**
     * Redirect user to HOME_PAGE
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(HOME_PAGE)
                .forward(request, response);
    }
}