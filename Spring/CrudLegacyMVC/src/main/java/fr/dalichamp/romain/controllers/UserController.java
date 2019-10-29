package fr.dalichamp.romain.controllers;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import fr.dalichamp.romain.entities.aUser;
import fr.dalichamp.romain.servicies.UserService;

public class UserController extends HttpServlet{
    private static final String USERLIST_PAGE = "/jsp/userlist.jsp";

    private UserService userService;
    private HttpServletRequest request;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("users", userService.getUserList());

        getServletContext()
                .getRequestDispatcher(USERLIST_PAGE)
                .forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("======================> User Controller ID = "+request.getParameter("id"));

        switch(request.getParameter("action")){
            case "add" : addUser(request); break;
            case "delete" : deleteUser(request); break;
        }

        doGet(request, response);
    }

    public Long randomId(){
        long leftLimit = 1L;
        long rightLimit = 10L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    /**
     * Add User
     */
    public void addUser(HttpServletRequest request){
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        aUser user = new aUser();
        user.setId(randomId());
        user.setFirstname(firstname);
        user.setLastname(lastname);

        userService.addUser(user);
    }

    /**
     * Delete User
     */
    public void deleteUser(HttpServletRequest request){
        Long id = Long.parseLong(request.getParameter("id"));

        userService.delUser(id);
    }
}
