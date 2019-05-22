package utils;



import java.util.ArrayList;

public class ROLExportFilters {

    public String monthFrom;
    public String monthTo;
    public String yearFrom;
    public String yearTo;
    public String HeadofficeDocument;
    public String AffiliateDocument;
    public ArrayList regions = new ArrayList();
    public ArrayList status = new ArrayList();




    public String setSuffixURI() {

        String suffixURI = null;

        if (HeadofficeDocument != null) {
            suffixURI =  "&headoffice=" + HeadofficeDocument;
        }


        if (AffiliateDocument != null) {
            suffixURI = suffixURI + "&affiliate=" + AffiliateDocument;
        }


        if (monthFrom != null) {
            suffixURI = suffixURI + "&monthFrom=" + monthFrom;
        }
        if (monthTo != null) {
            suffixURI = suffixURI + "&monthTo=" + monthTo;
        }

        if (yearFrom != null) {
            suffixURI = suffixURI + "&yearFrom=" + yearFrom;
        }
        if (yearTo != null) {
            suffixURI = suffixURI + "&yearTo=" + yearTo;
        }

        if (regions != null) {

            int regionSize = regions.size();

            for (int i = 0; i < regionSize; i++) {
                suffixURI = suffixURI + "&district=" + regions.get(i).toString();
            }

        }

        return suffixURI;
    }
}
