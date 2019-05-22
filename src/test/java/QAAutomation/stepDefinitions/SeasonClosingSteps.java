package QAAutomation.stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.SeasonClosingPage;
import utils.Token;

import static utils.EnviromentsConfig.CURRENT_ENV;

public class SeasonClosingSteps {

    SeasonClosingPage page;

    Response SaveResponse;
    Response SubmitResponse;
    Response RejectResponse;
    Response ApproveResponse;


    @Given("set Season Closing Page")
    public void set_Season_Closing_Page(){
        page  = new SeasonClosingPage();
        page.env= CURRENT_ENV;
    }

    @Given("SC_An ADMINISTRATOR")
    public void SC_An_ADMINISTRATOR(){
        this.page.token = Token.AdminToken;
        this.page.user="STARK_A";
    }

    @Given("Headoffice without Season Closing")
    public void Headoffice_without_Season_Closing(){
        page.HeadofficeDoc = page.getHeadofficeDocWithoutSeasonClosing();
        if (page.HeadofficeDoc!=null){
            System.out.println("se obtuvo el documento del headoffice ");
        }
    }

    @Given("SC_Year \"([^\"]*)\"")
    public void SC_Year(String year){
        page.year = year;
        if (page.year!=null){
            System.out.println("Se obtuvo el year");
        }

    }

    @When("Save Season Closing")
    public void Save_Season_Closing(){
        SaveResponse = page.saveSeasonClosing();
        if (SaveResponse!= null ){
            System.out.println("Season Closing saved");
        }
    }
    @When("Submit Season Closing")
    public void Submit_Season_Closing(){
        SubmitResponse = page.submitSeasonClosing();
    }
    @When("Reject Season Closing")
    public void Reject_Season_Closing(){
        RejectResponse = page.rejectSeasonClosing();
    }
    @When("Approve Season Closing")
    public void Approve_Season_Closing(){
        ApproveResponse = page.approveSeasonClosing();
    }

    @Then ("Verify that the Season Closing was saved")
    public void Verify_that_the_Season_Closing_was_saved(){
        Assert.assertTrue("Error! Response status code: "+ SaveResponse.statusCode(),
                SaveResponse.statusCode()==200);
    }

    @Then ("Verify that the Season Closing was submitted")
    public void Verify_that_the_Season_Closing_was_submitted(){
        Assert.assertTrue("Error! Response status code: "+ SubmitResponse.statusCode(),
                SubmitResponse.statusCode()==200);
    }

    @Then ("Verify that the Season Closing was rejected")
    public void Verify_that_the_Season_Closing_was_rejected(){
        Assert.assertTrue("Error! Response status code: "+ RejectResponse.statusCode(),
                RejectResponse.statusCode()==200);
    }

    @Then ("Verify that the Season Closing was approved")
    public void Verify_that_the_Season_Closing_was_approved(){
        Assert.assertTrue("Error! Response status code: "+ ApproveResponse.statusCode(),
                ApproveResponse.statusCode()==200);
    }

}

