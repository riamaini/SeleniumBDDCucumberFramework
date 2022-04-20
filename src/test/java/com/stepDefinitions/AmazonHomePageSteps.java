package com.stepDefinitions;

import com.factory.DriverFactory;
import com.pageObjects.AmazonHomePage;
import com.pageObjects.AmazonLoginPage;
import com.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Properties;


public class AmazonHomePageSteps {

    public ConfigReader configReader;
    public Properties prop;
    private AmazonHomePage amazonHomePage = new AmazonHomePage(DriverFactory.getDriver());

    public AmazonHomePageSteps(){
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    @Then("the user navigates to Health, Household and Personal Care section")
    public void the_user_navigates_to_health_household_and_personal_care_section() {
        amazonHomePage.navigateToHealthAndPersonalCareSection();
        amazonHomePage.validateHealthAndPersonalCareSectionIsDisplayed();
    }

    @Then("the user navigates to Diet And Nutrition Section")
    public void the_user_navigates_to_diet_and_nutrition_section() {
       amazonHomePage.navigateToDietAndNutritionSection();
    }

    @Then("the user selects Whey Protein Powders")
    public void the_user_selects_whey_protein_powders() {
       amazonHomePage.selectWheyProtein();
    }

    @When("the user selects the quantity {string} of the product and clicks on Add to Cart")
    public void the_user_selects_the_quantity_of_the_product_and_clicks_on_add_to_cart(String quantity) {
        amazonHomePage.switchToChildTab();
        amazonHomePage.selectQuantity(quantity);
        amazonHomePage.addToCart();
    }

    @Then("the user is shown the option of Proceed to Buy")
    public void the_user_is_shown_the_option_of_proceed_to_buy() {
        amazonHomePage.validateProceedToByIsDisplayed();
        amazonHomePage.deleteCartItems();
        amazonHomePage.switchToParentTab();
    }
}
