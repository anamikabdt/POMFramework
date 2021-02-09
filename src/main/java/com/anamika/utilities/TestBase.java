package com.anamika.utilities;

import com.anamika.listners.ExtentReport;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;


public class TestBase {
    public static CommonUtilities oCommUtil = new CommonUtilities();
    public static BrowserUtilities oBroUti = new BrowserUtilities();
    public static ExtentReport oExt = new ExtentReport();
    //public static Constants oCons = new Constants();
    public static WebDriver driver;
 @BeforeSuite
    public void TriggerDependencies() throws Exception {
         String sEnvironment;
         String sBaseUrl = "www.google.com";
         String sAutomation;
         String sBrowser;
        oCommUtil.loadPropertyFile("testdata");
        sEnvironment = (oCommUtil.readPropertyFile("config","Environment")).replaceAll("\"","");
        sAutomation = (oCommUtil.readPropertyFile("config","Automation")).replaceAll("\"","");
        sBrowser = (oCommUtil.readPropertyFile("config","Browser")).replaceAll("\"","");
        System.out.println(sEnvironment+ "-->" + sAutomation + "--->"+sBrowser);
        //write code for other environment
        if(sEnvironment.equalsIgnoreCase("qa")) {
            sBaseUrl = oCommUtil.readPropertyFile("urlDetails","qaBaseUrl");
            //sBaseUrl = System.getProperty("qaBaseUrl");
        }
        if(sAutomation.equalsIgnoreCase("web")) {
            oExt.test = oExt.extent.createTest("Launch Browser");
            oBroUti.launchBrowser(sBrowser,sBaseUrl);
        }
    }

@AfterSuite
    public void ShuttingDownAllDependencies() throws Exception {
        //if (System.getProperty("AutomationRunning").equalsIgnoreCase(Constants.sAutomationWeb.toLowerCase())) {
            driver.quit();
    oExt.extent.flush();
        //}
    }
}
