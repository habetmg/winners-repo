package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickCardPage extends BasePage {

    @FindBy(className = "QuickCardContainer")
    private List<WebElement> quickCardContainerElements;

    public QuickCardPage(WebDriver webDriver) {
        super(webDriver);
    }

    public List<String> getQuickCardInfo(String bookmakerName, int place) {
        WebElement webElement;
        String xpath = null;
        webElement = quickCardContainerElements.get(place - 1).findElement(By.tagName("img"));
        if (webElement.getAttribute("title").equals(bookmakerName)) {
            xpath = "//img[@title='" + bookmakerName + "']/ancestor::div[@class='QuickCardContainer']";
        }

        assert xpath != null;
        WebElement quickCardElement = webDriver.findElement(By.xpath(xpath));
        scrollIntoElement(quickCardElement);
        String[] str = quickCardElement.getText().split("\n");
        List<String> contentItems = new ArrayList<String>();
        contentItems = Arrays.asList(str);
        System.out.println(contentItems);
        return contentItems;
    }

    public void clickOnQuickCardButton(String bookmakerName, String buttonName) {
        WebElement webElement;
        String xpath = null;
        for (WebElement quickCardContainer : quickCardContainerElements) {
            webElement = quickCardContainer.findElement(By.tagName("img"));
            if (webElement.getAttribute("title").equals(bookmakerName)) {
                xpath = "//img[@title='" + bookmakerName + "']/ancestor::div[@class='QuickCardContainer']";
                break;
            }
        }
        assert xpath != null;
        WebElement quickCardElement = webDriver.findElement(By.xpath(xpath));
        WebElement buttonElement;
        scrollIntoElement(quickCardElement);
        buttonElement = switch (buttonName) {
            case "website" -> quickCardElement.findElement(By.cssSelector(".buttons .website-btn"));
            case "review" -> quickCardElement.findElement(By.cssSelector(".buttons .review-btn"));
            default -> throw new IllegalStateException("Unexpected value: " + buttonName);
        };
        scrollIntoElement(buttonElement);
        clickOnElement(buttonElement);
    }

    public ArrayList getQuickCardTags(String bookmakerName) {
        ArrayList tagNames = new ArrayList();
        WebElement webElement;
        String xpath = null;
        for (WebElement quickCardContainer : quickCardContainerElements) {
            webElement = quickCardContainer.findElement(By.tagName("img"));
            if (webElement.getAttribute("title").equals(bookmakerName)) {
                xpath = "//img[@title='" + bookmakerName + "']/ancestor::div[@class='QuickCardContainer']";
                break;
            }
        }
        assert xpath != null;
        WebElement quickCardElement = webDriver.findElement(By.xpath(xpath));
        scrollIntoElement(quickCardElement);
        List<WebElement> tagElements = quickCardElement.findElement(By.className("tags")).findElements(By.tagName("img"));
        for(WebElement tagElement: tagElements) {
            tagNames.add(tagElement.getAttribute("title"));
        }

        return tagNames;
    }

    public void hoverOnBookmakerImage() {
        WebElement logoElement = webDriver.findElement(By.className("bookmaker-item-select"));
        Actions actions = new Actions(webDriver);
        scrollIntoElement(logoElement);
        actions.moveToElement(logoElement);
    }

    public boolean isElementPresent() {
        try {
            webDriver.findElement(By.className("QuickCard"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public int quickCardCount() {
        return quickCardContainerElements.size();
    }
}
