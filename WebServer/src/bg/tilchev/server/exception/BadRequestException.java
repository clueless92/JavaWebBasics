package bg.tilchev.server.exception;

/**
 * Created on 2017-02-12.
 */
public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
        super();
    }
}
