package fr.dalichamp.romain.ipFilterInterceptor.interceptors;

import fr.dalichamp.romain.ipFilterInterceptor.utils.AuthorizedIpAddresses;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @author Romain DALICHAMP - romain.dalichamp@gmail.com
 * <p>
 * Interceptor checking the IP adress before to continue on any WebNMvc root
 */
@Log4j2
@Component
public class AwsInterceptor extends AbstractInterceptor {

    private final Set<String> whitelist = new HashSet<>();
    private final String unauthorizedIpAddressMessage = "CONNECTION REFUSED: Unauthorized IP address: ";

    public AwsInterceptor() {
        patternList.add(AuthorizedIpAddresses.DNS_PATTERN_AWS_1);
        patternList.add(AuthorizedIpAddresses.DNS_PATTERN_AWS_2);
        patternList.add(AuthorizedIpAddresses.DNS_PATTERN_AWS_3);

        ipWhitelist.add(AuthorizedIpAddresses.AWS_1);
        ipWhitelist.add(AuthorizedIpAddresses.AWS_2);
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String ipAddress = getClientIpAddress(request);
        String clientServerName = getClientServerName(request);
        String clientRequestUri = getClientRequestUri(request);
        String remoteHost = getClientRemoteHost(request);

        if (!isAuthorizedIpOrDnsAddress(ipAddress, clientServerName)) {
            logCustomRequestError(ipAddress, clientServerName, remoteHost, clientRequestUri,
                    unauthorizedIpAddressMessage);
            setUnauthorizedHttpServletResponse(response, HttpServletResponse.SC_UNAUTHORIZED,
                    "AWS " + unauthorizedIpAddressMessage + ipAddress);
            return false;
        }
        logIpConnectionOk(ipAddress, clientServerName, remoteHost, clientRequestUri,
                "Connection Authorized on AWS with IP: " + ipAddress);
        return true;
    }
}