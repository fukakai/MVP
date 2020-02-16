package fr.dalichamp.romain.ipFilterInterceptor.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Romain DALICHAMP - romain.dalichamp@gmail.com
 * <p>
 */
@Log4j2
@RestController
@RequestMapping("securedcontroller")
public class SecuredController {

    @GetMapping("/securedroute")
    public String securedroute() {
        log.info("Your Ip is Authorized to access");
        return "Your Ip is Authorized to access !";
    }

    @GetMapping("/publicroute")
    public String publicroute() {
        log.info("Your Ip is Authorized to access");
        return "Your Ip is Authorized to access !";
    }
}
