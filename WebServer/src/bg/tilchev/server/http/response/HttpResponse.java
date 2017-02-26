package bg.tilchev.server.http.response;

public interface HttpResponse {

    String getResponse();

    void addResponseHeader(String header, String value);
}
