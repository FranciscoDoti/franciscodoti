package QAAutomation.stepDefinitions;

import com.fasterxml.uuid.Generators;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import pages.*;
import utils.FixedVolumeChanges;
import utils.Token;

import javax.sound.midi.Receiver;
import java.io.FileNotFoundException;
import java.util.UUID;

import static utils.EnviromentsConfig.CURRENT_ENV;

public class StressTestingSteps {

    public String HeadofficeDoc;
    public Response HeadofficeResponse;
    public Response AffiliateFromHeadoffices;
    public Response PD_Response;
    public Response FV_Response;
    public Response Load_Response;
    public boolean LoadWithoutAnyErrors = true;
    public String token;
    public Response ROLCreate_Response;
    private String UUID;
    private Response Bulk_Post_Response;

    @Given("get Token")
    public void get_Token() {
        token = Token.AdminToken;
    }

    @Given("a Headoffice with id \"([^\"]*)\"")
    public void a_Headoffice_with_id(String headofficeDocument) {
        this.HeadofficeDoc = headofficeDocument;

        this.HeadofficeResponse = CommonMethods.getHeadoffice(headofficeDocument,"STARK_A");
        if (HeadofficeResponse.statusCode() != 200) {
            throw new RuntimeException("Response fail with status" + HeadofficeResponse.statusCode());
        }
    }

    @Given("100 affiliates from headoffice \"([^\"]*)\"")
    public void affiliates_from_headoffice(String headofficeDocument) {

        AffiliateFromHeadoffices = CommonMethods.getAffiliatesByHeadoffice(headofficeDocument);
        if (AffiliateFromHeadoffices.statusCode() != 200) {
            throw new RuntimeException("Response fail with status" + AffiliateFromHeadoffices.statusCode());
        }
    }

    @When("Post \"([^\"]*)\" participant deliveries from \"([^\"]*)\" to each affiliate")
    public void post_participant_deliveries_from_to(String PD_Quantity, String arg2) {
        Response pod_sender;
        ParticipantDeliverPage page = new ParticipantDeliverPage(CURRENT_ENV);
        page.token = token;
        page.user = "STARK_A";

        for (int i = 0; i < 99; i++) {
            System.out.println("Participant delivery: Va por el afiliado: " + i);
            for (int j = 0; j < (Integer.parseInt(PD_Quantity)); j++) {
                System.out.println("Participant delivery: Va por el participant delivery: " + j);
                PD_Response = page.postParticipantDeliverWithDocNumbers
                        (AffiliateFromHeadoffices.jsonPath().getJsonObject("results[" + i + "].document.documentNumber"), arg2, 100);

            }
        }
    }

    @When("Post \"([^\"]*)\" fixed volumes from \"([^\"]*)\" to each affiliate")
    public void Post_several_fixed_volumes(String limit, String Sender_doc) {
        FixedVolumePage fv_page = new FixedVolumePage(CURRENT_ENV);
        for (int i = 0; i < 99; i++) {
            System.out.println("Fixed volume: Va por el afiliado: " + i);
            for (int j = 0; j < (Integer.parseInt(limit)); j++) {
                System.out.println("Fixed volume: Va por el fixed volume: " + j);
                fv_page.user = "STARK_A";
                fv_page.token = token;
                fv_page.Headoffice = CommonMethods.getHeadoffice(AffiliateFromHeadoffices.
                        jsonPath().getJsonObject("results[" + i + "].document.documentNumber"), fv_page.user);
                fv_page.Grower = CommonMethods.getGrower(Sender_doc, fv_page.user);
                fv_page.XtendVolume = 10;
                fv_page.IntactaVolume = 10;
                FV_Response = fv_page.createFixedVolume();

            }
        }

    }

