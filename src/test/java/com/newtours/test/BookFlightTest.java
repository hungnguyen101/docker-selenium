package com.newtours.test;

import com.basetest.BaseTest;
import com.newtours.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookFlightTest extends BaseTest {
    private RegistrationPage registrationPage;
    private RegistrationConfirmationPage registrationConfirmationPage;
    private FlightDetailPage flightDetailPage;
    private FindFlightPage findFlightPage;
    private BillingAddressPage billingAddressPage;
    private OrderConfirmationPage orderConfirmationPage;

    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"}) //extract value from xml file
    public void setupParameter(String noOfPassengers, String expectedPrice){
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
        //set path
        this.registrationPage = new RegistrationPage(driver);
        this.registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        this.flightDetailPage = new FlightDetailPage(driver);
        this.findFlightPage = new FindFlightPage(driver);
        this.billingAddressPage = new BillingAddressPage(driver);
        this.orderConfirmationPage = new OrderConfirmationPage(driver);
    }

    @Test
    public void goTo(){
        registrationPage.goTo();
    }

    @Test(dependsOnMethods = "goTo")
    public void registrationPage(){
        registrationPage.enterUserDetail("selenium", "docker");
        registrationPage.enterUserCredentials("selenium", "docker");
        registrationPage.submit();
    }

    @Test(dependsOnMethods="registrationPage")
    public void registrationConfirmationPage(){
        registrationConfirmationPage.goToFlightDetailPage();
    }

    @Test(dependsOnMethods="registrationConfirmationPage")
    public void flightDetail(){
        flightDetailPage.selectPassenger(noOfPassengers);
        flightDetailPage.goToFindFlightPage();
    }

    @Test(dependsOnMethods="flightDetail")
    public void findFlightPage(){
        findFlightPage.submitFindFlightPage();
    }

    @Test(dependsOnMethods="findFlightPage")
    public void billingAddressPage(){
        billingAddressPage.enterBillingAddress("add 1", "add 2", "hcm", "phu nhuan", "5000");
        billingAddressPage.submit();
    }

    @Test(dependsOnMethods="billingAddressPage")
    public void orderConfirmationPageTest(){
        String actualPrice = orderConfirmationPage.getPrice();
        Assert.assertEquals(actualPrice, expectedPrice);
    }

}
