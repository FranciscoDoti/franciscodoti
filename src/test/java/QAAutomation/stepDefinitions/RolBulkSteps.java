package QAAutomation.stepDefinitions;

import com.fasterxml.uuid.Generators;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Assert;
import pages.RolBulkPage;
import utils.Token;

import java.io.FileNotFoundException;
import java.util.UUID;

import static utils.EnviromentsConfig.CURRENT_ENV;

public class RolBulkSteps {


    public RolBulkPage page;
    public String UUID;
    public Response PostBulkResponse;
    public Response UploadedFilesResponse;
    public String IdToFound;
    public Response DownloadResponse;


    @Given("RB_set ROL Bulk Page")
    public void RB_set_ROL_Bulk_Page(){
        page = new RolBulkPage();
        page.env = CURRENT_ENV;

    }
    @Given("RB_Set new UUID")
    public void RB_Set_new_UUID(){
        String bulkId;
        UUID uuid = Generators.randomBasedGenerator().generate();
        bulkId = uuid.toString();
        this.UUID = bulkId;
        System.out.println("UUID: "+ this.UUID);
    }

    @Given("Bulk file with 3 records and all data ok")
    public void Bulk_file_with_3_records_and_all_data_ok() throws FileNotFoundException {
        String jsonFileName = "RolBulkOK.json";

        this.page.loadJsonArray(jsonFileName);
    }

    //Bulk file with 3 records and several errors

    @Given("Bulk file with 3 records and several errors")
    public void Bulk_file_with_3_records_and_several_errors() throws FileNotFoundException {

        String jsonFileName = "RolBulkErrors.json";
        this.page.loadJsonArray(jsonFileName);
    }

