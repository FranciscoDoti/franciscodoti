package utils;

import java.util.ArrayList;

public class ExportSeasonClosingFilters {

    public String year;

    public String HeadofficeDocument;
    public String GrowerDocument;
    public String monthFrom;
    public String yearFrom;
    public String monthTo;
    public String yearTo;

    public ArrayList regions = new ArrayList();
    public ArrayList status = new ArrayList();
    public String dayFrom;
    public String dayTo;


    public String setSuffixURI() {

        String suffixUri = null;

        /*
        https://gkg1ib9end.execute-api.us-east-1.amazonaws.com/dev/pod/season-closing/export/xlsx/
        season-closing-report/en-US?username=STARK_A&headOffice=11636261000119
        &grower=SEM&dateFrom=2019/01/01&dateTo=2019/04/12&year=2018
        &status=APPROVED,REGISTERED,SUBMITTED&district=102314,102311
         */

        if (HeadofficeDocument!= null){
             suffixUri = "&headOffice=" + HeadofficeDocument;
        }
        if (GrowerDocument!=null){
            suffixUri = suffixUri + "&grower=" + GrowerDocument;
        }
        if (monthFrom!= null){
            suffixUri = suffixUri +  "&dateFrom=" + yearFrom + "/" + dayFrom + "/" + monthFrom;
        }
        if (monthTo!= null){
            suffixUri = suffixUri +  "&dateTo=" + yearTo+ "/" + dayTo + "/" + monthTo;
        }
        if (year!= null){
            suffixUri =  suffixUri + "&year=" + year;
        }

        if (status!=  null){

            int statusSize  = status.size();

            for (int i = 0; i< statusSize; i++){
                suffixUri = suffixUri + "&status=" + status.get(i).toString();
            }
        }

        if (regions != null) {

            int regionSize = regions.size();

            for (int i = 0; i < regionSize; i++) {
                suffixUri = suffixUri + "&district=" + regions.get(i).toString();
            }

        }
        return suffixUri;
    }
}
