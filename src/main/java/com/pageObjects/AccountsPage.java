package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {

    private WebDriver driver;

    @FindBy(css = "div#center_column span")
    List<WebElement> accountSections;


    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getAccountsPageTitle() {
        return driver.getTitle();
    }

    public int getAccountsSectionCount() {
        return accountSections.size();
    }

    public List<String> getAccountsSectionsList() {

        List<String> accountsList = new ArrayList<>();

        for (WebElement e : accountSections) {
            String text = e.getText();
            accountsList.add(text);
        }

        return accountsList;

    }
}
