package com.utils;

import com.factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

public class CommonUtils {

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;


    public CommonUtils() {
        driver = DriverFactory.getDriver();
        wait = new WebDriverWait(driver, 60);
    }

    public void navigateToURL(String URL) {
        System.out.println("Navigating to: " + URL);
        System.out.println("Thread id = " + Thread.currentThread().getId());

        try {
            driver.navigate().to(URL);
        } catch (Exception e) {
            System.out.println("URL did not load: " + URL);
            throw new TestException("URL did not load");
        }
    }

    public String getPageTitle() {
        try {
            System.out.print(String.format("The title of the page is: %s\n\n", driver.getTitle()));
            return driver.getTitle();
        } catch (Exception e) {
            throw new TestException(String.format("Current page title is: %s", driver.getTitle()));
        }
    }

    public WebElement getElement(By selector) {
        try {
            return driver.findElement(selector);
        } catch (Exception e) {
            System.out.println(String.format("Element %s does not exist - proceeding", selector));
        }
        return null;
    }


    public void sendKeys(By selector, String value) {
        WebElement element = getElement(selector);
        clearField(element);
        try {
            element.sendKeys(value);
        } catch (Exception e) {
            throw new TestException(String.format("Error in sending [%s] to the following element: [%s]", value, selector.toString()));
        }
    }

    public void clearField(WebElement element) {
        try {
            element.clear();
        } catch (Exception e) {
            System.out.print(String.format("The following element could not be cleared: [%s]", element.getText()));
        }
    }

    public void click(By selector) {
        WebElement element = getElement(selector);
        waitForElementToBeClickable(selector);
        try {
            element.click();
        } catch (Exception e) {
            throw new TestException(String.format("The following element is not clickable: [%s]", selector));
        }
    }

    private void waitForElementToBeClickable(By selector) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(selector));
        } catch (Exception e) {
            throw new TestException(String.format("The following element is not clickable: [%s]", selector));
        }

    }

}

