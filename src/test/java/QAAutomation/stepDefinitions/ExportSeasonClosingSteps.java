package QAAutomation.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.ExportSeasonClosingPage;
import utils.ExportSeasonClosingFilters;
import utils.RegionMapper;
import utils.Token;

import static utils.EnviromentsConfig.CURRENT_ENV;

public class ExportSeasonClosingSteps {

    ExportSeasonClosingPage page;
    Response ExportResponse;

    @Given("set Export Season Closing Page")
    public void set_Export_Season_Closing_Page() {
        page = new ExportSeasonClosingPage();
        page.filters = new ExportSeasonClosingFilters();
        page.env = CURRENT_ENV;
    }

    @Given("ESC_An ADMINISTRATOR")
    public void ESC_An_ADMINISTRATOR() {
        page.user = "STARK_A";
        page.token = Token.AdminToken;
    }

    @Given("ESC_Filter by Year \"([^\"]*)\"")
    public void ESC_Filter_by_Year(String year) {
        page.filters.year = year;
    }

    @Given("ESC_Filter by Headoffice document \"([^\"]*)\"")
    public void ESC_Filter_by_Headoffice_document(String headofficeDoc) {
        page.filters.HeadofficeDocument = headofficeDoc;
    }

    @Given("ESC_Filter by Grower document \"([^\"]*)\"")
    public void ESC_Filter_by_Grower_document(String growerDoc) {
        page.filters.GrowerDocument = growerDoc;
    }

    @Given("ESC_Filter by region \"([^\"]*)\"")
    public void ESC_Filter_by_region(String region) {
        RegionMapper regionMapper = new RegionMapper();
        String regionCode = regionMapper.searchRegionCode(region);
        page.filters.regions.add(regionCode);
    }


    @Given("ESC_Filter by date from \"([^\"]*)\" / \"([^\"]*)\" / \"([^\"]*)\"")
    public void ESC_Filter_by_date_from(String dayFrom, String monthFrom, String yearFrom) {
        page.filters.dayFrom = dayFrom;
        page.filters.monthFrom = monthFrom;
        page.filters.yearFrom = yearFrom;
    }

    @Given("ESC_Filter by date to \"([^\"]*)\" / \"([^\"]*)\" / \"([^\"]*)\"")
    public void ESC_Filter_by_date_to(String dayTo, String monthTo, String yearTo) {
        page.filters.dayTo = dayTo;
        page.filters.monthTo = monthTo;
        page.filters.yearTo = yearTo;
    }

    @Given("ESC_Filter by status \"([^\"]*)\"")
    public void ESC_Filter_by_Status(String status) {
        page.filters.status.add(status);
    }


    @When("Export Season Closing report")
    public void Export_Season_Closing_report() {
        ExportResponse = page.exportSeasonClosingReport();
    }


    @Then("ESC_Verify that the report was generated")
    public void ESC_Verify_that_the_report_was_generated() {
        Assert.assertTrue("Error! Response status code:  "
                        + ExportResponse.statusCode(),
                ExportResponse.statusCode() == 200);
    }
}