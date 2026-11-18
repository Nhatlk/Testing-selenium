package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


/**
 * Hello world!
 *
 */
public class App 
{
        @Test
        public void testLoginFunctionality(){
            WebDriver driver = new ChromeDriver();
            
            // type user name student into Username field
            var usernameInput = driver.findElement(By.id("username"));
            usernameInput.sendKeys("student");

            var passInput = driver.findElement(By.id("password"));
            passInput.sendKeys("Password1223");

            var submitButton = driver.findElement(By.id("submit"));
            submitButton.click();
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){
                throw new RuntimeException();
            }
        
            //verify new page URL contrains practicetestauto.com
            String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
            String actulUrl = driver.getCurrentUrl();
            AssertJUnit.assertEquals(actulUrl, expectedUrl);

            //verify new page contrains expected text
            String expectedMessage = "Congratulations student. You successfully logged in!";
            String pageSource = driver.getPageSource();
            Assert.assertTrue(pageSource.contains(expectedMessage));

            // verify button log out is displayed on the new page
            var logOutButton = driver.findElement(By.linkText("log out"));
            Assert.assertTrue(logOutButton.isDisplayed());
            driver.quit();
        
        
        }
}
