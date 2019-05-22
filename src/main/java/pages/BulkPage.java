package pages;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.Apis;
import utils.ReadJson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.UUID;

import com.fasterxml.uuid.Generators;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BulkPage {

    public String enviroment;
    public JSONArray jsonArray;
    public String UUID;
    public String user;
    public String token;

    public BulkPage(String enviroment, String uuid)  {
        this.enviroment = enviroment;
        this.UUID  = uuid;

    }

    public void loadJsonArray(String jsonFileName) throws FileNotFoundException {
        File jsonDir = new File("jsons/");
        File jsonFile = new File(jsonDir, jsonFileName);
        jsonArray = ReadJson.getJson(new FileReader(jsonFile.getAbsolutePath()));
        replaceWithUUID(jsonArray);
    }
    public void replaceWithUUID(JSONArray jsonArray) {

        for (int i=0; i< jsonArray.size(); i++) {
            ((JSONObject) jsonArray.get(i)).replace("bulk_id", UUID);
        }

    }

    public Response registerBulk() {

        RequestSpecification request;
        Response response_aux;

        baseURI = Apis.POD_API;

        request = given()
                    .header("Content-Type","application/json")
                    .header("Authorization",token)
                    .pathParam("bulkID", UUID)
                    .pathParam("username",user)
                    .body(jsonArray.toJSONString());

        response_aux = request.post(enviroment+"/pod/participant/delivery/bulk?username={username}&filename=AUTOMATION_TEST.xlsx" +
                "&bulkId={bulkID}");

        return  response_aux;


    }

    public String getLastBulkStatus() throws InterruptedException {
        RequestSpecification request;
        Response response_aux;

        baseURI = Apis.POD_API;

        request = given()
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("username", user);
        String status;
        int i=0;
        do {
            response_aux = request.get(enviroment + "/pod/participant/delivery/bulk?username={username}");
            status = response_aux.jsonPath().getString("results[0].status");
            System.out.println("El response devuelve lo siguiente: ");
            System.out.println(response_aux.asString());
            System.out.println("el status está devolviendo " + status);
            i++;
            if (status.equals("PENDING_PROCESS")){
                Thread.sleep(20000);
            }

        }
            while (status.equals("PENDING_PROCESS") && i<3);


        return status;
    }

    public boolean Bulk_Has_An_Error() throws InterruptedException {

        RequestSpecification request;
        Response response_aux;

        baseURI = Apis.POD_API;

        request = given()
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("username", user);
        String status;
        int i=0;
        do {
            response_aux = request.get(enviroment + "/pod/participant/delivery/bulk?username={username}");
            status = response_aux.jsonPath().getString("results[0].status");
            System.out.println("El response devuelve lo siguiente: ");
            System.out.println(response_aux.asString());
            System.out.println("el status está devolviendo " + status);
            i++;

            if (status.equals("PENDING_PROCESS")){
            Thread.sleep(10000);
            }

        }while (status.equals("PENDING_PROCESS") && i<3);


        return status.equals("BULK_ERROR");

    }

    public Response registerBulkFromJsonArray(JSONArray bulk_jsonArray) {

        RequestSpecification request;
        Response response_aux;

        baseURI = Apis.POD_API;

        request = given()
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("bulkID", UUID)
                .pathParam("username",user)
                .body(bulk_jsonArray.toJSONString());

        response_aux = request.post(enviroment+"/pod/participant/delivery/bulk?username={username}&filename=STRESS_TEST.xlsx" +
                "&bulkId={bulkID}");

        return  response_aux;

    }
}