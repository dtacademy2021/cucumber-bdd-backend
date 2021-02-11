package apiTests.payloads;

public class Payloads {

    public static String addPlaceRequestBody(String name, String phone){
        return "{\n" +
                " \"location\": {\n" +
                "   \"lat\": -77.2273128,\n" +
                "   \"lng\": 38.9151944\n" +
                " },\n" +
                " \"accuracy\": 50,\n" +
                " \"name\": \""+name+"\",\n" +
                " \"phone_number\": \""+phone+"\",\n" +
                " \"address\": \"8133 Leesburg Pike STE 300, Vienna, VA 22182\",\n" +
                " \"types\": [\n" +
                "   \"academy\",\n" +
                "   \"bootcamp\"\n" +
                " ],\n" +
                " \"website\": \"duotech.io\",\n" +
                " \"language\": \"English\"\n" +
                "}\n" ;
    }
}
