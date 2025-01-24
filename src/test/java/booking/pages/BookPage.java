package booking.pages;

import booking.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.time.LocalDate;
import java.util.List;

public class BookPage {
    public BookPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> allRows;

    @FindBy(xpath = "//button[@aria-label='Godkänn alla']")
    public WebElement acceptCookies;

    @FindBy(id = "cb_js_geo_location_dropdown")
    public  List<WebElement> allLocationsDropdown;

    @FindBy(id = "cb_accommodationtype")
    public List <WebElement> accomodationTypeDropdown;

    @FindBy(id = "Citybreak_trigger_from")
    public WebElement checkInDateBox;

    @FindBy(id = "Citybreak_trigger_to")
    public WebElement checkOutDateBox;

    @FindBy(id = "cb-ui-datepicker-div")
    public  WebElement datePickerTable;

    @FindBy(id = "cb_nodates")
    public WebElement noSpecificDatesCheckbox;

    @FindBy(id = "cb_numrooms")
    public List<WebElement> numberOfRoomsDropdown;

    @FindBy(id = "cb_numadults1")
    public List<WebElement> adultGuestsDropdown;

    @FindBy(id = "cb_numchild1")
    public List<WebElement> childrenGuestsDropdown;

    @FindBy(id = "cb_js_promocode")
    public WebElement promoCode;

    @FindBy(id = "CB_SearchButton")
    public WebElement searchButton;

    @FindBy(id = "cb_js_search_result")
    public WebElement hotelsList;

    public void selectDatePicker(LocalDate date)
    {
        int getDate = date.getDayOfMonth();
        String dateFormat = String.format("%02d", getDate); // Format to always have two digits

        // Locate the table of dates in the date picker
        List<WebElement> rows = datePickerTable.findElements(By.tagName("tr"));

        // Iterate through the rows to find tomorrow's date and click it
        boolean dateFound = false;
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                String cellText = cell.getText();
                if (cellText.equals(dateFormat)) {
                    cell.click(); // Click on the cell containing tomorrow's date
                    dateFound = true;
                    break;
                }
            }
            if (dateFound) break;
        }
    }
    public void bookingSearch(LocalDate checkInDate, LocalDate checkOutDate)
    {
        checkInDateBox.click();
        selectDatePicker(checkInDate);
        checkOutDateBox.click();
        selectDatePicker(checkOutDate);
        searchButton.click();
    }

    public void selectHotel(int hotelItem) {

        List<WebElement> hotelSearchResultItems = hotelsList.findElements(By.xpath("//*[contains(@class, 'Citybreak_AccInfoBasic hproduct')]"));

        hotelSearchResultItems.get(hotelItem).findElement(By.partialLinkText("Book now")).click();
    }

}