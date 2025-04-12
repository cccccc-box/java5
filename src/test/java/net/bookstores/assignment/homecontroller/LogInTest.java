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

public class LogInTest {
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

    @Test // login acc admin
    public void testDN1() {
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
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "http://localhost:8080/admin/user/index";
        Assert.assertEquals(expectedURL, currentURL, "Đăng nhập thành công");
    }

    @Test // login acc custommer
    public void testDN2() {
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
        driver.get("http://localhost:8080/account/index");
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "http://localhost:8080/account/index";
        Assert.assertEquals(expectedURL, currentURL, "Đăng nhập thành công");
    }

    @Test // login null email and null password
    public void testDN3() {
        driver.get("http://localhost:8080/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]")));

        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));

        btnLogin.click();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.getStackTrace();
        }
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "http://localhost:8080/account/index";
        Assert.assertNotEquals(expectedURL, currentURL, "Không đăng nhập được");
    }

    @Test // login null password
    public void testDN4() {
        driver.get("http://localhost:8080/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]")));

        WebElement email = driver.findElement(By.name("email"));
        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));
        email.sendKeys("test@gmail.com");
        btnLogin.click();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.getStackTrace();
        }
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "http://localhost:8080/account/index";
        Assert.assertNotEquals(expectedURL, currentURL, "Không đăng nhập được");
    }

    @Test // login null email
    public void testDN5() {
        driver.get("http://localhost:8080/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]")));

        WebElement password = driver.findElement(By.name("password"));
        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));
        password.sendKeys("12345678");
        btnLogin.click();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.getStackTrace();
        }
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "http://localhost:8080/account/index";
        Assert.assertNotEquals(expectedURL, currentURL, "Không đăng nhập được");
    }

    @Test // email và password không tồn tại
    public void testDN6() {
        driver.get("http://localhost:8080/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]")));

        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));
        email.sendKeys("test@gmail.com.vn");
        password.sendKeys("12345678");
        btnLogin.click();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.getStackTrace();
        }
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "http://localhost:8080/account/index";
        Assert.assertNotEquals(expectedURL, currentURL, "Không đăng nhập được");
    }

    @Test // login acc admin sai password
    public void testDN7() {
        driver.get("http://localhost:8080/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]")));
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));

        email.sendKeys("admin@admin.com");
        password.sendKeys("saimatkhau");
        btnLogin.click();

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.getStackTrace();
        }
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "http://localhost:8080/login";
        Assert.assertEquals(expectedURL, currentURL, "Đăng nhập thành công");
    }

    @Test // login acc khách hàng sai mật khẩu
    public void testDN8() {
        driver.get("http://localhost:8080/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]")));
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));

        email.sendKeys("tranthib@example.com");
        password.sendKeys("saimatkhau");
        btnLogin.click();

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.getStackTrace();
        }
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "http://localhost:8080/login";
        Assert.assertEquals(expectedURL, currentURL, "Đăng nhập thành công");
    }

    @Test // login acc khách hàng bị khóa
    public void testDN9() {
        driver.get("http://localhost:8080/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]")));
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));

        email.sendKeys("nguyenvana@example.com");
        password.sendKeys("password123");
        btnLogin.click();

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.getStackTrace();
        }
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "http://localhost:8080/login2";
        Assert.assertEquals(expectedURL, currentURL, "Đăng nhập không thành công");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
