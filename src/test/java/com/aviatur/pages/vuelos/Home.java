package com.aviatur.pages.vuelos;

import com.aviatur.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Home extends BasePage {
    @FindBy(xpath = "//input[contains(@value, 'round')]")
    private WebElement btnRoundTrip;

    @FindBy(xpath = "//input[contains(@value, 'oneway')]")
    private WebElement btnOneWay;

    @FindBy(xpath = "//input[contains(@value, 'multi')]")
    private WebElement btnMultidest;

    @FindBy(css = "[data-testid='same-origin-destination-tooltip']")
    private WebElement txtSameOrigin;

    @FindBy(id = "js-iata-code-origin")
    private WebElement origin;

    @FindBy(id = "js-origin-input")
    private WebElement txtOrigen;

    @FindBy(id = "js-origin-input-iata")
    private WebElement txtIataOrigin;

    @FindBy(css = "div[id^='js-autocomplete-searcher-origin']")
    private WebElement containerOptionOrigin;

    @FindBy(id = "js-iata-code-destination")
    private WebElement destin;

    @FindBy(id = "js-destination-input")
    private WebElement txtDestin;

    @FindBy(id = "js-destination-input-iata")
    private WebElement txtIataDestin;

    @FindBy(css = "div[id^='js-autocomplete-searcher-destination']")
    private WebElement containerOptionDestination;

    @FindBy(css = "li[data-value-for-input]")
    private List<WebElement> optionsSearch;

//    @FindBy(id = "js-createDatepicker1")
//    @FindBy(id = "js-month-datePicker-1")
    @FindBy(name = "dateDepartureFormatted")
    private WebElement dateLeave;

    @FindBy(className = "js-month-datePicker-1")
    private WebElement month1;

    @FindBy(xpath = "//button[contains(@data-type, 'next')]")
    private WebElement btnNextMonth;

    @FindBy(id = "js-createDatepicker-return1")
    private WebElement dateReturn;

    @FindBy(id = "js-departure-schedule")
    private WebElement btnScheduleDeparture;

    @FindBy(id = "js-arrival-schedule")
    private WebElement btnScheduleArrival;

    @FindBy(className = "js-day-in-calendar")
    private List<WebElement> daysCalendar;

    @FindBy(className = "js-option-list-select")
    private List<WebElement> scheduleOptions;

    @FindBy(xpath = "//form[@id='js-searcher-general-form']//span[contains(@class, 'js-field-change-passengers')]")
    private WebElement passengers;

    @FindBy(xpath = "//div[@data-classproperty='amountAdults']//span[@class='js-counter-passengers']")
    private WebElement countPassengersAdt;

    @FindBy(xpath = "//div[@data-classproperty='amountChildren']//span[@class='js-counter-passengers']")
    private WebElement countPassengersChd;

    @FindBy(xpath = "//div[@data-classproperty='amountInfants']//span[@class='js-counter-passengers']")
    private WebElement countPassengersInf;

    @FindBy(xpath = "(//div[@id='js-home-select-passengers']//button[contains(@class, 'js-change-amount-passengers') and contains(@data-action, 'remove')])[1]")
    private WebElement removeAdt;

    @FindBy(xpath = "(//div[@id='js-home-select-passengers']//button[contains(@class, 'js-change-amount-passengers') and contains(@data-action, 'remove')])[2]")
    private WebElement removeChd;

    @FindBy(xpath = "(//div[@id='js-home-select-passengers']//button[contains(@class, 'js-change-amount-passengers') and contains(@data-action, 'remove')])[3]")
    private WebElement removeInf;

    @FindBy(xpath = "(//div[@id='js-home-select-passengers']//button[contains(@class, 'js-change-amount-passengers') and contains(@data-action, 'add')])[1]")
    private WebElement addAdt;

    @FindBy(xpath = "(//div[@id='js-home-select-passengers']//button[contains(@class, 'js-change-amount-passengers') and contains(@data-action, 'add')])[2]")
    private WebElement addChd;

    @FindBy(xpath = "(//div[@id='js-home-select-passengers']//button[contains(@class, 'js-change-amount-passengers') and contains(@data-action, 'add')])[3]")
    private WebElement addInf;

    @FindBy(xpath = "//select[contains(@class,'js-textToChange')]")
    private WebElement btnCabin;

    @FindBy(xpath = "//select[contains(@class,'js-textToChange')]/option")
    private List<WebElement> optionsCabinType;

    @FindBy(id = "js-select-options-baggages")
    private WebElement btnBaggages;

    @FindBy(xpath = "//ul[@id='baggage-options']//li[@id='js-option-0']")
    private WebElement checkCarryOn;

    @FindBy(xpath = "//ul[@id='baggage-options']//li[@id='js-option-1']")
    private WebElement checkBaggage;

    @FindBy(id = "js-select-options-stops")
    private WebElement btnStops;

    @FindBy(xpath = "//ul[@id='scales-options']/li")
    private List<WebElement> optionsScales;

    @FindBy(id = "js-select-options-airline-prefference")
    private WebElement btnAirlinePreference;

    @FindBy(id = "js-button-search-flights")
    private WebElement btnSearchFlights;

    @FindBy(id = "js-origin-input-1")
    private WebElement txtOrigin1;

    @FindBy(id = "js-destination-input-1")
    private WebElement txtDestin1;

    @FindBy(className = "js-date-departure-container-1")
    private WebElement dateDeparture1;

    @FindBy(id = "js-departure-schedule-1")
    private WebElement scheduleDeparture1;

    @FindBy(id = "add-flight")
    private WebElement addFlight;

    @FindBy(id = "remove-flight")
    private WebElement removeFlight;

    @FindBy(css = "//div[contains(@data-testid, 'same-origin-destination-tooltip')]")
    //span[contains (text(), "Origen y destino no pueden ser iguales")]
    private WebElement txtValidationDestin;

    public Home (WebDriver driver){
        super(driver);
    }

    /**
     * Metodo para dar clic al campo ciudad de origen
     */
    public void clickOnOrigin(){
        if (origin != null) {
            clickOn(origin);
        }else {
            System.out.println("Fallo al dar click para ingresar ciudad de Origen.");
        }
    }

    /**
     * Metodo para agregar la ciudad de origen
     */
    public void inputOrigin(String city, String iataOrigin){

        if(txtOrigen != null) {
            typeText(txtOrigen, city);
            waitForElementToAppear(containerOptionOrigin);
            // Seleccionar la opción más parecida por texto ingresado
            selectFromAutocomplete(containerOptionOrigin, By.cssSelector("li[data-value-for-input]"), city);
        }
    }

    /**
     * Metodo para dar clic al campo ciudad de destino
     */
    public void clickOnDestin(){

        if(destin != null) {
            clickAndHighlight(destin);
            clickOn(destin);
        }else {
            System.out.println("Fallo al dar click para ingresar ciudad de Destino.");
        }
    }

    /**
     * Metodo para agregar la ciudad de destino
     */
    public void inputDestin(String destinCity, String iataDestin){

        if(txtDestin != null) {
            typeText(txtDestin, destinCity);
            // Esperar que aparezcan las opciones de autocompletado
            waitForElementToAppear(containerOptionDestination);
            // Seleccionar la opción más parecida por texto ingresado
            selectFromAutocomplete(containerOptionDestination, By.cssSelector("li[data-value-for-input]"), destinCity);
        }
    }

    public void clickOnBtnSearch(){
        clickAndHighlight(btnSearchFlights);
        clickOn(btnSearchFlights);
        System.out.println("Se da clic en el boton Buscar");
    }

    /**
     * Metodo para obtener el mensaje de validación de mismo origen-destino
     */
    public String getSameOriginValidationMessage() {
        waitForElementToAppear(txtSameOrigin);
        clickAndHighlight(txtSameOrigin);
        return getTxtValidation(txtSameOrigin);
    }

    /**
     * Metodo que selecciona día según el número que se le pase
     */
    public void selectDay(int day) {
        String dayText = String.valueOf(day);
        WebElement dayElement = month1.findElement(
                By.xpath(".//*[normalize-space() = '" + dayText + "']")
        );
        dayElement.click();
    }

    /**
     * Metodo para seleccionar una fecha
     */
    public void selectFirstDate(int day){
        clickAndHighlight(dateLeave);
        System.out.println(month1);
        waitForElementToAppear(month1);
        for (int i = 0; i < 4; i++) {
            clickAndHighlight(btnNextMonth);
        }
        System.out.println(month1);
        waitForElementToAppear(month1);
        selectDay(day);

    }

}
