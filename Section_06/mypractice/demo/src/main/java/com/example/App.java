package com.example;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class App {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        String testUrl = "https://practicetestautomation.com/practice-test-exception/";
        // Chọn 1 trong 2 hàm dưới đây để chạy kiểm thử
        findLoginPageElements(driver);
        findExceptionPageElements(driver);
        String chromeTitle = chromeTest(testUrl); 
        System.out.println("Tiêu đề lấy từ Chrome: " + chromeTitle);
        driver.quit();
    }

    private static void findExceptionPageElements(WebDriver driver) {
        driver.get("https://practicetestautomation.com/practice-test-exception/");

        // 1. Sửa lại chính xác chữ hoa/thường và từ "for"[cite: 3]
        var linkByText = driver.findElement(By.linkText("Selenium WebDriver with Java for beginners program"));
        // 2. Thêm dấu chấm phẩy ở cuối dòng[cite: 3]
        var linkBypartialText = driver.findElement(By.partialLinkText("with Java for beginners"));

        var inputByTag = driver.findElement(By.tagName("input"));
        var inputByClass = driver.findElement(By.className("input-field"));
        // 3. Sửa vị trí dấu nháy đơn ' nằm trước dấu ][cite: 3]
        var inputByXpath = driver.findElement(By.xpath("//input[@class='input-field']"));
        var inputByCss = driver.findElement(By.cssSelector("input.input-field"));

        List<WebElement> buttons = driver.findElements(By.tagName("button"));

        // Identify both buttons, using ID, name, css, Xpath for each[cite: 3]
        var editButtonById = driver.findElement(By.id("edit_btn"));
        var editButtonByName = driver.findElement(By.name("Edit"));
        var editButtonByCss = driver.findElement(By.cssSelector("button#edit_btn"));
        var editButtonByXpath = driver.findElement(By.xpath("//button[@id='edit_btn']"));

        WebElement addButtonById = driver.findElement(By.id("add_btn"));
        WebElement addButtonByName = driver.findElement(By.name("Add"));
        WebElement addButtonByCss = driver.findElement(By.cssSelector("button#add_btn"));
        WebElement addButtonByXpath = driver.findElement(By.xpath("//button[@id='add_btn']"));
    }  

    private static void findLoginPageElements(WebDriver driver) {
        driver.get("https://practicetestautomation.com/practice-test-login/");

        var usernameInputField = driver.findElement(By.id("username"));
        usernameInputField.sendKeys("anyUsername");
        
        // 4. Đổi tên biến thứ 2 thành Css để không bị trùng tên[cite: 4]
        var passwordInputFieldXpath = driver.findElement(By.xpath("//input[@name='password']"));
        var passwordInputFieldCss = driver.findElement(By.cssSelector("input[name='password']"));

        // 5 & 6. Xóa đoạn code khai báo trùng và giữ lại bộ locator đúng của nút Submit[cite: 4, 5]
        var submitButton = driver.findElement(By.className("btn"));
        var submitButtonXpath = driver.findElement(By.xpath("//button[@id='submit']"));
        var submitButtonCss = driver.findElement(By.cssSelector("button[id='submit']"));
        
        submitButton.isDisplayed();
        submitButton.click();

        List<WebElement> inputFields = driver.findElements(By.tagName("input"));

        WebElement linkTextLocator = driver.findElement(By.linkText("Practice Test Automation."));
        WebElement partialLinkTextLocator = driver.findElement(By.partialLinkText("Test Automation"));

        WebElement passwordFieldBelowUsername = driver.findElement(
            RelativeLocator.with(By.tagName("input")).below(By.id("username"))
        );
        WebElement privacyPolicyLink = driver.findElement(
            RelativeLocator.with(By.tagName("a")).toRightOf(By.partialLinkText("Test Automation"))
        );

        WebElement homeButton = driver.findElement(By.className("menu-item-home"));
    }

    // cach này nhanh hơn 
    private static String chromeTest(String url){
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        String title = driver.getTitle();
        driver.quit();
        return title;
    }

    // này là cách cũ trước đây -> phải tự tải file thực thi geckodriver.exe
    private static String firefoxTest(String url){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        var driver = new FirefoxDriver();
        driver.get(url);
        String title = driver.getTitle();
        driver.quit();
        return title;
    }
}