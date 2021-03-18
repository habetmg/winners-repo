package tests.FooterTests.production;

import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckFooterBookmakerReviews extends BaseTest {
    @Test(description = "WN-182 : (OK) Footer Bookmaker-review (WAF-213) 'Partially'")
    public void FooterBookmakerReview() throws InterruptedException {
        mainPage.waitToTitleContains("Find the best betting sites");
        footerPage.clickOnReviewsSubMenuElement("Betway Review");
        mainPage.waitToTitleContains("Betway");
        softAssert.assertEquals(bookmakersPage.getOverviewTitleText(), "Betway Review");
        footerPage.clickOnReviewsSubMenuElement("Winners.bet Review");
        mainPage.waitToTitleContains("Winners.bet");
        softAssert.assertEquals(bookmakersPage.getOverviewTitleText(), "Winners.bet Review");
        footerPage.clickOnReviewsSubMenuElement("Bet365 Review");
        mainPage.waitToTitleContains("Bet365");
        softAssert.assertEquals(bookmakersPage.getOverviewTitleText(), "Bet365 Review");
        footerPage.clickOnReviewsSubMenuElement("William Hill Review");
        mainPage.waitToTitleContains("William Hill");
        softAssert.assertEquals(bookmakersPage.getOverviewTitleText(), "William Hill Review");
        footerPage.clickOnReviewsSubMenuElement("1XBET Review");
        mainPage.waitToTitleContains("1XBET");
        softAssert.assertEquals(bookmakersPage.getOverviewTitleText(), "1XBET Review");
        footerPage.clickOnReviewsSubMenuElement("Unibet Review");
        mainPage.waitToTitleContains("Unibet");
        softAssert.assertEquals(bookmakersPage.getOverviewTitleText(), "Unibet Review");
        softAssert.assertAll();
    }
}
