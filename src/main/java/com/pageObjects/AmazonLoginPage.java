package com.pageObjects;

import com.utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AmazonLoginPage {

    private WebDriver driver;
    CommonUtils commonUtils = new CommonUtils();

    @FindBy(xpath = "//span[text()='Hello, Sign in']")
    WebElement lnk_SignIn;

    @FindBy(xpath = "//input[@id = 'ap_email']")
    WebElement inp_UserName;

    @FindBy(xpath = "//input[@id = 'continue']")
    WebElement btn_Continue;

    @FindBy(xpath = "//input[@id = 'ap_password']")
    WebElement inp_Password;

    @FindBy(xpath = "//input[@id = 'signInSubmit']")
    WebElement btn_Submit;

    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    WebElement inp_Search;

    public AmazonLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    public void navigateToSignInPage(){
        commonUtils.click(lnk_SignIn);
    }

    public void enterUserName(String username){
        commonUtils.sendKeys(inp_UserName,username);
    }

    public void clickOnContinue(){
        commonUtils.click(btn_Continue);
    }

    public void enterPassword(String pass){
        commonUtils.sendKeys(inp_Password,pass);
    }

    public void clickOnSignIn(){
        commonUtils.click(btn_Submit);
    }

    public void validateUserIsSignedIn(){
        commonUtils.verifyElementDisplayed(inp_Search, "Search Text box");
    }
}
