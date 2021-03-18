package tests.FooterTests.production;

import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckNewsletterAlreadySubscribed extends BaseTest {
    @Test(description = "WN-214 : (NOK) Newsletter check")
    public void checkNewsLetterExistingEmail() throws InterruptedException {
        mainPage.waitToTitleContains("Find the best betting sites");
        footerPage.newsletterInput("test@gmail.com");
        footerPage.clickOnIamInterestedButton();
        softAssert.assertEquals(footerPage.getNewsletterSuccessText(),"Subscription successful!");
        mainPage.refreshPage();
        mainPage.waitToTitleContains("Find the best betting sites");
        footerPage.newsletterInput("test@gmail.com");
        footerPage.clickOnIamInterestedButton();
        softAssert.assertEquals(footerPage.getNewsletterSuccessText(),"Already subscribed");
        softAssert.assertAll();
    }
}
