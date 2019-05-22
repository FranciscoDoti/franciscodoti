package QAAutomation.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import gherkin.events.CucumberEvent;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.*;
import utils.FixedVolumeChanges;
import utils.Token;

import java.util.ArrayList;
import java.util.HashMap;

import static utils.EnviromentsConfig.CURRENT_ENV;

public class WorkflowSteps {
//Workflow Vars
    WorkflowPage workflowPage;
    ConsolidateROLCreationPage rolCreationPage;
    ROLModelsPage rolModelsPage;
    Response Search_Response;
    Response Load_Response;
    Response Approve_Response;
    Response Reject_Response;
    ROLPeriod rolPeriod;
    String HeadofficeDoc;
    //When Search ROL by document "08707604000184"
    //Then Verify that Workflow search return results



    /*

            Given Period without ROL for Headoffice "08707604000184"
        When WF_Create "CONSOLIDATED" ROL Model for "08707604000184"
            And WF_I load the ROL
            And WF_I create the ROL
            And WF_I submit the ROL
        Given Filter ROL by document "08707604000184"
        When Workflow search ROLs
            And I approve the ROL
        Then Verify that ROL was approved
        Then Verify that the Approve Reject search return the approved ROL

     */
    @Given("Period without ROL for Headoffice \"([^\"]*)\"")
    public void Period_without_ROL_for_Headoffice_X (String doc){
        this.HeadofficeDoc = doc;
        rolPeriod = new ROLPeriod();
        rolPeriod =  CommonMethods.getPeriodWithoutROL(doc);
        if (rolPeriod.month == null || rolPeriod.year == null){
            throw new RuntimeException("Error al obtener el período sin rol.");
        }
    }


    @Given("Setup Workflow Page")
    public void Setup_Workflow_Page(){
        workflowPage = new WorkflowPage();
        workflowPage.user="STARK_A";
        workflowPage.token = Token.AdminToken;
    }

    @Given("WF_Setup ROL Creation Page")
    public void WF_Setup_ROL_Creation_Page(){
        rolCreationPage = new ConsolidateROLCreationPage(CURRENT_ENV);
        rolCreationPage.user = "STARK_A";
        rolCreationPage.token = Token.AdminToken;
    }


    @Given("WF_Setup ROL Models Page")
    public void WF_Setup_ROL_Models_Page(){
        rolModelsPage = new ROLModelsPage(CURRENT_ENV);
        rolModelsPage.user = "STARK_A";
        rolModelsPage.token = Token.AdminToken;
    }

    @Given("WF_An ADMINISTRATOR")
    public void WF_An_ADMINISTRATOR(){
        this.workflowPage.user="STARK_A";
    }

    @Given("Filter ROL by document \"([^\"]*)\"")
    public void Filter_ROL_by_document (String docNumber){
        this.workflowPage.filters.docNumber = docNumber;
    }

    @Given("Filter ROL by Year From \"([^\"]*)\"")
    public void Filter_ROL_by_Year_From (String year){
        this.workflowPage.filters.yearFrom = year;
    }

    @Given("Filter ROL by Month From \"([^\"]*)\"")
    public void Filter_ROL_by_Month_From (String year){
        this.workflowPage.filters.monthFrom = year;
    }


    @Given("Filter ROL by Year To \"([^\"]*)\"")
    public void Filter_ROL_by_Year_To (String year){
        this.workflowPage.filters.yearTo = year;
    }

    @Given("Filter ROL by Month To \"([^\"]*)\"")
    public void Filter_ROL_by_Month_To (String year){
        this.workflowPage.filters.monthTo = year;
    }

    @Given("Filter ROL by region \"([^\"]*)\"")
    public void Filter_ROL_by_region(String region){
        this.workflowPage.filters.region = region;
    }

    @Given("Workflow search ROLs")
    public void Workflow_search_ROLs(){
        Search_Response = this.workflowPage.searchROL();
        if (this.Search_Response.statusCode() != 200) {
            throw new RuntimeException("Error al buscar el ROL. " +
                    "Response status code: " + this.Search_Response.statusCode());
        }
    }

