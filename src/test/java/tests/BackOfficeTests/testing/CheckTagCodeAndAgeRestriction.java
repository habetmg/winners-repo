package tests.BackOfficeTests.testing;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckTagCodeAndAgeRestriction extends BaseTest {

    @BeforeMethod(description = "")
    public void setTagCodeAndAgeRestrictionForCountry() throws Exception {
        backOfficeApiCalls.setCountryCodeAndAge();
    }

    @Test(description = "Check tag code and age restriction functionality for Country tag")
    public void checkTagCodeAndAgeRestriction() {
        filterPage.clickOnBestBettingSitesForDropdown();
        filterPage.selectCountryByCountryCode("zmb");
        Assert.assertEquals(mainPage.getAgeRestriction(), "18");
    }

    @AfterTest(description = "Unset tag code and age restriction")
    public void unsetTagCodeAndAgeRestriction() throws Exception {
        backOfficeApiCalls.unsetCountryCodeAndAge();
    }
}
