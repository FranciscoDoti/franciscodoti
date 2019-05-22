package pages;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import utils.ROLHistoryFilters;
import utils.Token;

import java.io.*;
import java.sql.*;
import java.util.Calendar;
import java.util.Properties;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.Apis.API_LEGACY;
import static utils.Apis.REQUEST_TOKEN;
import static utils.EnviromentsConfig.CURRENT_ENV;

public class CommonMethods {



    public static String getToken(String user){


        String pass="";
        if (user.equals("STARK_A")){
            pass = "Monsanto4";
        }else if (user.equals("STARK_P")){
            pass = "Monsanto4";
        }
        Response response;
        RequestSpecification request;


        request = given()
                .header("Authorization","Basic UEQtQjJCLUNMSTo=")
                .header("Content-Type","application/x-www-form-urlencoded")
                .pathParam("username", user)
                .pathParam("password", pass);

        response = request.post(REQUEST_TOKEN+"as/token.oauth2?grant_type=password&username={username}&password={password}");

        System.out.println("Obteniendo token....");

        String str;

        str = "Bearer " + response.jsonPath().getString("access_token");

        return str;
    }

    public static Response getHeadoffice(String docNumber, String user) {

            Response response;
            RequestSpecification request;
            String env;

            if (CURRENT_ENV.equals("it")){
                env= "test";
             }else{
                env="dev";
                 }
            baseURI = API_LEGACY;
            request = given()
                    .header("Authorization", Token.getTokenByUser(user))
                    .pathParam("partnerType","POD")
                    .pathParam("onlyHeadoffices","true")
                    .pathParam("matrixDocNumbers", docNumber)
                    .pathParam("partnerIdentifier", docNumber);
            response = request.get(env+ "/partner/MONSANTO/SOJA/search/headoffices" +
                    "?partnerType={partnerType}" +
                    "&onlyHeadoffices={onlyHeadoffices}" +
                    "&matrixDocumentNumbers={matrixDocNumbers}" +
                    "&partnerIdentifier={partnerIdentifier}");

            return  response;

    }



    public static Response getAffiliate(String docNumber, String user) {

        Response response;
        RequestSpecification request;
        String env;
        if (CURRENT_ENV.equals("it")){
            env= "test";
        }else{
            env="dev";
        }

        baseURI = API_LEGACY;
        request = given()
                .header("Authorization", Token.getTokenByUser(user))
                .pathParam("partnerType","POD")
                .pathParam("onlyHeadoffices","false")
                .pathParam("partnerIdentifier", docNumber);
        response = request.get(env+"/partner/MONSANTO/SOJA/search" +
                "?partnerType={partnerType}" +
                "&onlyHeadoffices={onlyHeadoffices}" +
                "&partnerIdentifier={partnerIdentifier}");

        return  response;

    }

    public static Response getAffiliatesByHeadoffice(String docNumber){

        Response response;
        RequestSpecification request;
        String env;
        if (CURRENT_ENV.equals("it")){
            env= "test";
        }else{
            env="dev";
        }
        baseURI = API_LEGACY;
        request = given()
                .pathParam("headofficeDocument", docNumber);

        response = request.get(env+"/partner/MONSANTO/SOJA/search?" +
                "partnerType=POD&matrixDocumentNumbers={headofficeDocument}");

        return  response;
    }

    public static Response getGrower(String growerDoc, String user) {

        Response response;
        RequestSpecification request;

        String env;
        if (CURRENT_ENV.equals("it")){
            env= "test";
        }else{
            env="dev";
        }

        baseURI = API_LEGACY;
        request = given()
                .header("Authorization", Token.getTokenByUser(user))
                .pathParam("partnerType","GROWER")
                .pathParam("onlyHeadoffices","false")
                .pathParam("partnerIdentifier", growerDoc);
        response = request.get(env+"/partner/MONSANTO/SOJA/search" +
                "?partnerType={partnerType}" +
                "&onlyHeadoffices={onlyHeadoffices}" +
                "&partnerIdentifier={partnerIdentifier}");

        return  response;
    }



    public static Response SearchAllHeadoffices() {
        Response response;
        RequestSpecification request;
        String env;
        if (CURRENT_ENV.equals("it")){
            env= "test";
        }else{
            env="dev";
        }
        baseURI = API_LEGACY;
        request = given()
                .header("Authorization",Token.getTokenByUser("STARK_A"));
        response = request.get(env+ "/partner/MONSANTO/SOJA/search/headoffices" +
                "?partnerType=POD&onlyHeadoffices=true");

        return response;
    }

    public static boolean searchROLByPeriod(String currentHeadoffice, int j, int year) {
        boolean founded;
        Response searchROLResponse;
        ROLHistoryPage page = new ROLHistoryPage(CURRENT_ENV);
        page.token = Token.AdminToken;
        ROLHistoryFilters filters = new ROLHistoryFilters();
        filters.Headoffice = currentHeadoffice;
        filters.Affiliate = currentHeadoffice;
        filters.year = String.valueOf(year);
        filters.month = String.valueOf(j);
        page.user = "STARK_A";

        searchROLResponse = page.searchROL(filters);


        try{
            if (searchROLResponse.jsonPath().getString("results[0].status").equals("NOT_REGISTERED")){
                founded= true;
            }else{
                founded = false;
            }

        }catch ( Exception e ){
            System.out.println("Exception:"+e);
            founded =true;
        }


        return founded;

    }

    public static ROLPeriod getPeriodWithoutROL(String doc) {

        ROLPeriod period = new ROLPeriod();
        Calendar now =Calendar.getInstance();

        now.get((Calendar.MONTH));
        int currentMonth = now.get((Calendar.MONTH));
        int currentYear = now.get((Calendar.YEAR));
        boolean EmptyPeriod= false;
        int month = Integer.valueOf(currentMonth);
        int year = Integer.valueOf(currentYear);



        while ( !EmptyPeriod && month > 0 && year >=2013){
            System.out.println("Buscando en el mes "+month+ " y el año "+ year);
            EmptyPeriod = searchROLByPeriod(doc,month, year);
            System.out.println("Encontrado: " + EmptyPeriod);
            if (!EmptyPeriod){
                if (month==1){
                    month=12;
                    year--;
                }else{
                    month--;
                }

            }

        }

        period.month= String.valueOf(month);
        period.year = String.valueOf(year);

        return period;



    }
}