    @Given("Workflow search Approved and Rejected ROLs")
    public void Workflow_searchApproved_and_Rejected_ROLs(){
        Search_Response = this.workflowPage.searchApprovedRejectedROL();
        if (this.Search_Response.statusCode() != 200) {
            throw new RuntimeException("Error al buscar el ROL dento de la busqueda de Aprobados y Rechazados. " +
                    "Response status code: " + this.Search_Response.statusCode());
        }
    }

    @When("WF_Create \"([^\"]*)\" ROL Model for \"([^\"]*)\"")
    public void WF_Create_ROL_Model(String reportType, String Headoffice_Document ){
       Response response;
        response = rolModelsPage.createROLModel( reportType, Headoffice_Document);

    }

    @When("WF_I load the ROL")
    public void WF_I_load_the_ROL(){

        Load_Response = rolCreationPage.loadROL(HeadofficeDoc,rolPeriod.year,rolPeriod.month);
        if (Load_Response.statusCode() != 200) {
            throw new RuntimeException("Error al hacer el LOAD del rol. " +
                    "Response status code: " + Load_Response.statusCode());
        }
    }

    @When("WF_I create the ROL")
    public void WF_I_create_the_ROL(){
        FixedVolumeChanges fixedVolumeChanges= new FixedVolumeChanges("",2,10);
        Response Create_Response = rolCreationPage.createROL(Load_Response, fixedVolumeChanges);
        if (Create_Response.statusCode() != 200) {
            throw new RuntimeException("Error al hacer el CREATE del rol. " +
                    "Response status code: " + Create_Response.statusCode());
        }
    }
    @When("WF_I submit the ROL")
    public void WF_I_submit_the_ROL(){

        Response Submit_Response = rolCreationPage.submitRol(Load_Response, "Automation_Test_SUBMIT");
        if (Submit_Response.statusCode() != 200) {
            throw new RuntimeException("Error al hacer el SUBMIT del rol. " +
                    "Response status code: " + Submit_Response.statusCode());
        }
    }

    @When("I approve the ROL")
    public void I_approve_the_ROL(){
      Approve_Response = this.workflowPage.approveROL(Search_Response.jsonPath().getString("results[0].id"));

    }

    @When("I reject the ROL")
    public void I_reject_the_ROL(){
        Reject_Response = this.workflowPage.rejectRol(Search_Response.jsonPath().getString("results[0].id"),
                Search_Response.jsonPath().getJsonObject("results[0].comments"));

    }
    @Then("Verify that ROL was approved")
    public void Verify_that_ROL_was_approved(){

        Assert.assertTrue("Approve_Response status code: "+ Approve_Response.statusCode(),
                Approve_Response.statusCode() ==200);

    }

    @Then("Verify that ROL was rejected")
    public void Verify_that_ROL_was_rejected(){
        Assert.assertTrue("Reject_Response status code: "+ Reject_Response.statusCode(),
                Reject_Response.statusCode() ==200);

    }


    @Then("Verify that Workflow search return results")
    public void verify_ROL_Workflow_search_return_results(){
        Assert.assertTrue("Search_Response status code: "+ Search_Response.statusCode(),
                Search_Response.statusCode()==200);

    }

    @Then("Verify that the Approve Reject search return the approved ROL")
    public void Verify_that_the_Approve_Reject_search_return_the_approved_ROL(){
        workflowPage.filters.docNumber= HeadofficeDoc;
        workflowPage.filters.monthFrom= rolPeriod.month;
        workflowPage.filters.yearFrom=rolPeriod.year;
        workflowPage.filters.yearTo="2019";
        workflowPage.filters.monthTo="1";
        ArrayList list = workflowPage.searchApprovedRejectedROL()
                .jsonPath().getJsonObject("results");
        Assert.assertTrue("Trajo : "+ list.size()+ " resultados.",
                list.size()>0);

    }
}


