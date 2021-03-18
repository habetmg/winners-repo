package tests.ComparisonTests.testing;


import api.BookmakerApiCalls;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

class ComparisonPageTooltips extends BaseTest {

    @BeforeMethod(description = "Create one bookmaker")
    public void createBookmaker() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "WN-173 Checks tooltips at Comparison page")
    public void comparisonPageTooltips() throws InterruptedException {

        String bkmkrNm= BookmakerApiCalls.bookmakerName;

        mainPage.selectTabInHeader("Bookmaker Comparison");

        softAssert.assertEquals(comparisonPage.getTooltip(bkmkrNm, "bookmaker"),bkmkrNm,
                "Actual and Expected Tooltips on Bookmaker Name - DO NOT MATCH!!!");
        softAssert.assertEquals(comparisonPage.getTooltip(bkmkrNm,"buttonBeforeClick")
                ,"Add this bookmaker to the comparison"
                ,"Actual Tooltip and Expected Tooltip on '+' Button - DO NOT MATCH!!!" );
        softAssert.assertEquals(comparisonPage.getTooltip(bkmkrNm,"buttonAfterClick"),"",
                "Actual Tooltip at + Button is not Empty after the click!!!");
        softAssert.assertAll();
    }

    @AfterTest(alwaysRun = true,description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}