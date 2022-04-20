package com.utils;

import com.factory.DriverFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public WebElement getElement(WebElement selector) {
        try {
            return selector;
        } catch (Exception e) {
            System.out.println(String.format("Element %s does not exist - proceeding", selector));
        }
        return null;
    }


    public void sendKeys(WebElement selector, String value) {
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

    public void click(WebElement selector) {
        WebElement element = getElement(selector);
        waitForElementToBeClickable(selector);
        try {
            element.click();
        } catch (Exception e) {
            throw new TestException(String.format("The following element is not clickable: [%s]", selector));
        }
    }

    private void waitForElementToBeClickable(WebElement selector) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(selector));
        } catch (Exception e) {
            throw new TestException(String.format("The following element is not clickable: [%s]", selector));
        }

    }

    public String readJSON(String fileName, String queryKey) {

        String queryString = null;

        try {

            byte[] jsonData = Files.readAllBytes(Paths.get("src/test/resources/DBQueries/" + fileName + ".json"));

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(jsonData);

            Iterator<String> nodeFields = jsonNode.fieldNames();

            while(nodeFields.hasNext()) {

                String nodeName = nodeFields.next();

                JsonNode jsonNodeName = jsonNode.get(nodeName);

                if (jsonNodeName.has(queryKey)) {
                    queryString = jsonNodeName.get(queryKey).asText();
                }

            }

            return queryString;

        } catch (Exception e) {
            return queryString;
        }

    }

    public List<String> getDBRecords(ResultSet records, String colName)
    {
        List<String> listRecord = new ArrayList<String>();

        try {

            while (records.next()){
                listRecord.add(records.getString(colName));
            }

            return listRecord;

        } catch(Exception e) {
            Assert.fail("Exception in getDBRecords method: " + e.getMessage());
            return null;
        }
    }

    public void verifyElementDisplayed(WebElement ele, String byName) {

        try {
            waitForElementToBeClickable(ele);
            Assert.assertTrue(ele.isDisplayed());
        } catch (Exception e) {
            Assert.fail("Exception in verifyElementDisplayed method: " + e.getMessage());
        }

    }

    public void select(WebElement ele, String option) {

        try {

            Select select = new Select (ele);
            select.selectByVisibleText(option);

        } catch (Exception e) {
            Assert.fail("Exception in select method: " + e.getMessage());
        }

    }

    public void select(WebElement ele, int index) {

        try {

            Select select = new Select (ele);
            select.selectByIndex(index);

        } catch (Exception e) {
            Assert.fail("Exception in select method: " + e.getMessage());

        }

    }

}

