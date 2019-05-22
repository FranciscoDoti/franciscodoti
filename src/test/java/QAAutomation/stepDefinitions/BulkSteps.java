package QAAutomation.stepDefinitions;

import com.fasterxml.uuid.Generators;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Assert;
import pages.BulkPage;
import pages.CommonMethods;
import utils.Token;

import java.io.FileNotFoundException;
import java.lang.management.ManagementFactory;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static utils.EnviromentsConfig.CURRENT_ENV;

public class BulkSteps {


    public String enviroment;
    public BulkPage bulkPage;
    public Response response;
    public String UUID;

    @Given("set Enviroment \"([^\"]*)\"")
    public void Set_enviroment_for_Bulk(String arg1){
        this.enviroment = CURRENT_ENV;
    }
    @Given("A Bulk file with Participant Delivers")
    public void A_Bulk_file_with_Participant_Delivers() throws FileNotFoundException {
        String jsonFileName = "Bulk.json";
        this.bulkPage  =new BulkPage(CURRENT_ENV,UUID);
        this.bulkPage.loadJsonArray(jsonFileName);
    }

    @Given("PD_Bulk_An ADMINISTRATOR")
    public void PD_Bulk_An_ADMINISTRATOR(){
        this.bulkPage.user="STARK_A";
        this.bulkPage.token= Token.AdminToken;
    }

    @Given("PD_Bulk_A PARTICIPANT")
    public void PD_Bulk_A_Participant(){
        this.bulkPage.user="STARK_P";
        this.bulkPage.token= Token.PartToken;
    }

    @Given("Set new UUID")
    public void Set_new_UUID(){
        String bulkId;
        UUID uuid = Generators.randomBasedGenerator().generate();
        bulkId = uuid.toString();
        this.UUID = bulkId;
    }

    @Given("I replace a PD with wrong Document Number")
    public void I_replace_a_PD_with_wrong_Document_Number(){
        ((JSONObject)((JSONObject)((JSONObject)((JSONObject) bulkPage.jsonArray.get(0) ).get("delivery")).get("sender")).get("document")).replace("documentNumber", "0000000123123123");
    }

    @Given("I replace a PD with with negative volume")
    public void I_replace_a_PD_with_negative_volume(){
        ((JSONObject)((JSONObject)bulkPage.jsonArray.get(0) )
                .get("delivery")).replace("quantity", -120);
    }

    @Given("I replace a PD with empty Document Type")
    public void I_replace_a_PD_with_empty_Document_Type(){
        ((JSONObject)((JSONObject)((JSONObject)((JSONObject) bulkPage.jsonArray.get(0) ).get("delivery")).get("sender"))
                .get("document")).replace("documentType", null);
    }

    @Given("I replace a PD with empty Document Number")
    public void I_replace_a_PD_with_empty_Document_Number(){
        ((JSONObject)((JSONObject)((JSONObject)((JSONObject) bulkPage.jsonArray.get(0) ).get("delivery")).get("sender"))
                .get("document")).replace("documentNumber", null);
    }


    @Given("I replace a PD with with null quantity")
    public void I_replace_a_PD_with_null_quantity(){
        ((JSONObject)((JSONObject)bulkPage.jsonArray.get(0) )
                .get("delivery")).replace("quantity", null);
    }


    @Given("I replace a PD with wrong Document Type")
    public void I_replace_a_PD_with_wrong_Document_Type(){
        ((JSONObject)((JSONObject)((JSONObject)((JSONObject) bulkPage.jsonArray.get(0) ).get("delivery")).get("sender"))
                .get("document")).replace("documentType", "WRONG_TYPE");
    }
    @Given("I replace a PD with quantity with letters")
    public void I_replace_a_PD_with_quantity_with_letters(){
        ((JSONObject)((JSONObject)bulkPage.jsonArray.get(0) )
                .get("delivery")).replace("quantity", "asdsad123");
    }

    @Given("I replace a PD with quantity with simbols")
    public void I_replace_a_PD_with_quantity_with_simbols(){
        ((JSONObject)((JSONObject)bulkPage.jsonArray.get(0) )
                .get("delivery")).replace("quantity", "!!@123");
    }

    @When("I post the Bulk")
    public void I_post_the_Bulk()  {
        this.response = this.bulkPage.registerBulk();

    }

    @Then("Verify that each participant deliver was posted ok")
    public void Verify_that_each_participant_deliver_was_posted_ok()  {
        String status;

        try{
            status = this.bulkPage.getLastBulkStatus();
        }catch (Exception e){
            throw new RuntimeException("Couldn't get the bulk status");
        }

        Assert.assertTrue("Response status code: " + this.response.statusCode()
                + "Bulk status: "+ status + ". Bulk id: " + UUID
                ,status.equals("REGISTERED"));

    }

    @Then("Verify that the Bulk has an error")
    public void Verify_that_the_Bulk_has_an_error() throws InterruptedException {
        Thread.sleep(7000);

        String status;

        try{
            status = this.bulkPage.getLastBulkStatus();
        }catch (Exception e){
            throw new RuntimeException("Couldn't get the bulk status");
        }
        Assert.assertTrue("Response status code: " + this.response.statusCode()
                        + "Bulk status: "+ status + ". Bulk id: " + UUID
                ,status.equals("BULK_ERROR"));


    }
    @Then ("PD_Verify that the user is not able to bulk Participant Deliveries")
    public void PD_Verify_that_the_user_is_not_able_to_bulk_Participant_Deliveries(){
        Assert.assertTrue("Response status code: " + this.response.statusCode(),
                this.response.statusCode()!=200);

    }
}




