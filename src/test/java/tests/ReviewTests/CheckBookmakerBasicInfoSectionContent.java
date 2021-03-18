package tests.ReviewTests;

import api.BookmakerApiCalls;
import tests.base.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckBookmakerBasicInfoSectionContent extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add Data in Bookmaker Profile section" +
            "4. Add Bonus for bookmaker, 5. Add data in 'Reviews' tab, 6. Add Bookmaker SEO, 7. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerSEO(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "Check Bookmaker content")
    public void checkBookmakerBasicInfoContent() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Full Review");
        reviewPage.clickOnReadMoreButton();
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Company Name","Year Established"), "Company-Name-Test");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Year Established","Jurisdiction"), "2021");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Jurisdiction","Website Terms & Conditions"), "Afghanistan, Austria, Bangladesh");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Jurisdiction", "Website Terms & Conditions"), "https://www.bookmakers.bet/");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Products",null),"Esports, Sports, Live Casino, Pool Betting");
    }

    @AfterTest(description = "Delete created Bookmaker", alwaysRun = true)
    public void deleteCreatedBookmaker() throws Exception {
//        bookmakerApiCalls.deleteBookmakerById();
    }
}
