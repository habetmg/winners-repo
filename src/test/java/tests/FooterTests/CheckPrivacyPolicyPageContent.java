package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckPrivacyPolicyPageContent extends BaseTest {
    @Test(description = "WN-17 : (OK) Privacy Policy, WN-146 : (OK) Header/Footer URL")
    public void checkPrivacyPolicyPageContent() throws Exception {
        footerPage.clickOnWinnersNetSubMenuPages("Privacy Policy");
        softAssert.assertEquals(footerPage.getSubHeaderText(), "Privacy Policy");
        softAssert.assertEquals(footerPage.getPrivacyPolicyPageInfo(), new ArrayList<>(Arrays.asList("1. Key Details",
                "2. Information You Provide to Us", "3. Use of This Data", "4. Disclosure of Your Personal Data",
                "5. Information Security", "6. Note to International Users", "7. Changes to the Privacy Policy")));
        softAssert.assertEquals(mainPage.getUrl(), "https://winners.net/privacy-policy");
        softAssert.assertEquals(footerPage.getContentByTitle("1. Key Details", "2. Information You Provide to Us"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Privacy Policy", "1. Key Details"));
        softAssert.assertEquals(footerPage.getContentByTitle("2. Information You Provide to Us", "3. Use of This Data"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Privacy Policy", "2. Information You Provide to Us"));
        softAssert.assertEquals(footerPage.getContentByTitle("3. Use of This Data", "4. Disclosure of Your Personal Data "),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Privacy Policy", "3. Use of This Data"));
        softAssert.assertEquals(footerPage.getContentByTitle("4. Disclosure of Your Personal Data ", "5. Information Security"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Privacy Policy", "4. Disclosure of Your Personal Data"));
        softAssert.assertEquals(footerPage.getContentByTitle("5. Information Security", "6. Note to International Users "),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Privacy Policy", "5. Information Security"));
        softAssert.assertEquals(footerPage.getContentByTitle("6. Note to International Users ", "7. Changes to the Privacy Policy"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Privacy Policy", "6. Note to International Users"));
        softAssert.assertEquals(footerPage.getContentByTitle("7. Changes to the Privacy Policy", null),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Privacy Policy", "7. Changes to the Privacy Policy"));
        softAssert.assertAll();
    }
}

