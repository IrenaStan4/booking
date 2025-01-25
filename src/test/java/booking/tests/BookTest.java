package booking.tests;

import booking.pages.BookPage;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookTest {

    @Test
    public void BookStay()
    {
        BookPage bookPage = new BookPage();
        bookPage.acceptCookies.click();

        LocalDate checkInTime = LocalDate.now().plusDays(1);
        LocalDate checkOutTime = LocalDate.now().plusDays(2);
        bookPage.bookingSearch(checkInTime, checkOutTime);

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
        bookPage.selectHotel(0);
    }

}
