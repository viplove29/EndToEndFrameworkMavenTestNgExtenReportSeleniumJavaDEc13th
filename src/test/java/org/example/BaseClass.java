package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseClass {

  WebDriver driver;
  ExtentReports extent;
  ExtentTest test;
  GoogleHomePage googlePage;

  @BeforeSuite
  public void setupReport() {
    ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
    extent = new ExtentReports();
    extent.attachReporter(spark);
  }

  @BeforeMethod
  @Parameters("browser")
  public void setUp(String browser) {
    DriverFactory.setDriver(browser);
  }

  @AfterMethod
  public void tearDown() {
    DriverFactory.quitDriver();
  }

  @AfterSuite
  public void tearDownReport() {
    extent.flush();

    // Automatically open the report in the default browser
    File reportFile = new File("target/ExtentReport.html");
    if (reportFile.exists()) {
      try {
        Desktop.getDesktop().browse(reportFile.toURI());
      } catch (IOException e) {
        System.err.println("Failed to open report: " + e.getMessage());
      }
    } else {
      System.err.println("Report file does not exist!");
    }
  }
}
