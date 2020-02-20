package fr.dalichamp.romain.ipFilterInterceptor.interceptors;

import fr.dalichamp.romain.ipFilterInterceptor.utils.AuthorizedIpAddresses;
import fr.dalichamp.romain.ipFilterInterceptor.utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Romain DALICHAMP - romain.dalichamp@alithya.com
 * <p>
 */
@Log4j2
public class AbstractInterceptor implements HandlerInterceptor {

    protected static final Set<String> ipWhitelist = new HashSet<>();
    protected static final Set<String> patternList = new HashSet<>();

    protected static final String unauthorizedIpAddressMessage = "CONNECTION REFUSED: Unauthorized IP address: ";
    protected static final String forbiddenAccessMessage = "CONNECTION REFUSED: Access is forbidden: ";

    protected static final List<String> localhostIps = new ArrayList<>();

    public AbstractInterceptor() {
        localhostIps.add(AuthorizedIpAddresses.LOCALHOST);
        localhostIps.add(AuthorizedIpAddresses.LOCAL);

        ipWhitelist.addAll(localhostIps);
    }

    /**
     * Set the response with a 401 Unauthorized message
     *
     * @param response
     * @param message
     * @throws IOException
     */
    protected void setUnauthorizedHttpServletResponse(HttpServletResponse response, int status,
            String message)
            throws IOException {
        response.setStatus(status);
        response.getWriter().write(message);
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * Check if the IP address is in the authorized whitelist
     */
    protected boolean isAuthorizedIpOrDnsAddress(String ipAddress, String clientServerName) {
        if (Utils.isElementInSetList(ipWhitelist, ipAddress) || isAuthorizedPattern(
                clientServerName)) {
            return true;
        }
        return false;
    }

    public boolean isAuthorizedPattern(String clientServerName) {
        for (String pattern : patternList) {
            if (clientServerName.matches(pattern)) {
                return true;
            }
        }
        return false;
    }

    public String getClientIpAddress(HttpServletRequest request) {
        return request.getRemoteAddr();
    }


    public String getClientServerName(HttpServletRequest request) {
        return request.getServerName();
    }

    public String getClientRemoteHost(HttpServletRequest request) {
        return request.getRemoteHost();
    }

    public String getClientRequestUri(HttpServletRequest request) {
        return request.getRequestURI();
    }

    public void logCustomRequestError(String ipAddress, String clientServerName, String remoteHost,
            String clientRequestUri, String message) {
        log.error(message + ipAddress +
                " with ServerName: " + clientServerName +
                " with RemoteHost: " + remoteHost +
                " on URI: " + clientRequestUri);
    }

    public void logIpConnectionOk(String ipAddress, String clientServerName, String remoteHost,
            String clientRequestUri, String message) {
        log.info(message + ipAddress +
                " with DNS: " + clientServerName +
                " with RemoteHost: " + remoteHost +
                " on URI: " + clientRequestUri);
    }

}
