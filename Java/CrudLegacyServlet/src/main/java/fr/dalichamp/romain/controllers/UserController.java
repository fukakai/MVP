package fr.dalichamp.romain.controllers;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dalichamp.romain.entities.User;
import fr.dalichamp.romain.servicies.UserService;

/**
 * @DoGet and @DoPost Methods
 * This class use the UserService to operate database operation as defined by the user
 */
public class UserController extends HttpServlet{
    private static final String USERLIST_PAGE = "/jsp/userlist.jsp";

    //User Service, using database
    private UserService userService;

    /**
     * Init User Service
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        try {
            userService = new UserService();
        }catch(Exception e){
            throw e;
            }
    }

    /**
     * Get User list and set a new attribute as request parameter
     * rredirect user to USERLIST_PAGE
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("users", userService.getUserList());

        getServletContext()
                .getRequestDispatcher(USERLIST_PAGE)
                .forward(request, response);
    }

    /**
     * Add or Delete user depending on the "action" parameters sent by the user
     * Redirect to @GetMethods, then  to USERLIST_PAGE
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch(request.getParameter("action")){
            case "add" : addUser(request); break;
            case "delete" : deleteUser(request); break;
        }

        doGet(request, response);
    }

    /**
     * Create a random Long Id for every New User
     * @return
     */
    public Long randomId(){
        long leftLimit = 1L;
        long rightLimit = 10L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    /**
     * Create a new User object, and call UserService to save it
     * @param request
     */
    public void addUser(HttpServletRequest request){
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        User user = new User();
        user.setId(randomId());
        user.setFirstname(firstname);
        user.setLastname(lastname);

        userService.addUser(user);
    }

    /**
     * Delete user with the Long id sent in parameter
     * @param request
     */
    public void deleteUser(HttpServletRequest request){
        Long id = Long.parseLong(request.getParameter("id"));

        userService.delUser(id);
    }
}
