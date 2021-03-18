package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class MainPage extends BasePage {
    @FindBy(id = "MainContent")
    private WebElement mainContentElement;

    @FindBy(className = "NavigationItems")
    private WebElement NavigationItemsElement;

    @FindBy(className = "esport-menu-items")
    private WebElement EsportSitesElement;

    @FindBy(className = "esport-dropdown-button-icon")
    private WebElement bestEsportBettingsSitesIconElement;

    @FindBy(className = "esport-dropdown-button-text")
    private WebElement esportDropDownButtonTextElement;

    @FindBy(className = "esport-menu-items")
    private WebElement bestEsportMenuItemElement;

    @FindBy(css = ".navigation-main-logo .WinnersLogo")
    private WebElement homepageIconElement;

    @FindBy(css = ".left .btn")
    private List <WebElement> subtabChooseElements;

    @FindBy(className = "bookmaker-item-select")
    private List <WebElement> addBookmakerToComparisonElements;

    @FindBy(className = "legend-item")
    private List <WebElement> comparisonBookmakerElements;

    @FindBy(className = "RadarChart")
    private WebElement chartElement;

    @FindBy(className = "country-setting-title")
    private WebElement subHeaderTextElement;

    @FindBy(className = "country-setting-description")
    private WebElement pageDescriptionTextElement;

    @FindBy(className = "age-restriction")
    private WebElement ageRestrictionElement;

    LocalDate currentdate = LocalDate.now();

    public MainPage(WebDriver webDriver) throws IOException {
        super(webDriver);
    }

    public String getIpBasedCountry() throws IOException {
        Scanner s = new Scanner(new java.net.URL("https://api.ipdata.co/country_name?api-key=test").openStream(), "UTF-8").useDelimiter("\\A");
        return s.next();
    }

    public void clickOnHomepageIcon() {
        homepageIconElement.click();
    }

    public void openUrl(String url) {
        webDriver.navigate().to(url);
    }

    public int getComparisonChartBookmakersCount() {
        return comparisonBookmakerElements.size();
    }

    public void chooseComparisonSubtab() {
        //waitToElementIsVisible(subtabChooseElements.get(1));
        //scrollIntoElement(subtabChooseElements.get(1));
        subtabChooseElements.get(1).click();
        waitToElementIsVisible(chartElement);
    }

    public void addBookmakerToComparisonByPosition(int bookmakerPosition) {
        waitToElementIsVisible(chartElement);
        scrollIntoElement(chartElement);
        addBookmakerToComparisonElements.get(bookmakerPosition-1).click();
    }

    public void selectTabInHeader(String tabName) {
        List<WebElement> navigationTabElements = NavigationItemsElement.findElements(By.className("navigation-item"));
        for (WebElement navigationTab : navigationTabElements) {
            if (navigationTab.getText().equals(tabName)) {
                clickOnElement(navigationTab);
                break;
            }
        }
    }



    public void selectBestEsportsBettingSites(String value) {
        clickOnElement(bestEsportBettingsSitesIconElement);
        List<WebElement> esportGameElements = EsportSitesElement.findElements(By.tagName("a"));
        for (WebElement esportGameElement : esportGameElements) {
            if (esportGameElement.getText().equals(value)) {
                WebElement divElement = esportGameElement.findElement(By.tagName("div"));
                clickOnElement(divElement);
                break;
            }
        }
    }

    public ArrayList getBestEsportsBettingSitesMenuItems() {
        ArrayList esportsItems = new ArrayList<>();
        waitToElementIsVisible(bestEsportBettingsSitesIconElement);
        clickOnElement(bestEsportBettingsSitesIconElement);
        waitToElementIsVisible(EsportSitesElement);
        List<WebElement> esportsMenuItemElements = EsportSitesElement.findElements(By.className("esport-menu-item"));
        for (WebElement esportsMenuItemElement : esportsMenuItemElements) {
            esportsItems.add(esportsMenuItemElement.getText());
        }
        return esportsItems;
    }

    public String getSelectedEsportMenuItem() {
        return esportDropDownButtonTextElement.getText();
    }

    public String getPageSubHeaderText() {
        return subHeaderTextElement.getText();
    }

    public String getPageDescriptionText() {
        return pageDescriptionTextElement.getText();
    }

    public String getPageNotFoundMessage() {
        Log.warn(mainContentElement.getText());
        return mainContentElement.getText().replace("/n", "");
    }

    public void waitToTitleContains(String title) {
        this.webDriverWait.until(ExpectedConditions.titleContains(title));
    }

    public String getUrl() throws InterruptedException {
        waitToElementIsVisible(webDriver.findElement(By.id("__next")));
        return webDriver.getCurrentUrl();
    }

    public String getPageUrl() {
        return webDriver.getCurrentUrl();
    }

    public String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleFormat = new SimpleDateFormat("MMM dd, yyyy");
        return simpleFormat.format(cal.getTime());
    }

    public String getPageSource() {
        return webDriver.getPageSource();
    }

    public String getRobotsTxt() {
        return webDriver.findElement(By.tagName("pre")).getText();
    }

    public String getAgeRestriction() {
        waitToElementIsVisible(ageRestrictionElement);
        scrollIntoElement(ageRestrictionElement);
        return ageRestrictionElement.getText();
    }

    public String getTitle()  {
       return webDriver.getTitle();
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        checkPageTitle("Winners.net");
    }

}
