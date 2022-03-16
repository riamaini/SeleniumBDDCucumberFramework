package com.stepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.factory.DriverFactory;
import com.pageObjects.ContactUsPage;
import com.utils.ConfigReader;
import com.utils.ExcelReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactUsPageSteps {

    public ConfigReader configReader;
    public Properties prop;
    private ContactUsPage contactUsPage = new ContactUsPage(DriverFactory.getDriver());
    ExcelReader reader = new ExcelReader();

    public ContactUsPageSteps(){
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    @Given("user navigates to contact us page")
    public void user_navigates_to_contact_us_page() {
        String contactUrl = prop.getProperty("contactUrl");
        DriverFactory.getDriver().get(contactUrl);
    }

    @When("user fills the form from given sheetname {string} and rownumber {int}")
    public void user_fills_the_form_from_given_sheetname_and_rownumber(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {

        List<Map<String, String>> testData =
                reader.getData("src/test/resources/TestData/TestData.xlsx", sheetName);

        String heading = testData.get(rowNumber).get("Subject Heading");
        String email = testData.get(rowNumber).get("Email");
        String orderRef = testData.get(rowNumber).get("Order Ref");
        String message = testData.get(rowNumber).get("Message");

        contactUsPage.fillContactUsForm(heading, email, orderRef, message);

    }

    @When("user clicks on send button")
    public void user_clicks_on_send_button() {
        contactUsPage.clickSend();
    }

    @Then("it shows a successful message {string}")
    public void it_shows_a_successful_message(String expSuccessMessage) {
        String actualSuccessMessage = contactUsPage.getSuccessMessage();
        Assert.assertEquals(actualSuccessMessage, expSuccessMessage);
    }
}
