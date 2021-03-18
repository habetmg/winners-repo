package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;


public class CheckAboutUsPageContent extends BaseTest {
    @Test(description = "WN-15 : (OK) About us, WN-146 : (OK) Header/Footer URL")
    public void checkAboutUsPage() throws Exception {
        footerPage.clickOnWinnersNetSubMenuPages("About Us");
        softAssert.assertEquals(footerPage.getSubHeaderText(), "About Us");
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/about-us");
        softAssert.assertEquals(footerPage.getAboutUsPageInfoByParagraphs(),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "About Us", "About Us"));
        softAssert.assertAll();
    }
}
