package QAAutomation.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.sl.In;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.ROLExportPage;
import utils.RegionMapper;
import utils.Token;

import static utils.EnviromentsConfig.CURRENT_ENV;

public class ROLExportSteps {

    public ROLExportPage page;


    Response DownloadResponse;

    @Given("set Rol Export Page")
    public void set_Rol_Export_Page(){
        page = new ROLExportPage();

        page.env = CURRENT_ENV;
    }

    @Given("RE_An ADMINISTRATOR")
    public void RE_An_ADMINISTRATOR(){
        this.page.user = "STARK_A";
        this.page.token= Token.AdminToken;
    }

    @Given("RE_A PARTICIPANT")
    public void RE_A_PARTICIPANT(){
        this.page.user = "STARK_P";
        this.page.token= Token.PartToken;
    }

    @Given("RE_Filter Date From \"([^\"]*)\"/\"([^\"]*)\"")
    public void RE_Filter_Date_From (String monthFrom, String yearFrom){
        page.filters.monthFrom = monthFrom;
        page.filters.yearFrom = yearFrom;
    }

    @Given("RE_Filter Date To \"([^\"]*)\"/\"([^\"]*)\"")
    public void RE_Filter_Date_To (String monthTo, String yearTo ){
        page.filters.monthTo =monthTo;
        page.filters.yearTo = yearTo;
    }

    @Given("RE_Filter Headoffice Document \"([^\"]*)\"")
    public void RE_Filter_Headoffice_Document (String headofficeDoc ){
        page.filters.HeadofficeDocument = headofficeDoc;
    }

    @Given("RE_Affiliate Document \"([^\"]*)\"")
    public void RE_Filter_Affiliate_Document (String affiliateDoc ){
        page.filters.AffiliateDocument = affiliateDoc;
    }

    @Given("RE_Filter Region \"([^\"]*)\"")
    public void RE_Filter_Region (String region ){

        RegionMapper regionMapper = new RegionMapper();
        String regionCode = regionMapper.searchRegionCode(region);
        page.filters.regions.add(regionCode);
    }

    @Given("RE_Filter Status \"([^\"]*)\"")
    public void RE_Filter_Status (String status ){
        page.filters.status.add(status);
    }


    @When("RE_Download Rol")
    public void RE_Download_Rol (){
        DownloadResponse = page.downloadRol();
    }

    @Then("RE_Verify that rol was downloaded")
    public void RE_Verify_that_rol_was_downloaded(){
        Assert.assertTrue("Error. Response status code: "+ DownloadResponse.statusCode()
                ,DownloadResponse.statusCode()==200);
    }

    @Then("RE_Verify that the user dont have permissions")
    public void RE_Verify_that_the_user_dont_have_permissions(){
        Assert.assertTrue("Error. Response status code: " + DownloadResponse.statusCode(),
                DownloadResponse.statusCode()==401 || DownloadResponse.statusCode()==403);
    }
}


