package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckContactPageContent extends BaseTest {
    @Test(description = "WN-16 : (OK) Contact Page, WN-146 : (OK) Header/Footer URL")
    public void checkContactPage() throws Exception {
        footerPage.clickOnWinnersNetSubMenuPages("Contact");
        mainPage.waitToTitleContains("Contact Us");
        softAssert.assertEquals(footerPage.getSubHeaderText(), "Contact");
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/contact");
        softAssert.assertEquals(footerPage.getContactPageInfo(), new ArrayList<>(Arrays.asList("+18332222946",
                "help@winners.net", "120 South 6th St, Suite 900, Minneapolis, MN 55402")));
        softAssert.assertAll();
    }
}
