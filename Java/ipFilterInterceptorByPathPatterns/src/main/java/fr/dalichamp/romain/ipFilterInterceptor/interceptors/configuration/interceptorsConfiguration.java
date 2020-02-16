package fr.dalichamp.romain.ipFilterInterceptor.interceptors.configuration;

import fr.dalichamp.romain.ipFilterInterceptor.interceptors.BlockerInterceptor;
import fr.dalichamp.romain.ipFilterInterceptor.interceptors.IpAddressInterceptor;
import java.util.ArrayList;
import java.util.List;
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
    private final BlockerInterceptor blockerInterceptor;

    private final List<String> securedRoutesList = new ArrayList<>();

    private final String applicationWebContextRoute = "/**";
    private final String securedRoute = "/securedcontroller/securedroute";

    public interceptorsConfiguration(
            IpAddressInterceptor ipAddressInterceptor,
            BlockerInterceptor blockerInterceptor) {
        this.ipAddressInterceptor = ipAddressInterceptor;
        this.blockerInterceptor = blockerInterceptor;

        securedRoutesList.add(securedRoute);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Block access for all ips to all the routes except for the secured one
        registry.addInterceptor(blockerInterceptor)
                .addPathPatterns(applicationWebContextRoute)
                .excludePathPatterns(securedRoutesList);

        // Authorize access to the whitelist of this interceptor on this route
        registry.addInterceptor(ipAddressInterceptor)
                .addPathPatterns(securedRoute); // the interceptor is used only for this route
    }

}
