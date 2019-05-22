package pages;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sun.font.CoreMetrics;
import utils.Apis;
import utils.ReadJson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BulkFixedVolumePage {

    public String enviroment;
    public JSONArray jsonArray;
    public String UUID;
    public String user;
    public String token;


    public BulkFixedVolumePage(String enviroment ,String uuid)  {
        this.enviroment = enviroment;
        this.UUID  = uuid;

    }
    public void loadJsonArray(String jsonFileName) throws FileNotFoundException {
        File jsonDir = new File("jsons/");
        File jsonFile = new File(jsonDir, jsonFileName);
        jsonArray = ReadJson.getJson(new FileReader(jsonFile.getAbsolutePath()));
        replaceWithUUID(jsonArray);
    }

    private void replaceWithUUID(JSONArray jsonArray) {

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
                .body(this.jsonArray.toJSONString());

        response_aux = request.post(enviroment+"/pod/rol/fixed-volume/bulk?username="+user+"&filename=AUTOMATION_TEST.xlsx" +
                "&bulkId={bulkID}");

        return  response_aux;

    }

    public Response registerBulkFromJsonArray(JSONArray jsonArray) {

        RequestSpecification request;
        Response response_aux;

        baseURI = Apis.POD_API;

        request = given()
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("bulkID", UUID)
                .body(jsonArray.toJSONString());

        response_aux = request.post(enviroment+"/pod/rol/fixed-volume/bulk?username="+user+"&filename=STRESS_TEST.xlsx" +
                "&bulkId={bulkID}");

        return  response_aux;

    }


    public String getBulkStatus() throws InterruptedException {
        RequestSpecification request;
        Response response_aux;

        baseURI = Apis.POD_API;

        request = given()
                .header("Content-Type","application/json")
                .header("Authorization",token);

        String status;
        int i= 0;

        do {
            Thread.sleep(10000);
            response_aux = request.get(enviroment + "/pod/rol/fixed-volume/bulk/list?username=" + user);
            status = response_aux.jsonPath().getString("results[0].status");
            System.out.println("El response devuelve lo siguiente: ");
            System.out.println(response_aux.asString());
            System.out.println("el status está devolviendo " + status);
            i++;
        }while(status.equals("PENDING_PROCESS") && i<3);

        return status;

    }
}
