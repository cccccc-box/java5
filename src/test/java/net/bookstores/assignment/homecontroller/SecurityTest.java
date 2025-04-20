package net.bookstores.assignment.homecontroller;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SecurityTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\FPT Polytechnic\\SOF3041\\chrome-win64\\chrome.exe");
        System.setProperty("webdriver.chrome.driver",
                "C:\\FPT Polytechnic\\SOF3041\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test // test đăng xuất
    public void testBM1() {
        driver.get("http://localhost:8080/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]")));
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));

        email.sendKeys("admin@admin.com");
        password.sendKeys("admin");
        btnLogin.click();

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.getStackTrace();
        }
        driver.get("http://localhost:8080/admin/book/index");
        driver.get("http://localhost:8080/logout");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.getStackTrace();
        }
        driver.get("http://localhost:8080/admin/book/index");
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "http://localhost:8080/admin/book/index";
        Assert.assertNotEquals(expectedURL, currentURL, "Đăng nhập không thành công");
    }

    @Test // test bảo mật đăng nhập bằng acc khác hàng vào trang web admin
    public void testBM2() {
        driver.get("http://localhost:8080/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]")));
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));

        email.sendKeys("tranthib@example.com");
        password.sendKeys("password123");
        btnLogin.click();

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.getStackTrace();
        }
        driver.get("http://localhost:8080/admin/book/index");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.getStackTrace();
        }
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "http://localhost:8080/admin/book/index";
        Assert.assertNotEquals(expectedURL, currentURL, "Vào được trang admin");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
