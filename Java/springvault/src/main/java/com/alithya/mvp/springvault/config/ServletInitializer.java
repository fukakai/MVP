package com.alithya.mvp.springvault.config;

import com.alithya.mvp.springvault.SpringvaultApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(SpringvaultApplication.class);
    }
}
