package com.anamika.accounts;
import com.anamika.listners.ExtentReport;
import com.anamika.utilities.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static com.anamika.utilities.BrowserUtilities.*;
import static com.anamika.utilities.TestBase.*;

@Test
public class poAccounts {
    WebDriver driver;
    public poAccounts(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath="//*[@id='AllTab_Tab']/a/img")
    WebElement sf_allTabs;
    @FindBy(xpath="//*[@id='bodyCell']/div[3]/div[2]/table/tbody/tr[1]/td[1]/a")
    WebElement sf_accountsLink;
    @FindBy(xpath="//*[@id='hotlist']/table/tbody/tr/td[2]/input")
    WebElement sf_newAccount;
    @FindBy(id="acc2")
    WebElement sf_newAccount_done;
    @FindBy(xpath="//*[@id='topButtonRow']/input[1]")
    WebElement sf_saveAccount;
    @FindBy(id="acc2_ileinner")
    WebElement sf_newAccName;







    String td_username = oCommUtil.readPropertyFile("testdata", "valid.username");
    String td_password = oCommUtil.readPropertyFile("testdata","valid.password");
    String td_AccountName = oCommUtil.readPropertyFile("testdata","newAccountname");

    public boolean TC10CreatAccount() throws Exception {
        boolean bRes_Flag = false;
        oExt.test = oExt.extent.createTest("TC10CreatAccount");
        //loginSalesforce("tbd@mycompany.com","anamika0125");
        Thread.sleep(6000);
        oBroUti.toClick(sf_allTabs,"All tabs");
        oBroUti.toClick(sf_accountsLink,"Accounts link");
        oBroUti.toClick(sf_newAccount,"New account");
        String newAccountname = "testAccount";
        oBroUti.enterText(sf_newAccount_done,"testAccount","New Account Creation");
        oBroUti.toClick(sf_saveAccount,"Save account");
        if(oBroUti.validateStrEquals(sf_newAccName.getText(),newAccountname,"new account name")){
            bRes_Flag = true;
            oExt.test.pass("TC10CreatAccount Testcase passed");
        }else {
            oExt.test.fail("TC10CreatAccount Testcase failed");
        }
        return bRes_Flag;
    }

    public void loginSalesforce(String username, String password) throws Exception{
        boolean bRes_Flag=false;
        oBroUti.enterText(TestBase.driver.findElement(By.id("username")),username,"username");
        oBroUti.enterText( TestBase.driver.findElement(By.id("password")),password,"password");
        toClick(TestBase.driver.findElement(By.id("Login")), "Login");

    }
}
