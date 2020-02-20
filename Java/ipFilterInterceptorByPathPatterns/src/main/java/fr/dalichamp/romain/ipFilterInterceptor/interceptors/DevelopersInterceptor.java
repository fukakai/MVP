package fr.dalichamp.romain.ipFilterInterceptor.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @author Romain DALICHAMP - romain.dalichamp@gmail.com
 * <p>
 * Interceptor checking the IP address before to continue on any WebNMvc root
 */
@Log4j2
@Component
public class DevelopersInterceptor extends AbstractInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {

        String ipAddress = getClientIpAddress(request);
        String clientServerName = getClientServerName(request);
        String clientRequestUri = getClientRequestUri(request);
        String remoteHost = getClientRemoteHost(request);

        if (!isAuthorizedIpOrDnsAddress(ipAddress, clientServerName)) {
            logCustomRequestError(ipAddress, clientServerName, remoteHost, clientRequestUri,
                    unauthorizedIpAddressMessage);
            setUnauthorizedHttpServletResponse(response, HttpServletResponse.SC_UNAUTHORIZED,
                    "Developers " + unauthorizedIpAddressMessage + ipAddress);
            return false;
        }
        logIpConnectionOk(ipAddress, clientServerName, remoteHost, clientRequestUri,
                "Connection Authorized on Developers with IP: " + ipAddress);
        return true;
    }
}