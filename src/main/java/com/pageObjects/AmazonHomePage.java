package com.pageObjects;

import com.utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.Set;

public class AmazonHomePage {

    private WebDriver driver;
    CommonUtils commonUtils = new CommonUtils();

    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    WebElement inp_Search;

    @FindBy(xpath = "//a[text()='Health, Household & Personal Care']")
    WebElement lnk_HealthCare;

    @FindBy(xpath = "//h1[text()='Health, Household & Personal Care']")
    WebElement txt_HealthCare;

    @FindBy(xpath = "//span[text()='Diet & Nutrition']")
    WebElement lnk_DietAndNutrition;

    @FindBy(xpath = "//a[@aria-label='Whey protein powders']")
    WebElement lnk_WheyProtein;

    @FindBy(xpath = "(//span[@class='rush-component']/a/div)[1]")
    WebElement lnk_ProductSelected;

    @FindBy(xpath = "//select[@id='quantity']")
    WebElement drpdwnQuantity;

    @FindBy(xpath = "//input[@id='add-to-cart-button']")
    WebElement btn_AddToCart;

    @FindBy(xpath = "//input[@name='proceedToRetailCheckout']")
    WebElement btn_Checkout;

    @FindBy(xpath = "(//span[@class='a-button-inner'])[2]/a")
    WebElement btn_GoToCart;

    @FindBy(xpath = "//input[@value='Delete']")
    WebElement btn_DeleteCartValue;

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void navigateToHealthAndPersonalCareSection(){
        commonUtils.click(lnk_HealthCare);
    }

    public void validateHealthAndPersonalCareSectionIsDisplayed(){
        commonUtils.verifyElementDisplayed(txt_HealthCare, "Health, Household & Personal Care header");
    }

    public void navigateToDietAndNutritionSection(){
        commonUtils.click(lnk_DietAndNutrition);
    }

    public void selectWheyProtein(){
        commonUtils.click(lnk_WheyProtein);
        commonUtils.click(lnk_ProductSelected);
    }

    public void selectQuantity(String value){
        commonUtils.select(drpdwnQuantity,value);
    }

    public void switchToChildTab(){
        // It will return the parent window name as a String
        String parent=driver.getWindowHandle();

        Set<String> s=driver.getWindowHandles();

        // Now iterate using Iterator
        Iterator<String> I1= s.iterator();
        while(I1.hasNext())
        {
            String child_window=I1.next();
            if(!parent.equals(child_window))
            {
                driver.switchTo().window(child_window);
                System.out.println(driver.switchTo().window(child_window).getTitle());
            }
        }
    }

    public void switchToParentTab(){
        // It will return the parent window name as a String
        String parent=driver.getWindowHandle();

        Set<String> s=driver.getWindowHandles();

        // Now iterate using Iterator
        Iterator<String> I1= s.iterator();
        while(I1.hasNext())
        {
            String child_window=I1.next();
            if(!parent.equals(child_window))
            {
                driver.switchTo().window(child_window);
                System.out.println(driver.switchTo().window(child_window).getTitle());
                driver.close();
            }
        }
        //switch to the parent window
        driver.switchTo().window(parent);
    }

    public void addToCart(){
        commonUtils.click(btn_AddToCart);
    }

    public void validateProceedToByIsDisplayed(){
        commonUtils.verifyElementDisplayed(btn_Checkout, "Proceed to Buy");
    }

    public void deleteCartItems(){
        commonUtils.click(btn_GoToCart);
        commonUtils.click(btn_DeleteCartValue);
    }
}
