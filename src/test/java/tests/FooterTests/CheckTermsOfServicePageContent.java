package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckTermsOfServicePageContent extends BaseTest {
    @Test(description = "WN-18 : (OK) Terms of Service, WN-146 : (OK) Header/Footer URL")
    public void checkTermsOfService() throws Exception {
        footerPage.clickOnWinnersNetSubMenuPages("Terms of Service");
        softAssert.assertEquals(footerPage.getSubHeaderText(), "Terms of Service");
        softAssert.assertEquals(footerPage.getTermsOfServicePageInfo(), new ArrayList<>(Arrays.asList("IMPORTANT NOTICE",
                "1. User Agreement", "2. Consideration", "3. Modifications", "4. Eligibility",
                "5. Communication And Information Practices", "6. Conduct", "7. User Content", "8. Intellectual Property Rights",
                "9. Third Party Websites", "10. Pre-Arbitration Claim Resolution", "11. Class Action Waiver", "12. Jury Waiver",
                "13. Warranty Disclaimers", "14. Limitation On Liability", "15. Miscellaneous")));
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/terms-of-service");
        softAssert.assertEquals(footerPage.getContentByTitle("IMPORTANT NOTICE", "1. User Agreement"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "IMPORTANT NOTICE"));
        softAssert.assertEquals(footerPage.getContentByTitle("1. User Agreement", "2. Consideration"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "1. User Agreement"));
        softAssert.assertEquals(footerPage.getContentByTitle("2. Consideration", "3. Modifications"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "2. Consideration"));
        softAssert.assertEquals(footerPage.getContentByTitle("3. Modifications", "4. Eligibility"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "3. Modifications"));
        softAssert.assertEquals(footerPage.getContentByTitle("4. Eligibility", "5. Communication And Information Practices"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "4. Eligibility"));
        softAssert.assertEquals(footerPage.getContentByTitle("5. Communication And Information Practices", "6. Conduct"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "5. Communication And Information Practices"));
        softAssert.assertEquals(footerPage.getContentByTitle("6. Conduct", "7. User Content"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "6. Conduct"));
        softAssert.assertEquals(footerPage.getContentByTitle("7. User Content", "8. Intellectual Property Rights"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "7. User Content"));
        softAssert.assertEquals(footerPage.getContentByTitle("8. Intellectual Property Rights", "9. Third Party Websites"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "8. Intellectual Property Rights"));
        softAssert.assertEquals(footerPage.getContentByTitle("9. Third Party Websites", "10. Pre-Arbitration Claim Resolution"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "9. Third Party Websites"));
        softAssert.assertEquals(footerPage.getContentByTitle("10. Pre-Arbitration Claim Resolution", "11. Class Action Waiver"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "10. Pre-Arbitration Claim Resolution"));
        softAssert.assertEquals(footerPage.getContentByTitle("11. Class Action Waiver", "12. Jury Waiver"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "11. Class Action Waiver"));
        softAssert.assertEquals(footerPage.getContentByTitle("12. Jury Waiver", "13. Warranty Disclaimers"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "12. Jury Waiver"));
        softAssert.assertEquals(footerPage.getContentByTitle("13. Warranty Disclaimers", "14. Limitation On Liability"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "13. Warranty Disclaimers"));
        softAssert.assertEquals(footerPage.getContentByTitle("14. Limitation On Liability", "15. Miscellaneous"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "14. Limitation On Liability"));
        softAssert.assertEquals(footerPage.getContentByTitle("15. Miscellaneous", null),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "15. Miscellaneous"));
        softAssert.assertAll();
    }
}
