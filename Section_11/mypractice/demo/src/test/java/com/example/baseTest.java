package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class baseTest {
  protected WebDriver driver ;

  @Parameters ({"browser"})
  @BeforeMethod 
  public void setup(@Optional ("chrome") String browser){
    switch (browser.toLowerCase()) {
      case "firefox":
        driver = new FirefoxDriver();
        break;
      case "edge":
        driver = new EdgeDriver();

      default:
        driver = new ChromeDriver();  
      break;
    }
    driver.manage().window().maximize();
  }

  @AfterMethod 
  public void tearDown(){
    if(driver != null){
      driver.quit();
    }
  }
}
