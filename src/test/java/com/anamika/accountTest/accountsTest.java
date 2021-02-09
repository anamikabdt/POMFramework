package com.anamika.accountTest;

import com.anamika.accounts.poAccounts;
import com.anamika.login.poLogin;
import com.anamika.utilities.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.anamika.utilities.TestBase.*;
import static com.anamika.utilities.TestBase.oCommUtil;

public class accountsTest extends TestBase{
    poAccounts accounts;


    @BeforeMethod(alwaysRun = true)
    public void launchApplication() throws Exception {
        accounts = new poAccounts(TestBase.driver);
        oExt.test = oExt.extent.createTest("Launch Browser");
        /*TestBase tb = new TestBase();
        tb.TriggerDependencies();*/
        accounts.loginSalesforce("tbd@mycompany.com","tushib0125");
    }
    @AfterMethod(alwaysRun = true)
    public void CloseApplication() throws IOException {
        TestBase.driver.quit();
    }
    @Test
    public void TC10CreatAccount() throws Exception {
        accounts.TC10CreatAccount();
    }
}
