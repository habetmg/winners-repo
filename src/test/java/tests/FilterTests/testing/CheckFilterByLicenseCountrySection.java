package tests.FilterTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Collections;

public class CheckFilterByLicenseCountrySection extends BaseTest {


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

    @Test(description = "WN-92 : (OK) Bookmakers filter by Language section")
    public void filterByLicenseCountry() throws Exception {
        filterPage.clickOnExpandIconsBySectionName("License Country");
        filterPage.selectCountryInLicenseCountry("Algeria");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Collections.singletonList(BookmakerApiCalls.bookmakerName)));
        filterPage.selectCountryInLicenseCountry("American Samoa");
        softAssert.assertTrue(bookmakersTablePage.checkEmptyBookmakersTable());
        softAssert.assertAll();
    }

    @AfterTest(alwaysRun = true, description = "Delete created bookmaker")
    public void deleteCreatedBookmaker() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
