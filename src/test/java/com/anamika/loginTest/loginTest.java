package com.anamika.loginTest;

import com.anamika.listners.ExtentReport;
import com.anamika.login.poLogin;
import com.anamika.utilities.TestBase;
import com.anamika.utilities.CommonUtilities;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import static com.anamika.utilities.TestBase.oCommUtil;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.anamika.listners.ExtentReport.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class loginTest extends TestBase{
    poLogin login ;
    @BeforeTest
   public void settingUpEnvironment() throws Exception {
      login = new poLogin(TestBase.driver);
    }
    @AfterTest
    public void close() throws Exception {
        TestBase.driver.quit();
    }
    @Test
    public void loginErrorMessage1() throws Exception {
       login.loginErrorMessage1();
    }
    @Test
    public void checkRememberMe3() throws IOException, InterruptedException {
        login.checkRememberMe3();
    }
    /*@Test(groups="functional")
    public void forgotPassword4A() throws IOException {
        login.forgotPassword4A();
    }*/
   /* @Test(groups="functional")
    public void forgotPassword4B() throws IOException {
        login.forgotPassword4B();
        SoftAssert sa = new SoftAssert();
        sa.assertAll();
    }*/

}
