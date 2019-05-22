package QAAutomation.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.Assert;
import pages.CommonMethods;
import pages.ROLHistoryPage;
import utils.ROLHistoryFilters;

import io.restassured.response.Response;
import utils.Token;

import static utils.EnviromentsConfig.CURRENT_ENV;

public class ROLHistorySteps {

    ROLHistoryPage page;
    ROLHistoryFilters filters;
    Response Search_Response;
    Response Generate_Response;

    @Given("setup ROL History Page")
    public void setup_ROL_History_Page(){
        this.page = new ROLHistoryPage(CURRENT_ENV);
        this.filters = new ROLHistoryFilters();
    }

    @Given("RH_An ADMINISTRATOR")
    public void RH_An_ADMINISTRATOR()
    {
        this.page.user = "STARK_A";
        this.page.token= Token.AdminToken;
    }
    @Given("RH_A PARTICIPANT")
    public void RH_An_PARTICIPANT()
    {
        this.page.user = "STARK_P";
        this.page.token= Token.PartToken;

    }


    @Given("Filter Headoffice by document \"([^\"]*)\"")
    public void Filter_Headoffice_by_Document(String headoffice_doc){
        this.filters.Headoffice = headoffice_doc;
    }
    @Given("Filter Affiliate by doc \"([^\"]*)\"")
    public void Filter_Affiliate_by_Doc(String AffiliateDoc){
        this.filters.Affiliate = AffiliateDoc;
    }
    @Given("Filter period year \"([^\"]*)\"")
    public void Filter_Period(String year) {
        this.filters.year = year;
    }
    @Given("Filter status \"([^\"]*)\"")
    public void Filter_status(String status){
         int statusCode;

        switch (status){
           case  "REGISTERED": statusCode= 1;
                                break;
           case "SUBMITTED" : statusCode = 2;
                                break;
            case "APPROVED" : statusCode = 3;
                break;
            case "BILLED" : statusCode = 4;
                break;
            case "REJECTED" : statusCode = 5;
                break;
            default: statusCode = -1;
        }

        if (statusCode!= -1){
            this.filters.status = status;}
            else {
                System.out.println("Status incorrecto");
        }
    }

    @When("Search ROLs")
    public void Search_ROLs(){
       Search_Response =  this.page.searchROL(filters);

    }




    //RH_Search ROLs with Limit "25"
    @When("Generate Report")
    public void Generate_Report(){
        Generate_Response =  this.page.generateReport(filters);

    }

    @Then("Verify that the ROL History search return results")
    public void Verify_that_the_ROL_History_search_return_results(){
        int count;
        try{
            count = Integer.parseInt(Search_Response.jsonPath().getString("count"));
            Assert.assertTrue("Search Response status code: "+Search_Response.statusCode(),
                    count > 0);
        }catch(Exception e){
            throw new RuntimeException("The search didn't retrieve results." +
                    "Search Response status code: "+Search_Response.statusCode());
        }
    }

    @Then("RH_Verify that the user dont have permissions to search in ROL History")
    public void RH_Verify_that_the_user_dont_have_permissions_to_search_in_ROL_History(){
        Assert.assertTrue("Search Response status code: "+Search_Response.statusCode(),
                Search_Response.statusCode()!=200);
    }
    @Then("RH_Verify that it couldnt search cause of the year is mandatory")
    public void RH_Verify_that_it_couldnt_search_cause_of_the_year_is_mandatory(){
        Assert.assertTrue("Error: Search Response status code: "+Search_Response.statusCode(),
                Search_Response.statusCode()!=200);
    }


    @Then("Verify that the report was generated")
    public void Verify_that_the_report_was_generated() {
        Assert.assertTrue("Report wasnt generated. Response status code: " +Generate_Response.statusCode(),
                Generate_Response.statusCode()==200);
    }



    @Then("RH_Verify that the user dont have permissions")
    public void Verify_that_the_user_dont_have_permissions(){
        if (Search_Response != null){

            Assert.assertTrue("Response status code: ", Search_Response.statusCode()!=200);

        } else if (Generate_Response != null){
                Assert.assertTrue("Response status code: ",  Generate_Response.statusCode()!=200);
        }
    }
}
