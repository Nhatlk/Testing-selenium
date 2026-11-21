package com.example.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriverFactory {
  private String browser;
  public BrowserDriverFactory(String browser){
    this.browser = browser;
  }
  public WebDriver createDriver(){
    WebDriver driver;
    switch (browser) {
      case "firefox":
        driver = new FirefoxDriver();
        break;
      case "edge":
      driver = new EdgeDriver();    
        break;
      case "chrome-headless":
        // chạy chrome gầm khôn bật cửaso63 (nhanh và nhẹ)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--wwindow-size=1920,1080");
        driver = new ChromeDriver(options);
        break;
        case "chrome":
        default:
          driver = new ChromeDriver();
        break;
    }
    return driver;
  }
}
