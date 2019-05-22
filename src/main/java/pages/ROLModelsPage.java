package pages;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.ROLModelsFilter;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.Apis.POD_API;
import static utils.Apis.VC_API01_NP_AGRO_SERVICES;

public class ROLModelsPage {

    String enviroment;
    public String user;
    public String token;

    public  ROLModelsPage(String env){

        this.enviroment = env;

    }

    public Response searchROLModels(ROLModelsFilter filters, int limit) {
        Response response;
        RequestSpecification request;
        JSONObject json = new JSONObject();
        JSONArray reportTypes = new JSONArray();

        if (filters.DETAILED){
            reportTypes.add("DETAILED");
        }

        if (filters.CONSOLIDATED){
            reportTypes.add("CONSOLIDATED");
        }

        if (filters.PARTIAL){
            reportTypes.add("PARTIAL");
        }

        if (filters.Headoffice_Document != null){
            json.put("matchText",filters.Headoffice_Document);
        }

        if (reportTypes.size()!= 0 ){
            json.put("reportTypes", reportTypes);
        }

        if (filters.User != null){
            json.put("username",filters.User);
        }


        if (limit!=-1){
            json.put("page",1);
            json.put("limit",limit);
        }

        baseURI = POD_API;
        request=  given()
                .header("Authorization", token)
                .header("Content-Type","application/json")
                .body(json.toJSONString());

        response= request.post(enviroment+"/pod/rol/model/search");

        return response;


    }

    public boolean verifyThatSearchFoundResults(Response search_response) {
        int count;

        count= Integer.parseInt(search_response.jsonPath().getString("count"));

        if (count >=1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean verifyThatSearchDontFoundResults(Response search_response) {
        int count;

        count= Integer.parseInt(search_response.jsonPath().getString("count"));

        if (count == 0 ){
            return true;
        }
        else{
            return false;
        }
    }

    public String getConsolidateReport() {

        Response response;
        ROLModelsFilter rolFilters = new ROLModelsFilter();
        rolFilters.CONSOLIDATED= true;
        response = searchROLModels(rolFilters,-1);
        String id;
        int count;

        count= Integer.parseInt(response.jsonPath().getString("count"));
        if (count>=1){

            id= response.jsonPath().getString("results[0].document");
        } else {
             id =  "No se encontraron resultados.";
        }

        return id;

    }

    public Response deleteROLModel(String id_to_delete) {
        Response response;
        RequestSpecification request;

        baseURI = POD_API;

        request = given()
                .header("Authorization", token)
                .pathParam("id_to_delete", id_to_delete);
        response= request.delete(enviroment+"/pod/rol/model/{id_to_delete}");

        return response;
    }

    public Response createROLModel(String reportType, String Headoffice_Document)  {
        Response response;
        Response response_getHO;
        RequestSpecification request;
        JSONObject json = new JSONObject();
        JSONObject ho_subjson = new JSONObject();
        JSONObject Headoffice_Json = new JSONObject();


        response_getHO = CommonMethods.getHeadoffice(Headoffice_Document, user);
        if (response_getHO.statusCode()!= 200){
            throw new RuntimeException("Falló al armar el Json del rol model. No se pudo obtener el headoffice." +
                    " Response status code:" + response_getHO.statusCode());
        }
        json.put("document",Headoffice_Document );
        json.put("headoffice", response_getHO.body().jsonPath().getJsonObject("results[0]"));
        json.put("reportType", reportType);

        baseURI = POD_API;

        request = given()
                .header("Authorization",token)
                .header("Content-Type", "application/json")
                .body(json.toJSONString())
                .pathParam("username", user);

        response= request.post(enviroment+"/pod/rol/model?username={username}");

        return response;
    }

    public Response editROLModel(String reportType, String headoffice_Document) {
        Response response;
        RequestSpecification request;
        JSONObject json = new JSONObject();

        json.put("reportType",reportType);

        baseURI = POD_API;

        request = given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .body(json.toJSONString())
                .pathParam("username", user)
                .pathParam("headoffice_document",headoffice_Document);

        response= request.put(enviroment+"/pod/rol/model/{headoffice_document}?username={username}");

        return response;
    }


}
