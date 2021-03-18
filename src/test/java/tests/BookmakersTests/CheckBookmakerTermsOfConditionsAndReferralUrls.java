package tests.BookmakersTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckBookmakerTermsOfConditionsAndReferralUrls extends BaseTest {

    @Test(description = "Check Bookmaker Terms Of Conditions and Referral Urls")
    public void CheckBookmakerTermsOfConditionsAndReferralUrls() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Full Review");
        reviewPage.clickOnNavBarItem("Responsible Gaming");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Full Review");
    }
}
