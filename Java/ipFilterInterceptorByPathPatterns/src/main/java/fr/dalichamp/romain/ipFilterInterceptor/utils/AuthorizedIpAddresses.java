package fr.dalichamp.romain.ipFilterInterceptor.utils;

/**
 * @author Romain DALICHAMP - romain.dalichamp@alithya.com
 * <p>
 * TODO: Write Description
 */
public class AuthorizedIpAddresses {

    public static final String LOCALHOST = "0:0:0:0:0:0:0:1";
    public static final String LOCAL = "127.0.0.1";

    // AWS IP Adresses
    public static final String AWS_1 = "54.58.695.23"; // Random IP for Sample
    public static final String AWS_2 = "87.254.58.21"; // Random IP for Sample

    // Patterns
    public static final String DNS_PATTERN_AWS_1 = "^ec2-\\d{2,3}-\\d{2,3}-\\d{2,3}-\\d{2,3}\\.ca-central-1\\.compute\\.amazonaws\\.com$";
    public static final String DNS_PATTERN_AWS_2 = "^bullhubs(.*).\\w\\.ca-central-1\\.elasticbeanstalk\\.com$";
    public static final String DNS_PATTERN_AWS_3 = "^ec2-\\d{2,3}-\\d{2,3}-\\d{2,3}-\\d{2,3}\\.us-west-2\\.compute\\.amazonaws\\.com$"; // US
}
