package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckArticleScheduleFunctionality extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Insert link in content which opens in current window , " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Simple Content");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleScheduleData();
    }

    @Test(description = "Check Article Schedule functionality")
    public void checkArticleScheduleFunctionality() throws InterruptedException {
        Thread.sleep(120000);
        mainPage.selectTabInHeader("Analysis and Predictions");
        Assert.assertEquals(analysisPage.getSelectedInfoByArticleNameAndInfoName(ArticleApiCalls.articleName, "article subtext"), "selenium-subtext-test12249");
        Assert.assertEquals(analysisPage.getSelectedInfoByArticleNameAndInfoName(ArticleApiCalls.articleName, "article info"),
                mainPage.getCurrentDate() + " • Zeus Πορτοκάλι");
    }

    @AfterTest(description = "Delete created Article")
    public void deleteCreatedArticle() throws Exception {
        articleApiCalls.deleteArticle();
    }
}
