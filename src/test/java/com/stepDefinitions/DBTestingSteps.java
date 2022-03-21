package com.stepDefinitions;

import com.utils.CommonUtils;
import com.utils.DBUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import javax.smartcardio.CommandAPDU;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBTestingSteps {

    DBUtil dbUtil = new DBUtil();
    String dbQuery = null;
    ResultSet rs;
    Connection con = null;
    CommonUtils commonUtils = new CommonUtils();

    @Given("the DB connection is set up")
    public void the_db_connection_is_set_up() {
        con = dbUtil.getDBConnection();
    }

    @When("the user executes the query for finding the number of cities in Afghanistan")
    public void theUserExecutesTheQueryForFindingTheNumberOfCitiesInAfghanistan() {
        dbQuery = dbUtil.getQuery("World","GetCity");
        rs = dbUtil.executeQuery(dbQuery);
    }

    @Then("the DB should fetch the records and provide the value as {int}")
    public void theDBShouldFetchTheRecordsAndProvideTheValueAs(int count) throws SQLException {
        List<String> listCities = commonUtils.getDBRecords(rs, "Name");
        Assert.assertEquals(count,listCities.size());
        System.out.println("DB count and expected count match - Expected : "+count+ " Actual : "+listCities.size());
        con.close();
    }
}
