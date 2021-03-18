package tests.ComparisonTests.production;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckCompareWithOthersButtonFunctionalityWn134 extends BaseTest {

    @Test(description = "Check compare with others button functionality in Bookmaker review page")
    public void checkCompareWithOtherButtonFunctionality() throws InterruptedException {

        mainPage.openUrl("https://winners.net");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Full Review");
        reviewPage.clickOnCompareWithOthersButton();
        Assert.assertTrue(mainPage.getPageUrl().contains("https://winners.net/bookmaker-comparison"));
    }
}
