package fr.dalichamp.romain.ipFilterInterceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Romain DALICHAMP - romain.dalichamp@gmail.com
 * <p>
*  Sample of IP Filtering using an Interceptor
 */
@SpringBootApplication
public class IpFilterInterceptorMVP {

    public static void main(String[] args) {
        SpringApplication.run(IpFilterInterceptorMVP.class, args);
    }
}
