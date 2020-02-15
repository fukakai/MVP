package fr.dalichamp.romain.ipfilterspringsecurity.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Romain DALICHAMP - romain.dalichamp@gmail.com
 * <p>
 */
@Log4j2
@RestController
public class SampleController {

    @GetMapping("/ipTest")
    public String ipTest() {
        log.info("Your Ip is Authorized to access");
        return "Your Ip is Authorized to access";
    }
}
