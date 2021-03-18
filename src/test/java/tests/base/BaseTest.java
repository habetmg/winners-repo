package tests.base;

import api.*;
import com.epam.reportportal.service.ReportPortal;
import config.AppConfig;
import helper.Excel;
import helper.Factory;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;
import pages.*;
import rest.RequestHelpers;
import rest.RequestMethods;
//import ru.yandex.qatools.ashot.AShot;
//import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class BaseTest {
    public String baseUrl;
    public Excel excel;
    public Authentication authentication;
    public ArticleApiCalls articleApiCalls;
    public BookmakerApiCalls bookmakerApiCalls;
    public QuickCardApiCalls quickCardApiCalls;
    public BackOfficeApiCalls backOfficeApiCalls;
    public ComparisonApiCalls comparisonApiCalls;
    public ThreadLocal<WebDriver> driver;
    public MainPage mainPage;
    public FilterPage filterPage;
    public FooterPage footerPage;
    public ArticlePage articlePage;
    public ReviewPage reviewPage;
    public QuickCardPage quickCardPage;
    public RecommenderPage recommenderPage;
    public RequestMethods requestMethods;
    public RequestHelpers requestHelpers;
    public AnalysisPage analysisPage;
    public FacebookPage facebookPage;
    public TwitterPage twitterPage;
    public RedditPage redditPage;
    public BookmakersTablePage bookmakersTablePage;
    public ComparisonPage comparisonPage;
    public BookmakersPage bookmakersPage;
    public BackOfficePage backOfficePage;
    public SoftAssert softAssert = new SoftAssert();


    protected WebDriver webDriver() {
        return driver.get();
    }

    @BeforeClass(alwaysRun = true, description = "Before method")
    public void setup() throws Exception {
        driver = new ThreadLocal<>();
        AppConfig appConfig = ConfigFactory.create(AppConfig.class);
        System.out.println("Browser Name" + appConfig.getBrowserName());
        driver.set(Factory.createWebDriver());
        driver.get().manage().window().maximize();
        mainPage = PageFactory.initElements(webDriver(), MainPage.class);
        filterPage = PageFactory.initElements(webDriver(), FilterPage.class);
        footerPage = PageFactory.initElements(webDriver(), FooterPage.class);
        bookmakersTablePage = PageFactory.initElements(webDriver(), BookmakersTablePage.class);
        comparisonPage = PageFactory.initElements(webDriver(), ComparisonPage.class);
        articlePage = PageFactory.initElements(webDriver(), ArticlePage.class);
        reviewPage = PageFactory.initElements(webDriver(), ReviewPage.class);
        analysisPage = PageFactory.initElements(webDriver(), AnalysisPage.class);
        facebookPage = PageFactory.initElements(webDriver(), FacebookPage.class);
        twitterPage = PageFactory.initElements(webDriver(), TwitterPage.class);
        redditPage = PageFactory.initElements(webDriver(), RedditPage.class);
        bookmakersPage = PageFactory.initElements(webDriver(), BookmakersPage.class);
        recommenderPage = PageFactory.initElements(webDriver(), RecommenderPage.class);
        quickCardPage = PageFactory.initElements(webDriver(), QuickCardPage.class);
        backOfficePage = PageFactory.initElements(webDriver(), BackOfficePage.class);
        excel = new Excel();
        authentication = new Authentication();
        articleApiCalls = new ArticleApiCalls();
        bookmakerApiCalls = new BookmakerApiCalls();
        quickCardApiCalls = new QuickCardApiCalls();
        backOfficeApiCalls = new BackOfficeApiCalls();
        comparisonApiCalls= new ComparisonApiCalls();
        requestMethods = new RequestMethods();
        requestHelpers = new RequestHelpers();
        webDriver().navigate().to("https://win:w1nPorta2@affiliates.priotix.xyz/");
    }

//    @AfterMethod
//    public void screenShot(ITestResult result){
//        if(ITestResult.FAILURE==result.getStatus()){
//            File file = new File("src/test/resources/" + "failed.png");
//            BufferedImage screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
//                    .takeScreenshot(webDriver()).getImage();
//            try {
//                ImageIO.write(screenshot, "PNG",
//                        file);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            ReportPortal.emitLog("screenshot", "FAIL", Calendar.getInstance().getTime(), file);
//        }
//    }

    @AfterMethod
    public void screenShot(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            File screenshot = ((TakesScreenshot) webDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("src/test/resources/homePageScreenshot.png"));
        }
    }

    @AfterClass(alwaysRun = true, description = "After method")
    public void tearDown() throws IOException {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }
}
