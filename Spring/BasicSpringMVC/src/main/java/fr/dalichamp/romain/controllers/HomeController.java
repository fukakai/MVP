package fr.dalichamp.romain.controllers;

import fr.dalichamp.romain.entities.User;
import fr.dalichamp.romain.repositories.UserRepository;
import fr.dalichamp.romain.servicies.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class HomeController {

    @Autowired
    private UserService userService;
    private static final String HOME_PAGE = "home";

    @RequestMapping(method = RequestMethod.GET,path = "/")
    public String homePage(ModelMap mm){
        mm.addAttribute("title","Squad Alithya !");
        return HOME_PAGE;
    }

    @RequestMapping(method = RequestMethod.GET,path = "/userlist")
    public List<User> getUserList(){
        return userService.getUserList();
    }
    //TODO: Write Post Methods, DAO, HTML and connect to Database
}
