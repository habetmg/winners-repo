package tests.FooterTests.production;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;


public class CheckAgeRestriction extends BaseTest {
    @Test(description = "WN-152 : (OK) Footer Age Restriction (WAF-312)")
    public void checkFooterRestrictionItem() throws InterruptedException {
        mainPage.waitToTitleContains("Find the best betting sites");
        Assert.assertEquals(footerPage.getFooterAgeRestriction(), "21");
        filterPage.selectCountryInHeader("All Countries");
        Assert.assertEquals(footerPage.getFooterAgeRestriction(), "18");
        filterPage.selectCountryInHeader("Belgium");
        Assert.assertEquals(footerPage.getFooterAgeRestriction(), "21");
        filterPage.selectCountryInHeader("Albania");
        Assert.assertEquals(footerPage.getFooterAgeRestriction(), "18");
    }
}
