package bg.tilchev.server.http.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created on 2017-02-12.
 */
public class SessionCreator {

    private static SessionCreator sessionCreator;

    private SecureRandom random;

    private SessionCreator() {
        this.random = new SecureRandom();
    }

    public static SessionCreator getInstance() {
        if (sessionCreator == null) {
            sessionCreator = new SessionCreator();
        }
        return sessionCreator;
    }

    public String generateSessionId() {
        return new BigInteger(130, this.random).toString(32);
    }
}
