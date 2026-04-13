package com.aviatur.pages.vuelos;

import com.aviatur.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

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

    @FindBy(id = "js-month-datePicker-1")
    private WebElement month1;

    @FindBy(id = "js-month-datePicker-2")
    private WebElement month2;

    @FindBy(xpath = "//button[contains(@data-type, 'next')]")
    private WebElement btnNextMonth;

    @FindBy(id = "js-createDatepicker-return1")
    private WebElement dateReturn;

    @FindBy(id = "js-departure-schedule")
    private WebElement btnScheduleDeparture;

    @FindBy(id = "js-arrival-schedule")
    private WebElement btnScheduleArrival;

    @FindBy(className = "js-day-in-calendar")
    private WebElement daysCalendar;

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

    @FindBy(xpath = "//div[@data-classproperty='amountAdults']//button[@data-action='remove']")
    private WebElement removeAdt;

    @FindBy(xpath = "//div[@data-classproperty='amountChildren']//button[@data-action='remove']")
    private WebElement removeChd;

    @FindBy(xpath = "//div[@data-classproperty='amountInfants']//button[@data-action='remove']")
    private WebElement removeInf;

    @FindBy(xpath = "//div[@data-classproperty='amountAdults']//button[@data-action='add']")
    private WebElement addAdt;

    @FindBy(xpath = "//div[@data-classproperty='amountChildren']//button[@data-action='add']")
    private WebElement addChd;

    @FindBy(xpath = "//div[@data-classproperty='amountInfants']//button[@data-action='add']")
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
     * Agrega la ciudad de destino
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

    /**
     * Intenta dar clic en el boton buscar
     */
    public void clickOnBtnSearch(){
        clickAndHighlight(btnSearchFlights);
        clickOn(btnSearchFlights);
        System.out.println("Se da clic en el boton Buscar");
    }

    /**
     * Obtener el mensaje de validación de mismo origen-destino
     */
    // TODO mirar como puedo hacer este método general para ponerlo general en BasePage
    public String getSameOriginValidationMessage() {
        waitForElementToAppear(txtSameOrigin);
        clickAndHighlight(txtSameOrigin);
        return getTxtValidation(txtSameOrigin);
    }

    private static final Locale SPANISH_LOCALE = new Locale("es", "ES");
    private static final DateTimeFormatter SPANISH_DATE_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("dd-MMMM-yyyy")
            .toFormatter(SPANISH_LOCALE);

    /**
     * Selecciona una fecha de salida
     */
    public void selectDateLeave(String dateValue) {
        System.out.println("Empieza la selección de fechas");
        clickAndHighlight(dateLeave);
        waitForElementToAppear(month1);
        LocalDate targetDate = parseSpanishDate(dateValue);
        selectCalendarDate(targetDate);
    }

    /**
     * Metodo para seleccionar la fecha de regreso
     * @param dateValue
     */
    public void selectDateReturn(String dateValue) {
        waitForElementToAppear(month1);
        LocalDate targetDate = parseSpanishDate(dateValue);
        selectCalendarDate(targetDate);
    }

    /**
     * Metodo para pasar el dateValue de texto al formato fecha
     * @param dateValue
     * @return
     */
    private LocalDate parseSpanishDate(String dateValue) {
        try {
            return LocalDate.parse(dateValue, SPANISH_DATE_FORMATTER);
        } catch (Exception e) {
            throw new IllegalArgumentException("Fecha inválida para el calendario: " + dateValue, e);
        }
    }

    /**
     * Metodo donde se pasa la fecha formateada y la busca en el calendario
     * @param targetDate
     */
    private void selectCalendarDate(LocalDate targetDate) {
        for (int i = 0; i < 12; i++) {
            if (clickCalendarDay(targetDate)) {
                return;
            }
            clickAndHighlight(btnNextMonth);
            waitForElementToAppear(month1);
        }
        throw new IllegalStateException("No se encontró la fecha " + targetDate + " en el calendario.");
    }

    /**
     * Metodo donde busca coincidir día, mes y año en las cards del calendario
     * @param targetDate
     * @return
     */
    private boolean clickCalendarDay(LocalDate targetDate) {
        String expectedDay = String.valueOf(targetDate.getDayOfMonth());
        String expectedMonth = targetDate.getMonth().getDisplayName(TextStyle.FULL, SPANISH_LOCALE);
        String expectedYear = String.valueOf(targetDate.getYear());

        for (WebElement dayElement : month1.findElements(By.cssSelector(".js-day-in-calendar"))) {
            if (matchesDay(dayElement, expectedDay, expectedMonth, expectedYear)) {
                clickAndHighlight(dayElement);
                return true;
            }
        }
        for (WebElement dayElement : month2.findElements(By.cssSelector(".js-day-in-calendar"))) {
            if (matchesDay(dayElement, expectedDay, expectedMonth, expectedYear)) {
                clickAndHighlight(dayElement);
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si un elemento de día del calendario coincide con la fecha especificada.
     * Compara los atributos data-day, data-month-name y data-year del elemento con los valores proporcionados.
     * @param dayElement
     * @param day
     * @param month
     * @param year
     * @return
     */
    private boolean matchesDay(WebElement dayElement, String day, String month, String year) {
        String dataDay = dayElement.getAttribute("data-day");
        String dataMonth = dayElement.getAttribute("data-month-name");
        String dataYear = dayElement.getAttribute("data-year");
        return day.equals(dataDay) && year.equals(dataYear) && month.equalsIgnoreCase(dataMonth);
    }

    private void setPassengers(WebElement counterLocator, WebElement addButton, int target) {

        int current = getTextAsInt(counterLocator);

        if (current == target) {
            return;
        }

        int diff = target - current;

        for (int i = 0; i < diff; i++) {
            System.out.println("Agregando pasajero: " + (i + 1));
            clickAndHighlight(addButton);
        }
    }

    public void addPassengers(int nAdt, int nChd, int nInf) {

        System.out.println("Empieza la selección de pasajeros");

        setPassengers(countPassengersAdt, addAdt, nAdt);
        setPassengers(countPassengersChd, addChd, nChd);
        setPassengers(countPassengersInf, addInf, nInf);
    }

}
