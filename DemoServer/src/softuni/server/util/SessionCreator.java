package softuni.server.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by s_she on 09.2.2017 г..
 */
public class SessionCreator {

    private SecureRandom random;

    private static SessionCreator sessionCreator;

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
