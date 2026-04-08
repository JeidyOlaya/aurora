package com.aviatur.base;

import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.NonNull;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

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
                        try {
                                if (driver instanceof JavascriptExecutor) {
                                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", locator);
                                        return;
                                }
                        } catch (Exception ignored) {
                        }
                        System.out.println("No se pudo hacer click en el elemento: " + locator);
                        e.printStackTrace();
                }
        }

        /**
         * Metodo para escribir texto y pulsar Enter.
         */
        public void writeText(WebElement locator, String text){
                try {
                        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                        if (element != null) {
                                element.clear();
                                element.sendKeys(text);
                                element.sendKeys(Keys.ENTER);
                        }
                } catch (Exception e) {
                        System.out.println("No se pudo escribir en el elemento: " + locator);
                        e.printStackTrace();
                }
        }

        /**
         * Metodo para escribir texto sin pulsar Enter (para autocompletados).
         */
        public void typeText(WebElement locator, String text){
                try {
                        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                        if (element != null) {
                                element.clear();
                                element.sendKeys(text);
                        }
                } catch (Exception e) {
                        System.out.println("No se pudo escribir en el elemento: " + locator);
                        e.printStackTrace();
                }
        }

        /**
         * Metodo para esperar hasta que aparezca un elemento específico
         */
        public boolean waitForElementToAppear(WebElement element) {
                try {
                        WebElement element1 = wait.until(ExpectedConditions.visibilityOf(element));
                        return element1 != null;
                } catch (Exception e) {
                        return false;
                }
        }

        /**
         * Metodo para seleccionar una opcion de un dropdown
         */
        public void selectFromDropdown(WebElement dropdownTrigger, List<WebElement> options, String optionText) {
                try {
                        // Hacer click en el dropdown para abrirlo
                        clickOn(dropdownTrigger);
                        // Esperar a que aparezcan las opciones
                        wait.until(ExpectedConditions.visibilityOfAllElements(options));
                        // Buscar y seleccionar la opción por texto
                        for (WebElement option : options) {
                                if (option.getText().trim().equals(optionText)) {
                                        clickOn(option);
                                        break;
                                }
                        }
                } catch (Exception e) {
                        System.out.println("No se pudo seleccionar la opción '" + optionText + "' del dropdown: " + dropdownTrigger);
                        e.printStackTrace();
                }
        }

        public void selectFromAutocomplete(WebElement container, By optionLocator, String optionText) {
                try {
                        wait.until(ExpectedConditions.visibilityOf(container));
                        List<WebElement> options = container.findElements(optionLocator);
                        int count = options.size();
                        System.out.println(count + " opciones encontradas");
                        String needle = normalizeText(optionText);

                        WebElement exactOption = null;
                        for (WebElement option : options) {
                                String name = normalizeText(option.getAttribute("data-name"));
                                if (name.isEmpty()) {
                                        name = normalizeText(option.getText());
                                }
                                if (name.equals(needle)) {
                                        exactOption = option;
                                        break;
                                }
                        }

                        if (exactOption != null) {
                                System.out.println("Buscando opción exacta: " + optionText);
                                wait.until(ExpectedConditions.visibilityOf(exactOption));
                                System.out.println("Opción encontrada: " + optionText);
                                clickOn(exactOption);
                                System.out.println("Opción seleccionada: " + optionText);
                                return;
                        }

                        System.out.println("No se encontró opción exacta: " + optionText + ", buscando la mejor coincidencia...");
                        WebElement bestOption = null;
                        int bestScore = -1;

                        for (WebElement option : options) {
                                String cityCode = option.getAttribute("data-city-code");
                                if (cityCode != null && cityCode.equalsIgnoreCase(optionText)) {
                                        clickOn(option);
                                        return;
                                }
                        }

                        for (WebElement option : options) {
                                String name = normalizeText(option.getAttribute("data-name"));
                                if (name.isEmpty()) {
                                        name = normalizeText(option.getText());
                                }
                                int score = getMatchScore(needle, name);
                                if (score > bestScore) {
                                        bestScore = score;
                                        bestOption = option;
                                }
                        }

                        if (bestOption != null && bestScore > 0) {
                                clickOn(bestOption);
                                return;
                        }

                        for (WebElement option : options) {
                                String text = normalizeText(option.getText());
                                if (text.equals(needle)) {
                                        clickOn(option);
                                        return;
                                }
                        }
                } catch (Exception e) {
                        System.out.println("No se pudo seleccionar la opción '" + optionText + "' del autocompletado.");
                        e.printStackTrace();
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

        /*
        * Metodo para seleccionar una opción de autocompletado
         */
        private String normalizeText(String text) {
                if (text == null) {
                        return "";
                }
                return java.text.Normalizer.normalize(text, java.text.Normalizer.Form.NFD)
                        .replaceAll("\\p{M}", "")
                        .toLowerCase()
                        .trim();
        }

        private int getMatchScore(String needle, String value) {
                if (needle == null || needle.isEmpty() || value == null || value.isEmpty()) {
                        return 0;
                }
                int score = 0;
                if (value.equals(needle)) {
                        score += 10000;
                }
                if (value.startsWith(needle)) {
                        score += 8000;
                }
                if (value.contains(needle)) {
                        score += 6000;
                }
                int common = longestCommonSubstringLength(needle, value);
                score += common * 50;
                int distance = levenshteinDistance(needle, value);
                int maxLen = Math.max(needle.length(), value.length());
                if (maxLen > 0) {
                        double similarity = 1.0 - ((double) distance / maxLen);
                        if (similarity > 0) {
                                score += (int) (similarity * 3000);
                        }
                }
                return score;
        }

        private int longestCommonSubstringLength(String a, String b) {
                if (a == null || b == null || a.isEmpty() || b.isEmpty()) {
                        return 0;
                }
                int[][] dp = new int[a.length() + 1][b.length() + 1];
                int longest = 0;
                for (int i = 1; i <= a.length(); i++) {
                        for (int j = 1; j <= b.length(); j++) {
                                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                                        dp[i][j] = dp[i - 1][j - 1] + 1;
                                        if (dp[i][j] > longest) {
                                                longest = dp[i][j];
                                        }
                                }
                        }
                }
                return longest;
        }

        private int levenshteinDistance(String a, String b) {
                if (a == null) {
                        return b == null ? 0 : b.length();
                }
                if (b == null) {
                        return a.length();
                }
                int[] prev = new int[b.length() + 1];
                int[] curr = new int[b.length() + 1];
                for (int j = 0; j <= b.length(); j++) {
                        prev[j] = j;
                }
                for (int i = 1; i <= a.length(); i++) {
                        curr[0] = i;
                        for (int j = 1; j <= b.length(); j++) {
                                int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
                                curr[j] = Math.min(Math.min(curr[j - 1] + 1, prev[j] + 1), prev[j - 1] + cost);
                        }
                        int[] temp = prev;
                        prev = curr;
                        curr = temp;
                }
                return prev[b.length()];
        }

        public void selectFromAutocomplete(List<WebElement> options, String optionText) {
                try {
                        wait.until(ExpectedConditions.visibilityOfAllElements(options));
                        int count = options.size();
                        System.out.println(count + " opciones encontradas");
                        String needle = normalizeText(optionText);
                        WebElement exactOption = null;

                        for (WebElement option : options) {
                                String name = normalizeText(option.getAttribute("data-name"));
                                if (name.isEmpty()) {
                                        name = normalizeText(option.getText());
                                }
                                if (name.equals(needle)) {
                                        exactOption = option;
                                        break;
                                }
                        }

                        if (exactOption != null) {
                                System.out.println("Buscando opción exacta: " + optionText);
                                wait.until(ExpectedConditions.visibilityOf(exactOption));
                                System.out.println("Opción encontrada: " + optionText);
                                clickOn(exactOption);
                                System.out.println("Opción seleccionada: " + optionText);
                                return;
                        }

                        System.out.println("No se encontró opción exacta: " + optionText + ", buscando la mejor coincidencia...");
                        WebElement bestOption = null;
                        int bestScore = -1;

                        for (WebElement option : options) {
                                String cityCode = option.getAttribute("data-city-code");
                                if (cityCode != null && cityCode.equalsIgnoreCase(optionText)) {
                                        clickOn(option);
                                        return;
                                }
                        }

                        for (WebElement option : options) {
                                String name = normalizeText(option.getAttribute("data-name"));
                                if (name.isEmpty()) {
                                        name = normalizeText(option.getText());
                                }
                                int score = getMatchScore(needle, name);
                                if (score > bestScore) {
                                        bestScore = score;
                                        bestOption = option;
                                }
                        }

                        if (bestOption != null && bestScore > 0) {
                                clickOn(bestOption);
                                return;
                        }

                        for (WebElement option : options) {
                                String text = normalizeText(option.getText());
                                if (text.equals(needle)) {
                                        clickOn(option);
                                        return;
                                }
                        }
                } catch (Exception e) {
                        System.out.println("No se pudo seleccionar la opción '" + optionText + "' del autocompletado.");
                        e.printStackTrace();
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

        public void clickAndHighlight(WebElement element) {
                // 1. Resaltar el elemento con un borde rojo
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].style.border='3px solid red'", element);

                // 2. Hacer clic en el elemento
                element.click();
        }
}
