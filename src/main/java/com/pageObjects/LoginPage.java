package com.pageObjects;


import com.factory.DriverFactory;
import com.utils.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;
    CommonUtils commonUtils = new CommonUtils();

    // 1. WebElements

    @FindBy(xpath = "//input[@id='email']")
    WebElement emailId;

    @FindBy(xpath = "//input[@id='passwd']")
    WebElement password;

    @FindBy(xpath = "//button[@id='SubmitLogin']")
    WebElement signInButton;

    @FindBy(xpath = "//a[text()='Forgot your password?']")
    WebElement forgotPwdLink;


    // 2. Constructor of the page class:
    public LoginPage(WebDriver driver) {
       this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    // 3. page actions: features(behavior) of the page the form of methods:

    public String getLoginPageTitle() {
        return driver.getTitle();
    }

    public boolean isForgotPwdLinkExist() {
        return forgotPwdLink.isDisplayed();
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
