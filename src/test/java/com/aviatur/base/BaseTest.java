package com.aviatur.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    private static final String URL = "https://aviatursym.com/";
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger logger = LogManager.getLogger(String.valueOf(BaseTest.class));

    /*
     * Configuro WebDriver para Chrome
     */

    @BeforeSuite
    public void setupWebDriverManager(){
        logger.info("WebDriverManager configurado para Chrome.");
    }

    @BeforeMethod
    public void setup(){
        logger.info("Inicia la configuración de la prueba...");
        logger.info("Configuración del entorno de prueba.");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(URL);
        logger.info("Navegando en: https://aviatursym.com/");
    }

    /*
     * Cierra el navegador después de cada metodo de prueba.
     */
//    @AfterMethod
//    public void teardown(){
//        if (driver != null){
//            driver.quit();
//            logger.info("Navegador cerrado.");
//        }
//        logger.info("Configuración del test completado.");
//    }

}
