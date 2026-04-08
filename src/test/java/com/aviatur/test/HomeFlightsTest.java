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
                {"Bogotá", "BOG", "Cali", "CLO"}
        };
    }

    @DataProvider(name = "dates")
    public Object[][] datesDataProvider() {
        return new Object[][] {
                {5},
                {10},
        };
    }

    @DataProvider(name = "passengers")
    public Object[][] passengersDataProvider() {
        return new Object[][] {
                {1, 0, 0},  // 1 adulto, 0 niños, 0 infantes
                {2, 1, 0},  // 2 adultos, 1 niño, 0 infantes
        };
    }

    @DataProvider(name = "completeFlightData")
    public Object[][] completeFlightDataProvider() {
        return new Object[][] {
                // {city, iataOrigen, destinCity, iataDestino, dayDate, adults, children, infants}
                {"Bogotá", "BOG", "Cali", "CLO", 5, 1, 0, 0},
//                {"Bogotá", "BOG", "Cali", "CLO", 10, 2, 1, 0},
        };
    }

    @Test(dataProvider = "completeFlightData")
    public void initialTest(String city, String iataOrigen, String destinCity, String iataDestino, 
                            int dayDate, int adults, int children, int infants){
        Home home= new Home(driver);
        Dispo dispo = new Dispo(driver);

        home.clickOnOrigin();
        home.inputOrigin(city, iataOrigen);
        home.clickOnDestin();
        home.inputDestin(destinCity, iataDestino);
        home.selectFirstDate(dayDate);
        // home.selectPassengers(adults, children, infants);
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
