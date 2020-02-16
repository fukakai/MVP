package fr.dalichamp.romain.ipFilterInterceptor.interceptors;

import fr.dalichamp.romain.ipFilterInterceptor.utils.Utils;
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
public class IpAddressInterceptor implements HandlerInterceptor {

    private final Set<String> whitelist = new HashSet<>();
    private final String unauthorizedIpAddressMessage = "CONNECTION REFUSED: Unauthorized IP address: ";

    public IpAddressInterceptor() {
        whitelist.add("0:0:0:0:0:0:0:1"); // Localhost
        whitelist.add("127.0.0.1"); // Local adresse

        //request.getServerName(); -- litteral "localhost" name maybe implemented in the future
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (!isAuthorizedIpAdress(ipAddress)) {
            log.error(unauthorizedIpAddressMessage + ipAddress);
            setUnauthorizedHttpServletResponse(response, unauthorizedIpAddressMessage + ipAddress);
            return false;
        }
        return true;
    }

    /**
     * Set the response with a 403 Unauthorized message
     *
     * @param response
     * @param message
     * @throws IOException
     */
    public void setUnauthorizedHttpServletResponse(HttpServletResponse response, String message)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(message);
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * Check if the IP adress is in the authorized whitelist
     *
     * @param ipAddress
     * @return
     */
    public boolean isAuthorizedIpAdress(String ipAddress) {
        if (Utils.isElementInSetList(whitelist, ipAddress)) {
            return true;
        }
        return false;
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