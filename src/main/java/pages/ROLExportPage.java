package pages;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ROLExportFilters;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.Apis.POD_API;

public class ROLExportPage {
    
    public String env;
    public String user;
    public String token;
    public ROLExportFilters filters;

    public ROLExportPage(){
        filters = new ROLExportFilters();
    }

    public Response downloadRol() {

        RequestSpecification request;
        Response response;

        String suffixURI = filters.setSuffixURI();

        baseURI= POD_API;

        request=
                given()
                   .header("Authorization", token)
                   .header("Accept", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                   .header("Content-Type","application/json");

        response = request.post("dev/pod/rol/export/xlsx/rol/en-US?username="+user+suffixURI);

        return response;
    }
}
