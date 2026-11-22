package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage{
  private String pageUrl = "https://practicetestautomation.com/practice-test-login/";

  private By usernameInput = By.id("username");
  private By passwordInput = By.id("password");
  private By submitButton = By.id("submit");
  private By errorMessage = By.id("error");

  private By successHeader = By.xpath("//h1[contains(@class,'post-title') or contains(text(),'Successfully')]");
  private By logOutButton = By.linkText("Log out"); // Dùng linkText chuẩn hơn cho thẻ <a></a>
  // constructor nhận driver from BaseTest
  public LoginPage(WebDriver driver){
    super(driver);
  }
  
  // 3. các hàm nghiệp vụ
  public void open(){
    openUrl(pageUrl);
  }
  public void enterUsername(String username){
    type(usernameInput, username);
  }
  public void enterPassword(String password){
    type(passwordInput, password);
  }
  public void clickSubmitButton(){
    click(submitButton);
  }

  // hàm tiện ích đăng nhập trọn gói trong 1 bước

  public void executeLogin(String username, String password){
    enterUsername(username);
    enterPassword(password);
    clickSubmitButton();
    try {
      wait.until(ExpectedConditions.or(
          ExpectedConditions.urlContains("logged-in-successfully"),
          ExpectedConditions.visibilityOfElementLocated(errorMessage)
      ));

    }catch (Exception e) {
      // Bỏ qua nếu là test case sai để hàm getErrorMessage xử lý
    }
  }

  public String getErrorMessageText(){
    return find(errorMessage).getText();
  }
  public boolean isErrorMessageDisplayed(){
    return find(errorMessage).isDisplayed();
  }
  public boolean isSuccessfulLoginHeaderDisplayed(){
    return find(successHeader).isDisplayed();
  }
  public String getSuccessHeaderText() {
    return find(successHeader).getText();
  }
  public boolean isLogoutButtonDisplayed(){
    return find(logOutButton).isDisplayed();
  }
  public String getCurrentPageUrl() {
    return driver.getCurrentUrl();
  }
  
}
