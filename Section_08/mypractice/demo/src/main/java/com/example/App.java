package com.example;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class App {
    private WebDriver d;
    private WebDriverWait dw;

    @BeforeMethod
    public void setUp() {
        d = new ChromeDriver();
        d.manage().window().maximize();
        dw = new WebDriverWait(d, Duration.ofSeconds(10));
        // Đã sửa sang đúng URL trang Practice Test Exceptions
        d.get("https://practicetestautomation.com/practice-test-exception/");
    }

    @Test
    public void testAddRow2Successfully() {
        WebElement addBtn = d.findElement(By.id("add_btn"));
        addBtn.click();

        // Chờ động: đợi đến khi ô input của row 2 thực sự xuất hiện
        WebElement row2Input = dw.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input"))
        );
        Assert.assertTrue(row2Input.isDisplayed());
    }

    @Test
    public void testSaveRow2WithMessage() {
        // Click nút Add
        d.findElement(By.id("add_btn")).click();

        WebElement row2Input = dw.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input"))
        );
        // Nhập dữ liệu mới vào hàng 2
        row2Input.sendKeys("Phở Bò đặc biệt");

        // Click nút Save riêng của row 2
        WebElement saveBtnRow2 = d.findElement(By.xpath("//div[@id='row2']/button[@id='save_btn']"));
        saveBtnRow2.click();

        // Chờ dòng thông báo xuất hiện
        WebElement confirmationMsg = dw.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("confirmation"))
        );

        Assert.assertEquals(confirmationMsg.getText(), "Row 2 was saved", "Lỗi: Thông báo hiển thị không khớp");
    }

    @AfterMethod
    public void teardown() {
        if (d != null) {
            d.quit();
        }
    }
}