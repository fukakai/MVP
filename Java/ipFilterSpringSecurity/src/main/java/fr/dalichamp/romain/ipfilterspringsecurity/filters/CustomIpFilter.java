package fr.dalichamp.romain.ipfilterspringsecurity.filters;

import fr.dalichamp.romain.ipfilterspringsecurity.utils.Utils;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

/**
 * @author Romain DALICHAMP - romain.dalichamp@gmail.com
 * <p>
 * Custom IP Filter
 */
@Log4j2
@WebFilter
public class CustomIpFilter implements Filter {

    private final Set<String> whitelist = new HashSet<>();
    private final String unauthorizedIpAddressMessage = "CONNECTION REFUSED: Unauthorized IP address: ";

    public CustomIpFilter() {
        whitelist.add("0:0:0:0:0:0:0:1"); //localhost
        whitelist.add("127.0.0.1");

        //request.getServerName(); -- litteral "localhost" name maybe implemented in the future
    }

    /**
     * Check the User IP Adress. If it is in the whitelist, accept the request, else print an error
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String ipAddress = request.getRemoteAddr();

        if (!isAuthorizedIpAdress(ipAddress)) {
            log.error(unauthorizedIpAddressMessage + ipAddress);
            setUnauthorizedHttpServletResponse(response, unauthorizedIpAddressMessage + ipAddress);
            return;
        }

        chain.doFilter(request, response); //Continue
    }

    /**
     * Set the response with a 403 Unauthorized message
     *
     * @param response
     * @param message
     * @throws IOException
     */
    public void setUnauthorizedHttpServletResponse(ServletResponse response, String message)
            throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write(message);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
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
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
