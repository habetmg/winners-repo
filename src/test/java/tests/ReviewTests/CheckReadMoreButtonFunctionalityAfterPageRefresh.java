package tests.ReviewTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckReadMoreButtonFunctionalityAfterPageRefresh extends BaseTest {

    @Test(description = "Read More button again visible after page refresh")
    public void readMoreButtonVisibleAgainAfterPageRefresh() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName("betway", "Full Review");
        reviewPage.clickOnReadMoreButton();
        mainPage.refreshPage();
        Assert.assertTrue(reviewPage.readMoreButtonIsVisible());
        reviewPage.clickOnReadMoreButton();
        reviewPage.clickOnShowLessButton();
        mainPage.refreshPage();
        Assert.assertTrue(reviewPage.readMoreButtonIsVisible());
        reviewPage.clickOnNavBarItem("Basic Info");
        Assert.assertTrue(reviewPage.showLessButtonIsVisible());
    }

}
