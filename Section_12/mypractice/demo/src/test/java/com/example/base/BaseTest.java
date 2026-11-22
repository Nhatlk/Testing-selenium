package com.example.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Files;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/*
  ThreadLocal: gán WebDriver vào  ThreadLocal (new) hiện tại if has 1 WebDriver thì lại new threadlocal tiếp => Parallel
  Logger
  Create method takeScreenshot()

  use @Optional("") to default browser from testng.xml
  use ITestResult to check status


*/



public class BaseTest {
  // protected WebDriver driver ;
  private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
  // khoi tao logger
  protected Logger log = LogManager.getLogger(getClass());

  @Parameters ({ "browser" })
  @BeforeMethod (alwaysRun = true)
  public void setup(@Optional ("chrome") String browser){
    // 1. 
    log.info("Khởi động bài test với trình duyệt: " + browser);
    //
    BrowserDriverFactory factory = new BrowserDriverFactory(browser);
    var driver = factory.createDriver();
    driver.manage().window().maximize();

    // Gán driver vào luồng hiện tại
    driverThreadLocal.set(driver);

  }
  
  // Hàm lấy driver của luồng hiện tại 
  public WebDriver getDriver() { 
    return driverThreadLocal.get(); 
  }

  @AfterMethod (alwaysRun = true)
  public void tearDown(ITestResult result){
    var driver = getDriver();
    //2. tự động kiểm tra: nếu bài test bị faill thì chụp ảnh lại
    if(result.getStatus() == ITestResult.FAILURE){
      takeScreenshot(result.getName());
    }
    if(driver != null){
      driver.quit();
      driverThreadLocal.remove(); // Giải phóng luồng
    }
  }

  private void takeScreenshot(String testName) {
    var driver = getDriver();
    if(driver instanceof TakesScreenshot){
      TakesScreenshot camera = (TakesScreenshot) driver;
      File tempImage = camera.getScreenshotAs(OutputType.FILE);
      
      // đặt file theo định dạng testTest_NamThangNgay_GioPhutGiay
      String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
      File destinationFolder = new File("screenshots");
      if(!destinationFolder.exists()){
        destinationFolder.mkdir();
      }

      File finalImage = new File(destinationFolder, testName + "_" + timeStamp + ".png");

      try {
        Files.copy(tempImage.toPath(), finalImage.toPath());
        System.out.println(">>> [SCREENSHOT] đã lưu ảnh chụp khi test fail tại: " + finalImage.getAbsolutePath());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
