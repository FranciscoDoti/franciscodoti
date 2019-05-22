package utils;

public class RegionMapper {

    public static final String [][] Regions= new String[53][2];

    public RegionMapper(){



        Regions[0][0] =  "102248";    Regions[0][1] = "ALFENAS-SOJA";
        Regions[1][0] =  "102275";    Regions[1][1] = "ASSIS-SOJA";
        Regions[2][0] =  "102237";    Regions[2][1] = "CATALAO-SOJA & ICS";
        Regions[3][0] =  "102226";    Regions[3][1] = "ITAPEVA-SOJA";
        Regions[4][0] =  "102269";    Regions[4][1] = "PATOS DE MINAS-SOJA";
        Regions[5][0] =  "102236";    Regions[5][1] = "SAO PAULO-SOJA/ICS";
        Regions[6][0] =  "102293";    Regions[6][1] = "UBERLANDIA-SOJA/ICS";
        Regions[7][0] =  "102265";    Regions[7][1] = "BALSAS-SOJA/ICS";
        Regions[8][0] =  "102238";    Regions[8][1] = "FORMOSA-SOJA/ICS";
        Regions[9][0] =  "102217";    Regions[9][1] = "GOIANIA-SOJA/ICS";
        Regions[10][0] = "102278";   Regions[10][1] = "IMPERATRIZ-SOJA";
        Regions[11][0] = "102280";   Regions[11][1] = "JATAI-SOJA";
        Regions[12][0] = "102242";   Regions[12][1] = "LUIS E MAG-SOJA/ICS";
        Regions[13][0] = "102307";   Regions[13][1] = "PALMAS-SOJA/ICS";
        Regions[14][0] = "102270";   Regions[14][1] = "RIO VERDE-SOJA/ICS";
        Regions[15][0] = "102250";   Regions[15][1] = "CAMPO GRANDE-SOJA/ICS";
        Regions[16][0] = "102298";   Regions[16][1] = "CAMPO VERDE-SOJA";
        Regions[17][0] = "102266";   Regions[17][1] = "DOURADOS-SOJA/ICS";
        Regions[18][0] = "102255";   Regions[18][1] = "LUCAS DO RIO VERDE-SOJA/ICS";
        Regions[19][0] = "102314";   Regions[19][1] = "NAVIRAI-SOJA";
        Regions[20][0] = "102311";   Regions[20][1] = "PRIMAVERA-SOJA/ICS";
        Regions[21][0] = "102227";   Regions[21][1] = "QUERENCIA SOJA & ICS";
        Regions[22][0] = "102267";   Regions[22][1] = "RONDONOPOLIS-SOJA/ICS";
        Regions[23][0] = "102241";   Regions[23][1] = "SINOP-SOJA";
        Regions[24][0] = "102310";   Regions[24][1] = "SORRISO-SOJA/ICS ";
        Regions[25][0] = "102308";   Regions[25][1] = "TANGARA-SOJA";
        Regions[26][0] = "102296";   Regions[26][1] = "CAMPO MOURAO-SOJA";
        Regions[27][0] = "102292";   Regions[27][1] = "CASCAVEL-SOJA";
        Regions[28][0] = "102297";   Regions[28][1] = "CORNELIO PROCOP-SOJA";
        Regions[29][0] = "102239";   Regions[29][1] = "GUARAPUAVA-SOJA";
        Regions[30][0] = "102281";   Regions[30][1] = "LONDRINA-SOJA";
        Regions[31][0] = "102312";   Regions[31][1] = "MARINGA-SOJA";
        Regions[32][0] = "102279";   Regions[32][1] = "PALOTINA-SOJA";
        Regions[33][0] = "102268";   Regions[33][1] = "PATO BRANCO-SOJA";
        Regions[34][0] = "102240";   Regions[34][1] = "PONTA GROSSA-SOJA";
        Regions[35][0] = "102252";   Regions[35][1] = "TOLEDO-SOJA";
        Regions[36][0] = "102219";   Regions[36][1] = "CANOINHAS-SOJA";
        Regions[37][0] = "102249";   Regions[37][1] = "CARAZINHO-SOJA";
        Regions[38][0] = "102291";   Regions[38][1] = "CHAPECO-SOJA";
        Regions[39][0] = "102243";   Regions[39][1] = "LAGOA VERMELHA-SOJA";
        Regions[40][0] = "102276";   Regions[40][1] = "PASSO FUNDO-SOJA";
        Regions[41][0] = "102295";   Regions[41][1] = "PORTO ALEGRE-SOJA";
        Regions[42][0] = "102263";   Regions[42][1] = "RIO DO SUL-SOJA";
        Regions[43][0] = "102264";   Regions[43][1] = "SARANDI-SOJA";
        Regions[44][0] = "102306";   Regions[44][1] = "TAPEJARA-SOJA";
        Regions[45][0] = "102309";   Regions[45][1] = "CRUZ ALTA-SOJA";
        Regions[46][0] = "102313";   Regions[46][1] = "IBIRUBA-SOJA";
        Regions[47][0] = "102253";   Regions[47][1] = "IJUI-SOJA";
        Regions[48][0] = "102277";   Regions[48][1] = "SANTA MARIA-SOJA";
        Regions[49][0] = "102218";   Regions[49][1] = "SANTA ROSA-SOJA";
        Regions[50][0] = "102294";   Regions[50][1] = "SANTO ANGELO-SOJA";
        Regions[51][0] = "102251";   Regions[51][1] = "SANTO AUGUSTO-SOJA";
        Regions[52][0] = "102228";   Regions[52][1] = "SAO GABRIEL-SOJA";


    }

    public static String searchRegionCode(String region) {

        String code = null;
        boolean founded= false;
        int i=0;



        while (!founded && i< Regions.length){

            if (Regions[i][1].equals(region)){
                founded=true;
                code= Regions[i][0];
            }
            i++;
        }

        if (founded){
            return code;
        }
        else {
            return "0";
            }


    }
}
