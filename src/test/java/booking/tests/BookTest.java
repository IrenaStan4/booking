package booking.tests;

import booking.pages.BookPage;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.time.LocalDate;

public class BookTest {

    @Test
    public void BookStay()
    {
        BookPage bookPage = new BookPage();
        bookPage.acceptCookies.click();

        LocalDate checkInTime = LocalDate.now().plusDays(1);
        LocalDate checkOutTime = LocalDate.now().plusDays(2);
        bookPage.bookingSearch(checkInTime, checkOutTime);
        bookPage.selectHotel(0);

    }

}
