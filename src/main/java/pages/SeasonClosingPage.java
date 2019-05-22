package pages;

import gherkin.lexer.Ru;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.Apis.POD_API;

public class SeasonClosingPage {

    public String env;
    public String user;
    public String token;
    public String HeadofficeDoc;
    public String year;
    public String ID;

    public String getHeadofficeDocWithoutSeasonClosing() {
        String headofficeDoc = null;
        Response AllHeadoffices;
        Response LoadResponse;
        AllHeadoffices = CommonMethods.SearchAllHeadoffices();
        if (AllHeadoffices.statusCode()!=200) {
            throw new RuntimeException("Error al cargar el json con todos los Headoffices " +
                    "Response status code: " + AllHeadoffices.statusCode());
        }
        String status;


         boolean founded = false;
         int i = 0;

        while (!founded ){

            headofficeDoc = AllHeadoffices.jsonPath().getString("results["+i+"].document.documentNumber");
            if (headofficeDoc== null){
                throw new RuntimeException("Error al cargar el documento del headoffice.");
            }

            LoadResponse = loadSeasonClosing(headofficeDoc,this.year);
            if (LoadResponse.statusCode()!= 200){
                throw new RuntimeException("Error al cargar el reporte de Season Closing (Load). " +
                        "Response status code: " + LoadResponse.statusCode());
                }

            status = LoadResponse.jsonPath().getString("results.status");

            if (status==null){
                founded =true;
            }

            i++;
            System.out.println("Iteración: "+ i + "." + "Headoffice: " + headofficeDoc);
        }

        return headofficeDoc;

    }



    public Response loadSeasonClosing(String headofficeDoc, String year){


        if (headofficeDoc==null){
            throw new RuntimeException("Error: HeadofficeDoc vacio");
            }

        Response response;
        RequestSpecification request;

        baseURI = POD_API;

        request= given()
                .header("Authorization",token)
                .header("Content-Type","application/json")
                .pathParam("year",year)
                .pathParam("headofficeDoc",headofficeDoc);

        response = request.get(env+"/pod/season-closing/load?year={year}&headofficeDocument={headofficeDoc}");

        return response;

    }

    public Response saveSeasonClosing() {

        RequestSpecification request;
        Response response;
        JSONObject json = new JSONObject();

        json.put("year", Integer.parseInt(year));
        json.put("headoffice",
                ((Response) CommonMethods.getHeadoffice(HeadofficeDoc, user)).body()
                        .jsonPath().getJsonObject("results[0]"));


        baseURI = POD_API;

        request= given()
                    .header("Authorization",token)
                    .header("Content-Type","application/json")
                    .body(json.toJSONString());

        response = request.post(env+"/pod/season-closing/save");

        this.ID= response.jsonPath().getString("results.id");
        if (response.statusCode()==200){
            return response;
        }else{
            throw new RuntimeException("Error al guardar el reporte de Season Closing.");
        }


    }

    public Response submitSeasonClosing() {

        RequestSpecification request;
        Response response;
        JSONObject json = new JSONObject();

        json.put("year", Integer.parseInt(year));
        json.put("headoffice",
                ((Response) CommonMethods.getHeadoffice(HeadofficeDoc, user)).body()
                        .jsonPath().getJsonObject("results[0]"));
        json.put("id",this.ID);

        baseURI = POD_API;

        request= given()
                .header("Authorization",token)
                .header("Content-Type","application/json")
                .body(json.toJSONString());

        response = request.post(env+"/pod/season-closing/submit");

        if (response.statusCode()==200){
            return response;
        }else{
            throw new RuntimeException("Error al subtmitear el reporte de Season Closing.");
        }
    }

    public Response rejectSeasonClosing() {

        RequestSpecification request;
        Response response;
        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();

        json.put("id",this.ID);
        json.put("comments","Rejected by an automated test");

        jsonArray.add(json);
        baseURI = POD_API;

        request= given()
                .header("Authorization",token)
                .header("Content-Type","application/json")
                .body(jsonArray.toJSONString());

        response = request.put(env+"/pod/season-closing/workflow/reject");

        if (response.statusCode()==200){
            return response;
        }else{
            throw new RuntimeException("Error al rechazar el reporte de Season Closing.");
        }
    }

    public Response approveSeasonClosing() {

        RequestSpecification request;
        Response response;
        String ID;

        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();

        json.put("id", this.ID);
        jsonArray.add(json);
        baseURI = POD_API;

        request= given()
                .header("Authorization",token)
                .header("Content-Type","application/json")
                .body(jsonArray.toJSONString());

        response = request.put(env+"/pod/season-closing/workflow/approve");

        if (response.statusCode()==200){
            return response;
        }else{
            throw new RuntimeException("Error al aprobar el reporte de Season Closing.");
        }
    }
}
