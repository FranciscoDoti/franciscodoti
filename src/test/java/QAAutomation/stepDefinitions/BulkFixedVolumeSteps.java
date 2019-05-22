package QAAutomation.stepDefinitions;

import com.fasterxml.uuid.Generators;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

import org.json.simple.JSONObject;
import org.junit.Assert;
import pages.BulkFixedVolumePage;
import pages.BulkPage;
import pages.CommonMethods;
import utils.Token;

import java.io.FileNotFoundException;
import java.util.UUID;

import static utils.EnviromentsConfig.CURRENT_ENV;

public class BulkFixedVolumeSteps {


    Response Affiliate;
    Response Headoffice;
    String UUID;
    BulkFixedVolumePage bulkPage;
    Response BulkRegisterResponse;

    @Given("BFV_Set new UUID")
    public void BFV_Set_new_UUID(){
        String bulkId;
        UUID uuid = Generators.randomBasedGenerator().generate();
        bulkId = uuid.toString();
        this.UUID = bulkId;
    }

    @Given("A Bulk with fixed volumes")
    public void I_have_a_Bulk_with_fixed_volumes() throws FileNotFoundException {
        String jsonFileName = "FixedVolumeBulk.json";
        this.bulkPage  =new BulkFixedVolumePage(CURRENT_ENV,UUID);
        this.bulkPage.loadJsonArray(jsonFileName);
    }

    @Given("An user with usertype \"([^\"]*)\"")
    public void An_user_with_usertype(String userType){

        if (userType.equals("ADMINISTRATOR")){
            this.bulkPage.user= "STARK_A";
            this.bulkPage.token = Token.AdminToken;
        } else if (userType.equals("PARTICIPANT")){
            this.bulkPage.user= "STARK_P";
            this.bulkPage.token = Token.PartToken;
        }
    }

    @Given("BFD_An Affiliate \"([^\"]*)\"")
    public void BFD_An_Affiliate (String docNumber){
        Affiliate = CommonMethods.getAffiliate(docNumber, bulkPage.user);
        if (Affiliate.statusCode() != 200) {
            throw new RuntimeException("Error al buscar el affiliate " +
                    "Response status code: " + Affiliate.statusCode());
        }
    }
    @Given("BFD_A Headoffice \"([^\"]*)\"")
    public void BFD_A_Headoffice (String docNumber){
        Headoffice = CommonMethods.getHeadoffice(docNumber, this.bulkPage.user);
        if (Headoffice.statusCode() != 200) {
            throw new RuntimeException("Error al buscar el Headoffice " +
                    "Response status code: " + Headoffice.statusCode());
        }
    }

    @Given("Negative Volume")
    public void Negative_Volume(){
        ((JSONObject)((JSONObject)this.bulkPage.jsonArray.get(0) )
                .get("fix_volume")).replace("volume", -120);
    }
    @Given("Volume with letters")
    public void Volume_with_letters(){
        ((JSONObject)((JSONObject)this.bulkPage.jsonArray.get(0) )
                .get("fix_volume")).replace("volume", "ABC123");
    }

    @Given("Volume null")
    public void Volume_null(){
        ((JSONObject)((JSONObject)this.bulkPage.jsonArray.get(0) )
                .get("fix_volume")).replace("volume", null);
    }
    @Given("Volume empty")
    public void Volume_empty(){
        ((JSONObject)((JSONObject)this.bulkPage.jsonArray.get(0) )
                .get("fix_volume")).replace("volume", "");
    }

    @Given("Volume with simbols")
    public void Volume_with_simbols(){
        ((JSONObject)((JSONObject)this.bulkPage.jsonArray.get(0) )
                .get("fix_volume")).replace("volume", "ABC#%!123");
    }

    @Given("Technology different to INTACTA and XTend")
    public void Technology_different_to_INTACTA_and_XTend(){
        ((JSONObject)((JSONObject)this.bulkPage.jsonArray.get(0) )
                .get("fix_volume")).replace("technology", "NON_TECH");
    }
    //Technology with number "232"


    @Given("Technology with number \"([^\"]*)\"")
    public void Technology_with_number(String arg1){
        ((JSONObject)((JSONObject)this.bulkPage.jsonArray.get(0) )
                .get("fix_volume")).replace("technology", Integer.parseInt(arg1));
    }

    @Given("Document Number \"([^\"]*)\"")
    public void Document_Number(String arg1){
        ((JSONObject)((JSONObject)((JSONObject)((JSONObject)this.bulkPage.jsonArray.get(0) )
                .get("fix_volume")).get("grower")).get("document")).replace("documentNumber", arg1);
    }

    @Given("Document Number null")
    public void Document_Number_null(){
        ((JSONObject)((JSONObject)((JSONObject)((JSONObject)this.bulkPage.jsonArray.get(0) )
                .get("fix_volume")).get("grower")).get("document")).replace("documentNumber", null);
    }
    //Document Number with letters
    @Given("Document Number with letters")
    public void Document_Number_with_letters(){
        ((JSONObject)((JSONObject)((JSONObject)((JSONObject)this.bulkPage.jsonArray.get(0) )
                .get("fix_volume")).get("grower")).get("document")).replace("documentNumber", "ASDASD123213");
    }

    @Given("Document Type \"([^\"]*)\"")
    public void Document_Type(String arg1){
        ((JSONObject)((JSONObject)((JSONObject)((JSONObject)this.bulkPage.jsonArray.get(0) )
                .get("fix_volume")).get("grower")).get("document")).replace("documentType", arg1);
    }

    @When("I register the Fixed Volume Bulk")
    public void I_register_the_Fixed_Volume_Bulk(){
        BulkRegisterResponse = this.bulkPage.registerBulk();

    }

    @Then("Verify that the Bulk has status registered")
    public void Verify_that_the_Bulk_has_status_registered() throws InterruptedException {

        String status;

        try{
            status = this.bulkPage.getBulkStatus();}
        catch (Exception e){
            throw  new RuntimeException("Couldnt get the bulk's status");
        }

        Assert.assertTrue("Response status code: " + BulkRegisterResponse.statusCode()+
                        ". Status del bulk: "+ status,
                status.equals("REGISTERED"));




    }

    @Then("Verify that the Bulk has status Bulk_Error")
    public void Verify_that_the_Bulk_has_status_Bulk_Error() throws InterruptedException {
        String status;

        try{
        status = this.bulkPage.getBulkStatus();}
        catch (Exception e){
            throw  new RuntimeException("Couldnt get the bulk's status");
        }

        Assert.assertTrue("Response status code: " + BulkRegisterResponse.statusCode() +
                        "Bulk status = " + status+".",
                status.equals("BULK_ERROR"));

    }

    @Then("Verify that the user is not able to bulk a Fixed Volume")
    public void Verify_that_the_user_is_not_able_to_bulk_a_Fixed_Volume(){

        Assert.assertTrue("Response status code: " + BulkRegisterResponse.statusCode() ,
                BulkRegisterResponse.statusCode()!=200);


    }


}
