package pages;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sun.font.CoreMetrics;
import utils.FixedVolumeChanges;
import utils.PoliteString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.Apis.POD_API;
import static utils.Apis.VC_API01_NP_AGRO_SERVICES;
import static utils.EnviromentsConfig.CURRENT_ENV;

public class ConsolidateROLCreationPage {

    String enviroment;
    public String user;
    public String token;

    public ConsolidateROLCreationPage(String env){
        this.enviroment= env;

    }

    public Response loadROL(String affiliate_doc, String year, String month) {
        Response response;
        RequestSpecification request;
        JSONObject json = new JSONObject();
        Response Affiliate;

        Affiliate= CommonMethods.getAffiliate(affiliate_doc, user);
        if (Affiliate.statusCode()!=200){
            throw new RuntimeException ("Couldnt get affiliate. Response status code: "+Affiliate.statusCode());
        }

        json.put("affiliateDocument",affiliate_doc);
        json.put("year",Integer.parseInt(year));
        json.put("month", Integer.parseInt(month));
        json.put("headofficeDocument",
                ((ArrayList) Affiliate
                        .jsonPath().get("results[0].matrix.document.documentNumber")).get(0));

        baseURI = POD_API;

        request = given()
                    .header("Content-Type","application/json")
                    .header("Authorization", token)
                    .pathParam("username", user)
                    .body(json.toJSONString());

        response = request.post(CURRENT_ENV+"/pod/rol/load?username={username}");

        return response;
    }

    public Response createROLDirectlyFromLoad(Response load_response) {
        Response response;
        RequestSpecification request;
        JSONObject json  = new JSONObject();
        JSONObject subJson  = new JSONObject();
        JSONObject affiliateJson  = new JSONObject();
        JSONObject receivedVolumeJson  = new JSONObject();
        JSONObject fixedVolumeJson  = new JSONObject();
        String AffiliateDocument = load_response.jsonPath().getString("results.affiliate.document.documentNumber");
        Response Affiliate_Response = CommonMethods.getAffiliate(AffiliateDocument, user);



        subJson.put("year",Integer.parseInt(load_response.jsonPath().getString("results.year")));
        subJson.put("month", Integer.parseInt(load_response.jsonPath().getString("results.month")));
        subJson.put("rolTemplate",load_response.jsonPath().getString("results.rolTemplate"));
        subJson.put("affiliate", Affiliate_Response.body().jsonPath().getJsonObject("results[0]"));
        subJson.put("headoffice", Affiliate_Response.body().jsonPath().getJsonObject("results[0]"));
        subJson.put("creationUser", user);
        subJson.put("receivedVolume", load_response.jsonPath().getJsonObject("results.receivedVolume"));
        subJson.put("fixedVolume", load_response.jsonPath().getJsonObject("results.fixedVolume"));


        json.put("rol",subJson);


        request = given()
                .header("Content-Type","application/json")
                .header("Authorization", token)
                .pathParam("username", user)
                .body(json.toJSONString());

        response = request.post(enviroment+"/pod/rol?username={username}");

        return response;
    }


