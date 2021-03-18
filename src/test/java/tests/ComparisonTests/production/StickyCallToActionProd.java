package tests.ComparisonTests.production;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class StickyCallToActionProd extends BaseTest {

    @Test(description = "WAF-7 Full review scroll down functionality checking for bookmaker.")
    public void checkStickyCallToActionProd() throws InterruptedException {

        mainPage.openUrl("https://winners.net");
        mainPage.selectTabInHeader("Bookmaker Comparison");

        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Logo");
        Thread.sleep(5000);
        softAssert.assertEquals(webDriver().getCurrentUrl(), "https://winners.net/review/betway","Wrong Page!!!");

        comparisonPage.clickOnCompareWithOthersButton();

        softAssert.assertNotNull(comparisonPage.getNamesAndColorsFromLegend(1),"NO CHARTS WERE FOUND!!!");
        Thread.sleep(2000);
        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Website");
        comparisonPage.switchToNextTab();
        Thread.sleep(3000);
        softAssert.assertEquals(webDriver().getCurrentUrl(), "https://betway.com/bwp/sportsoffercan/en-ca/","Wrong Page!!!");
        softAssert.assertAll();
    }
}
