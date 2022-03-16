package com.stepDefinitions;

import com.factory.DriverFactory;
import com.pageObjects.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginPageSteps {

    private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    private static String actualTitle;

    @Given("user is on login page")
    public void user_is_on_login_page() {
       DriverFactory.getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }

    @When("user gets the title of the page")
    public void user_gets_the_title_of_the_page() {
        actualTitle = loginPage.getLoginPageTitle();
    }

    @Then("page title should be {string}")
    public void page_title_should_be(String expectedTitle) {
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Then("forgot your password link should be displayed")
    public void forgot_your_password_link_should_be_displayed() {
       Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }

    @When("user enters username {string}")
    public void user_enters_username(String userName) {
        loginPage.enterUserName(userName);
    }

    @When("user enters password {string}")
    public void user_enters_password(String passWord) {
       loginPage.enterPassword(passWord);
    }

    @When("user clicks on Login button")
    public void user_clicks_on_login_button() {
       loginPage.clickOnLogin();
    }

}
