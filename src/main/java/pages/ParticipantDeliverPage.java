package pages;

import HeaderPages.ParticipantDeliverHeaders;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Apis;
import utils.ParticipantDeliverSearchFilters;
import utils.PoliteString;

import java.util.ArrayList;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static utils.Apis.POD_API;
import static utils.Apis.VC_API01_NP_AGRO_SERVICES;
import static utils.EnviromentsConfig.CURRENT_ENV;

public class ParticipantDeliverPage {

    public String POD_User;
    public String POD_Sender;
    public String enviroment;
    public  RequestSpecification request;
    public JSONObject json;
    public Response response;
    public String user;
    public String token;


    public ParticipantDeliverPage(String enviroment) {


        this.POD_User = POD_User;
        this.POD_Sender = POD_Sender;



    }

    public Response postParticipantDeliverWithDocNumbers (String ReceiverDoc, String SenderDoc, int Volume)
    {



        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj = BuildPDJson(ReceiverDoc, SenderDoc, Volume);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        baseURI = POD_API;
        request = given()
                .header("Authorization", token)
                .header("Content-Type","application/json")
                .body(jsonObj.toJSONString());

        int i=0;
        do{

            response = request.post(CURRENT_ENV + "/pod/participant/delivery?username="+user);

            if (response.statusCode()!=200){
                i++;
                token = CommonMethods.getToken(user);
            }
        }while (response.statusCode()!= 200 && i<3);

                return response;

    }


    public JSONObject BuildPDJson(String ReceiverDoc, String SenderDoc, int Volume){

            JSONObject json = new JSONObject();
            JSONObject SenderJson = new JSONObject();
            JSONObject ReceiverJson = new JSONObject();

            JSONObject SenderDocumentJson = new JSONObject();
            JSONObject ReceiverDocumentJson = new JSONObject();

            JSONObject SenderHeadofficeJson = new JSONObject();
            JSONObject ReceiverHeadofficeJson = new JSONObject();

            JSONObject SenderHeadofficeDocJson = new JSONObject();
            JSONObject ReceiverHeadofficeDocJson = new JSONObject();


            Response receiver= CommonMethods.getAffiliate(ReceiverDoc, user);
            Response sender = CommonMethods.getAffiliate(SenderDoc, user);
            if (receiver.statusCode()!=200){
                throw new RuntimeException("Error al obtener el Receiver. " +
                        "Response status code: " + receiver.statusCode());
            }

             if (sender.statusCode()!=200){
            throw new RuntimeException("Error al obtener el Sender. " +
                    "Response status code: " + sender.statusCode());
              }

            ReceiverDocumentJson.put("documentType",receiver.jsonPath().get("results[0].document.documentType"));
            ReceiverDocumentJson.put("documentNumber",receiver.jsonPath().get("results[0].document.documentNumber"));

            SenderDocumentJson.put("documentType",sender.jsonPath().get("results[0].document.documentType"));
            SenderDocumentJson.put("documentNumber",sender.jsonPath().get("results[0].document.documentNumber"));

            SenderHeadofficeDocJson.put("documentType", ((ArrayList) sender.jsonPath().get("results[0].matrix.document.documentType")).get(0));
            SenderHeadofficeDocJson.put("documentNumber", ((ArrayList) sender.jsonPath().get("results[0].matrix.document.documentNumber")).get(0));

            ReceiverHeadofficeDocJson.put("documentType",((ArrayList) receiver.jsonPath().get("results[0].matrix.document.documentType")).get(0));
            ReceiverHeadofficeDocJson.put("documentNumber",((ArrayList) receiver.jsonPath().get("results[0].matrix.document.documentNumber")).get(0));

            SenderHeadofficeJson.put("document",SenderHeadofficeDocJson);
            ReceiverHeadofficeJson.put("document",ReceiverHeadofficeDocJson);

            SenderHeadofficeJson.put("name",((ArrayList)sender.jsonPath().get("results[0].matrix.name")).get(0));
            ReceiverHeadofficeJson.put("name",((ArrayList) receiver.jsonPath().get("results[0].matrix.name")).get(0));



            SenderJson.put("document",SenderDocumentJson);
            SenderJson.put("headOffice",SenderHeadofficeJson);


            ReceiverJson.put("document",ReceiverDocumentJson);
            ReceiverJson.put("headOffice",ReceiverHeadofficeJson);

            SenderJson.put("name",sender.jsonPath().get("results[0].name"));
            ReceiverJson.put("name", receiver.jsonPath().get("results[0].name"));


            json.put("quantity", Volume);
            json.put("receiver",ReceiverJson);
            json.put("sender", SenderJson);


            return json;

    }






