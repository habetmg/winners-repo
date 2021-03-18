package tests.BookmakersTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Log;

public class CheckRobotsTxtToDo extends BaseTest {

    @Test(description = "Check Robots.txt")
    public void checkRobotsTxt() throws InterruptedException {
        mainPage.openUrl("https://winners.net/robots.txt");
        Thread.sleep(2000);
        Log.warn(mainPage.getRobotsTxt());
        Assert.assertEquals(mainPage.getRobotsTxt(), "");
    }
}
