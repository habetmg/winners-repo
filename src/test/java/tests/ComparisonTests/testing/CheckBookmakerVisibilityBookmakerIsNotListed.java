package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.Collections;

public class CheckBookmakerVisibilityBookmakerIsNotListed extends BaseTest {

    @BeforeMethod(description = "Create one bookmaker")
    public void createBookmaker() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerProfileWithRestrictedArmenia(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "WN-134 Checking bookmakers visibility in comparison chart when bookmaker is not available in list")
    public void checkBookmakerVisibilityBookmakerIsNotListed() throws InterruptedException {

        mainPage.openUrl("https://affiliates.priotix.xyz/review/"+bookmakerApiCalls.bookmakerName);
        comparisonPage.clickOnCompareWithOthersButton();
        Thread.sleep(5000);
        Assert.assertEquals(comparisonPage.getComparisonChartCoordinates(), Collections.emptyList(),"Chart is not empty");
    }
    @AfterTest(alwaysRun = true,description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}

