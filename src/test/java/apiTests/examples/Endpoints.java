package apiTests.examples;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public interface Endpoints {

    public static final String GET_PLACE =  "/add/json" ;
    public static final String POST_PLACE =  "/post/json" ;
    public static final String DELETE_PLACE =  "/delete/json" ;
    public static final String PUT_PLACE =  "/put/json" ;


    public String getToken();

    public default String getAPIKey(){
        return null;
    }


}
