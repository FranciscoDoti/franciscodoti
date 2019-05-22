package QAAutomation.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.Assert;
import pages.CommonMethods;
import pages.FixedVolumePage;
import utils.FixedVolumeFilters;
import utils.PoliteString;
import utils.Token;

import static utils.EnviromentsConfig.CURRENT_ENV;

public class FixedVolumeSteps {


    FixedVolumePage page;
    Response createResponse;
    Response deleteResponse;
    Response searchResponse;
    Response editResponse;
    FixedVolumeFilters filters;



    @Given("set Fixed Volume Page")
    public void set_Fixed_Volume_Page(){
        this.page = new FixedVolumePage(CURRENT_ENV);
        this.filters=  new FixedVolumeFilters();
    }

    @Given("A Grower \"([^\"]*)\"")
    public void A_Grower(String growerDoc)
    {

       this.page.Grower = CommonMethods.getGrower(growerDoc, this.page.user);

        if (this.page.Grower.statusCode() != 200) {
            throw new RuntimeException("Error al obtener el Grower. " +
                    "Response status code: " + this.page.Grower.statusCode());
        }



    }

    @Given("Filter Headoffice by doc \"([^\"]*)\"")
    public void Filter_Headoffice_by_doc(String HeadofficeDoc){
        this.filters.Headoffice_Document = HeadofficeDoc;
    }

    @Given("Filter Headoffice by name \"([^\"]*)\"")
    public void Filter_Headoffice_by_name(String HeadofficeName){
        this.filters.Headoffice_Name = HeadofficeName;
    }
    @Given("Filter Technology \"([^\"]*)\"")
    public void Filter_Technology (String technology){
        if(technology.equalsIgnoreCase("INTACTA")){
        this.filters.Technology = "e945be00-5f35-4c0e-94e8-07bd41c8a84c";}
        if(technology.equalsIgnoreCase("XTEND")){
            this.filters.Technology = "bb537c34-0288-42c6-b5bb-db99ef4e0de2";}
    }

    @Given("Filter Identifier")
    public void Filter_Identifier(){
        String identifier;
        if (CURRENT_ENV.equals("dev")){
            identifier="a21f68dc-7403-479d-8188-0ff413e0830e";
            this.filters.Identifer = identifier;
        }else if (CURRENT_ENV.equals("it")){
            identifier="0d8b05f5-66d3-4f7d-93ce-37acee3e8c48";
            this.filters.Identifer = identifier;
        }
    }
    @Given("Filter Grower \"([^\"]*)\"")
    public void Filter_Grower(String grower){
        this.filters.Grower = grower;
    }
    @Given("FV_An ADMINISTRATOR")
    public void FV_An_ADMINISTRATOR(){
        this.page.user = "STARK_A";
        this.page.token= Token.AdminToken;
    }

    @Given("FV_A PARTICIPANT")
    public void FV_A_PARTICIPANT(){
        this.page.user = "STARK_P";
        this.page.token= Token.PartToken;
    }

    @Given("A headoffice with doc \"([^\"]*)\"")
    public void A_Headoffice(String headofficeDoc){
        this.page.Headoffice = CommonMethods.getHeadoffice(headofficeDoc,this.page.user);
        if (this.page.Headoffice.statusCode() != 200) {
            throw new RuntimeException("Error al obtener el Headoffice. " +
                    "Response status code: " + this.page.Headoffice.statusCode());
        }
    }
    @Given("Xtend volume \"([^\"]*)\"")
    public void Xtend_Volume(String vol){
        this.page.XtendVolume = Integer.parseInt(vol);
    }
    @Given("Intacta volume \"([^\"]*)\"")
    public void Intacta_Volume(String vol){
        this.page.IntactaVolume = Integer.parseInt(vol);
    }

    @When("Create Fixed Volume")
    public void Create_Fixed_Volume(){
        createResponse =  this.page.createFixedVolume();

    }

