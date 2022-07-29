package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FindFlightPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name="reserveFlights")
    private WebElement submit;

    public FindFlightPage(final WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void submitFindFlightPage(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.submit));
        this.submit.click();
    }

}
