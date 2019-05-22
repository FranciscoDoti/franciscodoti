package pages;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;


public class PageBase {
    protected RequestSpecification request;
    protected Response response;
    protected JSONObject json;


    public PageBase(RequestSpecification request, Response response, JSONObject json){
        this.request = request;
        this.response = response;
        this.json = json;
    }
}

