package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
  private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

  public static WebDriver getDriver() {
    return driverThreadLocal.get();
  }

  public static void setDriver(String browser) {
    if (browser.equalsIgnoreCase("chrome")) {
      WebDriverManager.chromedriver().setup();
      driverThreadLocal.set(new ChromeDriver());
    } else if (browser.equalsIgnoreCase("firefox")) {
      WebDriverManager.firefoxdriver().setup();
      driverThreadLocal.set(new FirefoxDriver());
    } else if (browser.equalsIgnoreCase("edge")) {
      WebDriverManager.edgedriver().setup();
      driverThreadLocal.set(new EdgeDriver());
    } else {
      throw new IllegalArgumentException("Unsupported browser: " + browser);
    }
  }

  public static void quitDriver() {
    if (getDriver() != null) {
      getDriver().quit();
      driverThreadLocal.remove();
    }
  }
}
