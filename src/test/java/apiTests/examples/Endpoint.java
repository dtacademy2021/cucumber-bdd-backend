package apiTests.examples;

public enum Endpoint {

    POST_PLACE("/add/json"), GET_PLACE("/get/json"), DELETE_PLACE("/delete/json");

    private String uri;

     Endpoint(String s) {
        this.uri = s;
    }
}