    @Given("Bulk file with Received Declared Volume null and \"([^\"]*)\"")
    public void Bulk_file_with_Received_Declared_Volume_null(String technology) throws FileNotFoundException {


        if (technology.equals("NA")){
            String jsonFileName = "RB_ReceivedDeclaredVolumeNULL.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_ReceivedDeclaredVolumeNULL_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }

    }


    @Given("Bulk file with Received Declared Volume with big number and \"([^\"]*)\"")
    public void Bulk_file_with_Received_Declared_Volume_with_big_number(String technology) throws FileNotFoundException {


        if (technology.equals("NA")){
            String jsonFileName = "RB_ReceivedDeclaredVolumeBigNumber.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_ReceivedDeclaredVolumeBigNumber_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }
    }



    @Given("Bulk file with affiliate with report rol not valid")
    public void Bulk_file_with_affiliate_with_report_rol_not_valid() throws FileNotFoundException {

        String jsonFileName = "RB_AffiliateWithReportROLNotValid.json";
        this.page.loadJsonArray(jsonFileName);

    }



    @Given("Bulk file with affiliate not found for a Headoffice")
    public void Bulk_file_with_affiliate_not_found_for_a_Headoffice() throws FileNotFoundException {

        String jsonFileName = "RB_AffiliateNotMatchWithHeadoffice.json";
        this.page.loadJsonArray(jsonFileName);

    }



    @Given("Bulk file with affiliate with report rol disabled")
    public void Bulk_file_with_affiliate_with_report_rol_disabled() throws FileNotFoundException {

        String jsonFileName = "RB_AffiliateWithReportROLDisabled.json";
        this.page.loadJsonArray(jsonFileName);

    }

    @Given("Bulk file with affiliate control null")
    public void Bulk_file_with_affiliate_control_null() throws FileNotFoundException {

        String jsonFileName = "RB_AffiliateControlNull.json";
        this.page.loadJsonArray(jsonFileName);

    }

    @Given("Bulk file with wrong technology")
    public void Bulk_file_with_headoffice_with_wrong_technology() throws FileNotFoundException {

        String jsonFileName = "RB_TechnologyWrong.json";
        this.page.loadJsonArray(jsonFileName);

    }

    @Given("Bulk file with technology empty")
    public void Bulk_file_with_headoffice_with_technology_empty() throws FileNotFoundException {

        String jsonFileName = "RB_TechnologyEmpty.json";
        this.page.loadJsonArray(jsonFileName);

    }

    @Given("Bulk file with headoffice with rol model not consolidated")
    public void Bulk_file_with_headoffice_with_rol_model_not_consolidated() throws FileNotFoundException {

            String jsonFileName = "RB_HeadofficeWithRolNotConsolidated.json";
            this.page.loadJsonArray(jsonFileName);

    }



    @Given("Bulk file with Received Declared Volume with negative number and \"([^\"]*)\"")
    public void Bulk_file_with_Received_Declared_Volume_with_negative_number(String technology) throws FileNotFoundException {


        if (technology.equals("NA")){
            String jsonFileName = "RB_ReceivedDeclaredVolumeNegativeNumber.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_ReceivedDeclaredVolumeNegativeNumber_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }
    }

    @Given("Bulk file with Received Declared Volume not number and \"([^\"]*)\"")
    public void Bulk_file_with_Received_Declared_Volume_not_number(String technology) throws FileNotFoundException {


        if (technology.equals("NA")){
            String jsonFileName = "RB_ReceivedDeclaredVolumeNotNumber.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_ReceivedDeclaredVolumeNotNumber_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }

    }

    @Given("Bulk file with period with wrong format and \"([^\"]*)\"")
    public void Bulk_file_with_period_with_wrong_format(String technology) throws FileNotFoundException {


        if (technology.equals("NA")){
            String jsonFileName = "RB_PeriodWithWrongFormat.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_PeriodWithWrongFormat_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }

    }

//

    @Given("Bulk file with ReceivedPositiveTestedVolume NULL and \"([^\"]*)\"")
    public void Bulk_file_with_ReceivedPositiveTestedVolume_null(String technology) throws FileNotFoundException {

        if (technology.equals("NA")){
            String jsonFileName = "RB_ReceivedPositiveTestedVolumeNULL.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_ReceivedPositiveTestedVolumeNULL_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }


    }


    @Given("Bulk file with ReceivedPositiveTestedVolume big number and \"([^\"]*)\"")
    public void Bulk_file_with_ReceivedPositiveTestedVolume_big_number(String technology) throws FileNotFoundException {

        if (technology.equals("NA")){
            String jsonFileName = "RB_ReceivedPositiveTestedVolumeBigNumber.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_ReceivedPositiveTestedVolumeBigNumber_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }

    }

    @Given("Bulk file with ReceivedPositiveTestedVolume negative number and \"([^\"]*)\"")
    public void Bulk_file_with_ReceivedPositiveTestedVolume_negative_number(String technology) throws FileNotFoundException {

        if (technology.equals("NA")){
            String jsonFileName = "RB_ReceivedPositiveTestedVolumeNegativeNumber.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_ReceivedPositiveTestedVolumeNegativeNumber_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }

    }

    @Given("Bulk file with ReceivedPositiveTestedVolume not number and \"([^\"]*)\"")
    public void Bulk_file_with_ReceivedPositiveTestedVolume_not_number(String technology) throws FileNotFoundException {

        if (technology.equals("NA")){
            String jsonFileName = "RB_ReceivedPositiveTestedVolumeNotNumber.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_ReceivedPositiveTestedVolumeNotNumber_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }


    }

    @Given("Bulk file with fixedVolume null and \"([^\"]*)\"")
    public void Bulk_file_with_fixedVolume_null(String technology) throws FileNotFoundException {

        if (technology.equals("NA")){
            String jsonFileName = "RB_fixedVolumeNULL.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_fixedVolumeNULL_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }


    }



    @Given("Bulk file with fixedVolume not number and \"([^\"]*)\"")
    public void Bulk_file_with_fixedVolume_not_number(String technology) throws FileNotFoundException {

        if (technology.equals("NA")){
            String jsonFileName = "RB_fixedVolumeNotNumber.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_fixedVolumeNotNumber_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }

    }


    @Given("Bulk file with fixedVolume with big value and \"([^\"]*)\"")
    public void Bulk_file_with_fixedVolume_with_big_value(String technology) throws FileNotFoundException {

        if (technology.equals("NA")){
            String jsonFileName = "RB_fixedVolumeBigValue.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_fixedVolumeBigValue_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }


    }


    @Given("Bulk file with fixedVolume negative and \"([^\"]*)\"")
    public void Bulk_file_with_fixedVolume_negative(String technology) throws FileNotFoundException {

        if (technology.equals("NA")){
            String jsonFileName = "RB_fixedVolumeNegativeNumber.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_fixedVolumeNegativeNumber_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }


    }


    @Given("Bulk file with fixedPrice null and \"([^\"]*)\"")
    public void Bulk_file_with_fixedPrice_null(String technology) throws FileNotFoundException {

        if (technology.equals("NA")){
            String jsonFileName = "RB_fixedPriceNULL.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_fixedPriceNULL_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }


    }

    @Given("Bulk file with fixedPrice negative and \"([^\"]*)\"")
    public void Bulk_file_with_fixedPrice_negative(String technology) throws FileNotFoundException {

        if (technology.equals("NA")){
            String jsonFileName = "RB_fixedPriceNegative.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_fixedPriceNegative_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }


    }

    @Given("Bulk file with fixedPrice big value and \"([^\"]*)\"")
    public void Bulk_file_with_fixedPrice_big_value(String technology) throws FileNotFoundException {

        if (technology.equals("NA")){
            String jsonFileName = "RB_fixedPriceBigValue.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_fixedPriceBigValue_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }


    }


    @Given("Bulk file with receivedNegativeTestedVolume null and \"([^\"]*)\"")
    public void Bulk_file_with_receivedNegativeTestedVolume_null(String technology) throws FileNotFoundException {

        if (technology.equals("NA")){
            String jsonFileName = "RB_receivedNegativeTestedVolumeNULL.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_receivedNegativeTestedVolumeNULL_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }



    }

    @Given("Bulk file with receivedNegativeTestedVolume with letters and \"([^\"]*)\"")
    public void Bulk_file_with_receivedNegativeTestedVolume_with_letters(String technology) throws FileNotFoundException {

        if (technology.equals("NA")){
            String jsonFileName = "RB_receivedNegativeTestedVolume_with_letters.json";
            this.page.loadJsonArray(jsonFileName);
        }
        else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_receivedNegativeTestedVolume_with_letters_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }



    }

    @Given("Bulk file with receivedNegativeTestedVolume with negative value and \"([^\"]*)\"")
    public void Bulk_file_with_receivedNegativeTestedVolume_with_negative_value(String technology) throws FileNotFoundException {
        if (technology.equals("NA")){
            String jsonFileName = "RB_receivedNegativeTestedVolume_with_negative_value.json";
            this.page.loadJsonArray(jsonFileName);
        }
            else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_receivedNegativeTestedVolume_with_negative_value_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);
        }
    }

    @Given("Bulk file with receivedNegativeTestedVolume with big value and \"([^\"]*)\"")
    public void Bulk_file_with_receivedNegativeTestedVolume_with_big_value(String technology) throws FileNotFoundException {

        if (technology.equals("NA")){
            String jsonFileName = "RB_receivedNegativeTestedVolume_with_big_value.json";
            this.page.loadJsonArray(jsonFileName);
        }
            else if (technology.equals("INTACTA")){
            String jsonFileName = "RB_receivedNegativeTestedVolume_with_big_value_INTACTA.json";
            this.page.loadJsonArray(jsonFileName);}

    }




    //receivedNegativeTestedVolume with big value


    @Given("RB_Replace ID with new UUID")
    public void RB_Replace_ID_with_new_UUID(){
        this.page.replaceJsonWithNewUUID (this.UUID);
    }

    @Given("RB_An ADMINISTRATOR")
    public void RB_An_ADMINISTRATOR(){
        this.page.user = "STARK_A";
        this.page.token = Token.AdminToken;
    }




    @When("RB_View uploaded files")
    public void RB_View_uploaded_files(){
        UploadedFilesResponse = page.getUploadedFiles();
    }


    @When("Select a file with status \"([^\"]*)\"")
    public void Select_File_with_status (String status){

        IdToFound = page.findBulkByStatus(UploadedFilesResponse,status);

    }

    @When("RB_Download File")
    public void RB_Download_File (){
        DownloadResponse = page.downloadFile(IdToFound);
    }



    @When("RB_I post the Bulk")
    public void RB_I_post_the_Bulk () throws InterruptedException {
        PostBulkResponse = page.postBulk();

    }

    @Then("RB_Verify that the download response status code was 200")
    public void RB_Verify_that_the_download_response_status_code_was_200(){
        Assert.assertTrue("Download response status code: "+
                DownloadResponse.statusCode(),
                DownloadResponse.statusCode()==200);
    }

    @Then("RB_Verify that the download response status code wasnt 200")
    public void RB_Verify_that_the_download_response_status_code_wasnt_200(){
        Assert.assertTrue("Download response status code: "+
                        DownloadResponse.statusCode(),
                DownloadResponse.statusCode()==200);
    }

    @Then("RB_Verify that Bulk status code was 200")
    public void RB_Verify_that_Bulk_was_posted_ok(){
        Assert.assertTrue("Bulk response: "+ PostBulkResponse.statusCode(),PostBulkResponse.statusCode()==200);
    }

    @Then("RB_Verify the status of the Bulk load is \"([^\"]*)\"")
    public void RB_Verify_the_status_of_the_Bulk_load(String status) throws InterruptedException {
        Thread.sleep(7000);
       UploadedFilesResponse = this.page.getUploadedFiles();
       String BulkStatus = this.page.getBulkStatus(UploadedFilesResponse, UUID);
       System.out.println("Status: " + BulkStatus);
       int i= 0;
       boolean  couldGetStatus    = false;
       while (i<5 && couldGetStatus == false){
            Thread.sleep(4000);
           if (BulkStatus.equals(status)){
               couldGetStatus= true;
               Assert.assertTrue("Bulk status: "+ BulkStatus, BulkStatus.equals(status));
           }else{
               BulkStatus = this.page.getBulkStatus(UploadedFilesResponse, UUID);
               System.out.println("Status: " + BulkStatus);
           }


           i++;
           Thread.sleep(400);

       }

        Assert.assertTrue("Bulk status: "+ BulkStatus, BulkStatus.equals(status));
    }

}




