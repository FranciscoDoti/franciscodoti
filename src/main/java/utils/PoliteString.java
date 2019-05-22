package utils;

public class PoliteString {

    public String getPoliteString(String cad){

        String polite_String=cad;

        do {
            if (polite_String.startsWith("[") && polite_String.endsWith("]")){
                polite_String =  polite_String.substring(1,polite_String.lastIndexOf("]"));
            }
        }while (polite_String.contains("[") || polite_String.contains("]"));

        if (polite_String.equals("null")){
            polite_String=null;
        }
        return polite_String;
    }

    public String emprolijar(String cad){

        if (cad.equals("[null]")){
            cad=null;
        }else {cad =getPoliteString(cad);}

        return cad;
    }
}

