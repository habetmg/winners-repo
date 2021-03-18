package tests.ReviewTests;

import api.BookmakerApiCalls;
import tests.base.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckBookmakerSectionsContent extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add Data in Bookmaker Profile section" +
            "4. Add Bonus for bookmaker, 5. Add data in 'Reviews' tab, 6. Add Bookmaker SEO, 7. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerProfile(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerBonus(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerReviews(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.publishBookmaker(BookmakerApiCalls.bookmakerId);
    }

    @Test(description = "Check bookmaker 'Bonuses' section content")
    public void checkBookmakerSectionsContent() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Full Review");
        reviewPage.clickOnNavBarItem("Bonuses");
        softAssert.assertEquals(reviewPage.getContentBySectionName("Bonuses"), "Bonuses\n" +
                "4 / 10\n" +
                "Bonus-info-introduction-test\n" +
                "Bonus-info-summary-test\n" +
                "Interested in what other bookmakers are offering?\n" +
                "BONUS COMPARISON");
        reviewPage.clickOnNavBarItem("Sport / Game Coverage");
        softAssert.assertEquals(reviewPage.getContentBySectionName("Sport / Game Coverage"), "Sport / Game Coverage\n" +
                "7 / 10\n" +
                "Sports-Games-Covered-Introduction\n" +
                "Esports\n" +
                "Valorant\n" +
                "CS:GO\n" +
                "Sports\n" +
                "Baseball\n" +
                "Valleyball\n" +
                "Basketball\n" +
                "Others\n" +
                "Live Casino\n" +
                "Pool Betting\n" +
                "Sports-Games-Covered-Summary");
        reviewPage.clickOnNavBarItem("Responsible Gaming");
        softAssert.assertEquals(reviewPage.getContentBySectionName("Responsible Gaming"), "Responsible Gaming\n" +
                "9 / 10\n" +
                "Responsible-Gaming-Introduction-test\n" +
                "Organizations\n" +
                "WHO\n" +
                "IBIS\n" +
                "Responsible-Gaming-Summary-test");
        reviewPage.clickOnNavBarItem("Deposit & Withdrawal");
        softAssert.assertEquals(reviewPage.getContentBySectionName("Deposit & Withdrawal"), "Deposit & Withdrawal\n" +
                "1 / 10\n" +
                "Deposit-and-Withdrawal-test\n" +
                "Payment Options\n" +
                "SWIFT Transfers\n" +
                "Yandex Money\n" +
                "Withdrawal Options\n" +
                "Visa Electron\n" +
                "Maestro\n" +
                "Deposit-And-Withdrawal-test");
        reviewPage.clickOnNavBarItem("Onboarding Process");
        softAssert.assertEquals(reviewPage.getContentBySectionName("Onboarding Process"), "Onboarding Process\n" +
                "8 / 10\n" +
                "OnBoarding-process-intorduction-test\n" +
                "Account Verification\n" +
                "Account Verification Method\n" +
                "Email signature\n" +
                "Personal visita\n" +
                "OnBoarding-process-summary-test");
        reviewPage.clickOnNavBarItem("Customer Service");
        softAssert.assertEquals(reviewPage.getContentBySectionName("Customer Service"), "Customer Service\n" +
                "8 / 10\n" +
                "Customer-Services-introduction-test\n" +
                "Channels\n" +
                "Paper\n" +
                "Personal visit\n" +
                "Features\n" +
                "Professional treatment\n" +
                "Personalized service\n" +
                "Availability\n" +
                "500\n" +
                "Customer-Services-Summary-test");
        reviewPage.clickOnNavBarItem("Utility Features");
        softAssert.assertEquals(reviewPage.getContentBySectionName("Utility Features"), "Utility Features\n" +
                "4 / 10\n" +
                "Utility-Features-Introduction-test\n" +
                "Feature-1\n" +
                "Description-1\n" +
                "Utility-Features-Summary-test");
        reviewPage.clickOnNavBarItem("Additional Info");
        softAssert.assertEquals(reviewPage.getContentBySectionName("Additional Info"), "Additional Information\n" +
                "Excluded countries\n" +
                "Algeria\n" +
                "American Samoa\n" +
                "Languages\n" +
                "Portugese\n" +
                "Spanish\n" +
                "Currency accepted\n" +
                "AMD\n" +
                "USD\n" +
                "RUB");
        softAssert.assertAll();
    }

    @AfterTest(description = "Delete created bookmaker")
    public void deleteCreatedBookmaker() throws Exception {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
