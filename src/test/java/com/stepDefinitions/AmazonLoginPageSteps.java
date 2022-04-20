package com.stepDefinitions;

import com.factory.DriverFactory;
import com.pageObjects.AmazonLoginPage;
import com.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Properties;


public class AmazonLoginPageSteps {

    public ConfigReader configReader;
    public Properties prop;
    private AmazonLoginPage amazonLoginPage = new AmazonLoginPage(DriverFactory.getDriver());

    public AmazonLoginPageSteps(){
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    @Given("user is on the Amazon login page")
    public void user_is_on_the_amazon_login_page() {
        String amzUrl = prop.getProperty("amazonUrl");
        DriverFactory.getDriver().get(amzUrl);
        amazonLoginPage.navigateToSignInPage();
    }

    @When("user enters the username {string}")
    public void user_enters_the_username(String userName) {
       amazonLoginPage.enterUserName(userName);
    }

    @When("user click on Continue button")
    public void user_click_on_continue_button() {
      amazonLoginPage.clickOnContinue();
    }

    @When("user enters the password {string}")
    public void user_enters_the_password(String pass) {
        amazonLoginPage.enterPassword(pass);
    }

    @When("user clicks on Sign-In button")
    public void user_clicks_on_sign_in_button() {
        amazonLoginPage.clickOnSignIn();
    }

    @Then("the user is logged in to the application")
    public void the_user_is_logged_in_to_the_application() {
        amazonLoginPage.validateUserIsSignedIn();
    }

}
