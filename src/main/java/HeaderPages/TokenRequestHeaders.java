package HeaderPages;

import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.util.ArrayList;
import java.util.List;

public class TokenRequestHeaders  extends RequestHeadersKeys {
    public static final String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";

    public static Headers getTokenRequestHeaders(){
        List<Header> headers = new ArrayList<Header>();
        headers.add(new Header(AUTH_KEY, "Basic UEQtVkFMVUUtQ0FQVFVSRS1VSTpicldFTXdVVzRvbDhFdEhHcjBNS2dGVnAwbnprT29weUt6TjJTSlRGVkx0YUtvREpMcA=="));
        headers.add(new Header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE));

        return new Headers(headers);
    }



}
