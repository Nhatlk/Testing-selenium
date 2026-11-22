package com.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.base.BaseTest;
import com.example.pages.ExceptionsPage;

public class ExceptionsTest extends BaseTest{
  @Test (priority = 1) public void testAddRow2Successfully() { 
    ExceptionsPage exceptionsPage = new ExceptionsPage(getDriver()); 
    exceptionsPage.open(); 
    exceptionsPage.clickAddButton(); 
    Assert.assertTrue(exceptionsPage.isRow2Displayed(), "Row 2 input field should be displayed!"); 
  }
  @Test(priority = 2) public void testSaveRow2WithMessage() { 
    ExceptionsPage exceptionsPage = new ExceptionsPage(getDriver()); 
    exceptionsPage.open(); exceptionsPage.clickAddButton(); 
    exceptionsPage.enterRow2Text("Sushi"); 
    exceptionsPage.clickSaveRow2(); 
    String message = exceptionsPage.getConfirmationMessage(); 
    Assert.assertEquals(message, "Row  was saved", "Confirmation message mismatch!"); }
}