    public String getRegisteredPD_ID(){



        baseURI = POD_API;

        request = given()
                .header("Authorization", token)
                .header("Content-Type","application/json")
                .pathParam("username",user);
        response = request.get(CURRENT_ENV + "/pod/participant/delivery?username={username}&status=REGISTERED");



        return response.jsonPath().getString("results[0].id");

    }

    public String getCancelledPD_ID(){



        baseURI = POD_API;

        request = given()
                .header("Authorization", token)
                .header("Content-Type","application/json")
                .pathParam("username",user);
        response = request.get(CURRENT_ENV + "/pod/participant/delivery?username={username}&status=CANCELED");



        return response.jsonPath().getString("results[0].id");

    }



    public boolean VerifyIfParticipantDeliverWasPosted(Response response){
        if (response.statusCode() == 200){
            return true;
        }
        else {
            return  false;
        }
    }

    public Response cancelParticipantDeliver(String PD_ID){

       Response CancelResponse = null;

       CancelResponse =
               given()
                       .header("Authorization", token)
                       .header("Content-Type","application/json")
                       .pathParam("PD_ID",PD_ID)
                       .pathParam("username",user)
               .when()
                        .put("https://gkg1ib9end.execute-api.us-east-1.amazonaws.com/"+CURRENT_ENV+
                                "/pod/participant/delivery/{PD_ID}/cancel?username={username}");

       return CancelResponse;

    }

    public Response editParticipantDeliver(String PD_ID, String Volume) {

        baseURI = POD_API;

        Response Edit_Response;

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("quantity",Integer.parseInt(Volume));

        request = given()
                .header("Authorization", token)
                .header("Content-Type","application/json")
                .pathParam("PD_ID",PD_ID)
                .pathParam("username",user)
                .body(jsonObj.toJSONString());
        Edit_Response = request.put(CURRENT_ENV + "/pod/participant/delivery/{PD_ID}?username={username}");
        return Edit_Response;
    }

    public Response searchParticipantDelivers(ParticipantDeliverSearchFilters filters, int limit) {
        Response response;
        RequestSpecification request;

        baseURI = POD_API;

        request  = given()
                .header("Authorization",token);

        String queryParameters= null;

        queryParameters = "receiverDocumentNumber=" + filters.POD_USER;
        if (filters.DocumentOrName!= null){
            queryParameters = queryParameters + "&matchText=" + filters.DocumentOrName;
        }
        if (!(String.valueOf(filters.From)).equals("0")){
            queryParameters = queryParameters + "&fromDate=" + filters.From;
        }
        if (!(String.valueOf(filters.To)).equals("0")){
            queryParameters = queryParameters + "&toDate=" + filters.To;
        }
        if (filters.user!= null){
            queryParameters  = queryParameters + "&username="+ filters.user;
        }

        if (filters.Status!= null){
            queryParameters = queryParameters + "&status=" + filters.Status;
        }

        if (limit != -1){
            queryParameters = queryParameters + "&page=1&limit="+limit;
        }


        response = request.get(CURRENT_ENV+"/pod/participant/delivery?"+queryParameters);

        return  response;
    }
}



