package com.anamika.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonUtilities {
    //read from properties file
    public String readPropertyFile(String fileName, String keyName) throws IOException {
        //read from property file
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\TestData\\" + fileName + ".properties");
        properties.load(fis);
        return properties.getProperty(keyName);
    }
    public void loadPropertyFile(String fileName) throws IOException {
        //read from property file
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\TestData\\" + fileName + ".properties");
        properties.load(fis);
    }
   /* public String readJasonFile(String fileName, String keyName) throws IOException {
        //read from property file
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\" + fileName + ".json");
        properties.load(fis);
        return properties.getProperty(keyName);
    }*/
    /* public String readExcelFile(String fileName, String keyName) throws IOException {
        //read from property file
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\" + fileName + ".json");
        properties.load(fis);
        return properties.getProperty(keyName);
    }*/
}
