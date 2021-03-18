package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.Log;

import java.util.List;

public class AnalysisPage extends BasePage {

    @FindBy(className = "article-title")
    private List<WebElement> articleTitleElements;

    @FindBy(className = "NewsSubheader")
    private WebElement analysisPageSubheaderElement;

    @FindBy(className = "article-text")
    private List<WebElement> articleTextElements;

    public AnalysisPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getAnalysisSubheaderText() {
        return analysisPageSubheaderElement.getText();
    }

    public void selectArticle(String articleName) {
        for (WebElement articleElement : articleTitleElements) {
            waitToElementIsVisible(articleElement);
            Log.warn("Article name " + articleName);
            if (articleElement.getText().equals(articleName)) {
                clickOnElement(articleElement);
                break;
            }
        }
    }

    public void selectArticleByNumber(int num) {
        waitToElementIsVisible(articleTitleElements.get(num));
        scrollIntoElement(articleTitleElements.get(num));
        articleTitleElements.get(num).click();
    }

    public String getSelectedTagForArticle(String articleName) {
        String selectedTag = null;
        for (WebElement articleTextElement : articleTextElements) {
            waitToElementIsVisible(articleTextElement);
            if (articleTextElement.getText().contains(articleName)) {
                selectedTag = articleTextElement.findElement(By.className("article-tag")).getText();
            }
        }
        return selectedTag;
    }

    public String getFirstArticleName() {
        return articleTitleElements.get(0).getText();
    }

    public String getSelectedInfoByArticleNameAndInfoName(String articleName, String infoName) {
        WebElement webElement = null;
        for (WebElement articleTextElement : articleTextElements) {
            waitToElementIsVisible(articleTextElement);
            if (articleTextElement.getText().contains(articleName)) {
                webElement = switch (infoName) {
                    case "article subtext" -> articleTextElement.findElement(By.className("article-subtext"));
                    case "article info" -> articleTextElement.findElement(By.className("article-info"));
                    case "article tag" -> articleTextElement.findElement(By.className("article-info")).findElement(By.className("article-tag"));
                    default -> throw new IllegalArgumentException();
                };
            }
        }
        assert webElement != null;
        return webElement.getText().replace("\n", " ");
    }

    public float getImageSize() throws InterruptedException {
        Thread.sleep(2000);
        String imageSrc = webDriver.findElements(By.xpath("//img")).get(1).getAttribute("src");
        imageSrc.contains("progressive");
        String imageWidth = imageSrc.split("/h/")[0].split("w/")[1];
        String imageHeight = imageSrc.split("/h/")[1].split("/format")[0];
        int width = Integer.parseInt(imageWidth);
        int height = Integer.parseInt(imageHeight);
        return (float) width / (float) height;
    }

    public boolean isArticleNotVisible(String articleName) {
        try {
            webDriver.findElement(By.xpath("//div[@class='article-title'][text()=" + "'" + articleName + "']"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void openArticleInaNewTab(String articleName) throws InterruptedException {
        String clickl = Keys.chord(Keys.CONTROL,Keys.ENTER);
        for (WebElement articleElement : articleTextElements) {
            waitToElementIsVisible(articleElement);
            if (articleElement.getText().contains(articleName)) {
                Actions actions = new Actions(webDriver);
                actions.moveToElement(articleElement);
                articleElement.sendKeys(clickl);
                break;
            }
        }
    }

    //ActionChains(driver).move_to_element(link).key_down(Keys.COMMAND).click(link).key_up(Keys.COMMAND).perform()


    public void openArticle() throws InterruptedException {
        String opeTab = Keys.chord(Keys.CONTROL,Keys.ENTER);
        webDriver.findElement(By.cssSelector(".WinnersLogo")).sendKeys(opeTab);
    }

}
