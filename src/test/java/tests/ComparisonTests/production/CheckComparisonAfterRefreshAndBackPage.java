package tests.ComparisonTests.production;

import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckComparisonAfterRefreshAndBackPage extends BaseTest {

    @Test(description = "Check comparison chart when refreshing or back page")
    public void checkComparisonChartWhenRefreshingOrBackPage() throws InterruptedException {

        mainPage.openUrl("https://winners.net");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Bet365", "Plus");
        mainPage.refreshPage();
        mainPage.selectTabInHeader("Analysis and Predictions");
        mainPage.goToBackPage();
    }
}
