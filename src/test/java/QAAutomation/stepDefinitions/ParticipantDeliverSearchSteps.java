package QAAutomation.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.CommonMethods;
import pages.ParticipantDeliverPage;
import utils.ParticipantDeliverSearchFilters;
import utils.Token;

import java.sql.Date;
import java.text.ParseException;

import static utils.EnviromentsConfig.CURRENT_ENV;

public class ParticipantDeliverSearchSteps {

    ParticipantDeliverPage page;
    ParticipantDeliverSearchFilters filters;
    Response Search_Response;

    @Given("setup Participant Deliver Search Page")
    public void setup_Participant_Deliver_Search_Page() throws ParseException {
        this.page = new ParticipantDeliverPage(CURRENT_ENV);
        this.filters =  new ParticipantDeliverSearchFilters();
    }
    @Given("PDS_An ADMINISTRATOR")
    public void PD_An_ADMINISTRATOR(){
        this.page.user = "STARK_A";
        this.page.token= Token.AdminToken;
    }

    @Given("PDS_A PARTICIPANT")
    public void PD_A_PARTICIPANT(){
        this.page.user = "STARK_P";
        this.page.token= Token.PartToken;
    }


    @Given("Filter PD Headoffice by document \"([^\"]*)\"")
    public void Filter_PD_Headoffice_by_Document(String headoffice_doc){
        this.filters.POD_USER = headoffice_doc;
    }

    @Given("Filter PD Affiliate by document \"([^\"]*)\"")
    public void Filter_PD_Affiliate_by_Document(String Affiliate_doc){
        this.filters.POD_USER = Affiliate_doc;
    }

    @Given("Filter Date From \"([^\"]*)\"")
    public void Filter_Date_From(String dateFrom){
        try{
            long from = new java.text.SimpleDateFormat("MM/dd/yyyy").parse(dateFrom).getTime();
            this.filters.From = from;
        } catch ( ParseException e ) {
            e.printStackTrace();
        }

    }

    @Given("Filter Date To \"([^\"]*)\"")
    public void Filter_Date_To(String dateTo){
        try{
            long to = new java.text.SimpleDateFormat("MM/dd/yyyy").parse(dateTo).getTime();
            this.filters.To = to;
        } catch ( ParseException e ) {
            e.printStackTrace();
        }

    }

    @Given("Filter PD status \"([^\"]*)\"")
    public void Filter_PD_Status(String status){
        this.filters.Status= status;
    }
    @Given("Filter Sender by document Number \"([^\"]*)\"")
    public void Filter_Sender_by_document_Number(String Document){
        this.filters.DocumentOrName= Document;
    }

    @Given("Filter Sender by document Name \"([^\"]*)\"")
    public void Filter_Sender_by_document_Name(String name){
        this.filters.DocumentOrName= name;
    }

    @When("Search Participant Delivers")
    public void Search_Participant_Delivers(){
        Search_Response  = page.searchParticipantDelivers(filters, -1);

    }

    @When("PD_Search Participant Delivers with limit \"([^\"]*)\"")
    public void Search_Participant_Delivers(String limit){
        Search_Response  = page.searchParticipantDelivers(filters, Integer.parseInt(limit));


    }

    @Then("Verify that the Participant Deliver search return results")
    public void Verify_that_the_Participant_Deliver_search_return_results() throws InterruptedException {
        Assert.assertTrue("Search Response status code: "+Search_Response.statusCode(),
                Integer.parseInt(Search_Response
                .jsonPath().getString("count"))>0);
        Thread.sleep(4000);

    }
    @Then("Verify that the Participant Deliver search dont return more than \"([^\"]*)\" results")
    public void Verify_that_the_Participant_Deliver_search_dont_return_more_than_X_results(String limit) throws InterruptedException {
        int Limit = Integer.parseInt(limit);
        Assert.assertTrue("Search Response status code: "+Search_Response.statusCode(),
                Search_Response
                .jsonPath()
                .getString("results["+Limit+"]")== null);
        Thread.sleep(4000);

    }

    @Then("Verify that the user couldnt search")
    public void Verify_that_the_user_coulndt_search(){
        Assert.assertTrue("Search Response status code: "+Search_Response.statusCode(),
                Search_Response.statusCode()!=200);

    }
}

