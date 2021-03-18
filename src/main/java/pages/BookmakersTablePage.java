package pages;
////////new

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Log;

import java.util.ArrayList;
import java.util.List;



public class BookmakersTablePage<bookmakersTmpList> extends BasePage {

    @FindBy(className = "bookmaker-list-items")
    private WebElement bookmakersListElement;

    @FindBy(xpath = "//div[text()='Utility Features']")
    private WebElement sortByDropdownElement;

    @FindBy(className = "bookmaker-list-empty")
    private WebElement bookmakerListEmptyElement;

    @FindBy(className = "bookmaker-item-score")
    private List<WebElement> bookmakerItemScoreElements;

    @FindBy(className = "search-items")
    private WebElement searchItemsElement;

    @FindBy(xpath = "//a[@class='flex-all-center']")
    private List<WebElement> bookmakerNameElements;

    @FindBy(className = "BookmakerItem")
    private List<WebElement> bookmakerItemElements;

    @FindBy(className = "bookmaker-bonus-review")
    private WebElement readFullReviewButtonElement;

    @FindBy(className = "bookmaker-bonus-claim")
    private WebElement claimYourBonusButtonElement;

    public BookmakersTablePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnBookmakerReview(String value) {
        waitToElementIsVisible(bookmakersListElement);
        List<WebElement> bookmakerListItems = bookmakersListElement.findElements(By.className("flex-all-center"));
        for (WebElement bookmakerListItem : bookmakerListItems) {
            if (bookmakerListItem.getAttribute("aria-label").equals(value)) {
                clickOnElement(bookmakerListItem);
                break;
            }
        }
    }

    public ArrayList getBookmakersName() throws InterruptedException {
        Thread.sleep(2000);
        ArrayList bookmakersList = new ArrayList<>();
        List<WebElement> bookmakerItems = bookmakersListElement.findElements(By.className("BookmakerItem"));
        for (WebElement bookmakerItem : bookmakerItems) {
            WebElement bookmakerItemBookmakerElement = bookmakerItem.findElement(By.className("bookmaker-item-bookmaker")).findElement(By.tagName("a"));
            if (bookmakerItemBookmakerElement.getAttribute("next-link").equals("true")) {
                bookmakersList.add(bookmakerItemBookmakerElement.getAttribute("aria-label"));
            }
        }
        Log.warn(String.valueOf(bookmakersList));
        return bookmakersList;
    }

    public boolean checkEmptyBookmakersTable() {
        waitToElementIsVisible(bookmakerListEmptyElement);
        return true;
    }

    public void selectValueInSortByDropDown(String value) throws InterruptedException {
        Thread.sleep(5000);
        clickOnElement(webDriver.findElement(By.className("RankingSelector")));
        waitToElementIsVisible(searchItemsElement);
        List<WebElement> searchItemElements = searchItemsElement.findElements(By.className("search-item"));
        for (WebElement searchItemElement : searchItemElements) {
            if (searchItemElement.getText().equals(value)) {
                clickOnElement(searchItemElement);
                break;
            }
        }
    }

    public String valueInSortByDropDown() throws InterruptedException {
        Thread.sleep(5000);
        clickOnElement(webDriver.findElement(By.className("RankingSelector")));
        waitToElementIsVisible(searchItemsElement);
        WebElement itemElement = searchItemsElement.findElement(By.xpath("//*[@id=\"MainContent\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[3]/div"));
        return itemElement.getText();
    }

    public void clickOnSortByDropDown(){
        clickOnElement(sortByDropdownElement);
    }

    public void selectBookmakerFromBookmakersTable(String bookmakerName) {
        for (WebElement bookmakerNameElement : bookmakerNameElements) {
            if (bookmakerNameElement.getAttribute("aria-label").equals(bookmakerName)) {
                scrollIntoElement(bookmakerNameElement);
                clickOnElement(bookmakerNameElement);
                waitToElementIsVisible(webDriver.findElement(By.className("ReviewNavBarItem")));
                break;
            }
        }
    }

    public ArrayList getSortByDropDownOptions() {
        ArrayList dropDownOptionsList = new ArrayList<>();
        List<WebElement> dropDownOptionElements = searchItemsElement.findElements(By.className("search-item"));
        for (WebElement dropDownOptionElement : dropDownOptionElements) {
            dropDownOptionsList.add(dropDownOptionElement.getText());
        }
        return dropDownOptionsList;
    }

    public void clickOnButtonsByBookmakerName(String bookmakerName, String buttonName) throws InterruptedException {
        Thread.sleep(5000);
        WebElement bookmakerItemElement = webDriver.findElement(By.xpath("//a[@class='flex-all-center'][@aria-label=" + "'" + bookmakerName + "'" +
                "]/ancestor::div[@class='bookmaker-item-bookmaker']/ancestor::div[@class='BookmakerItem selectable']"));
        WebElement buttonElement = switch (buttonName) {
            case "Website" -> bookmakerItemElement.findElement(By.className("bookmaker-item-website"));
            case "Full Review" -> bookmakerItemElement.findElement(By.className("bookmaker-item-review"));
            case "Plus" -> bookmakerItemElement.findElement(By.className("bookmaker-item-select"));
            case "Expand" -> bookmakerItemElement.findElement(By.className("bookmaker-item-toggle"));
            case "Logo" -> bookmakerItemElement.findElement(By.className("bookmaker-logo"));
            default -> throw new IllegalArgumentException("Content not found");
        };
        scrollIntoElement(buttonElement);
        clickOnElement(buttonElement);
    }

