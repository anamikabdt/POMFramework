package com.anamika.login;

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

import static com.anamika.utilities.BrowserUtilities.takeScreenshot;
import static com.anamika.utilities.TestBase.*;

@Test
public class poLogin {
    WebDriver driver;
    public poLogin(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="username")
    WebElement sf_username;
    @FindBy(id="password")
    WebElement sf_password;
    @FindBy(id="Login")
    WebElement sf_button_login;
    @FindBy(xpath="//div[@id='error']")
    WebElement errorXpath;
    @FindBy(xpath="//*[@id='login_form']/div[3]/label")
    WebElement sf_checkboxRemembermexpath;
    @FindBy(id="home_Tab")
    WebElement sf_button_Home;
    @FindBy(xpath="//span[@id='userNavLabel']")
    WebElement sf_user_menu;
    @FindBy(xpath="//*[@id='userNav-menuItems']/a[5]")
    WebElement sf_usermenu_logout;
    @FindBy(xpath="/html/body/div[1]/div[1]/div/div/div[2]/div[3]/div/a[1]")
    WebElement sf_forgotPassword_link;
    @FindBy(xpath="//*[@id='un']")
    WebElement sf_forgotPassword_email;
    @FindBy(id="continue")
    WebElement sf_forgotPassword_cont_btn;
    @FindBy(xpath="//div[@id='error']")
    WebElement sf_Error_msg;



    String td_username = oCommUtil.readPropertyFile("testdata", "valid.username");
    String td_password = oCommUtil.readPropertyFile("testdata","valid.password");
    String td_invalidUserName = "invalidUser";
    String td_invalidPasswd = "jk";
    String td_invalidEntry_err_msg = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
    public boolean loginToSf() throws Exception{
        boolean bRes_Flag=false;
        oBroUti.waitForElementVisible(driver, sf_username, 10);
        oBroUti.ufSendKeys(sf_username, td_username);
        oBroUti.ufSendKeys(sf_password, td_password);
        oBroUti.ufClick(sf_button_login);
        oBroUti.waitForElementVisible(driver, sf_button_Home, 5);
        if(oBroUti.isDisplayed(sf_button_Home))
            bRes_Flag=true;
        return bRes_Flag;
    }

    /*public static void loginSalesforce(String username, String password) throws Exception{
        boolean bRes_Flag=false;
        oBroUti.enterText(TestBase.driver.findElement(By.id("username")),username,"username");
        oBroUti.enterText( TestBase.driver.findElement(By.id("password")),password,"password");
        oBroUti.toClick(TestBase.driver.findElement(By.id("Login")), "Login");

    }*/
    public boolean loginErrorMessage1() throws Exception {
        boolean bRes_Flag=false;

        oExt.test = ExtentReport.extent.createTest("loginErrorMessage");
        oBroUti.enterText(sf_username,td_username,"username");
        oBroUti.clearText(sf_password,"password");
        oBroUti.toClick(sf_button_login,"login");
        if(oBroUti.validateStrEquals(errorXpath.getText(),"Please enter your password.",
                "Please enter your password.")){
            bRes_Flag = true;
        }
        return bRes_Flag;
    }
    public boolean checkRememberMe3() throws IOException, InterruptedException {
        boolean bRes_Flag = false;
        oExt.test = oExt.extent.createTest("checkRememberMe3");
        oBroUti.enterText(sf_username,td_username,"username");
        oBroUti.enterText(sf_password,td_password,"password");
        if(!(sf_checkboxRemembermexpath).isSelected()){
            (sf_checkboxRemembermexpath).click();
        }
        oBroUti.toClick(sf_button_login, "Login");
        Thread.sleep(4000);
        sf_user_menu.click();
        Thread.sleep(4000);
        sf_usermenu_logout.click();
        if((sf_username.getText().equals(td_username))
                && (sf_checkboxRemembermexpath).isSelected()){
            bRes_Flag = true;
            oExt.test.pass("Remember Me Testcase passed");
        }else{
            oExt.test.fail("Remember Me Testcase failed");
            oExt.test.addScreenCaptureFromPath(takeScreenshot());
        }
        return bRes_Flag;
    }
    public boolean forgotPassword4A() throws IOException {
        boolean bRes_Flag = false;
        oExt.test = oExt.extent.createTest("forgotPassword4A");
        sf_forgotPassword_link.click();
        sf_forgotPassword_email.sendKeys("td_username");
        sf_forgotPassword_cont_btn.click();
        oExt.test.pass("forgotPassword4A passed");
        return bRes_Flag;
    }

    public boolean forgotPassword4B() throws IOException {
        boolean bRes_Flag = false;
        oExt.test = oExt.extent.createTest("forgotPassword4B");
        sf_username.sendKeys(td_invalidUserName);
        sf_password.sendKeys(td_invalidPasswd);
        sf_button_login.click();
        if (sf_Error_msg.getText().equals(td_invalidEntry_err_msg)) {
            bRes_Flag = true;
            oExt.test.pass("TestCase forgotPassword4B Passed");
        } else {
            oExt.test.fail("estCase forgotPassword4B Passed");
            oExt.test.addScreenCaptureFromPath(takeScreenshot());
        }
        return bRes_Flag;
    }

    public WebElement userName(){
        return sf_username;
    }
    public WebElement password(){
        return sf_password;
    }
    public WebElement loginButton(){
        return sf_button_login;
    }
    public WebElement errorXpath(){
        return errorXpath;
    }
    public WebElement rememberMeXpath(){
        return sf_checkboxRemembermexpath;
    }

}
