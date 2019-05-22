package pages;

import HeaderPages.TokenRequestHeaders;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import utils.Apis;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class RequestTokenPage extends PageBase {

    public RequestTokenPage(RequestSpecification request, Response response, JSONObject json) {
        super(request, response, json);
    }

    public String getToken(String User, String Password){
        String token_type, access_token;

        baseURI = Apis.REQUEST_TOKEN;

        request = given().headers(TokenRequestHeaders.getTokenRequestHeaders());
        response =  request.post("as/token.oauth2?grant_type=password&username=" + User + "&password=" + Password);
        token_type = response.jsonPath().get("token_type");
        access_token = response.jsonPath().get("access_token");

        return token_type + " " + access_token;
    }
}
