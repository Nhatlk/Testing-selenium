package com.example;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ExceptionsTest
 */
public class ExceptionsTest extends baseTest{
  @Test 
  public void testAddRow2Successfully(){

    ExceptionsPage page = new ExceptionsPage(driver);
    page.open();
    page.clickAddbutton();

    Assert.assertTrue(page.isRow2InputDisplayed(), "Lỗi: Ô input của Row 2 không hiển thị!");
  }
  @Test 
  public void testSaveRow2TithMessage(){
    ExceptionsPage page = new ExceptionsPage(driver);
    page.open();
    page.clickAddbutton();
    page.enterRow2Text("Phở bò");
    page.clickRow2SaveButton();

    Assert.assertEquals(page.getConfirmationText(), "Row 2 was saved", "Lỗi: sai thông báo lưu!");
  }
  
}