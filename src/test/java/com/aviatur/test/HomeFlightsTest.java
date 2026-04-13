package com.aviatur.test;

import com.aviatur.base.BaseTest;
import com.aviatur.pages.vuelos.Dispo;
import com.aviatur.pages.vuelos.Home;
import com.aviatur.data.CiudadesColombia;

import junit.framework.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertEquals;

public class HomeFlightsTest extends BaseTest {


    @DataProvider(name = "flightData")
    public Object[][] flightDataProvider() {
        return new Object[][] {
                {"Bogotá", "BOG", "Cali", "CLO", "01-Febrero-2027", "05-Abril-2027", 1, 3, 2}
//            {"Bogotá",  "BOG", "Medellín", "MED"},
//            {"Bogotá", "BOG", "Bogotá", "BOG"}
        };
    }

//    @DataProvider(name = "dates")
//    public Object[][] datesDataProvider() {
//        return new Object[][] {
//                {5},  // Example: select the 5th day of the month
//                {10}, // Example: select the 10th day of the month
//        };
//    }

    @Test(dataProvider = "flightData")
    public void happyPathDetail(String city, String iataOrigen, String destinCity, String iataDestino, String leaveDate,
                                String returnDate, int nAdt, int nChd, int nInf ){
        Home home= new Home(driver);
        Dispo dispo = new Dispo(driver);

        home.clickOnOrigin();
        home.inputOrigin(city, iataOrigen);
        home.clickOnDestin();
        home.inputDestin(destinCity, iataDestino);
        home.selectDateLeave(leaveDate);
        home.selectDateReturn(returnDate);
        home.addPassengers(nAdt,nChd,nInf);
//        home.clickOnBtnSearch();
//        dispo.getCardsDispo();

    }

//    @Test(dataProvider = "flightData")
    public void msgValidationTest(String city, String iataOrigen, String destinCity, String iataDestino ){
        Home home= new Home(driver);

        home.clickOnOrigin();
        home.inputOrigin(city, iataOrigen);
        home.clickOnDestin();
        home.inputDestin(destinCity, iataDestino);
        home.clickOnBtnSearch();

        String mensaje = home.getSameOriginValidationMessage();
        Assert.assertEquals("Origen y destino no pueden ser iguales", mensaje);
    }


}
