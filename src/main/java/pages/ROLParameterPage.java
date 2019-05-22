package pages;

import gherkin.deps.com.google.gson.JsonObject;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.Apis.POD_API;

public class ROLParameterPage {

    public String user;
    public String enviroment;
    public String token;

    public  ROLParameterPage(String env){
        this.enviroment = env;
    }

    public Response getAllParameters (){
        Response response;
        RequestSpecification request;

        baseURI = POD_API;
        request = given();

        response= request.get(enviroment+"/pod/parameters/rol");

        return response;
    }

    public Response editParameter(String royalty){

        Response response;
        RequestSpecification request;
        JSONObject json=  new JSONObject();
        Response Parameters;

        Parameters = getAllParameters();

        json.put("rolParameter", Parameters.body().jsonPath().getJsonObject("results["+1+"]"));
        // EDITAR EL USER Y EL PARAMETER.
     //   ((Map)((List)((Map) json.get("rolParameter")).get("parameters")).get(0)).put("fixedVolume", fixedVolumeChanges.fixedVolume);
        ((Map)((Map)json.get("rolParameter")).get("parameters")).put("royalty",royalty);
        ((Map)json.get("rolParameter")).put("lastModificationUser",this.user);
        String id = (((Map)json.get("rolParameter")).get("id")).toString();
        baseURI = POD_API;
        request = given()
                    .header("Content-Type","application/json")
                    .header("Authorization",token)
                    .pathParam("PARAMETER_ID",id)
                    .pathParam("username", this.user)
                    .body(json.toJSONString());
        response = request.put(enviroment+"/pod/parameters/rol/{PARAMETER_ID}?username={username}");

        return response;
    }
}
