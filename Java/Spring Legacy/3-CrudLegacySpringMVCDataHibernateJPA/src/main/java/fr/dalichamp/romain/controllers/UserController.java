package fr.dalichamp.romain.controllers;

import fr.dalichamp.romain.entities.aUser;
import fr.dalichamp.romain.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class UserController {
    private static final String USERLIST_PAGE = "userlist";

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET,path = "/userlist")
    public String getUserList(ModelMap model){
        model.addAttribute("users",userService.getUserList());

        return USERLIST_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST, path="/add")
    public String addUser(ModelMap model, @RequestParam String firstname, @RequestParam String lastname){
        aUser user = new aUser();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        userService.addUser(user);

        return "redirect:"+USERLIST_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST,path = "/delete")
    public String deleteUser(ModelMap model, @RequestParam Long id){
        userService.delUser(id);

        return "redirect:"+USERLIST_PAGE;
    }
}
