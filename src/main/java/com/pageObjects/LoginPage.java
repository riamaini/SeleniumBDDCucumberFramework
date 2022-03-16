package com.pageObjects;


import com.utils.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    private WebDriver driver;
    CommonUtils commonUtils = new CommonUtils();

    // 1. By Locators: OR
    private By emailId = By.id("email");
    private By password = By.id("passwd");
    private By signInButton = By.id("SubmitLogin");
    private By forgotPwdLink = By.linkText("Forgot your password?");


    // 2. Constructor of the page class:
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // 3. page actions: features(behavior) of the page the form of methods:

    public String getLoginPageTitle() {
        return driver.getTitle();
    }

    public boolean isForgotPwdLinkExist() {
        return driver.findElement(forgotPwdLink).isDisplayed();
    }

    public void enterUserName(String username) {
        commonUtils.sendKeys(emailId,username);
    }

    public void enterPassword(String pwd) {
        commonUtils.sendKeys(password,pwd);
    }

    public void clickOnLogin() {
        commonUtils.click(signInButton);
    }

    public AccountsPage doLogin(String username, String pwd) {
        commonUtils.sendKeys(emailId,username);
        commonUtils.sendKeys(password,pwd);
        commonUtils.click(signInButton);
        return new AccountsPage(driver);
    }
}
