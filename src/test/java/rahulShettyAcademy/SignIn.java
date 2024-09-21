package rahulShettyAcademy;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class SignIn {
    public static void main(String[] args) throws InterruptedException, IOException {

        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        FileInputStream file = new FileInputStream("TestData/cred.properties");
        Properties p=new Properties();
        p.load(file);
        String url=p.getProperty("url");
        String userName=p.getProperty("userName");
        String password=p.getProperty("password");

        driver.get(url);

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("userEmail")));

        driver.findElement(By.id("userEmail")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@placeholder=\"enter your passsword\"]")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name=\"login\"]")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='ZARA COAT 3']/following::button[2]")));
        driver.findElement(By.xpath("//b[text()='ZARA COAT 3']/following::button[2]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()=\"1\"]")));
        driver.findElement(By.xpath("//label[text()=\"1\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Checkout\"]")));
        driver.findElement(By.xpath("//button[text()=\"Checkout\"]")).click();
        WebElement country=driver.findElement(By.xpath("//input[@placeholder=\"Select Country\"]"));
        country.sendKeys("Ind");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"ng-star-inserted\"]/following::button")));
        driver.findElement(By.xpath("//span[@class=\"ng-star-inserted\"]/following::button")).click();
        WebElement placeorder=driver.findElement(By.xpath("//a[text()=\"Place Order \"]/child::i"));
        Actions a=new Actions(driver);
        a.scrollToElement(placeorder);
        a.doubleClick(placeorder).build().perform();

        Thread.sleep(2000);
        WebElement TosterMessage=driver.findElement(By.xpath("//div[@id=\"toast-container\"]"));
        WebElement element = driver.findElement(By.xpath("//i[@class='fa fa-sign-out']/parent::button"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"toast-container\"]")));
        String message=TosterMessage.getText();
        System.out.println(TosterMessage.isDisplayed()+" Toaster message is >>"+ message);
        Assert.assertEquals(message, "Logout Successfully");
        driver.quit();
        }
    }