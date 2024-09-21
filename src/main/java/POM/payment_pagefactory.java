package POM;

import com.Utils.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class payment_pagefactory extends TestBase {

    public payment_pagefactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//input[@placeholder=\"Select Country\"]")
    WebElement selectCountry_L;
    @FindBy(xpath = "//span[text()=\" India\"]")
    WebElement India_L;
    @FindBy(xpath = "//a[text()=\"Place Order \"]/child::i")
    WebElement placeOrder_L;
    @FindBy(id = "toast-container")
    WebElement toasterMessage_L;
    @FindBy(xpath = "//*[text()=\" Sign Out \"]")
    WebElement signOut_L;

    public WebElement getSelectCountry() {
        return selectCountry_L;
    }

    public WebElement WebElementIndia() {
        return India_L;
    }

    public WebElement placeOrder() {
        return placeOrder_L;
    }
    public WebElement ToasterMessage(){
        return toasterMessage_L;
    }

    public WebDriver payment() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        FileInputStream file = new FileInputStream("TestData/cred.properties");
        Properties p = new Properties();
        p.load(file);
        String country = p.getProperty("country");

        wait.until(ExpectedConditions.elementToBeClickable(selectCountry_L));

        selectCountry_L.sendKeys(country);
        wait.until(ExpectedConditions.visibilityOf(India_L));
        India_L.click();
        Actions a=new Actions(driver);
        a.scrollToElement(placeOrder_L);
        a.doubleClick(placeOrder_L).build().perform();
        wait.until(ExpectedConditions.visibilityOf(toasterMessage_L));
        wait.until(ExpectedConditions.invisibilityOf(toasterMessage_L));
        js.executeScript("arguments[0].click();", signOut_L);
        wait.until(ExpectedConditions.visibilityOf(toasterMessage_L));

        return driver;
    }

}
