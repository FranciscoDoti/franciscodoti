package QAAutomation.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Assert;
import pages.CommonMethods;
import pages.ConsolidateROLCreationPage;
import utils.FixedVolumeChanges;
import utils.Token;

import java.util.Calendar;

import static utils.EnviromentsConfig.CURRENT_ENV;

public class ConsolidateCreationSteps {

    String Affiliate_Doc;
    String User;
    String Year;
    String Month;
    Response Load_Response;
    Response Create_Response;
    Response PD_Response;
    Response Submit_Response;
    ConsolidateROLCreationPage page;
    int Current_Amount;
    int Volume;
    FixedVolumeChanges fixedVolumeChanges;

    @Given("Setup ROL Creation Page")
    public void Setup_ROL_Creation_Page(){
                this.page = new ConsolidateROLCreationPage(CURRENT_ENV);
    }

    @Given("RC_An ADMINISTRATOR")
    public void RC_An_ADMINISTRATOR(){
        this.page.user = "STARK_A";
        this.page.token= Token.AdminToken;
    }

    @Given("RC_A PARTICIPANT")
    public void RC_A_PARTICIPANT(){
        this.page.user = "STARK_P";
        this.page.token= Token.PartToken;
    }

    @Given("AffiliateDocument \"([^\"]*)\"")
    public void AffiliateDocument (String arg1){
        Affiliate_Doc  =arg1;
    }

    @Given("Current Period")
    public void Current_Period(){
        Year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        Month= String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1);
        if (Integer.parseInt(Month)<10){
            Month = "0" + Month;
        }
    }
    @Given("Year \"([^\"]*)\"")
    public void Year (String arg1){
        Year  =arg1;
    }
    @Given("Month \"([^\"]*)\"")
    public void Month (String arg1){
        Month  =arg1;
    }

    @When("I load the ROL")
    public void I_load_the_ROL(){
        Load_Response = this.page.loadROL(Affiliate_Doc,Year,Month);

    }
    @When("Create the ROL directly from Load")
    public void I_create_the_ROL_directly_from_Load(){

        if (Load_Response.statusCode() != 200) {
            throw new RuntimeException("Error al hacer el Load del ROL " +
                    "Response status code: " + Load_Response.statusCode());
        }else{
            Create_Response = this.page.createROLDirectlyFromLoad(Load_Response);
        }

    }
    @When("I create the ROL")
    public void I_create_the_ROL(){

        if (Load_Response.statusCode() != 200) {
            throw new RuntimeException("Error al hacer el Load del ROL " +
                    "Response status code: " + Load_Response.statusCode());
        }else{
            Create_Response = this.page.createROL(Load_Response, this.fixedVolumeChanges);
        }


    }
    @When("I save the current amount")
    public void I_save_the_current_amount(){
        Current_Amount = Integer.parseInt(Load_Response.jsonPath().getString("results.receivedVolume.totalDelivery"));
    }
    @When("I post a participant deliver from \"([^\"]*)\" to \"([^\"]*)\" with volume \"([^\"]*)\"")
    public void postParticipantDeliver(String sender_doc, String receiver_doc, String volume){
        PD_Response = this.page.registerParticipantDeliver(receiver_doc,sender_doc,volume);
        Volume = Integer.parseInt(volume);
        if (PD_Response.statusCode() != 200) {
            throw new RuntimeException("Error al registar la Participant Deliver" +
                    "Response status code: " + PD_Response.statusCode());
        }
    }
    @When("I submit the ROL")
    public void I_submit_the_ROL(){

        Submit_Response = this.page.submitRol(Load_Response, "Automation_Test_SUBMIT");

    }

    @When("I add fixed volume \"([^\"]*)\" and fixed Price \"([^\"]*)\" for \"([^\"]*)\"")
    public void I_add_fixed_volume(String fixedVolume, String fixedPrice, String technology) throws Exception {
         this.fixedVolumeChanges = new FixedVolumeChanges(technology, Integer.parseInt(fixedVolume),Integer.parseInt(fixedPrice));

    }
    @Then("Verify that the ROL was created")
    public void Verify_that_the_ROL_was_created(){
        Assert.assertTrue("Create Response status code: "+ Create_Response.statusCode(),
                Create_Response.statusCode()==200);

    }
    @Then("Verify that the ROL couldnt be loaded")
    public void Verify_that_the_ROL_couldnt_be_loaded(){
        Assert.assertTrue("Load Response status code: "+ Load_Response.statusCode(),
                Load_Response.statusCode()!=200);

    }

    @Then("Verify that the ROL could be loaded")
    public void Verify_that_the_ROL_could_be_loaded(){
        Assert.assertTrue("Load Response status code: "+ Load_Response.statusCode(),
                Load_Response.statusCode()==200);


    }


    //Verify that the ROL was submitted
    @Then("Verify that the ROL was submitted")
    public void Verify_that_the_ROL_was_submitted(){
        String rolId = Load_Response.jsonPath().getString("results.id");
        String rolStatus = page.getRolStatus(User,Year, Month, Affiliate_Doc);
        Assert.assertTrue("Load Response status code: "+ Load_Response.statusCode() +
                        " rol Status : "+ rolStatus +
                        "rol ID : "+ rolId  ,
                rolStatus.equals("SUBMITTED"));

    }



    @Then("Verify that the sum of PD is correct")
    public void Verify_that_the_sum_of_PD_is_correct(){
        int sum = Integer.parseInt(Load_Response.jsonPath().getString("results.receivedVolume.totalDelivery"));

        Assert.assertTrue( "The previous amount was "+ Current_Amount +
                ". The volume added was "+ Volume +
                ". The current amount is =  " + sum + " .",
                Current_Amount + Volume == sum);
    }

}
