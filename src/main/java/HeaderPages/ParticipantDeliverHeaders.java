package HeaderPages;

import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.util.ArrayList;
import java.util.List;

public class ParticipantDeliverHeaders extends RequestHeadersKeys {


    public static final String ACCEPT_VALUE = "application/json, text/plain, */*";
    public static final String ACCEPT_ENCODING_VALUE = "gzip, deflate, sdch, br";
    public static final String ACCEPT_LANGUAGE_VALUE = "en-US,en;q=0.8";
    public static final String CONNECTION_VALUE = "keep-alive";
    public static final String HOST_VALUE = "api01-np.agro.services";
    public static final String ORIGIN_VALUE = "https://vc-sale-registration-ui-it.velocity-np.ag";
    public static final String REFERER_VALUE = "https://vc-sale-registration-ui-it.velocity-np.ag/sale";
    public static final String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.59 Safari/537.36";
    public static final String CONTENT_TYPE_VALUE = "application/json";

    public static Headers getParticipantDeliverHeaders(String token){
        List<Header> headers = new ArrayList<>();
        headers.add(new Header(AUTH_KEY, token));
        headers.add(new Header(ACCEPT_KEY, ACCEPT_VALUE));
        headers.add(new Header(ACCEPT_ENCODING_KEY, ACCEPT_ENCODING_VALUE));
        headers.add(new Header(ACCEPT_LANGUAGE_KEY, ACCEPT_LANGUAGE_VALUE));
        headers.add(new Header(CONNECTION_KEY, CONNECTION_VALUE));
        headers.add(new Header(HOST_KEY, HOST_VALUE));
        headers.add(new Header(ORIGIN_KEY, ORIGIN_VALUE));
        headers.add(new Header(REFERER_KEY, REFERER_VALUE));
        headers.add(new Header(USER_AGENT_KEY, USER_AGENT_VALUE));
        headers.add(new Header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE));

        return new Headers(headers);
    }
}
