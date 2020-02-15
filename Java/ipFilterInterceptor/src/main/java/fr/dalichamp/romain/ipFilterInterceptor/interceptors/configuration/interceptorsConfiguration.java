package fr.dalichamp.romain.ipFilterInterceptor.interceptors.configuration;

import fr.dalichamp.romain.ipFilterInterceptor.interceptors.IpAddressInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Romain DALICHAMP - romain.dalichamp@gmail.com
 * <p>
 * add the interceptor to the WebMvc Configuration
 */
@Configuration
public class interceptorsConfiguration implements WebMvcConfigurer {

    private final IpAddressInterceptor ipAddressInterceptor;

    public interceptorsConfiguration(
            IpAddressInterceptor ipAddressInterceptor) {
        this.ipAddressInterceptor = ipAddressInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipAddressInterceptor);
    }

}
