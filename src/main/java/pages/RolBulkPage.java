package pages;

import gherkin.deps.com.google.gson.JsonArray;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sun.misc.Request;
import sun.security.util.PendingException;
import utils.ReadJson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.Apis.POD_API;
import static utils.EnviromentsConfig.CURRENT_ENV;

public class RolBulkPage {

    public String env;
    public String user;
    public String token;
    public JSONArray jsonArray;
    public JSONObject jsonObject;

    public void loadJsonArray(String jsonFileName) throws FileNotFoundException {
        File jsonDir = new File("jsons/");
        File jsonFile = new File(jsonDir, jsonFileName);

        jsonArray = ReadJson.getJson(new FileReader(jsonFile.getAbsolutePath()));
        jsonObject = new JSONObject((JSONObject) jsonArray.get(0));

    }

    public void replaceJsonWithNewUUID(String uuid) {

        jsonObject.replace("bulkId",uuid);

        String lines = jsonObject.get("lines").toString();

        CharSequence s1 = "a67d5685-b171-42a0-a717-5b47ac893a70";
        CharSequence s2 = uuid;

        lines= lines.replace(s1,s2);
        lines= lines.replace(s1,s2);

        jsonObject.replace("lines", lines);

    }

    public Response postBulk() throws InterruptedException {

        Response response;
        RequestSpecification request;
        int i=0;
        do {
            baseURI = POD_API;

            request = given()
                    .header("Authorization", token)
                    .header("Content-Type", "application/json")
                    .body(jsonObject.toJSONString());

            response = request.post(CURRENT_ENV + "/pod/rol/bulk");
            i++;
            Thread.sleep(300);
        }
        while (i<3 && response.statusCode()!=200);

        System.out.println("Response del post del bulk : "+ response.statusCode());
        System.out.println("JSON que estamos mandando: "+jsonObject.toJSONString());
        return  response;

    }

    public Response getUploadedFiles() {
        Response response;
        RequestSpecification request;

        baseURI = POD_API;

        request = given()
                .header("Authorization", token)
                .header("Content-Type", "application/json");

        response= request.get(CURRENT_ENV + "/pod/rol/bulk");
        System.out.println("CURRENT_ENV "+ CURRENT_ENV);

        System.out.println("get uploaded files response " + response.asString());
        return  response;
    }

    public String getBulkStatus(Response uploadedFilesResponse, String uuid) {

        String status;
        String id_aux;

        JSONArray jsonArray;
        jsonArray = new JSONArray();
        jsonArray.addAll(uploadedFilesResponse.jsonPath().getJsonObject("results"));


        int i= 0;
        boolean founded= false;
        while (i<jsonArray.size() && founded == false){

            id_aux= ((HashMap) jsonArray.get(i)).get("id").toString();

            if (id_aux.equals(uuid)){
                founded=true;

            }else{
                i++;
            }


        }

        status= ((HashMap) jsonArray.get(i)).get("status").toString();
        System.out.println("Status que encontró: "+ status);
        return status;

    }

    public String findBulkByStatus(Response uploadedFilesResponse, String status) {
        String idFounded = null;
        boolean founded=false;
        JSONArray  jsonArray = new JSONArray();
        jsonArray.addAll(uploadedFilesResponse.jsonPath().getJsonObject("results"));

        int i= 0;

        while(i< jsonArray.size() && founded == false ){

            if (status.equals(((HashMap) jsonArray.get(i)).get("status"))){
                founded = true;
                idFounded = ((HashMap) jsonArray.get(i)).get("id").toString();
            }else{
                i++;
            }

        }
        if (idFounded!= null){
            return idFounded;
        }else{
            throw new RuntimeException( " No se pudo encontrar un bulk con el status "+ status);
        }

    }

    public Response downloadFile(String idToFound) {
        RequestSpecification request;
        Response response;

        baseURI = POD_API;
        request=  given()
                .header("Authorization", token)
                .header("Accept", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .header("Content-Type","application/json\r\n");

        response = request.post(CURRENT_ENV + "/pod/rol/bulk/xlsx/"+idToFound+"?language=en-US");

        return response;
    }

    public void setReceivedDeclaredVolume(String volume) {
        throw new PendingException();
    }




}
