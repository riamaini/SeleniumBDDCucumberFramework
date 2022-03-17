package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage {

    private WebDriver driver;

    @FindBy(id = "id_contact")
    WebElement subjectHeading;

    @FindBy(id = "email")
    WebElement email;

    @FindBy(id = "id_order")
    WebElement orderRef;

    @FindBy(id = "message")
    WebElement messageText;

    @FindBy(id = "submitMessage")
    WebElement sendButton;

    @FindBy(css = "div#center_column p")
    WebElement successMessg;


    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getContactUsPageTitle() {
        return driver.getTitle();
    }

    public void fillContactUsForm(String heading, String emailId, String orderReference, String message) {
        Select select = new Select(subjectHeading);
        select.selectByVisibleText(heading);
        email.sendKeys(emailId);
        orderRef.sendKeys(orderReference);
        messageText.sendKeys(message);
    }

    public void clickSend() {
        sendButton.click();
    }

    public String getSuccessMessage() {
        return successMessg.getText();
    }
}