    public Response createROL(Response load_response, FixedVolumeChanges fixedVolumeChanges)  {



        Response response;
        RequestSpecification request;
        JSONObject json  = new JSONObject();
        JSONObject subJson  = new JSONObject();

        JSONObject receivedVolumeJson  = new JSONObject();
        JSONObject fixedVolumeJson  = new JSONObject();
        String AffiliateDocument = load_response.jsonPath().getString("results.affiliate.document.documentNumber");
        Response Affiliate_Response = CommonMethods.getAffiliate(AffiliateDocument, user);

        JSONObject affiliateJson = new JSONObject(Affiliate_Response.jsonPath().getJsonObject("results[0]"));

        affiliateJson.put("isHeadOffice",true);
        affiliateJson.put("showHeadOffice",false);

        /*

                JSONObject AffiliateJson = new JSONObject(((Response) CommonMethods.getAffiliate(AffiliateDoc,user))
                .jsonPath().getJsonObject("results[0]"));

        AffiliateJson.put("isHeadOffice", true);
        AffiliateJson.put("showHeadOffice", false);

         */


        subJson.put("year",Integer.parseInt(load_response.jsonPath().getString("results.year")));
        subJson.put("month", Integer.parseInt(load_response.jsonPath().getString("results.month")));
        subJson.put("rolTemplate",load_response.jsonPath().getString("results.rolTemplate"));
        subJson.put("affiliate",affiliateJson );
        subJson.put("headoffice",((ArrayList) Affiliate_Response.body().jsonPath().getJsonObject
                ("results[0].matrix")).get(0));
        subJson.put("creationUser", user);
        subJson.put("receivedVolume", load_response.jsonPath().getJsonObject("results.receivedVolume"));
        subJson.put("fixedVolume", load_response.jsonPath().getJsonObject("results.fixedVolume"));
        subJson.put("comments", load_response.jsonPath().getJsonObject("results.comments"));
        subJson.put("status", load_response.jsonPath().getString("results.status"));
        subJson.put("comment", "Automation Test Save");


        if (fixedVolumeChanges.Technology.equals("Intacta")){
            ((Map)((List)((Map) subJson.get("fixedVolume")).get("fixedVolumes")).get(0)).put("fixedVolume", fixedVolumeChanges.fixedVolume);
            ((Map)((List)((Map) subJson.get("fixedVolume")).get("fixedVolumes")).get(0)).put("fixedPrice", fixedVolumeChanges.fixedPrice);
        }


        json.put("rol",subJson);

        baseURI = POD_API;
        request = given()
                .header("Content-Type","application/json")
                .header("Authorization", token)
                .pathParam("username", user)
                .body(json.toJSONString());

        int i=0;
        do{

            response = request.post(enviroment+"/pod/rol?username={username}");

            if (response.statusCode()!=200){
                i++;
                token = CommonMethods.getToken(user);
            }
        }while (response.statusCode()!= 200 && i<3);


        if (i==3) {
            throw new RuntimeException("Fail to Post on ROL Creation");
        }

        return response;
    }



    public Response registerParticipantDeliver(String receiver_doc, String sender_doc, String volume) {
        JSONObject json= new JSONObject();
        Response response;
        RequestSpecification request;
        Response sender;
        Response receiver;
        PoliteString politeString = new PoliteString();

        receiver = CommonMethods.getAffiliate(receiver_doc,user);
        sender = CommonMethods.getAffiliate(sender_doc,user);
        int Volume = Integer.parseInt(volume);

        JSONObject receiverDocJson = new JSONObject();
        JSONObject senderDocJson = new JSONObject();
        JSONObject receiverJson = new JSONObject();
        JSONObject senderJson = new JSONObject();
        JSONObject receiverHOJson = new JSONObject();
        JSONObject senderHOJson = new JSONObject();
        JSONObject SenderHeadofficeDocJson = new JSONObject();
        JSONObject ReceiverHeadofficeDocJson = new JSONObject();





        SenderHeadofficeDocJson.put("documentType", ((ArrayList) sender.jsonPath().get("results[0].matrix.document.documentType")).get(0));
        SenderHeadofficeDocJson.put("documentNumber", ((ArrayList) sender.jsonPath().get("results[0].matrix.document.documentNumber")).get(0));

        ReceiverHeadofficeDocJson.put("documentType",((ArrayList) receiver.jsonPath().get("results[0].matrix.document.documentType")).get(0));
        ReceiverHeadofficeDocJson.put("documentNumber",((ArrayList) receiver.jsonPath().get("results[0].matrix.document.documentNumber")).get(0));

        senderHOJson.put("document",SenderHeadofficeDocJson);
        receiverHOJson.put("document",ReceiverHeadofficeDocJson);

        senderHOJson.put("name",((ArrayList)sender.jsonPath().get("results[0].matrix.name")).get(0));
        receiverHOJson.put("name",((ArrayList) receiver.jsonPath().get("results[0].matrix.name")).get(0));



        receiverDocJson.put("documentNumber", politeString.emprolijar(receiver.jsonPath().getString("results.document.documentNumber")));
        receiverDocJson.put("documentType", politeString.emprolijar(receiver.jsonPath().getString("results.document.documentType")));

        senderDocJson.put("documentNumber",politeString.emprolijar(sender.jsonPath().getString("results.document.documentNumber")));
        senderDocJson.put("documentType",politeString.emprolijar(sender.jsonPath().getString("results.document.documentType")) );


        receiverJson.put("headOffice", receiverHOJson);
        receiverJson.put("document",receiverDocJson);
        receiverJson.put("name",politeString.emprolijar(receiver.jsonPath().getString("results.name")));

        senderJson.put("headOffice", senderHOJson);
        senderJson.put("document",senderDocJson);
        senderJson.put("name",politeString.emprolijar(sender.jsonPath().getString("results.name")));

        json.put("receiver", receiverJson);
        json.put("sender", senderJson);
        json.put("quantity", Volume);


        baseURI = POD_API;
        request =  given()
                .body(json.toJSONString())
                .header("Content-Type","application/json")
                .header("Authorization", token)
                .pathParam("username",user);

        response = request.post(enviroment+"/pod/participant/delivery?username={username}");

        return response;

    }



