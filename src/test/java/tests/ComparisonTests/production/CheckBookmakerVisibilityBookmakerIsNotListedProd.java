package tests.ComparisonTests.production;

import org.apache.commons.math3.util.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckBookmakerVisibilityBookmakerIsNotListedProd extends BaseTest {

    @Test(description = "WN-134 Checking bookmakers visibility in comparison chart when bookmaker is not available in list")
    public void checkBookmakerVisibilityBookmakerIsNotListed() throws InterruptedException {

        mainPage.openUrl("https://winners.net/review/5ef5e90dbdb0620010edb96c/betmgm");
        comparisonPage.clickOnCompareWithOthersButton();
        Thread.sleep(5000);
        Assert.assertEquals(comparisonPage.getComparisonChartCoordinates(), Collections.emptyList(),"Chart is not empty");
    }
}