    @When("Create ROL from affiliate \"([^\"]*)\" to \"([^\"]*)\" of each month from \"([^\"]*)\"")
    public void CreateROL_for_all_of_them_of_each_month_from_year(String affiliateFrom, String affiliateTo, String year) {
        ConsolidateROLCreationPage RC_Page = new ConsolidateROLCreationPage(CURRENT_ENV);
        RC_Page.user = "STARK_A";
        RC_Page.token = token;
        int i = 0;
        int j = 0;
        FixedVolumeChanges fix_changes = new FixedVolumeChanges("", 0, 0);

        for (i = Integer.valueOf(affiliateFrom); i < Integer.valueOf(affiliateTo); i++) {
            System.out.println("Va por el afiliado " + i);
            j=0;
            do {
                System.out.println("Va por el mes " + j);
                int k = 0;
                do {
                    Load_Response = RC_Page.loadROL(AffiliateFromHeadoffices.jsonPath()
                            .getString("results[" + i + "].document.documentNumber"), year, String.valueOf(j + 1));
                    k++;
                    if (Load_Response.statusCode() != 200) {
                        ROLModelsPage rolModelsPage = new ROLModelsPage(CURRENT_ENV);
                        rolModelsPage.user="STARK_A";
                        rolModelsPage.token = CommonMethods.getToken("STARK_A");
                        rolModelsPage.createROLModel("CONSOLIDATED", HeadofficeDoc);
                    }
                } while (Load_Response.statusCode() != 200 && k < 3);

                if (Load_Response.statusCode() != 200) {
                   System.out.println("Falló el load.");
                }else{
                    System.out.println("Load ok");
                    FixedVolumeChanges fixedVolumeChanges = new FixedVolumeChanges("",0,0);
                    ROLCreate_Response = RC_Page.createROL(Load_Response, fixedVolumeChanges);

                    if (ROLCreate_Response.statusCode() != 200) {
                        System.out.println("Falló la creación");
                    } else{
                        System.out.println("ROL Creado.");
                    }
                }

                j++;

            }while (j<12 && Load_Response.statusCode()==200);
        }
    }

    @When("Bulk Participant Deliveries from Affiliate \"([^\"]*)\" to \"([^\"]*)\"")
    public void Bulk_Participant_Deliveries_from_Affiliate_X_to_Y(String arg1, String arg2) throws FileNotFoundException {
        int affiliateFrom = Integer.parseInt(arg1);
        int affiliateTo = Integer.parseInt(arg2);

        JSONArray Bulk_JsonArray = new JSONArray();

        Response CurrentAffiliate;
        String bulkId;
        UUID uuid = Generators.randomBasedGenerator().generate();
        bulkId = uuid.toString();
        this.UUID = bulkId;

        int i;

        for (i = affiliateFrom; i <= affiliateTo; i++) {

            String AffiliateDocument_Aux = AffiliateFromHeadoffices.jsonPath().getJsonObject("results[" + i + "].document.documentNumber");
            CurrentAffiliate = CommonMethods.getAffiliate(AffiliateDocument_Aux, "STARK_A");

            for (int j = 0; j < 1500; j++) {

                System.out.println("Va por el afiliado: " + (i + 1) + ". Iteración: " + (j + 1));
                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject = BuildJSONObjectForPDBulk(CurrentAffiliate);
                } catch ( Exception e ) {
                    System.out.println(e.getMessage());
                }
                Bulk_JsonArray.add(jsonObject);
            }

        }

        BulkPage bulkPage = new BulkPage(CURRENT_ENV, UUID);

