package com.example.base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

public class TestListener implements ITestListener {
  @Attachment(value = "Failure Screenshot", type = "image/png")
  public byte[] saveScreenshotToAllure(WebDriver driver) {
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
  }

  @Override
  public void onTestStart(ITestResult result) {
    System.out.println("==================================================");
    System.out.println("[START] Bắt đầu chạy test: " + result.getName());
  }
  @Override
  public void onTestFailure(ITestResult result) {
    Object currentClass = result.getInstance();
    if (currentClass instanceof BaseTest) {

      WebDriver driver = ((BaseTest) currentClass).getDriver();

      if (driver != null) {
          // Đính kèm ảnh trực tiếp vào Allure Report
          saveScreenshotToAllure(driver);
      }
    }
    System.out.println("[FAIL] THẤT BẠI: " + result.getName());
    System.out.println("[ERROR REASON] " + result.getThrowable().getMessage());
    System.out.println("==================================================");
  }
  @Override
  public void onStart(ITestContext context) {
    System.out.println("&gt;&gt;&gt; KHỞI ĐỘNG SUITE: " + context.getName());
  }

  @Override
  public void onFinish(ITestContext context) {
    System.out.println("&gt;&gt;&gt; HOÀN TẤT SUITE: " + context.getName());
  }

}
