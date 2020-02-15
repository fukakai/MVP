package com.alithya.mvp.springvault.controller;


import com.alithya.mvp.springvault.config.VaultConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/vault")
public class VaultController{

    private final VaultConfiguration configuration;
    private String name = "world";

    VaultController(VaultConfiguration config){
        this.configuration = config;
    }

    @RequestMapping("/")
    public String home(){
        return "Hello "+this.configuration.getUrl();
    }

    @GetMapping("all")
    public void configs(){
        Logger logger = LoggerFactory.getLogger(VaultController.class);

        logger.info("----------------------------------------");
        logger.info("Vault - Configuration properties");
        logger.info("        example.username is {}", configuration.getUsername());
        logger.info("        example.password is {}", configuration.getPassword());
        logger.info("----------------------------------------");
    }

}