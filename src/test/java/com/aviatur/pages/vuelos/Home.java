package com.aviatur.pages.vuelos;

import com.aviatur.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
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
    private WebElement txtOrigen;

    @FindBy(id = "js-container-general-text-origin-")
    private WebElement containerOptionOrigin;

    @FindBy(id = "js-iata-code-destination")
    private WebElement txtDestin;

    @FindBy(id = "js-container-general-text-destination-")
    private WebElement containerOptionDestination;

    @FindBy(id = "js-createDatepicker1")
    private WebElement dateLeave;

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

    public Home (WebDriver driver){
        super(driver);
    }

    public void clickOnOrigin(){
        if (txtOrigen != null) {
            clickOn(txtOrigen);
        }
    }
    
    public void inputOrigin(String city){

        if(txtOrigen != null) {
            writeText(txtOrigen, containerOptionOrigin, city);
        }
    }

    public void clickOnDestin(){

        if(txtDestin != null) {
            clickOn(txtDestin);
        }
    }

    public void inputDestin(String destinCity){

        if(txtDestin != null) {
            writeText(txtDestin, containerOptionDestination, destinCity);
        }
    }

    @Override
    public String getTxtValidation(WebElement txtSameOrigin) {
        return super.getTxtValidation(txtSameOrigin);
    }
}
