package POM;

import com.Utils.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class signIn_PageFactory extends TestBase {

    public signIn_PageFactory(WebDriver driver) throws FileNotFoundException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement userName_Locator;
    @FindBy(xpath = "//input[@placeholder=\"enter your passsword\"]")
    WebElement password_Locator;
    @FindBy(xpath = "//input[@name=\"login\"]")
    WebElement LoginButton_Locator;
    @FindBy(xpath = "//p[text()=\"Automation Practice\"]")
    WebElement AutomationText_L;

    public WebElement userName() {
        return userName_Locator;
    }

    public WebElement Password() {
        return password_Locator;
    }

    public WebElement LogIn() {
        return LoginButton_Locator;
    }

    public WebElement AutomationText() {
        return AutomationText_L;
    }

    public void logIn_validCred() throws IOException {
        FileInputStream file = new FileInputStream("TestData/cred.properties");
        Properties p = new Properties();
        p.load(file);
        String url = p.getProperty("url");
        String userName = p.getProperty("userName");
        String password = p.getProperty("password");

        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(userName_Locator));
        userName_Locator.sendKeys(userName);
        password_Locator.sendKeys(password);
        LoginButton_Locator.click();
        wait.until(ExpectedConditions.visibilityOf(AutomationText_L));
        if (AutomationText_L.isDisplayed()) {
            System.out.println("Successfully navigated to signIn page");
        } else {
            System.out.println("erro occured while signing in!!!");
        }
    }
}