    @When("Search Fixed Volume")
    public void Search_Fixed_Volume(){
        searchResponse =  this.page.searchFixedVolume(filters,-1);

    }


    @When("FV_Search Fixed Volume with limit \"([^\"]*)\"")
    public void Search_Fixed_Volume_with_limit(String limit){
        searchResponse =  this.page.searchFixedVolume(filters, Integer.parseInt(limit));
        if (searchResponse.statusCode() != 200) {
            throw new RuntimeException("Error al buscar el Fixed Volume. " +
                    "Response status code: " + searchResponse.statusCode());
        }
    }


    @When("Delete the last Fixed Volume")
    public void Delete_the_last_Fixed_Volume(){
        PoliteString politeString = new PoliteString();
        String id = politeString.emprolijar(searchResponse.jsonPath().getString("results[0].id"));
        deleteResponse =  this.page.deleteLastFixedVolume(id);


    }

    @When("Edit a Fixed Volume setting \"([^\"]*)\"")
    public void Edit_Fixed_Volume(String volume){
        editResponse =  this.page.editFixedVolume(filters, volume);

    }


    @Then("Verify that fixed volume was created")
    public void Verify_that_fixed_volume_was_created()
    {
        Assert.assertTrue("Create Response status code: "+createResponse.statusCode(),
                createResponse.statusCode()==200);

    }

    @Then("Verify that fixed volume wasnt created")
    public void Verify_that_fixed_volume_wasnt_created()
    {
        Assert.assertTrue("Create Response status code: "+createResponse.statusCode(),
                createResponse.statusCode()!=200);

    }

    @Then("Verify that fixed volume was deleted")
    public void Verify_that_fixed_volume_was_deleted(){
        Assert.assertTrue("Delete Response status code: "+deleteResponse.statusCode(),
                deleteResponse.statusCode()==200);

    }
    @Then("Verify that fixed volume wasnt deleted")
    public void Verify_that_fixed_volume_wasnt_deleted(){
        Assert.assertTrue("Delete Response status code: "+deleteResponse.statusCode(),
                deleteResponse.statusCode()!=200);

    }

    @Then("Verify that the search brings results")
    public void Verify_that_the_search_brings_results(){
        Assert.assertTrue("Search Response status code: "+searchResponse.statusCode()+
                "Search count results: " + Integer.parseInt(searchResponse.jsonPath().getString("count")),
                Integer.parseInt(searchResponse.jsonPath().getString("count"))!= 0);


    }

    @Then("Verify that the user dont have permissions to create")
    public void Verify_that_the_user_dont_have_permissions_to_create(){
        Assert.assertTrue("Create Response status code: "+createResponse.statusCode(),
                 createResponse.statusCode()!= 401);
    }

    @Then("FV_Verify that the user dont have permissions to search")
    public void FV_Verify_that_the_user_dont_have_permissions_to_search(){
        Assert.assertTrue("Search Response status code: "+searchResponse.statusCode(),
                searchResponse.statusCode()!=200);

    }

    @Then("Verify that the Fixed Volume search doesnt bring more than \"([^\"]*)\" results")
    public void Verify_that_the_Fixed_Volume_search_doesnt_bring_more_than_X_results(String limit){
        int Limit = Integer.parseInt(limit);
        Assert.assertTrue("Search Response status code: "+searchResponse.statusCode(),
                searchResponse.jsonPath().getString("results["+Limit+"]")== null);
        System.out.println();
    }
    //Verify that the Fixed Volume search doesnt bring more than "10" results

    @Then("Verify that the fixedVolume was edited")
    public void Verify_that_the_fixedVolume_was_edited(){
        Assert.assertTrue("Edit Response status code: "+editResponse.statusCode(),
                editResponse.statusCode() ==200);

    }
    @Then("Verify that the fixedVolume wasnt edited")
    public void Verify_that_the_fixedVolume_wasnt_edited(){
        Assert.assertTrue("Edit Response status code: "+editResponse.statusCode(),
                editResponse.statusCode() !=200);

    }

}