package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class StickyCallToAction extends BaseTest {

    @BeforeMethod(description = "Create one bookmaker")
    public void createBookmaker() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "WAF-7 Full review scroll down functionality checking for bookmaker.")
    public void checkStickyCallToActionProd() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");

        bookmakersTablePage.clickOnButtonsByBookmakerName(bookmakerApiCalls.bookmakerName, "Logo");
        Thread.sleep(5000);
        softAssert.assertEquals(webDriver().getCurrentUrl(), "https://win:w1nPorta2@affiliates.priotix.xyz/review/"
                        +bookmakerApiCalls.bookmakerName,"Wrong Page!!!");

        comparisonPage.clickOnCompareWithOthersButton();

        softAssert.assertNotNull(comparisonPage.getNamesAndColorsFromLegend(1),"NO CHARTS WERE FOUND!!!");
        Thread.sleep(2000);
        bookmakersTablePage.clickOnButtonsByBookmakerName(bookmakerApiCalls.bookmakerName, "Website");
        comparisonPage.switchToNextTab();
        Thread.sleep(3000);
        softAssert.assertEquals(webDriver().getCurrentUrl(), "https://github.com/","Wrong Page!!!");
        softAssert.assertAll();
    }

    @AfterTest(alwaysRun = true,description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
