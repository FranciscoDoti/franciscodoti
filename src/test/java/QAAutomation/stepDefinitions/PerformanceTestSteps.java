package QAAutomation.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.CommonMethods;
import pages.PerformanceTestPage;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import static utils.EnviromentsConfig.CURRENT_ENV;

public class PerformanceTestSteps {

    public PerformanceTestPage performanceTestPage;
    public Response response;



    @Given("set Performance page")
    public void set_Performance_page(){
        this.performanceTestPage = new PerformanceTestPage();
        this.performanceTestPage.env= CURRENT_ENV;
    }

    @Given("PFT_An ADMINISTRATOR")
    public void PFT_An_ADMINISTRATOR(){
        this.performanceTestPage.user= "STARK_A";
        this.performanceTestPage.token = CommonMethods.getToken(performanceTestPage.user);
    }

    @Given("PFT_A PARTICIPANT")
    public void PFT_A_PARTICIPANT(){
        this.performanceTestPage.user= "STARK_P";
        this.performanceTestPage.token = CommonMethods.getToken(performanceTestPage.user);
    }

    @When("PFT_Get token")
    public void PFT_Get_token(){
        response = performanceTestPage.getToken(performanceTestPage.user);
    }

    @When("PFT_Post a fixed volume")
    public void PFT_Post_a_fixed_volume(){
        try
        {response = performanceTestPage.postFixedVolume();} catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
    @When("PFT_List Participant deliveries")
    public void PFT_List_Participant_deliveries(){

        response = performanceTestPage.listParticipantDeliveries();

    }

    @When("PFT_Post Participant delivery")
    public void PFT_Post_Participant_delivery(){

        try {
            response = performanceTestPage.postParticipantDelivery();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    @When("PFT_Load a Rol")
    public void PFT_Load_a_Rol(){

        try {
            response = performanceTestPage.loadRol();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @When("PFT_List Rol History")
    public void PFT_List_Rol_History(){

            response = performanceTestPage.listRolHistory();

    }

    @When("PFT_List rol pending requests")
    public void PFT_List_rol_pending_requests() {

        response = performanceTestPage.listRolPendingRequests();
    }


    @When("PFT_List rols approved and rejected")
    public void PFT_List_rols_approved_and_rejected() {

        response = performanceTestPage.listRolsApprovedAndRejected();
    }

    @When("PFT_List Rol Models")
    public void PFT_List_Rol_Models(){

        response = performanceTestPage.listRolModels();

    }

    @When("PFT_Load a Season closing")
    public void PFT_Load_a_Season_closing(){

        response = performanceTestPage.loadSeasonClosing();

    }


    @When("PFT_List Season Closing pending requests")
    public void PFT_List_Season_Closing_pending_requests(){

        response = performanceTestPage.listSeasonClosingPendingRequests();

    }

    @When("PFT_SC_List approved and rejected Season Closing reports")
    public void PFT_SC_List_Season_Closing_pending_requests(){

        response = performanceTestPage.listApprovedAndRejectedSeasonClosingReports();

    }




    @When("PFT_Export Rol History report")
    public void PFT_Export_Rol_History_report(){

        response = performanceTestPage.exportRolHistoryReport();

    }

    @When("PFT_Export Participant deliveries")
    public void PFT_Export_Participant_deliveries(){

        try {
            response = performanceTestPage.exportParticipantDeliveries();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @When("PFT_List parameters")
    public void PFT_List_parameters(){

        response = performanceTestPage.listParameters();

    }

    @When("PFT_List fixed volumes")
    public void PFT_List_fixed_volumes(){

        response = performanceTestPage.listFixedVolumes();

    }


    @Then("PFT_Verify that time response is less than \"([^\"]*)\"")
    public void verify_response_time(String time){
        int miliseconds =Integer.parseInt(time);

        System.out.println("Response time: "+ response.getTime());

        Assert.assertTrue("Response time: "+ response.getTime(),response.getTime()<miliseconds);
    }
}
