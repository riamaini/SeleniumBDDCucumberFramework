package com.stepDefinitions;

import com.factory.DriverFactory;
import com.pageObjects.AccountsPage;
import com.pageObjects.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class AccountPageSteps {

    private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    private AccountsPage accountsPage;

    @Given("user has already logged in to application")
    public void user_has_already_logged_in_to_application(io.cucumber.datatable.DataTable credTable) {
        List<Map<String, String>> credList = credTable.asMaps();
        String userName = credList.get(0).get("username");
        String password = credList.get(0).get("password");

        DriverFactory.getDriver()
                .get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        accountsPage = loginPage.doLogin(userName, password);
    }

    @Given("user is on Accounts page")
    public void user_is_on_accounts_page() {
        String title = accountsPage.getAccountsPageTitle();
    }

    @Then("user gets accounts section")
    public void user_gets_accounts_section(io.cucumber.datatable.DataTable sectionsTable) {
        List<String> expAccountSectionsList = sectionsTable.asList();
        List<String> actualAccountSectionsList = accountsPage.getAccountsSectionsList();
        Assert.assertTrue(expAccountSectionsList.containsAll(actualAccountSectionsList));
    }

    @Then("accounts section count should be {int}")
    public void accounts_section_count_should_be(int expectedSectionCount) {
        Assert.assertEquals(accountsPage.getAccountsSectionCount(), expectedSectionCount);
    }
}
