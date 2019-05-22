package pages;
import io.restassured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.WorkflowFilters;

import java.util.ArrayList;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.Apis.POD_API;
import static utils.EnviromentsConfig.CURRENT_ENV;

public class WorkflowPage {

    public WorkflowFilters filters;
    public String user;
    public String token;

    public WorkflowPage() {
        this.filters = new WorkflowFilters();
    }

    public Response searchROL() {
        Response response;
        RequestSpecification request;

        baseURI = POD_API;

        String queryParameters = "";

        if (filters.yearFrom != null) {
            queryParameters = queryParameters + "&yearFrom=" + filters.yearFrom;
        }
        if (filters.yearTo != null) {
            queryParameters = queryParameters + "&yearTo=" + filters.yearTo;
        }

        if (filters.monthFrom != null) {
            queryParameters = queryParameters + "&monthFrom=" + filters.monthFrom;
        }
        if (filters.monthTo != null) {
            queryParameters = queryParameters + "&monthTo=" + filters.monthTo;
        }


        if (filters.docNumber != null) {
            queryParameters = queryParameters + "&podMatchText=" + filters.docNumber;
        }

        if (filters.region != null) {
            queryParameters = queryParameters + "&district=" + filters.region;
        }


        request = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token);

        response = request.get(CURRENT_ENV + "/pod/rol/workflow?status=OPEN" + queryParameters);

        return response;
    }

    public Response rejectRol(String rolId, ArrayList comments) {
        Response response;
        RequestSpecification request;
        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();
        comments.add("Automation test comment");
        json.put("rolId", rolId);
        json.put("comments",comments);


        jsonArray.add(json);


        baseURI = POD_API;

        request = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(jsonArray.toJSONString());

        response = request.post(CURRENT_ENV + "/pod/rol/workflow/reject");

        return response;

    }

    public Response approveROL(String rolId) {
        Response response;
        RequestSpecification request;
        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();

        json.put("rolId", rolId);
        jsonArray.add(json);


        baseURI = POD_API;

        request = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(jsonArray.toJSONString());

        response = request.post(CURRENT_ENV + "/pod/rol/workflow/approve");

        return response;
    }

    public Response searchApprovedRejectedROL() {

        Response response;
        RequestSpecification request;

        baseURI = POD_API;

        String queryParameters = "";

        if (filters.yearFrom != null) {
            queryParameters = queryParameters + "&yearFrom=" + filters.yearFrom;
        }
        if (filters.yearTo != null) {
            queryParameters = queryParameters + "&yearTo=" + filters.yearTo;
        }

        if (filters.monthFrom != null) {
            queryParameters = queryParameters + "&monthFrom=" + filters.monthFrom;
        }
        if (filters.monthTo != null) {
            queryParameters = queryParameters + "&monthTo=" + filters.monthTo;
        }


        if (filters.docNumber != null) {
            queryParameters = queryParameters + "&podMatchText=" + filters.docNumber;
        }

        if (filters.region != null) {
            queryParameters = queryParameters + "&district=" + filters.region;
        }


        request = given()
                .header("Authorization", token)
                .header("Accept", "application/json, text/plain, */*")
                .header("Origin", "https://pod-inc-dev.velocity-np.ag")
                .header("Referer", "https://pod-inc-dev.velocity-np.ag/rol-workflow/list")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");

        response = request.get(CURRENT_ENV + "/pod/rol/workflow?status=CLOSED" + queryParameters);

        return response;
    }
}