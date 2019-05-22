package QAAutomation.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import pages.ParticipantDeliverPage;
import QAAutomation.commons.TestBase;
import utils.Token;

import static utils.EnviromentsConfig.CURRENT_ENV;

public class ParticipantDeliverSteps extends TestBase {

    ParticipantDeliverPage participantDeliverPage;
    public Response response;
    public RequestSpecification requestSpecification;
    public JSONObject json;
    public String enviroment;
    public String POD_Receiver;
    public String POD_Sender;
    public Response Cancel_Response;
    public Response Edit_Response;
    public int Volume;
    public String PD_ID;
    public String User;


    @Given("Setup PD Page")
    public void Setup_PD_Page(){
        this.enviroment = CURRENT_ENV;
        this.participantDeliverPage = new ParticipantDeliverPage(this.enviroment);
    }

    @Given("As a POD user \"([^\"]*)\" I want to receive a volume ParticipantDeliver from a \"([^\"]*)\"")
    public void As_a_POD_user_USER_I_want_to_receive_a_volume_ParticipantDeliver_from_a_POD_Sender(String POD_Receiver,String POD_Sender)  {


        this.POD_Receiver = POD_Receiver;
        this.POD_Sender = POD_Sender;

    }

    @Given("PD_An ADMINISTRATOR")
    public void PD_An_ADMINISTRATOR(){
        this.participantDeliverPage.user = "STARK_A";
        this.participantDeliverPage.token= Token.AdminToken;
    }

    @Given("PD_A PARTICIPANT")
    public void PD_A_PARTICIPANT(){
        this.participantDeliverPage.user = "STARK_P";
        this.participantDeliverPage.token= Token.PartToken;
    }


    @Given("A PD with status Registered")
        public void A_PD_with_status_Registered(){

        try {

            this.PD_ID = this.participantDeliverPage.getRegisteredPD_ID();
        }
        catch(Exception e){
            throw new RuntimeException("Error al obtener una Participant deliver registrada previamente ");
        }
    }

    @Given("A PD with status Cancelled")
    public void A_PD_with_status_Cancelled(){
        try {
            this.PD_ID = this.participantDeliverPage.getCancelledPD_ID();
        }
        catch(Exception e){
            throw new RuntimeException("Error al obtener una Participant deliver cancelada previamente ");
        }
    }


    @When("I post the ParticipantDeliver with Volume \"([^\"]*)\"")
    public void I_post_the_ParticipantDeliver( String Volume){

        this.response = this.participantDeliverPage.postParticipantDeliverWithDocNumbers(this.POD_Receiver,this.POD_Sender,Integer.parseInt(Volume));

    }

    @When("I cancel the ParticipantDeliver")
    public void I_cancel_the_ParticipantDeliver(){
        this.Cancel_Response = this.participantDeliverPage.cancelParticipantDeliver(this.PD_ID);

    }
    @When("I edit the ParticipantDeliver with Volume \"([^\"]*)\"")
    public void I_edit_the_ParticipantDeliver_with_Volume(String arg1){

        this.Edit_Response = this.participantDeliverPage.editParticipantDeliver(this.PD_ID, arg1);

    }


    @Then("Verify that the ParticipantDeliver was posted")
    public  void Verify_that_the_ParticipantDeliver_was_posted(){
        Assert.assertTrue("Response status code: "+response.statusCode(),
                this.participantDeliverPage.VerifyIfParticipantDeliverWasPosted(this.response));

    }


    @Then("Verify that the ParticipantDeliver was cancelled")
    public  void Verify_that_the_ParticipantDeliver_was_cancelled(){
        Assert.assertTrue("Cancel_Response status code: "+Cancel_Response.statusCode(),
                this.Cancel_Response.statusCode()==200);

    }
    @Then("Verify that the ParticipantDeliver wasnt cancelled")
    public  void Verify_that_the_ParticipantDeliver_wasnt_cancelled(){
        Assert.assertTrue("Cancel_Response status code: "+Cancel_Response.statusCode(),
                this.Cancel_Response.statusCode()!=200);

    }

    @Then("Verify that the ParticipantDeliver was edited")
    public  void Verify_that_the_ParticipantDeliver_was_edited(){
        Assert.assertTrue("Edit Response status code: "+Edit_Response.statusCode(),
                this.Edit_Response.statusCode()==200);

    }

    @Then("Verify that the ParticipantDeliver wasnt edited")
    public  void Verify_that_the_ParticipantDeliver_wasnt_edited(){
        Assert.assertTrue("Edit Response status code: "+Edit_Response.statusCode(),
                this.Edit_Response.statusCode()!=200);

    }



    @Then("Verify that the ParticipantDeliver wasnt posted")
    public void verify_that_the_ParticipantDeliver_wasnt_posted() throws Exception {
        Assert.assertTrue("Response status code: "+response.statusCode(),
                !this.participantDeliverPage.VerifyIfParticipantDeliverWasPosted(this.response));

    }


}
