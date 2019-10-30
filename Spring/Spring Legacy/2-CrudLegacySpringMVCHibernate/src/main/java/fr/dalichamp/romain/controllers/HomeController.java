package fr.dalichamp.romain.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This class welcome the user on the Home Page
 */
@Controller
public class HomeController extends HttpServlet {
    private static final String HOME_PAGE = "home";

    @RequestMapping(method = RequestMethod.GET,path = "/")
    public String homePage(ModelMap mm){
        mm.addAttribute("title","Squad Alithya !");
        return HOME_PAGE;
    }
}