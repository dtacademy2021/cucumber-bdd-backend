package step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.DBUtils;

import java.util.ArrayList;
import java.util.List;

public class DBOnlyStepDefs {
    List<String> actuaColumnNames;

    List<String> actualList;

    @When("I retrieve column names for the {string} table")
    public void iRetrieveColumnNamesForTheTable(String table) {


        String query = "select * from "+table+" limit 1" ;
        actuaColumnNames = DBUtils.getColumnNames(query);

        System.out.println(actuaColumnNames);


    }

    @Then("The names of the columns should be the following")
    public void theNamesOfTheColumnsShouldBeTheFollowing(List<String> expectedColumnNames) {
        System.out.println(expectedColumnNames);
        Assert.assertEquals(expectedColumnNames, actuaColumnNames);


    }



    @When("I retrieve genre names for the genres table")
    public void iRetrieveGenreNamesForTheGenresTable() {

        List<List<Object>> listDb = DBUtils.getQueryResultList("select name from genres");


        actualList = new ArrayList<>();

         for (List<Object> i: listDb){
            actualList.add((String)(i.get(0)));
         }


    }

    @Then("The names of the genres should be the following")
    public void theNamesOfTheGenresShouldBeTheFollowing(List<String> expectedGenres) {
        Assert.assertEquals(expectedGenres, actualList);
    }

}
