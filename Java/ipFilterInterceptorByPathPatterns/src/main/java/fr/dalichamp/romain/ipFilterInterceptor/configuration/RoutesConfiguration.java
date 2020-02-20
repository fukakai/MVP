package fr.dalichamp.romain.ipFilterInterceptor.configuration;

import fr.dalichamp.romain.ipFilterInterceptor.interceptors.AwsInterceptor;
import fr.dalichamp.romain.ipFilterInterceptor.interceptors.DenyAllInterceptor;
import fr.dalichamp.romain.ipFilterInterceptor.interceptors.DevelopersInterceptor;
import fr.dalichamp.romain.ipFilterInterceptor.utils.Routes;
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
public class RoutesConfiguration implements WebMvcConfigurer {

    private final AwsInterceptor awsInterceptor;
    private final DevelopersInterceptor developersInterceptor;
    private final DenyAllInterceptor blockerInterceptor;

    private final List<String> securedRoutesList = new ArrayList<>();

    private final List<String> developersRoutes = new ArrayList<>();
    private final List<String> awsRoutes = new ArrayList<>();

    private final String applicationWebContextRoute = "/**";

    public RoutesConfiguration(
            AwsInterceptor awsInterceptor,
            DevelopersInterceptor developersInterceptor,
            DenyAllInterceptor blockerInterceptor) {
        this.awsInterceptor = awsInterceptor;
        this.developersInterceptor = developersInterceptor;
        this.blockerInterceptor = blockerInterceptor;

        developersRoutes.add(Routes.SECURED_ROUTE);
//        developersRoutes.add(....);
//        developersRoutes.add(....);

//        awsRoutes.add(....);
//        awsRoutes.add(....);
//        awsRoutes.add(....);
//        awsRoutes.add(....);\

        securedRoutesList.addAll(developersRoutes);
//        securedRoutesList.addAll(awsRoutes);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Block access for all ips to all the routes except for the secured one
        registry.addInterceptor(blockerInterceptor)
                .addPathPatterns(applicationWebContextRoute)
                .excludePathPatterns(securedRoutesList);

        registry.addInterceptor(developersInterceptor).addPathPatterns(developersRoutes);
        registry.addInterceptor(awsInterceptor).addPathPatterns(awsRoutes);
    }

}
