package tests.BackOfficeTests.testing;

import api.ArticleApiCalls;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckOpenArticleInNewTab extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Insert link in content which opens in current window , " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Article Content Headers");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleStatusPublishNow();
    }

    @Test(description = "Check Open Article In New Tab")
    public void checkOpenArticleInNewTab() throws InterruptedException {
        mainPage.openUrl("https://backoffice.priotix.xyz/");
        backOfficePage.fillEmail("zeus@winesports.com");
        backOfficePage.fillPassword("w1nPorta2");
        backOfficePage.clickOnLoginButton();
        backOfficePage.selectNews();
        backOfficePage.fillValueInSearchField(ArticleApiCalls.articleName);
        backOfficePage.clickOnArticle(ArticleApiCalls.articleName);
        backOfficePage.clickOnOpenArticleInNewTabButton();
        backOfficePage.navigateSecondWindow();
        articlePage.openArticleUrl(ArticleApiCalls.articleId, ArticleApiCalls.articleName);
        Assert.assertEquals(mainPage.getPageNotFoundMessage(), "404\n" +
                "Page not Found\n" +
                "GO TO HOMEPAGE ", "We have bug");
    }
}