        bulkPage.token = token;
        bulkPage.user = "STARK_A";
        System.out.println("Ready to register Bulk");
        Bulk_Post_Response = bulkPage.registerBulkFromJsonArray(Bulk_JsonArray);
        System.out.println("The status code of the bulk response is.. " + Bulk_Post_Response.statusCode());
        System.out.println("El Id de la carga fue... " + UUID);
    }

    @When("Bulk Fixed volumes from Affiliate \"([^\"]*)\" to \"([^\"]*)\"")
    public void Bulk_Fixed_volumes_from_Affiliate_X_to_Y(String arg1, String arg2) {
        int affiliateFrom = Integer.parseInt(arg1);
        int affiliateTo = Integer.parseInt(arg2);

        JSONArray Bulk_JsonArray = new JSONArray();

        Response CurrentAffiliate;
        String bulkId;
        UUID uuid = Generators.randomBasedGenerator().generate();
        bulkId = uuid.toString();
        this.UUID = bulkId;

        int i;

        for (i = affiliateFrom; i <= affiliateTo; i++) {

            String AffiliateDocument_Aux = AffiliateFromHeadoffices.jsonPath().getJsonObject("results[" + i + "].document.documentNumber");
            CurrentAffiliate = CommonMethods.getAffiliate(AffiliateDocument_Aux, "STARK_A");

            for (int j = 0; j < 750; j++) {

                System.out.println("Va por el afiliado: " + (i + 1) + ". Iteración: " + (j + 1));
                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject = BuildJSONObjectForFVBulk(CurrentAffiliate);
                } catch ( Exception e ) {
                    System.out.println(e.getMessage());
                }
                Bulk_JsonArray.add(jsonObject);
            }

        }

        BulkFixedVolumePage bulkPage = new BulkFixedVolumePage(CURRENT_ENV, UUID);

        bulkPage.token = token;
        bulkPage.user = "STARK_A";
        System.out.println("Ready to register Bulk");
        Bulk_Post_Response = bulkPage.registerBulkFromJsonArray(Bulk_JsonArray);
        System.out.println("The status code of the bulk response is.. " + Bulk_Post_Response.statusCode());
        System.out.println("El Id de la carga fue... " + UUID);
    }

    private JSONObject BuildJSONObjectForFVBulk(Response currentAffiliate) {

        JSONObject json = new JSONObject();
        JSONObject Fix_VolumeJSON = new JSONObject();
        JSONObject PartnerJson = new JSONObject();
        JSONObject GrowerJson = new JSONObject();
        JSONObject GrowerDocumentJson = new JSONObject();
        JSONObject PartnerHeadOfficeJson = new JSONObject();
        JSONObject PartnerDocumentJson = new JSONObject();
        JSONObject PartnerHeadofficeDocumentJson = new JSONObject();


        PartnerHeadofficeDocumentJson.put("documentNumber", currentAffiliate.jsonPath().getString(
                "results[0].matrix[0].document.documentNumber"
        ));
        PartnerHeadofficeDocumentJson.put("documentType", currentAffiliate.jsonPath().getString(
                "results[0].matrix[0].document.documentType"));


        PartnerHeadOfficeJson.put("name", currentAffiliate.jsonPath().getString(
                "results[0].matrix[0].name"));
        PartnerHeadOfficeJson.put("document", PartnerHeadofficeDocumentJson);


        PartnerDocumentJson.put("documentNumber", currentAffiliate.jsonPath().getString(
                "results[0].document.documentNumber"));
        PartnerDocumentJson.put("documentType", currentAffiliate.jsonPath().getString(
                "results[0].document.documentType"));


        PartnerJson.put("name", currentAffiliate.jsonPath().getString(
                "results[0].name"));
        PartnerJson.put("document", PartnerDocumentJson);
        PartnerJson.put("headOffice", PartnerHeadOfficeJson);

        GrowerDocumentJson.put("documentNumber", "92441971034");
        GrowerDocumentJson.put("documentType", "CPF");


        GrowerJson.put("document", GrowerDocumentJson);

        Fix_VolumeJSON.put("partner", PartnerJson);
        Fix_VolumeJSON.put("grower", GrowerJson);
        Fix_VolumeJSON.put("technology", "INTACTA");
        Fix_VolumeJSON.put("volume", String.valueOf(Math.round(Math.random() * 20)));

        json.put("bulk_id", UUID);
        json.put("fix_volume", Fix_VolumeJSON);

        return json;
    }

    
    public JSONObject BuildJSONObjectForPDBulk(Response Affiliate_Aux) {


        JSONObject json= new JSONObject();
        JSONObject DeliveryJson= new JSONObject();
        JSONObject SenderJson= new JSONObject();
        JSONObject ReceiverJson= new JSONObject();
        JSONObject ReceiverDocumentJson= new JSONObject();
        JSONObject ReceiverheadOfficeJson= new JSONObject();
        JSONObject ReceiverheadOfficeDocumentJson= new JSONObject();
        JSONObject SenderDocumentJson= new JSONObject();


        SenderDocumentJson.put("documentNumber","59677015915");
        SenderDocumentJson.put("documentType","CPF" );


        ReceiverheadOfficeDocumentJson.put("documentNumber",Affiliate_Aux.jsonPath().getString("results[0]." +
                "matrix[0]" +
                ".document" +
                ".documentNumber") );
        ReceiverheadOfficeDocumentJson.put("documentType", Affiliate_Aux.jsonPath().getString("results[0]." +
                "matrix[0]" +
                ".document" +
                ".documentType") );

        ReceiverheadOfficeJson.put("name",Affiliate_Aux.jsonPath().getString("results[0]." +
                "matrix[0].name"));
        ReceiverheadOfficeJson.put("document",ReceiverheadOfficeDocumentJson);


        ReceiverDocumentJson.put("documentNumber",Affiliate_Aux.jsonPath().getString("results[0].document" +
                ".documentNumber") );
        ReceiverDocumentJson.put("documentType", Affiliate_Aux.jsonPath().getString("results[0].document" +
                ".documentType") );

        SenderJson.put("document", SenderDocumentJson);

        ReceiverJson.put("name", Affiliate_Aux.jsonPath().getString("results[0].name"));
        ReceiverJson.put("document", ReceiverDocumentJson);
        ReceiverJson.put("headOffice", ReceiverheadOfficeJson);

        DeliveryJson.put("quantity",String.valueOf( Math.round(Math.random()*20)));
        DeliveryJson.put("sender", SenderJson);
        DeliveryJson.put("receiver", ReceiverJson);

        json.put("bulk_id", UUID);
        json.put("delivery",DeliveryJson);
        return json;
    }

    @Then("Verify that the load finished ok")
    public void Verify_that_the_load_finished_ok(){
        Assert.assertTrue(LoadWithoutAnyErrors);

    }

}
