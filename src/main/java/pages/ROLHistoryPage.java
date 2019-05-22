package pages;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ROLHistoryFilters;
import io.restassured.*;
import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.given;
import static utils.Apis.POD_API;


public class ROLHistoryPage {

    String enviroment;
    public String user;
    public String token;

    public ROLHistoryPage(String env) {
        this.enviroment =env;

    }

    public Response searchROL(ROLHistoryFilters filters) {
        Response response;
        RequestSpecification request;

        baseURI= POD_API;


        String queryParameters;
        queryParameters  = "userName="+ user;
        queryParameters = queryParameters+ "&headofficeDocument="+filters.Headoffice;
        if(filters.Affiliate!= null){
            queryParameters = queryParameters+"&affiliateDocument="+filters.Affiliate;
        }
        if(filters.year!= null){
            queryParameters = queryParameters+"&year="+filters.year;
        }
        if (filters.month != null){
            queryParameters = queryParameters + "&month="+filters.month;
        }
        if (filters.status != null){
            queryParameters =  queryParameters + "&status="+ filters.status;
        }


        request = given()
                .header("Authorization", token);

        response = request.get(enviroment +"/pod/rol/history?"+ queryParameters);
        return response;

    }

    public Response generateReport(ROLHistoryFilters filters) {
        Response response;
        RequestSpecification request;

        baseURI= POD_API;

        String queryParameters;
        queryParameters  = "username="+ user;
        queryParameters = queryParameters+ "&headofficeDocument="+filters.Headoffice;
        if(filters.Affiliate!= null){
            queryParameters = queryParameters+"&affiliateDocument="+filters.Affiliate;
        }
        if(filters.year!= null){
            queryParameters = queryParameters+"&year="+filters.year;
        }
        if (filters.status != null){
            queryParameters =  queryParameters + "&status="+ filters.status;
        }
        request = given()
                .header("Authorization", token)
                .header("Accept","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .header("Content-Type","application/json");

        response = request.post(enviroment +"/pod/rol/history/xlsx/rol/es?"+ queryParameters);
        return response;
    }
}
