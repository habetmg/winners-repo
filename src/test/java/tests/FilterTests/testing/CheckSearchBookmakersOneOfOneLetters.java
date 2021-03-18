package tests.FilterTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Collections;

public class CheckSearchBookmakersOneOfOneLetters extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add Data in Bookmaker Profile section" +
            "4. Add Bookmaker bonus, 5. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "Check find Bookmaker functionality one by")
    public void checkOnebyONe() throws InterruptedException {
        filterPage.searchBookmakerName("B");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Collections.singletonList(BookmakerApiCalls.bookmakerName)));
        filterPage.searchBookmakerName("Bo");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Collections.singletonList(BookmakerApiCalls.bookmakerName)));
        filterPage.searchBookmakerName("Boo");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Collections.singletonList(BookmakerApiCalls.bookmakerName)));
        mainPage.selectTabInHeader("Bookmaker Comparison");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Collections.singletonList(BookmakerApiCalls.bookmakerName)));
        softAssert.assertAll();
    }

    @AfterTest(description = "Delete created bookmakers")
    public void deleteCreatedBookmaker() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
