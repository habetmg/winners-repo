package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckBulletAndNumberedListFunctionality extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Add article content with bullet and numbered list" +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Insert Bullet and Numbered List");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleStatusPublishNow();
    }

    @Test(description = "Check bullet and numbered list functionality")
    public void checkBulletAndNumberedListFunctionality() throws InterruptedException {
        mainPage.selectTabInHeader("Analysis and Predictions");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        softAssert.assertEquals(articlePage.getBulletOrNumberedList("Bullet"),
                new ArrayList<>(Arrays.asList("cont1", "cont2", "cont3")));
        softAssert.assertEquals(articlePage.getBulletOrNumberedList("Numbered"),
                new ArrayList<>(Arrays.asList("cont6", "cont8", "cont9")));
        softAssert.assertAll();
    }

    @AfterTest(description = "Delete created article")
    public void deleteCreatedArticle() throws Exception {
        articleApiCalls.deleteArticle();
    }


}
