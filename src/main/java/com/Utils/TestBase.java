package com.Utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {
    public static WebDriver driver;
    public static Logger logger;


    public WebDriver InitializeBrowser() throws IOException {
        FileInputStream file = new FileInputStream("TestData/cred.properties");
        Properties p = new Properties();
        p.load(file);
        String browser = p.getProperty("browser");

        if (browser.equalsIgnoreCase("chroMe")) {
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("fiReFox")) {
            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("microsoft edge")) {
            driver = new EdgeDriver();

        } else {
            System.out.println("Pls initialize browser in Test Base");
        }
        driver.manage().window().maximize();
        return driver;
    }

    /*
    ========================================================================================================
    */
    public void takeScreenShot(String TestName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("screenShots/"+TestName + ".png"));
    }
    //********************************************************************************************************************
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    //************************************************************************************************************
    public void WaitUntilvisibilityOfElement(WebElement Element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(Element));
    }
}