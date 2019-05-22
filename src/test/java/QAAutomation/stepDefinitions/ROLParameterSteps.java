package QAAutomation.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.CommonMethods;
import pages.ROLModelsPage;
import pages.ROLParameterPage;
import utils.ROLModelsFilter;
import utils.Token;

import static utils.EnviromentsConfig.CURRENT_ENV;

public class ROLParameterSteps {

    String user;
    Response Edit_Response;
    ROLParameterPage page;
    //Given Internal User
    //When Edit a Parameter with Royalty "11.25"
    //Then Verify that it was edited
    //When Edit a Parameter with Royalty "11.2"
    @Given("Setup Parameters Page")
    public void Setup_Parameters_Page(){
        this.page = new ROLParameterPage(CURRENT_ENV);

    }

    @Given("Internal User")
    public void Internal_User() {
        this.page.user = "STARK_A";
        this.page.token = Token.AdminToken;

    }

    @Given("External User")
    public void External_User() {
        this.page.user = "STARK_P";
        this.page.token = Token.PartToken;

    }


    @When("Edit a Parameter with Royalty \"([^\"]*)\"")
    public void Edit_a_Parameter(String royalty) {
        Edit_Response = this.page.editParameter(royalty);
    }

    @Then("Verify that parameter was edited")
    public void Verify_that_parameter_was_edited() {
        Assert.assertTrue("Response status code: "+Edit_Response.statusCode(),
                Edit_Response.statusCode()==200);

    }

    @Then("Verify that parameter wasnt edited")
    public void Verify_that_parameter_wasnt_edited() {

        Assert.assertTrue("Response status code: "+Edit_Response.statusCode() ,
                Edit_Response.statusCode()!=200);

    }

}
