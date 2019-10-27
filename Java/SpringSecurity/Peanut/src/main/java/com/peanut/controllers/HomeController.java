package com.peanut.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.peanut.models.User;
import com.peanut.services.UserService;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
	

    @RequestMapping(value={"/home"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        
        /**
         * org.springframework.security.core.userdetails.User@280fc89b: 
         * - Username: adress@email.com; 
         * - Password: [PROTECTED]; 
         * - Enabled: true; 
         * - AccountNonExpired: true; 
         * - credentialsNonExpired: true; 
         * - AccountNonLocked: true; 
         * - Granted Authorities: ADMIN
         */
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
        	UserDetails userDetails = (UserDetails)principal;
          modelAndView.addObject("userDetails",userDetails);
        }

        modelAndView.setViewName("home");
        return modelAndView;
    }
}
