package POM;

import com.Utils.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class dashBoard_pagefactory extends TestBase {

    public dashBoard_pagefactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//section[@id=\"products\"]/div/div[2]/div[1]/div/div/button[2]")
    WebElement AddToCartButtom_L;
    @FindBy(xpath = "//button[@routerlink=\"/dashboard/cart\"]/child::i/..")
    WebElement Cart_L;
    @FindBy(xpath = "//button[text()=\"Checkout\"]")
    WebElement CheckoutButton_L;
    @FindBy(id = "toast-container")
    WebElement ToasterMessage_L;

    public WebElement AddtoCart() {
        return AddToCartButtom_L;
    }

    public WebElement Cart() {
        return Cart_L;
    }

    public WebElement checkout() {
        return CheckoutButton_L;
    }
    public WebElement ToasterMessage(){
        return ToasterMessage_L;
    }

    public void AddToCart() throws InterruptedException {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOf(ToasterMessage_L));
        wait.until(ExpectedConditions.elementToBeClickable(AddToCartButtom_L));

        AddToCartButtom_L.click();
        wait.until(ExpectedConditions.visibilityOf(ToasterMessage_L));
        //js.executeScript("arguments[0].click();",Cart_L);
        Cart_L.click();
        wait.until(ExpectedConditions.visibilityOf(CheckoutButton_L));
        CheckoutButton_L.click();
    }
}
