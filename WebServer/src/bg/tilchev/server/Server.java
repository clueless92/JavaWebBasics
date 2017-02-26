package bg.tilchev.server;

import java.net.SocketException;

/**
 * Created on 2017-02-12.
 */
public interface Server {

    void run() throws SocketException;
}
