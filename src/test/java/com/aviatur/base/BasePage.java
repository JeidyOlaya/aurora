package com.aviatur.base;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.NonNull;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class BasePage {

        private WebDriver driver;
        protected WebDriverWait wait;
        protected Logger logs;
        private static final int TIMEOUT = 10;

        public BasePage(WebDriver driver){
                this.driver = driver;
                PageFactory.initElements(this.driver, this);
                this.logs = LogManager.getLogger(this.getClass().getName());
                this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(TIMEOUT));
        }

        /**
         * Metodo para dar click.
         */
        public void clickOn(@NonNull WebElement locator){
                try {
                        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                        if (element != null) {
                                element.click();
                        }
                } catch (Exception e) {
                        System.out.println("No se pudo hacer click en el elemento: " + locator);
                        e.printStackTrace();
                }
        }

        /**
         * Metodo para escribir texto.
         */
        public void writeText(@NonNull WebElement locator, WebElement options, String text){
                try {
                        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                        if (element != null) {
                                element.clear();
                                element.sendKeys(text);
                                wait.until(ExpectedConditions.elementToBeClickable(options));
                                element.sendKeys(Keys.ENTER);
                        }
                } catch (Exception e) {
                        System.out.println("No se pudo escribir en el elemento: " + locator);
                        e.printStackTrace();
                }
        }

        /**
         * Metodo para esperar hasta que aparezca un elemento específico
         */
        public boolean waitForElementToAppear(@NonNull WebElement element) {
                try {
                        WebElement element1 = wait.until(ExpectedConditions.visibilityOf(element));
                        return element1 != null;
                } catch (Exception e) {
                        return false;
                }
        }

        /**
         * Metodo para validar si es visible o no un elemento
         */
        public boolean isElementVisible(WebElement element) {
                try {
                        return element.isDisplayed();
                } catch (Exception e) {
                        return false;
                }
        }

        /**
         * Metodo para mensajes de validación
         */
        public String getTxtValidation(WebElement locator){
                try{
                        return locator.getText();
                }catch (Exception e){
                        return "No muestra mensaje";
                }
        }
}
