package tests.ComparisonTests.production;


import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;


class ComparisonPageTooltipsProd extends BaseTest {

    @Test(description = "WN-173 Checks tooltips at Comparison page")
    public void comparisonPageTooltips() throws InterruptedException {

        String bkmkrNm= "Bet365";

        mainPage.openUrl("https://winners.net");
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
}