package tests.BookmakersTests;

import api.BookmakerApiCalls;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckBookmakerBonusValueSizeIs6 extends BaseTest {


    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add  six digits in bookmaker bonus value field")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addIncorrectBookmakerBonusValue(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "Check bonus value max size is 5")
    public void checkBonusValueMaxSizeIs5() throws Exception {
       mainPage.selectTabInHeader("Bookmaker Comparison");
       bookmakersTablePage.selectBookmakerFromBookmakersTable(BookmakerApiCalls.bookmakerName);
       reviewPage.clickOnNavBarItem("Bonuses");
        Assert.assertEquals(reviewPage.getContentBySectionName("Bonuses"), "bonuses content");
    }

    @AfterTest(description = "Delete Created Bookmaker")
    public void deleteCreatedBookmaker() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
