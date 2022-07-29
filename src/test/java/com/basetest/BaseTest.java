package com.basetest;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    //child classes can use this property
    protected WebDriver driver;

    @BeforeTest
    public void setupDriver(ITestContext ctx) throws MalformedURLException {
        //To run in remote hub. You need to set host url to connect,
        // and declare browser you are using
        //browser -> chrome/firefox
        //HUB_HOST -> localhost/10.0.1.3/hostname
        String host = "localhost";

        MutableCapabilities dc;

        //default browser

        if (System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("firefox")) {
            dc = new FirefoxOptions();
        } else {
            dc = new ChromeOptions();

        }

        if(System.getProperty("HUB_HOST") != null){
            host = System.getProperty("HUB_HOST");
        }

        String testName = ctx.getCurrentXmlTest().getName();

        String completeURL = "http://" + host + ":4444/wd/hub";
        System.out.println(completeURL);
        dc.setCapability("name", testName);
        this.driver = new RemoteWebDriver(new URL(completeURL), dc);

    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }
}
