package com.aviatur.test;

import com.aviatur.base.BaseTest;
import com.aviatur.pages.vuelos.Home;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomeFlightsTest extends BaseTest {


    @DataProvider(name = "flightData")
    public Object[][] flightDataProvider() {
        return new Object[][] {
            {"Bogotá, Colombia", "Medellín, Colombia"}
        };
    }

    @Test(dataProvider = "flightData")
    public void initialTest(String city, String destinCity){
        Home home= new Home(driver);

        home.clickOnOrigin();
        home.inputOrigin(city);
        home.clickOnDestin();
        home.inputDestin(destinCity);
    }


}
