package org.example.pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.example.utility.ConfigManager;
import org.example.utility.DriverFactory;
import org.testng.annotations.*;

public class BaseClass {

  protected ExtentTest test;
  String reportPath;
  protected ExtentReports extent;

  @BeforeSuite
  public void setupReport() {
    // Generate unique file name using timestamp
    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    reportPath = "target/ExtentReport_" + timestamp + ".html";

    // Initialize the ExtentSparkReporter with unique report name
    ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
    extent = new ExtentReports();
    extent.attachReporter(spark);
  }

  @BeforeSuite
  public void setup() {
    // Example of accessing configuration
    String browser = ConfigManager.getProperty("browser");
    System.out.println("Running tests on browser: " + browser);
  }

  @BeforeMethod
  @Parameters("browser")
  public void setUp(@Optional("chrome") String browser) {
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
    File reportFile = new File(reportPath);
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
