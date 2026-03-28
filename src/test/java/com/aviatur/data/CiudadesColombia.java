package com.aviatur.data;

import org.testng.annotations.DataProvider;

public class CiudadesColombia {
    /**
     * @return
     */
@DataProvider(name = "ciudadesColombia")
public Object [][] ciudadesColombia(){
    return new Object[][] {
        {"Bogotá, Colombia", "Medellin, Colombia"},
        // {"Bogotá, Colombia", "Cali, Colombia"},
        // {"Bogotá, Colombia", "Cartagena, Colombia"},
        // {"Bogotá, Colombia", "Barranquilla, Colombia"},
        // {"Bogotá, Colombia", "Santa Marta, Colombia"},
        // {"Bogotá, Colombia", "Pereira, Colombia"},
        // {"Bogotá, Colombia", "Manizales, Colombia"},
        // {"Bogotá, Colombia", "Armenia, Colombia"},
        // {"Bogotá, Colombia", "Bucaramanga, Colombia"}
    };
}
}
