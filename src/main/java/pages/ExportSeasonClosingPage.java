package pages;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ExportSeasonClosingFilters;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.Apis.POD_API;

public class ExportSeasonClosingPage {
    public ExportSeasonClosingFilters filters;

    public String env;
    public String user;
    public String token;



    public Response exportSeasonClosingReport() {

        Response response;
        RequestSpecification request;

        String queryParameters = filters.setSuffixURI();


        baseURI= POD_API;

        request = given()
                    .header("Authorization", token)
                    .header("Content-Type", "application/json");

        response = request.post(env + "/pod/season-closing/export/xlsx/season-closing-report/en-US?username="+user+
        queryParameters);


        return  response;
    }
}
