package com.anamika.listners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.anamika.utilities.TestBase;

public class ExtentReport{
    public static ExtentReports extent;
    public static ExtentHtmlReporter extentHtmlReporter;
    public static ExtentTest test=null;
    static{
        String adddate = new SimpleDateFormat("yyyyMMDDHHss").format(new Date());
        extent = new ExtentReports();
        extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\src\\Reports\\ExtentReports_" + adddate + ".html");
        extent.attachReporter(extentHtmlReporter);
    }
}
