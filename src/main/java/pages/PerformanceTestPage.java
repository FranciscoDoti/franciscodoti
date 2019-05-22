package pages;

import gherkin.deps.com.google.gson.JsonArray;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.ReadJson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.Apis.POD_API;
import static utils.Apis.REQUEST_TOKEN;

public class PerformanceTestPage {

    public String token;
    public String env;
    public String user;

    public Response getToken(String user){

        String pass="";
        if (user.equals("STARK_A")){
            pass = "Monsanto3";
        }else if (user.equals("STARK_P")){
            pass = "Monsanto3";
        }
        Response response;
        RequestSpecification request;


        request = given()
                .header("Authorization","Basic UEQtQjJCLUNMSTo=")
                .header("Content-Type","application/x-www-form-urlencoded")
                .pathParam("username", user)
                .pathParam("password", pass);

        response = request.post(REQUEST_TOKEN+"as/token.oauth2?grant_type=password&username={username}&password={password}");

        return response;
    }

    public Response postFixedVolume() throws FileNotFoundException {

        JSONArray jsonArray = new JSONArray();
        File jsonDir = new File("jsons/PerformanceJsons");
        File jsonFile = new File(jsonDir, "postFixedVolume.json");
        jsonArray = ReadJson.getJson(new FileReader(jsonFile.getAbsolutePath()));


        Response response;
        RequestSpecification request;


        baseURI = POD_API;
        request = given()
                .body(jsonArray.toJSONString())
                .header("Content-Type","application/json")
                .header("Authorization", token)
                .pathParam("username",user);

        response = request.post(env+  "/pod/rol/fixed-volume?username={username}");



        return  response;
    }

    public Response listFixedVolumes() {

        RequestSpecification request;
        Response response;

        baseURI= POD_API;
        request=  given()
                    .header("Authorization",token)
                    .header("Content-Type","application/json")
                    .pathParam("1","77863223000107")
                    .pathParam("2","1")
                    .pathParam("3","5");

        response = request.get(env+"/pod/rol/fixed-volume?&partnerDocumentNumber={1}&page={2}&limit={3}");

        return response;
    }

    public Response listParameters() {

        RequestSpecification request;
        Response response;

        baseURI= POD_API;
        request=  given()
                .header("Authorization",token)
                .header("Content-Type","application/json");

        response = request.get(env+"/pod/parameters/rol");

        return response;
    }

    public Response listParticipantDeliveries() {

        RequestSpecification request;
        Response response;

        baseURI= POD_API;
        request=  given()
                .header("Authorization",token)
                .header("Content-Type","application/json")
                .pathParam("1","77863223000107")
                .pathParam("2","1")
                .pathParam("3","10");;

        response = request.get(env+"/pod/participant/delivery?receiverDocumentNumber={1}&page={2}&limit={3}");

        return response;
    }

    public Response exportParticipantDeliveries() throws FileNotFoundException {

        JSONArray jsonArray = new JSONArray();
        File jsonDir = new File("jsons/PerformanceJsons");
        File jsonFile = new File(jsonDir, "ExportParticipantDeliveries.json");
        jsonArray = ReadJson.getJson(new FileReader(jsonFile.getAbsolutePath()));


        Response response;
        RequestSpecification request;


        baseURI = POD_API;
        request = given()
                .body(jsonArray.toJSONString())
                .header("Content-Type","application/json")
                .header("Authorization", token);

        response = request.post(env+  "/pod/participant/delivery/xlsx/participant-delivery-report");



        return  response;

    }

    public Response postParticipantDelivery() throws FileNotFoundException {

        JSONArray jsonArray = new JSONArray();
        File jsonDir = new File("jsons/PerformanceJsons");
        File jsonFile = new File(jsonDir, "postParticipantDelivery.json");
        jsonArray = ReadJson.getJson(new FileReader(jsonFile.getAbsolutePath()));


        Response response;
        RequestSpecification request;


        baseURI = POD_API;
        request = given()
                .body(jsonArray.toJSONString())
                .header("Content-Type","application/json")
                .header("Authorization", token);

        response = request.post(env+  "/pod/participant/delivery");



        return  response;
    }

