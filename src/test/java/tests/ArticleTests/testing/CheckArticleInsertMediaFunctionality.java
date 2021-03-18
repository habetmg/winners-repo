package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckArticleInsertMediaFunctionality extends BaseTest {
    @BeforeMethod(description = "1. Create article, 2. Insert Media" +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Article Content Insert Media");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleStatusPublishNow();
    }

    @Test(description = "Check open twitch functionality")
    public void checkArticleInsertMediaFunctionality() throws InterruptedException {
        mainPage.selectTabInHeader("Analysis and Predictions");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        articlePage.clickOnYoutubePLayButton();

    }

//    @AfterTest(description = "delete created article", alwaysRun = true)
//    public void afterTest() throws Exception {
//        articleApiCalls.deleteArticle();
//    }
}
