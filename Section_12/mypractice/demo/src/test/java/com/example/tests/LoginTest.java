package com.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.base.BaseTest;
import com.example.pages.LoginPage;

public class LoginTest extends BaseTest{

  //test case 1 login successfully
  @Test (priority = 1 )
  public void testLoginSuccess(){
    LoginPage loginPage = new LoginPage(getDriver());
    loginPage.open();

    loginPage.executeLogin("student", "Password123");

    Assert.assertTrue(loginPage.isSuccessfulLoginHeaderDisplayed(), "Header chúc mừng đăng nhập thành công phải hiển thị!");
    Assert.assertTrue(loginPage.isLogoutButtonDisplayed(), "Nút Logout phải xuất hiện sau khi login thành công");
    // Kiểm tra URL đã chuyển hướng thành công
    Assert.assertTrue(loginPage.getCurrentPageUrl().contains("logged-in-successfully"),
                      "URL phải chứa 'logged-in-successfully' sau khi đăng nhập!");
    // Kiểm tra tiêu đề h1 hiển thị
    Assert.assertTrue(loginPage.isSuccessfulLoginHeaderDisplayed(),
                      "Header chúc mừng đăng nhập thành công phải hiển thị!");

    // Kiểm tra nút Logout
    Assert.assertTrue(loginPage.isLogoutButtonDisplayed(),
                      "Nút Logout phải xuất hiện!");
  }

  @Test(priority = 2)
  public void testLoginWithInvalidUsername(){
    LoginPage loginPage = new LoginPage(getDriver());
    loginPage.open();
    loginPage.executeLogin("incorrectUser", "Password123");

    Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
          "Thông báo lỗi phải hiển thị khi nhập sai username!");
    
    Assert.assertEquals(loginPage.getErrorMessageText(), "Your username is invalid!",
          "Nội dung thông báo lỗi không khớp!");
  }

  @Test(priority = 3)
  public void testLoginWithInvalidPassword() {
    LoginPage loginPage = new LoginPage(getDriver());
    loginPage.open();

    loginPage.executeLogin("student", "incorrectPassword");
    Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                      "Thông báo lỗi phải hiển thị khi nhập sai password!");

    Assert.assertEquals(loginPage.getErrorMessageText(), "Your password is invalid!",
                      "Nội dung thông báo lỗi không khớp!");






  }
}
