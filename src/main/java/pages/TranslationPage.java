package pages;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class TranslationPage {

    public String enviroment;
    public String language;

    public TranslationPage(String enviroment, String language) {
        this.enviroment = enviroment;
        this.language = language;
    }

    public boolean verifyTranslationsAreWorkingFine() throws SQLException, IOException {

        //Declarar variable local como true que si llega a haber alguna que falle pase a false

        boolean TranslationsAreWorkingFine=true;
        boolean TranslationsAreSameBetweenDBAndResponse = true;
        Response TranslationResponse = null;

        // Declaro y creo el archivo con el resumen
        FileWriter fileWriter = null;
        String fileName = "C:\\Users\\evspr\\ResumenTranslations"+language+".csv";

        try {
            fileWriter = new FileWriter(fileName);
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        try {
            loadFileHeader(fileWriter);
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        // ----------------------------

        //Conectarse a la base de datos

        Connection BaseDatos = null;
        Statement st = null;

        String url = "jdbc:postgresql://localhost:9109/vcquotadev";
        String usuario = "vctquotauser";
        String contraseña = "P455w0rd!";

        try {
            BaseDatos = DriverManager.getConnection(url, usuario, contraseña);
            st = BaseDatos.createStatement();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        // ----------------------------

        // Traer el query donde estan todas las translations

        ResultSet rs = null;
        try {
            rs = st.executeQuery("SELECT * FROM TRANSLATIONS");
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        // -----------------------------

        // DEFINE EL LOCALE
        String locale = getLocale(language);


        // -----------------------------------

        // Hacer un ciclo while para recorrer ese query

            while (rs.next()) {

                if(RecordIsFromLanguage(rs, locale)) {

                   // llama al microservicio para esa aplicacion y el locale de language
                    String application = getApplication(rs);
                    TranslationResponse = getTranslation(application, locale);
                    TranslationsAreSameBetweenDBAndResponse = VerifyTranslation(rs,TranslationResponse);
                            if ((TranslationResponse.statusCode()!=200) || (!TranslationsAreSameBetweenDBAndResponse)){
                                TranslationsAreWorkingFine = false;
                            }
                    WriteResultIntoTxtFile(fileWriter,TranslationResponse,rs,TranslationsAreWorkingFine);
                }
            }
        // Cortar el ciclo while

        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error while flushing/closing fileWriter !!!");
            e.printStackTrace();
        }

        rs.close();
        BaseDatos.close();

        return TranslationsAreWorkingFine;
    }

    public void WriteResultIntoTxtFile(FileWriter fileWriter,Response TranslationResponse, ResultSet rs, boolean TranslationsAreWorkingfine) throws IOException, SQLException {

            String DB_KEY = rs.getString("value");
            String MS_KEY = TranslationResponse.jsonPath()
                    .getString("messages.'"+rs.getString("application")+"."+rs.getString("key")+"'");
            fileWriter.append(DB_KEY);
            fileWriter.append(",");
            fileWriter.append(MS_KEY);
            fileWriter.append(",");
            fileWriter.append(String.valueOf(TranslationsAreWorkingfine));
            fileWriter.append(";");
            fileWriter.append("\n");



    }

    public boolean VerifyTranslation(ResultSet rs, Response TranslationResponse) throws SQLException {

        String LabelResponse =
                TranslationResponse.jsonPath()
                        .getString("messages.'"+rs.getString("application")+"."+rs.getString("key")+"'");

        String labelDB = rs.getString("value");

        return labelDB.equals(LabelResponse);
    }

    public boolean RecordIsFromLanguage(ResultSet rs, String locale) throws SQLException {


            if(rs.getString("locale").equals(locale)){
                return true;
            }else{
                return false;
            }


    }
    public Response getTranslation(String application, String locale){

        RequestSpecification request;

        baseURI = "https://gkg1ib9end.execute-api.us-east-1.amazonaws.com/";
        Response response_aux;

        request = given()
                .pathParam("app",application)
                .pathParam("locale",locale);

        response_aux  = request.get(enviroment+"/pod/translate?app={app}&locale={locale}");

        return response_aux;
    }
    public String getApplication(ResultSet rs) throws SQLException {

            return rs.getString("application");

    }


    public String getLocale(String language){

        String locale = null;

        switch (language){

            case "English":
                locale = "en-US";
                break;
            case "Portuguese":
                locale = "pt-BR";
                break;
            case "Spanish":
                locale = "pt-ES-AR";
                break;

            default:
                locale = null;
                break;
        }

        return locale;
    }

    public void loadFileHeader(FileWriter fileWriter) throws IOException {

        fileWriter.append("KEY");
        fileWriter.append(",");
        fileWriter.append("VALUE");
        fileWriter.append(",");
        fileWriter.append("RESULT");
        fileWriter.append(";");
        fileWriter.append("\n");
    }
}
