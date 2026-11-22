package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExceptionsPage {
  private WebDriverWait wait;
  private WebDriver driver;


  private By addBtn = By.id("add_btn");
  private By row2Input = By.xpath("//div[@id='row2']/input");
  private By row2SaveBtn = By.xpath("//div[@id='row2']/button[@id='save_btn']");
  private By confirmationMsg = By.id("confirmation");
  private By editBtn = By.id("edit_btn");
  private By row1Input = By.xpath("//div[@id='row1']/input");
  private By row1SaveBtn = By.xpath("//div[@id='row1']/button[@id='save_btn']");
  private By instruction = By.id("instruction");

  public  ExceptionsPage(WebDriver driver){
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); 
  }
  public void open(){
    driver.get("https://practicetestautomation.com/practice-test-exceptions/");
  }
  public void clickAddbutton(){
    driver.findElement(addBtn).click();
  }
  public boolean isRow2InputDisplayed(){
    return wait.until(ExpectedConditions.visibilityOfElementLocated(row2Input)).isDisplayed();
  }
  public void enterRow2Text(String text){
    // waiting for text was isplayed
    WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(row2Input));
    input.sendKeys(text);
  }
  public void clickRow2SaveButton(){
    driver.findElement(row2SaveBtn).click();
  }
  public String getConfirmationText(){
    // wait visibility Of Ele Located
    return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMsg)).getText();
  }

  // Action of row1
  public void clickEditButton(){
    driver.findElement(editBtn).click();
  }
  public void editRow1Text(String newText){
    // wait row1 is clickable
    WebElement input = wait.until(ExpectedConditions.elementToBeClickable(row1Input));
    input.clear();
    input.sendKeys(newText);
  }
  public void clickRow1SaveButton(){
    driver.findElement(row1SaveBtn).click();
  }
  public boolean areInstructionsDisappeared(){
    WebElement ele = driver.findElement(instruction);
    clickAddbutton();
    return wait.until(ExpectedConditions.invisibilityOf(ele));
  }
}
