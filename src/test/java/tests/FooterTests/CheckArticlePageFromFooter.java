package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckArticlePageFromFooter extends BaseTest {
    @Test(description = "WN-278 : (OK) Article page")
    public void checkAnalysisPage() {
        Assert.assertEquals(footerPage.getNewsPageSubHeader(), "Analysis and Predictions");
    }
}
