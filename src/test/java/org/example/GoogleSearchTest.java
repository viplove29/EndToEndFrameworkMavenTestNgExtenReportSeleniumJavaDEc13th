package org.example;

import org.example.utility.ConfigManager;
import org.example.utility.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class GoogleSearchTest extends BaseClass {

  @Test
  public void searchGoogle1() {
    WebDriver driver = DriverFactory.getDriver();
    String url = ConfigManager.getProperty("google.url");
    driver.get(url); // Open the URL from config
    System.out.println("Title of the page is: " + driver.getTitle());
    // Create a test in the report
    test = extent.createTest("Sample Test 1");
    test.info("Starting the test");
    test.pass("Test passed successfully");
  }

  @Test
  public void searchGoogle3() {
    WebDriver driver = DriverFactory.getDriver();
    test = extent.createTest("Search Test 3");
    test.info("Navigating to Google");
    googlePage = new GoogleHomePage(driver);
    googlePage.launchPage("https://www.google.com");
    googlePage.search("Selenium TestNG Maven tutorial 2");
    System.out.println("Title of the page is: " + driver.getTitle());
    String title = driver.getTitle();
    test.info("Page title is: " + title);
  }

  @Test
  public void searchTest2() {
    WebDriver driver = DriverFactory.getDriver();
    test = extent.createTest("Search Test 2");
    test.info("Navigating to Google");
    driver.get("https://www.google.com");
    String title = driver.getTitle();
    test.info("Page title is: " + title);

    if (title.contains("Google")) {
      test.pass("Title verification passed");
    } else {
      test.fail("Title verification failed");
    }
  }
}
