package booking.tests;

import booking.pages.BookPage;

import booking.tests.base.TestBase;
import booking.utility.Driver;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import booking.utility.BrowserUtil;
public class BookTest extends TestBase{

    @Test
    public void BookStay()
    {
        BookPage bookPage = new BookPage();
        bookPage.acceptCookies.click();

        //search for booking
        LocalDate checkInTime = LocalDate.now().plusDays(1);
        LocalDate checkOutTime = LocalDate.now().plusDays(2);
        bookPage.bookingSearch(checkInTime, checkOutTime);

        //validate search options
        List<String> actualBookingDetails = bookPage.getBookingDetails();
        List<String> expectedBookingDetails = new ArrayList<>();
        expectedBookingDetails.add("Area: All");
        expectedBookingDetails.add("Type of accommodation: Hotels and Rooms");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd MMM yyyy");
        expectedBookingDetails.add("Check in: " + checkInTime.format(formatter));
        expectedBookingDetails.add("Check out: " + checkOutTime.format(formatter));
        expectedBookingDetails.add("Nights: 1");
        expectedBookingDetails.add("rooms: 1");
        expectedBookingDetails.add("Guests: 2 adults");
        Assert.assertEquals(actualBookingDetails, expectedBookingDetails);

        //select first hotel from the list
        bookPage.selectHotel(0, 0);
        BrowserUtil.waitFor(5);
        String actulaChartDetails = bookPage.chartDetails.getText();
        String expectedChartDetails =
                "When: " + checkInTime.format(formatter) + " - " + checkOutTime.format(formatter) + ", 1 night\n" +
                "Guests:\n" +
                "2× Adult ";
        Assert.assertEquals(actulaChartDetails, expectedChartDetails);
    }
}
