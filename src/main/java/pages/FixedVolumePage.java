package pages;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.FixedVolumeFilters;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.Apis.POD_API;
import static utils.Apis.VC_API01_NP_AGRO_SERVICES;
import static utils.EnviromentsConfig.CURRENT_ENV;

public class FixedVolumePage {

    String enviroment;
    public String user;
    public Response Headoffice;
    public Response Grower;
    public int XtendVolume;
    public int IntactaVolume;
    public String token;

    public FixedVolumePage(String env){
        this.enviroment= env;
    }


//*

    public Response createFixedVolume() {
        Response response;
        RequestSpecification request;
        JSONObject json =  new JSONObject();
        json= LoadCreateFixedVolumeJSON();


        baseURI = POD_API;
        request = given()
                    .body(json.toJSONString())
                    .header("Content-Type","application/json")
                    .header("Authorization", token)
                    .pathParam("username",user);

        int i=0;
        do{

            response = request.post(enviroment+  "/pod/rol/fixed-volume?username={username}");
            i++;

        }while (response.statusCode() != 200 && i<3);


        return  response;

    }

    private JSONObject LoadCreateFixedVolumeJSON() {
        JSONObject json = new JSONObject();

        JSONObject partnerJson  = new JSONObject();
        JSONObject growerJson  = new JSONObject();
        JSONObject IntactaJson = new JSONObject();
        JSONObject XtendJson = new JSONObject();

        JSONArray technologyJsonArray  = new JSONArray();

        if (IntactaVolume != 0 ){
        IntactaJson.put("id","e945be00-5f35-4c0e-94e8-07bd41c8a84c");
        IntactaJson.put("fixedVolume", IntactaVolume);
            technologyJsonArray.add(IntactaJson);}

        if (XtendVolume != 0){
        XtendJson.put("id","bb537c34-0288-42c6-b5bb-db99ef4e0de2");
        XtendJson.put("fixedVolume", XtendVolume);technologyJsonArray.add(XtendJson);}




        json.put("partner", Headoffice.body().jsonPath().getJsonObject("results[0]"));
        json.put("grower", Grower.body().jsonPath().getJsonObject("results[0]"));
        json.put("technology", technologyJsonArray);

       return json;

    }

    public Response deleteLastFixedVolume(String id) {
        Response response;
        Response searchResponse;
        RequestSpecification request;


        request = given()
                    .pathParam("id",id)
                .header("Authorization",token)
                .pathParam("username", user);
        response = request.delete(enviroment + "/pod/rol/fixed-volume/{id}?username={username}");


        return response;
    }

    public Response searchFixedVolume(FixedVolumeFilters filters, int limit) {
        Response response;
        RequestSpecification request;

        String cadenaParaElGet="";
        if (filters.Technology != null){
            cadenaParaElGet=cadenaParaElGet+"&technologyID="+filters.Technology;
        }
        if(filters.Identifer != null){
            cadenaParaElGet = cadenaParaElGet+"&fixedVolumeId="+filters.Identifer;
        }
        if (filters.Headoffice_Document!= null){
            cadenaParaElGet = cadenaParaElGet+ "&partnerDocumentNumber=" + filters.Headoffice_Document;
        }
        if(filters.Grower != null){
            cadenaParaElGet = cadenaParaElGet+"&growerMatchText="+filters.Grower;
        }
        if (limit != -1){
            cadenaParaElGet = cadenaParaElGet +"&page=1&limit="+limit;
        }
        baseURI = POD_API;
        request =
                given()
                    .header("Authorization", token)
                    .pathParam("partnerDocumentNumber",filters.Headoffice_Document);

        response = request.get(enviroment+"/pod/rol/fixed-volume?" +
                "partnerDocumentNumber={partnerDocumentNumber}"+cadenaParaElGet);

        return response;
    }

    public Response editFixedVolume(FixedVolumeFilters filters, String volume) {
        Response response;
        RequestSpecification request;
        JSONObject json= new JSONObject();

        json.put("fixed_volume", Integer.parseInt(volume));

        baseURI = POD_API;

        request =  given()
                .header("Authorization", token)
                .pathParam("id",filters.Identifer)
                .pathParam("username", user)
                .header("Content-Type","application/json")
                .body(json.toJSONString());
        response = request.put(enviroment + "/pod/rol/fixed-volume/{id}?username={username}");

        return response;


    }
}
