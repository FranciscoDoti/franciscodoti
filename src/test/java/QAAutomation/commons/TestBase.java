package QAAutomation.commons;



import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;


public class TestBase {
    public RequestSpecification request;
    public Response response;
    public JSONObject json;
    public String access_token;

}