    public Response submitRol(Response load_response, String comment) {
        Response response;
        RequestSpecification request;


        JSONObject json= new JSONObject();

        json= BuildROLSubmitJson(load_response, comment);
        baseURI = POD_API;
        request = given()
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .header("Accept","application/json")
                .body(json.toJSONString());

        response = request.post(CURRENT_ENV + "/pod/rol/submit");

        return response;

    }

    private JSONObject BuildROLSubmitJson(Response load_response, String comment) {

        JSONObject jsonObject = new JSONObject();
        JSONObject rolJSON = new JSONObject();

        String AffiliateDoc = load_response.jsonPath().getString("results.affiliate.document.documentNumber");

        JSONObject AffiliateJson = new JSONObject(((Response) CommonMethods.getAffiliate(AffiliateDoc,user))
                .jsonPath().getJsonObject("results[0]"));

        AffiliateJson.put("isHeadOffice", true);
        AffiliateJson.put("showHeadOffice", false);

        JSONObject HeadofficeJson =new JSONObject((Map) ((ArrayList) AffiliateJson.get("matrix")).get(0));



        rolJSON.put("year", Integer.parseInt(load_response.jsonPath().getString("results.year")));
        rolJSON.put("month",Integer.parseInt(load_response.jsonPath().getString("results.month")));
        rolJSON.put("rolTemplate",load_response.jsonPath().getString("results.rolTemplate"));
        rolJSON.put("affiliate",AffiliateJson);
        rolJSON.put("headoffice",HeadofficeJson);
        rolJSON.put("creationUser",user);
        rolJSON.put("receivedVolume",load_response.jsonPath().getJsonObject("results.receivedVolume"));
        rolJSON.put("fixedVolume",load_response.jsonPath().getJsonObject("results.fixedVolume"));
        rolJSON.put("comment",comment);
        rolJSON.put("id",load_response.jsonPath().getJsonObject("results.id"));
        rolJSON.put("comments",load_response.jsonPath().getJsonObject("results.comments"));
        rolJSON.put("status", load_response.jsonPath().getJsonObject("results.status"));


        jsonObject.put("rol", rolJSON);

        return jsonObject;
    }

    public String getRolStatus(String User,String Year,String  Month,String Affiliate_Doc) {
        Response response;
        RequestSpecification request;
        String status;

        baseURI = POD_API;

        request = given()
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("user", User)
                .pathParam("year",Year)
                .pathParam("month", Month);

        response = request.get(CURRENT_ENV + "/pod/rol/history?userName={user}&year={year}&month={month}");

        status = response.jsonPath().getString("results[0].status");
        if (status!= null){
           return status;
        } else {
            return "NULL";
        }



    }
}
