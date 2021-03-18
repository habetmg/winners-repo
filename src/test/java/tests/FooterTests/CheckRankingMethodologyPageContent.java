package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckRankingMethodologyPageContent extends BaseTest {
    @Test(description = "WN-268 : (OK) Ranking Methodology page, WN-146 : (OK) Header/Footer URL")
    public void checkRankingMethodologyPageContent() throws Exception {
        footerPage.clickOnWinnersNetSubMenuPages("Ranking Methodology");
        softAssert.assertEquals(footerPage.getSubHeaderText(), "Ranking Methodology");
        softAssert.assertEquals(footerPage.getRankingMethodologyPageInfo(), new ArrayList<>(Arrays.asList(
                "How do we write bookmaker reviews on Winners.net?", "Bonuses", "Coverage & Odds", "Responsible Gaming",
                "Deposit & Withdrawal", "Onboarding Process", "Customer Service", "Utility Features")));
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/ranking-methodology");
        softAssert.assertEquals(footerPage.getContentByTitle("How do we write bookmaker reviews on Winners.net?","Bonuses"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls","Ranking Methodology", "How do we write bookmaker reviews on Winners.net?"));
        softAssert.assertEquals(footerPage.getContentByTitle("Bonuses", "Coverage & Odds"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls","Ranking Methodology", "Bonuses"));
        softAssert.assertEquals(footerPage.getContentByTitle("Coverage & Odds", "Responsible Gaming"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls","Ranking Methodology", "Coverage & Odds"));
        softAssert.assertEquals(footerPage.getContentByTitle("Responsible Gaming", "Deposit & Withdrawal"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls","Ranking Methodology", "Responsible Gaming"));
        softAssert.assertEquals(footerPage.getContentByTitle("Deposit & Withdrawal", "Onboarding Process"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls","Ranking Methodology", "Deposit & Withdrawal"));
        softAssert.assertEquals(footerPage.getContentByTitle("Onboarding Process", "Customer Service"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls","Ranking Methodology", "Onboarding Process"));
        softAssert.assertEquals(footerPage.getContentByTitle("Customer Service", "Utility Features"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls","Ranking Methodology", "Customer Service"));
        softAssert.assertEquals(footerPage.getContentByTitle("Utility Features", null),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls","Ranking Methodology", "Utility Features"));
        softAssert.assertAll();
    }
}
