package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BillingAddressPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name="q53_address[addr_line1]")
    private WebElement address1Txt;

    @FindBy(name="q53_address[addr_line2]")
    private WebElement address2Txt;

    @FindBy(name="q53_address[city]")
    private WebElement cityTxt;

    @FindBy(name="q53_address[state]")
    private WebElement stateTxt;

    @FindBy(name="q53_address[postal]")
    private WebElement postalTxt;

    @FindBy(name="buyFlights")
    private WebElement submit;

    public BillingAddressPage(final WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void enterBillingAddress(String add1, String add2, String city, String state, String zipcode){
        this.wait.until(ExpectedConditions.visibilityOf(this.address1Txt));
        this.address1Txt.sendKeys(add1);
        this.address2Txt.sendKeys(add2);
        this.cityTxt.sendKeys(city);
        this.stateTxt.sendKeys(state);
        this.postalTxt.sendKeys(zipcode);
    }

    public void submit(){
        this.submit.click();
    }




}
