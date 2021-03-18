package tests.ComparisonTests.production;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckCorrectButtonIsActiveProd extends BaseTest {

    @Test(description = "Going from Comparison section to Full Review then coming back the page should not navigate to the list section. WN-151")
    public void checkCorrectButtonIsActive() throws InterruptedException {

        mainPage.openUrl("https://winners.net");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        Thread.sleep(2000);
        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Full Review");
        Thread.sleep(7000);
        softAssert.assertEquals(webDriver().getCurrentUrl(), "https://winners.net/review/betway","Wrong Page!!!");

        webDriver().navigate().back();
        softAssert.assertEquals(comparisonPage.findClassNameOfComparisonButton(),"btn default-case active");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        comparisonPage.refreshPage();

        mainPage.selectTabInHeader("Analysis and Predictions");
        Thread.sleep(7000);
        softAssert.assertEquals(webDriver().getCurrentUrl(), "https://winners.net/analysis-and-predictions","Wrong Page!!!");

        webDriver().navigate().back();
        softAssert.assertEquals(comparisonPage.findClassNameOfComparisonButton(),"btn default-case active");
        softAssert.assertAll();
    }
}
