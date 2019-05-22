package QAAutomation.stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.CommonMethods;
import pages.ROLModelsPage;
import utils.ROLModelsFilter;
import utils.Token;

import static utils.EnviromentsConfig.CURRENT_ENV;

public class ROLModelsSteps {


    ROLModelsFilter filters;
    ROLModelsPage page;
    Response Search_Response;
    Response Delete_Response;
    Response Create_Response;
    Response Edit_Response;
    String id_to_delete;
    String affiliateDocNumber;
    Response HO_Search_Response;


    @Given("Setup ROL Models Page")
    public void Setup_ROL_Models_Page(){
        this.page = new ROLModelsPage(CURRENT_ENV);
        this.filters = new ROLModelsFilter();
    }

    @Given("RMod_An ADMINISTRATOR")
    public void RMod_An_ADMINISTRATOR(){
        this.page.user = "STARK_A";
        this.page.token = Token.AdminToken;
    }

    @Given("RMod_A PARTICIPANT")
    public void RMod_A_PARTICIPANT(){
        this.page.user = "STARK_P";
        this.page.token = Token.PartToken;
    }


    @Given ("An Affiliate Document Number \"([^\"]*)\"")
    public void  An_Affiliate_Document_Number (String doc){
        this.affiliateDocNumber =doc;
    }
    @Then ("Verify that it couldn't search")


    @Given("A Consolidate Report that exists")
    public void A_Consolidate_Report_that_exists(){
        id_to_delete = this.page.getConsolidateReport();
        if (this.id_to_delete==null) {
            throw new RuntimeException("Error al encontrar un rol consolidado existente" +
                    "El ID encontrado fue:  "+ id_to_delete);
        }
    }

    @Given("A headoffice \"([^\"]*)\"")
    public void A_headoffice (String arg1) {
        this.filters.Headoffice_Document = arg1;
    }



    @Given("A User \"([^\"]*)\"")
    public void A_User (String arg1) {
        this.filters.User = arg1;
    }


    @Given("Consolidate Report Type")
    public void Consolidate_Report_Type () {
        this.filters.CONSOLIDATED = true;
    }

    @Given("Partial Report Type")
    public void Partial_Report_Type () {
        this.filters.PARTIAL = true;
    }

    @Given("Detailed Report Type")
    public void Detailed_Report_Type () {
        this.filters.DETAILED = true;
    }

    @When("Search ROL Models")
    public void Search_ROL_Models () {
        Search_Response = this.page.searchROLModels(filters,-1);

    }


    @When("RM_Search ROL Models with limit \"([^\"]*)\"")
    public void Search_ROL_Models (String limit) {
        Search_Response = this.page.searchROLModels(filters, Integer.parseInt(limit));

    }

    @When("Edit to a \"([^\"]*)\" one")
    public void edit_to_another_model_type(String arg1){
        Edit_Response = this.page.editROLModel(arg1,filters.Headoffice_Document);

    }

    @When("Delete the ROL Model")
    public void Delete_ROL_Model () {
        Delete_Response = this.page.deleteROLModel(id_to_delete);

    }

    @When("Delete ROL Model with id \"([^\"]*)\"")
    public void Delete_ROL_Model (String ID_DELETE) {
        Delete_Response = this.page.deleteROLModel(ID_DELETE);

    }

    @When("Create \"([^\"]*)\" ROL Model for \"([^\"]*)\"")
    public void Create_ROL_Model(String reportType, String Headoffice_Document ){
        Create_Response = page.createROLModel( reportType, Headoffice_Document);

    }
    @When ("Search For Headoffices to create ROL")
    public void Search_For_Headoffices_to_create_ROL(){
        HO_Search_Response = CommonMethods.getHeadoffice(this.affiliateDocNumber, this.page.user);
        if (this.HO_Search_Response.statusCode()!= 200) {
            throw new RuntimeException("Error al encontrar el headoffice " +
                    "Response status code:  "+ this.HO_Search_Response.statusCode());
        }
    }
    @Then("Verify that the search return results")
    public void Verify_that_the_search_return_results () {
        Assert.assertTrue("Response status code: "+Search_Response.statusCode(),
                page.verifyThatSearchFoundResults(Search_Response));

    }

    @Then("Verify that the search dont return results")
    public void Verify_that_the_search_dont_return_results () {
        Assert.assertTrue("Response status code: "+Search_Response.statusCode(),
                page.verifyThatSearchDontFoundResults(Search_Response));

    }

    @Then("Verify that the ROL Model search dont return more than \"([^\"]*)\" results")
    public void Verify_that_the_search_dont_return_more_than_X_results (String limit) {
        int Limit = Integer.parseInt(limit);
        Assert.assertTrue("Response status code: "+Search_Response.statusCode(),
                Search_Response.jsonPath().getString("results["+Limit+"]")== null);

    }

    @Then("Verify that it was deleted")
    public void Verify_that_it_was_deleted(){
        Assert.assertTrue("Response status code: "+Delete_Response.statusCode(),
                this.Delete_Response.statusCode()==200);

    }

    @Then("Verify that it wasnt deleted")
    public void Verify_that_it_wasnt_deleted(){

        Assert.assertTrue("Response status code: "+this.Delete_Response.statusCode(),
                this.Delete_Response.statusCode()!=200);

    }

    @Then("Verify that it was created")
    public void Verify_that_it_was_created(){

        Assert.assertTrue("Response status code: "+this.Create_Response.statusCode(),
                this.Create_Response.statusCode()==200);

    }


    @Then("Verify that it wasnt created")
    public void Verify_that_it_wasnt_created(){

        Assert.assertTrue("Response status code: "+this.Create_Response.statusCode(),
                this.Create_Response.statusCode()!=200);

    }

    @Then("Verify that it was edited")
    public void Verify_that_it_was_edited(){

        Assert.assertTrue("Response status code: "+this.Edit_Response.statusCode(),
                this.Edit_Response.statusCode()==200);

    }


    @Then("Verify that it wasnt edited")
    public void Verify_that_it_wasnt_edited(){

        Assert.assertTrue("Response status code: "+this.Edit_Response.statusCode(),
                this.Edit_Response.statusCode()!=200);

    }

    @Then("Verify that it couldnt search")
    public void Verify_that_it_couldnt_search(){
        Assert.assertTrue("Response status code: "+this.HO_Search_Response.statusCode(),
                Integer.parseInt(this.HO_Search_Response.jsonPath().getString("total")) == 0);

    }

    @Then("Verify that the user dont have permissions to search")
    public void Verify_that_the_user_dont_have_permissions_to_search(){
        Assert.assertTrue("Response status code: "+Search_Response.statusCode(),
                Search_Response.statusCode()!=200);

    }
}
