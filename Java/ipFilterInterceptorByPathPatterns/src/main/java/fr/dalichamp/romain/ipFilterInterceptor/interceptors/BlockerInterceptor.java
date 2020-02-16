package fr.dalichamp.romain.ipFilterInterceptor.interceptors;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Romain DALICHAMP - romain.dalichamp@gmail.com
 * <p>
 * Interceptor checking the IP adress before to continue on any WebNMvc root
 */
@Log4j2
@Component
public class BlockerInterceptor implements HandlerInterceptor {

    private final Set<String> whitelist = new HashSet<>(); // Stay Empty
    private final String forbiddenAccessMessage = "CONNECTION REFUSED: Access is forbidden";

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // Access is always forbidden to everybody
        log.error(forbiddenAccessMessage);
        setUnauthorizedHttpServletResponse(response, forbiddenAccessMessage);
        return false;
    }

    /**
     * Set the response with a 403 Forbidden message
     *
     * @param response
     * @param message
     * @throws IOException
     */
    public void setUnauthorizedHttpServletResponse(HttpServletResponse response, String message)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(message);
        response.getWriter().flush();
        response.getWriter().close();
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception exception) throws Exception {
    }
}