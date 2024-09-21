package rahulShettyAcademy;

import POM.dashBoard_pagefactory;
import POM.payment_pagefactory;
import POM.signIn_PageFactory;
import com.Utils.TestBase;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class Signin_POM extends TestBase {

    @BeforeTest
    public void initializeBrowser() throws IOException {
        TestBase t = new TestBase();
        t.InitializeBrowser();
    }
    @Test
    public void signIn() throws IOException, InterruptedException {
        //Logging
        logger= Logger.getLogger("Signin_POM");
        PropertyConfigurator.configure("log4j.properties");

        signIn_PageFactory s = new signIn_PageFactory(driver);
        s.logIn_validCred();
        logger.info("sign in successfully completed");
        String getText = s.AutomationText().getText();
        Assert.assertEquals(getText, "Automation Practice");

        dashBoard_pagefactory d = new dashBoard_pagefactory(driver);
        d.AddToCart();
        logger.info("Item added to Cart");

        payment_pagefactory pf = new payment_pagefactory(driver);
        pf.payment();
        logger.info("Payment done");
        String toasterMessage = pf.ToasterMessage().getText();
        Assert.assertEquals(toasterMessage, "Logout Successfully");
        logger.info("signout done");
    }

    @AfterTest
    public void closeBrowser(){
        TestBase t=new TestBase();
        t.tearDown();
        logger.info("Browser closed done");
    }
}