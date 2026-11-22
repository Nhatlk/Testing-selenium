package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.base.ConfigurationReader;

public class ExceptionsPage extends BasePage{
  // Thay vì: private String pageUrl = "https://practicetestautomation.com/practice-test-exceptions/";
  // private String pageUrl = "https://practicetestautomation.com/practice-test-exceptions/";
  
  // Đổi thành đọc từ file cấu hình:
  private String pageUrl = ConfigurationReader.getProperty("url");

  // Locator
  private By addButton = By.id("add_btn");
  private By row2InputField = By.xpath("//div[@id='row2']/input");
  private By row2SaveButton = By.xpath("//div[@id='row2']/button[@name='Save']");
  private By confirmationMessage = By.id("confirmation");


  public ExceptionsPage(WebDriver driver) {
    super(driver);
  }
  public void open() { 
    openUrl(pageUrl); 
  }

  public void clickAddButton() { 
    click(addButton); 
  }

  public boolean isRow2Displayed() { 
    return find(row2InputField).isDisplayed(); 
  }

  public void enterRow2Text(String text) { 
    type(row2InputField, text); 
  }
  
  public void clickSaveRow2() { 
    click(row2SaveButton); 
  }

  public String getConfirmationMessage() { 
    return find(confirmationMessage).getText(); 
  }
}