    public Response loadRol() throws FileNotFoundException {

        JSONArray jsonArray = new JSONArray();
        File jsonDir = new File("jsons/PerformanceJsons");
        File jsonFile = new File(jsonDir, "LoadRol.json");
        jsonArray = ReadJson.getJson(new FileReader(jsonFile.getAbsolutePath()));


        Response response;
        RequestSpecification request;


        baseURI = POD_API;
        request = given()
                .body(jsonArray.toJSONString())
                .header("Content-Type","application/json")
                .header("Authorization", token)
                .pathParam("1",user);

        response = request.post(env+  "/pod/rol/load?username={1}");



        return  response;
    }

    public Response listRolHistory() {

        RequestSpecification request;
        Response response;

        baseURI= POD_API;
        request=  given()
                .header("Authorization",token)
                .header("Content-Type","application/json")
                .pathParam("1","77863223000107")
                .pathParam("2","1")
                .pathParam("year","2019")
                .pathParam("3","10");;

        response = request.get(env+"/pod/rol/history?headofficeDocument={1}&year={year}&page={2}&limit={3}");

        return response;
    }

    public Response exportRolHistoryReport() {

        RequestSpecification request;
        Response response;

        baseURI= POD_API;
        request=  given()
                .header("Authorization",token)
                .header("Content-Type","application/json")
                .pathParam("1","77863223000107")
                .pathParam("user",user)
                .pathParam("year","2019");

        response = request.post(env+"/pod/rol/history/xlsx/rol/en-US" +
                "?username={user}&headofficeDocument={1}" +
                "&year={year}");

        return response;
    }

    public Response listRolModels() {

        RequestSpecification request;
        Response response;

        baseURI= POD_API;
        request=  given()
                .header("Authorization",token)
                .header("Content-Type","application/json");

        response = request.get(env+"/pod/rol/model/search");

        return response;
    }

    public Response listRolPendingRequests() {

        RequestSpecification request;
        Response response;

        baseURI= POD_API;
        request=  given()
                .header("Authorization",token)
                .header("Content-Type","application/json");

        response = request.get(env+"/pod/rol/workflow?status=OPEN&limit=0");

        return response;
    }

    public Response listRolsApprovedAndRejected() {

        RequestSpecification request;
        Response response;

        baseURI= POD_API;
        request=  given()
                .header("Authorization",token)
                .header("Content-Type","application/json");

        response = request.get(env+"/pod/rol/workflow?status=CLOSED&yearFrom=2014" +
                "&yearTo=2018&monthFrom=1&monthTo=12&page=1&limit=10");

        return response;

    }

    public Response loadSeasonClosing() {


        RequestSpecification request;
        Response response;

        baseURI= POD_API;
        request=  given()
                .header("Authorization",token)
                .header("Content-Type","application/json");

        response = request.get(env+"/pod/season-closing/load?year=2018&headofficeDocument=08707604000184");

        return response;

    }

    public Response listSeasonClosingPendingRequests() {

        RequestSpecification request;
        Response response;

        baseURI= POD_API;
        request=  given()
                .header("Authorization",token)
                .header("Content-Type","application/json");

        response = request.get(env+"pod/season-closing/workflow?year=2018&status=SUBMITTED&page=1&limit=10");



        return response;
    }

    public Response listApprovedAndRejectedSeasonClosingReports() {
        RequestSpecification request;
        Response response;

        baseURI= POD_API;
        request=  given()
                .header("Authorization",token)
                .header("Content-Type","application/json");

        response = request.get(env+"/pod/season-closing/workflow" +
                "?year=2018&status=APPROVED%2CREJECTED&page=1&limit=10");


        return response;
    }
}
