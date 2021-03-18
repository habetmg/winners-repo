package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckCorrectButtonIsActive extends BaseTest {

    @BeforeMethod(description = "Create one bookmaker")
    public void createBookmaker() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "Going from Comparison section to Full Review then coming back the page should not navigate to the list section.WN-151")
    public void checkCorrectButtonIsActive() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");
        Thread.sleep(7000);
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Full Review");
        Thread.sleep(7000);
        softAssert.assertEquals(webDriver().getCurrentUrl(), "https://win:w1nPorta2@affiliates.priotix.xyz/review/"+BookmakerApiCalls.bookmakerName,"Wrong Page!!!");

        webDriver().navigate().back();
        softAssert.assertEquals(comparisonPage.findClassNameOfComparisonButton(),"btn default-case active");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        comparisonPage.refreshPage();

        mainPage.selectTabInHeader("Analysis and Predictions");
        softAssert.assertEquals(webDriver().getCurrentUrl(), "https://win:w1nPorta2@affiliates.priotix.xyz/analysis-and-predictions","Wrong Page!!!");

        webDriver().navigate().back();
        softAssert.assertEquals(comparisonPage.findClassNameOfComparisonButton(),"btn default-case active");
        softAssert.assertAll();
    }

    @AfterTest(alwaysRun = true,description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() throws Exception {
       bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
