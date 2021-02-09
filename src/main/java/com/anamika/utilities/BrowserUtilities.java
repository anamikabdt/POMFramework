package com.anamika.utilities;

import com.aventstack.extentreports.Status;
import com.google.common.base.Function;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.anamika.utilities.TestBase.oExt;

public class BrowserUtilities {
    public static void launchBrowser(String browser,String baseUrl){
        //if (browser.toLowerCase().startsWith("ch")) {
        boolean bBrowserDetected = true;
        //write code for other browser
        if(browser.toLowerCase().startsWith("ch")) {
            WebDriverManager.chromedriver().setup();
            TestBase.driver = new ChromeDriver();
            TestBase.driver.get(baseUrl);
        }else{
            bBrowserDetected = false;
        }
        if(bBrowserDetected) {
            TestBase.driver.manage().window().maximize();
            TestBase.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            oExt.test.log(Status.INFO,"Browser is launched");
        }
    }
    static void waitImplicit(){
        TestBase.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    static void  closeChromeBrowser(){
        TestBase.driver.close();
        oExt.test.log(Status.INFO,"Browser is closed");
    }
    public static void enterText(WebElement webElement,String textToBeEntered,String elementName){
        if (webElement.isDisplayed()){
            webElement.sendKeys(textToBeEntered);
            oExt.test.log(Status.INFO,elementName + " is entered");
        }
        else{
            oExt.test.log(Status.INFO,elementName + " is not displayed");
        }
    }
    public static void toClick(WebElement webElement,String elementName){

        webElement.click();
         oExt.test.log(Status.INFO,elementName + " is clicked");
    }

    public static boolean validateStrEquals(String actualString,String strToComparewith,String msg) throws IOException {
        boolean bool_flag = false;
        if(actualString.equals(strToComparewith)){
            oExt.test.pass( msg + " verification successful");
            bool_flag = true;
        }
        else{
            oExt.test.fail( msg + " verification unsuccessful");
            oExt.test.addScreenCaptureFromPath(takeScreenshot());

        }
       return bool_flag;
    }
    public static String takeScreenshot() throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot)TestBase.driver;
        String addDate= new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String destinationPath=System.getProperty("user.dir")+"\\src\\Reports\\ScreenShots\\"+addDate+".PNG";
        File srcfile = screenshot.getScreenshotAs(OutputType.FILE);
        File dstfile = new File(destinationPath);
        FileUtils.copyFile(srcfile,dstfile);
        return destinationPath;
    }
   /* static boolean waitForElementVisible(WebElement element,int timeinseconds){
        try {
            WebDriverWait wait = new WebDriverWait(TestBase.driver, timeinseconds);
            wait.until(ExpectedConditions.visibilityOf(element));
            return  true;
        }
        catch (Exception e){
            return false;
        }
    }*/
    public static boolean waitForElementClickable(WebElement element,int timeinseconds){
        try {
            WebDriverWait wait = new WebDriverWait(TestBase.driver, timeinseconds);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return  true;
        }
        catch (Exception e){
            return false;
        }
    }
    /*static void closeReport(){
        extent.flush();
    }*/

    public static void iframeSwitch(WebElement iframe){
        TestBase.driver.switchTo().frame(iframe);
        oExt.test.log(Status.INFO,"Switched to iframe");
    }
    static void windowSwitch(List<WebElement> windowElement, int index){

    }
    public static void clearText(WebElement element, String elementName){
        element.clear();
        oExt.test.log(Status.INFO,elementName + " is cleared");
    }
    public static void clearAndEnterText(WebElement element, String strToBeEntered){
        if (element.isDisplayed()){
            element.clear();
            element.sendKeys(strToBeEntered);
            oExt.test.log(Status.INFO,strToBeEntered + " is entered");
        }
        else{
            oExt.test.log(Status.INFO,strToBeEntered + " is not displayed");
        }
        //element.clear();
    }
    public static void verifyStringContains(String strElement, String strContains,String  elementName) throws IOException{
        if(strElement.contains(strContains)){
            oExt.test.pass(elementName + " changes Verification succesfully");
        }
        else{
            oExt.test.fail(elementName + " changes Verification failed");
            oExt.test.addScreenCaptureFromPath(takeScreenshot());
        }
    }
    public static String SelectFromDropdownByIndex(WebElement element,int Index){

        if(element.isDisplayed()) {
            Select dropDownMenu = new Select(element);
            dropDownMenu.selectByIndex(Index);
            oExt.test.log(Status.INFO,dropDownMenu.getOptions().get(Index).getText() + " is selected" );
            return (dropDownMenu.getOptions().get(Index).getText());
        }else{
            oExt.test.log(Status.INFO," is not displayed" );
            return " ";
        }

    }
    public static void SelectFromDropdown(WebElement element,String selectFromDropdown){
        String defaultItem = "";
        if(element.isDisplayed()) {
            Select dropDownMenu = new Select(element);
            //if some thing is already selected
            if(element.isSelected()) {
                WebElement option = dropDownMenu.getFirstSelectedOption();
                defaultItem = option.getText();
            }

            if (!defaultItem.equals(selectFromDropdown) && ifOptionExists(element,selectFromDropdown)){
                dropDownMenu.selectByVisibleText(selectFromDropdown);
            }
            oExt.test.log(Status.INFO,selectFromDropdown + " is selected" );
        }else{
            oExt.test.log(Status.INFO,element.getText() + " is not displayed" );
        }
    }

    public static boolean ifOptionExists(WebElement element,String strComapre){
        boolean flag = false;
        Select dropDownMenu = new Select(element);
        List<WebElement> listOptions= dropDownMenu.getOptions();
        for (int i = 1; i < listOptions.size() ; i++) {
            // System.out.println(listOptions.get(i).getText());
            if ((listOptions.get(i).getText()).equals(strComapre)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    public static boolean ifOptionExistsInList(List<WebElement> element,String strComapre){
        boolean flag = false;
        for (int i = 1; i < element.size() ; i++) {
            element.get(i).getText();
            if ((element.get(i).getText()).equals(strComapre)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    public static void verifyifAllContains(List<WebElement> elementlist, String contains) throws IOException{
        boolean flag=true;
        for (int i = 0; i < elementlist.size(); i++) {
            if(!(elementlist.get(i).getText()).contains(contains)){
                flag=false;
                break;
            }
        }
        if(flag){
            oExt.test.pass("All in the list contains '" + contains + "'");

        } else{
            oExt.test.fail("All in the list does not contain '" + contains + "'");
            oExt.test.addScreenCaptureFromPath(takeScreenshot());
        }
    }

    public static void validateSelectBox(WebElement element,String visibleText) throws IOException{
        Boolean flag = true;
        Select dropDownMenu = new Select(element);
        List<WebElement> listOptions= dropDownMenu.getOptions();
        // System.out.println(listOptions.size());
        for (int i = 1; i < listOptions.size() ; i++) {
            // System.out.println(listOptions.get(i).getText());
            if ((listOptions.get(i).getText()).equals(visibleText)) {
                oExt.test.pass(visibleText + " is present");
                flag = false;
                break;
            }
        }
        if(flag==true){
           oExt.test.fail(visibleText + " is not present");
            oExt.test.addScreenCaptureFromPath(takeScreenshot());
        }
    }

    public static void verifyList(List<WebElement> listElem,String strComapre) throws IOException{
        Boolean flag = true;
        for (int i = 0; i <listElem.size() ; i++) {
            if((listElem.get(i).getText()).equals(strComapre)){
                oExt.test.pass(strComapre + " in the list");
                flag = false;
                break;
            }
        }
        if(flag==true){
            oExt.test.fail(strComapre + " not in the list");
            oExt.test.addScreenCaptureFromPath(takeScreenshot());
        }
    }

    public static void clickRadioButton(List<WebElement> radioOptions, int index){
        if(!radioOptions.get(index).isSelected()){
            toClick(radioOptions.get(index),radioOptions.get(index).getText());
        }
    }

   public boolean waitForElementVisible(WebDriver driver, WebElement ele, int iTimeInSeconds) throws Exception {

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(iTimeInSeconds, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

        wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                if (ele.isDisplayed()) {
                    return ele;
                } else {
                    return null;
                }
            }
        });

        return ele.isDisplayed();
    }

    public boolean isDisplayed(WebElement ele) throws Exception {
        boolean bRes_flag = false;
        try {
            if (ele.isDisplayed()) {
                bRes_flag = true;
            }
        } catch (Exception ea) {
            bRes_flag = false;
        }
        return bRes_flag;
    }

    public void ufClick(WebElement ele) throws Exception {
        ele.click();
    }

    public void ufSendKeys(WebElement ele, String keysToSend) throws Exception {
        ele.sendKeys(keysToSend);
    }

    static void ufClearText(WebElement ele, String elementName){
        ele.clear();
    }

}