    public void clickOnButtonsByBookmakerNameOnSportsBettingSitesPage(String bookmakerName, String buttonName) throws InterruptedException {
        Thread.sleep(5000);
        WebElement bookmakerItemElement = webDriver.findElement(By.xpath("//a[@class='flex-all-center'][@aria-label='" + bookmakerName + "'" +
                "]/ancestor::div[@class='bookmaker-item-bookmaker']/ancestor::div[@class='BookmakerItem']"));
        WebElement buttonElement = switch (buttonName) {
            case "Website" -> bookmakerItemElement.findElement(By.className("bookmaker-item-website"));
            case "Full Review" -> bookmakerItemElement.findElement(By.className("bookmaker-item-review"));
            case "Plus" -> bookmakerItemElement.findElement(By.className("bookmaker-item-select"));
            case "Expand" -> bookmakerItemElement.findElement(By.className("bookmaker-item-toggle"));
            case "Logo" -> bookmakerItemElement.findElement(By.className("bookmaker-logo"));
            default -> throw new IllegalArgumentException("Content not found");
        };
        scrollIntoElement(buttonElement);
        clickOnElement(buttonElement);
    }

    public String getScoreColorByBookmakerName(String bookmakerName) throws InterruptedException, IllegalArgumentException {
        Thread.sleep(6000);
        WebElement bookmakerItemElement = webDriver.findElement(By.xpath("//a[@class='flex-all-center'][@aria-label=" + "'" + bookmakerName + "'" +
                "]/ancestor::div[@class='bookmaker-item-bookmaker']/ancestor::div[@class='BookmakerItem selectable']"));
        scrollIntoElement(bookmakerItemElement);
        WebElement bookmakerItemScoreElement = bookmakerItemElement.findElement(By.className("bookmaker-item-score"));
        String scoreText = bookmakerItemScoreElement.getText().split("/")[0].trim();
        float score = Float.parseFloat(scoreText);
        String color = null;
        if (score <= 10 && score >= 8) {
            color = "Strong green";
        }
        if (score <= 8 && score >= 6) {
            color = "Citrus";
        }
        if (score <= 6 && score >= 3) {
            color = "Sunglow";
        }
        if (score <= 3 && score >= 0) {
            color = "Radical Red";
        }
        Log.warn("color is " + color);
        return color;
    }

    public String getSelectedItemInSortByDropDown() {
        String selectedValue = null;
        List<WebElement> searchItem = searchItemsElement.findElements(By.className("search-item"));
        for (WebElement element : searchItem)
            if (element.getAttribute("class").contains("selected")) {
                selectedValue = element.getText();
            }
        return selectedValue;
    }

    public boolean checkIfBookmakerScoresAreDescendingSorted() {
        ArrayList<Float> bookmakersScore = new ArrayList<Float>();
        for (WebElement bookmakerScoreElement : bookmakerItemScoreElements) {
            float score = Float.parseFloat(bookmakerScoreElement.getText().split("/")[0].trim());
            bookmakersScore.add(score);
        }
        boolean sorted = true;
        for (int i = 1; i < bookmakersScore.size(); i++) {
            if (bookmakersScore.get(i) - (bookmakersScore.get(i - 1)) > 0) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public int getBookmakersCount() throws InterruptedException {
       return bookmakerItemElements.size();
    }


    public boolean isBookmakerExpanded(String bookmakerName) {
        try {
            webDriver.findElement(By.xpath("//a[@class='flex-all-center'][@aria-label=" + "'" + bookmakerName + "']" + "/ancestor::div[@class='BookmakerItem ShowContent']"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public List<String> getNumberOfCheckedBookmakers() throws InterruptedException {
        List<String> bookmakersTmpList = new ArrayList<>();
        Integer cnt = 0;
        Thread.sleep(1000);
        for (int i = 1; i < getBookmakersCount()+1; ++i) {
            Thread.sleep(300);

            WebElement el = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div["+i+"]/div[4]"));
            if (el.getAttribute("title")== "") {
                ++cnt;
                WebElement elBookmakerName = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div[" + i + "]/div[1]/a/img"));
                bookmakersTmpList.add((String) elBookmakerName.getAttribute("title"));
            }
        }
        System.out.println("\n"+cnt+" Bookmakers were checked out of "+getBookmakersCount());
        System.out.println(bookmakersTmpList);
        return (bookmakersTmpList);
    }

    public Integer getNumberOfDisabledBookmakers() throws InterruptedException {

        Integer cnt = 0;
        Thread.sleep(1000);
        for (int i = 1; i < getBookmakersCount()+1; ++i) {
            Thread.sleep(300);
            WebElement el = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div["+i+"]/div[4]"));
            if (el.getAttribute("outerHTML").contains("disabled")) { ++cnt; }
        }
        System.out.println("\n"+cnt+" Bookmakers were disabled out of "+getBookmakersCount());
        return (getBookmakersCount()-cnt);
    }

    public boolean bookmakerNameInBookmakerTable (String args){
        boolean a = false;
        for (int i=0; i<bookmakerItemElements.size();++i){
            if (args.equals(bookmakerItemElements.get(i))){
                a= true;
                break;
             }else{
                a= false;
            }
        }
        return a;
    }
 }










