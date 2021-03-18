package tests.ComparisonTests.production;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;


public class CheckComparisonChartAfterPageRefreshProd extends BaseTest {

    @Test(description = "WN-133 Bookmaker Comparison-Refresh/Back page")
    public void checkComparisonChartAfterPageRefreshProd() throws InterruptedException {

        Integer numberOfBookmakersAdded=2;
        Integer startingFrom=1;

        mainPage.openUrl("https://winners.net");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        Thread.sleep(3000);

        ArrayList lstBeforeSort=comparisonPage.getNamesAndColorsFromLegend(comparisonPage.addNBookmakersFromNbr
                (numberOfBookmakersAdded,startingFrom,"accenting").size());

        bookmakersTablePage.refreshPage();
        bookmakersTablePage.clickOnButtonsByBookmakerName("Bet365", "Full Review");

        webDriver().navigate().back();
        Thread.sleep(3000);

        comparisonPage.clickOnComparisonButton();
        Thread.sleep(3000);

        ArrayList lstAfterSort=comparisonPage.getNamesAndColorsFromLegend(bookmakersTablePage
                .getNumberOfCheckedBookmakers().size());

        Assert.assertEquals(lstBeforeSort,lstAfterSort,"Legends before and after sort DO NOT MATCH!!!");

    }
}
