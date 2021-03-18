package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;


public class FooterPage extends BasePage {
    @FindBy(className = "Footer")
    private WebElement footerElement;

    @FindBy(className = "bottom-part")
    private WebElement bottomPartElement;

    @FindBy(className = "footer-socials")
    private WebElement footerSocialsElement;

    @FindBy(className = "footer-winners")
    private WebElement footerWinnersElement;

    @FindBy(css = "a[aria-label='facebook']")
    private WebElement facebookIconElement;

    @FindBy(xpath = "//a[text()='Winners.Net']")
    private WebElement facebookWinnersElement;

    @FindBy(id = "react-root")
    private WebElement twitterWinnersElement;

    @FindBy(id = "block-begambleaware-branding")
    private WebElement beGambleAwareElement;

    @FindBy(id = "menu-main-menu")
    private WebElement IbiaElement;

    @FindBy(id = "support-links-menu")
    private WebElement gamblingTherapyElement;

    @FindBy(className = "age-restriction")
    private WebElement ageRestrictionElement;

    @FindBy(className = "subheader-title")
    private WebElement subHeaderElement;

    @FindBy(className = "AboutUs")
    private WebElement aboutUsElement;

    @FindBy(className = "about-us-info")
    private WebElement aboutUsPageTextElement;

    @FindBy(className = "RankingMethodology")
    private WebElement rankingMethodologyElement;

    @FindBy(id = "MainContent")
    private WebElement mainContentElement;

    @FindBy(className = "contact-info")
    private WebElement contactInfoElement;

    @FindBy(className = "PrivacyPolicy")
    private WebElement privacyPolicyElement;

    @FindBy(className = "TermsOfService")
    private WebElement termsOfServiceElement;

    @FindBy(className = "footer-bookmakers")
    private WebElement footerBookmakersElement;

    @FindBy(css = ".navigation-item.isActive")
    private WebElement headerActiveElement;

    @FindBy(css = ".EsportSites.isActive")
    private WebElement headerEsportsElement;

    @FindBy(className = "footer-newsletter")
    private WebElement footerNewsletterElement;

    @FindBy(className = "news-letter-input")
    private WebElement newsletterInputElement;

    @FindBy(className = "news-letter-button")
    private WebElement iAmInterestedButtonElement;

    @FindBy(className = "news-letter-error")
    private WebElement newsLetterErrorElement;

    @FindBy(className = "news-letter-success")
    private WebElement newsLetterSuccessElement;

    @FindBy(className = "footer-bookmaker-reviews")
    private WebElement footerBookmakerReviewsElement;

    @FindBy(className = "footer-news")
    private WebElement footerNewsElement;

    @FindBy(className = "NewsSubheader")
    private WebElement newsPageElement;



    public FooterPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean checkAllFooterElements() {
        waitToElementIsVisible(footerSocialsElement.findElement(By.className("facebook-icon")));
        waitToElementIsVisible(footerSocialsElement.findElement(By.className("twitter-icon")));
        waitToElementIsVisible(footerSocialsElement.findElement(By.className("age-restriction")));
        waitToElementIsVisible(footerSocialsElement.findElement(By.className("footer-block-list")));
        waitToElementIsVisible(footerSocialsElement.findElement(By.className("beGambleAware-icon")));
        waitToElementIsVisible(footerSocialsElement.findElement(By.className("ibia-icon")));
        waitToElementIsVisible(footerSocialsElement.findElement(By.className("gamblingTherapy-icon")));
        waitToElementIsVisible(footerWinnersElement.findElement(By.className("footer-block-list")));
        waitToElementIsVisible(footerBookmakersElement.findElement(By.className("footer-block-list")));
        waitToElementIsVisible(footerBookmakerReviewsElement.findElement(By.className("footer-block-list")));
        waitToElementIsVisible(footerNewsElement.findElement(By.className("footer-block-item")));
        waitToElementIsVisible(footerNewsletterElement.findElement(By.className("NewsLetter")));
        return true;
    }

    public String getCopyrightText() {
        waitToElementIsVisible(footerSocialsElement.findElement(By.className("copyright")));
        return footerSocialsElement.findElement(By.className("copyright")).getText();
    }

    public ArrayList getFooterBlockTitles() {
        waitToElementIsVisible(footerElement);
        scrollIntoElement(footerElement);
        ArrayList selectedItems = new ArrayList<>();
        List<WebElement>  Titles = footerElement.findElements(By.className("footer-block-title"));
        for (WebElement element : Titles) {
            selectedItems.add((element.getText()));
        }
        return selectedItems;
    }

    public String getBottomPartText() {
        waitToElementIsVisible(bottomPartElement);
        scrollIntoElement(bottomPartElement);
        return bottomPartElement.getText();
    }

    public void clickOnSocialIcons(String value) throws InterruptedException {
        scrollIntoElement(footerSocialsElement);
        waitToElementIsVisible(footerSocialsElement);
        List<WebElement> footerSocialsElements = footerSocialsElement.findElements(By.className("footer-block-item"));
        for (WebElement element : footerSocialsElements) {
            if (element.getAttribute("aria-label").equals(value)) {
                element.click();
                break;
            }
        }
    }

    public void ClickOnSocialPages(String value) throws InterruptedException {
        scrollIntoElement(footerSocialsElement);
        WebElement footerSocialsOthersElement = footerSocialsElement.findElement(By.className("footer-block-list"));
        List<WebElement> footerSocialsOtherElements = footerSocialsOthersElement.findElements(By.className("footer-block-item"));
        for (WebElement subElement : footerSocialsOtherElements) {
            if (subElement.getAttribute("aria-label").equals(value)) {
                subElement.click();
                break;
            }
        }
    }

    public void clickOnWinnersNetSubMenuPages(String value) throws InterruptedException {
        scrollIntoElement(footerWinnersElement);
        waitToElementIsVisible(footerWinnersElement);
        WebElement footerWinnersPartElement = footerWinnersElement.findElement(By.className("footer-block-list"));
        List<WebElement> footerWinnersElements = footerWinnersPartElement.findElements(By.className("footer-block-item"));
        for (WebElement element : footerWinnersElements) {
            scrollIntoElement(element);
            if (element.getText().equals((value))) {
                element.click();
                break;
            }
        }
    }


    public String getWinnersNetFacebookPageUrl() {
        waitToElementIsVisible(facebookWinnersElement);
        return webDriver.getCurrentUrl();
    }

    public String getWinnersNetTwitterPageUrl() {
        return webDriver.getCurrentUrl();
    }

    public String getBeGambleAwarePageUrl() {
        waitToElementIsVisible(beGambleAwareElement);
        return webDriver.getCurrentUrl();
    }

    public String getIbiaPageUrl() {
        waitToElementIsVisible(IbiaElement);
        return webDriver.getCurrentUrl();
    }

    public String getGamblingTherapyPageUrl() {
        waitToElementIsVisible(gamblingTherapyElement);
        return webDriver.getCurrentUrl();
    }

    public String getFooterAgeRestriction() throws InterruptedException {
        waitToElementIsVisible(ageRestrictionElement);
        scrollIntoElement(ageRestrictionElement);
        Thread.sleep(1000);
        return ageRestrictionElement.findElement(By.className("number")).getText();
    }

    public String getSubHeaderText() {
        waitToElementIsVisible(subHeaderElement);
        scrollIntoElement(subHeaderElement);
        return subHeaderElement.getText();
    }

    public ArrayList getAboutUsPageInfoByParagraphs() {
        waitToElementIsVisible(aboutUsElement);
        scrollIntoElement(aboutUsElement);
        WebElement aboutUsLogo = aboutUsElement.findElement(By.className("WinnersLogo"));
        waitToElementIsVisible(aboutUsLogo);
        ArrayList selectedItems = new ArrayList<>();
        List<WebElement> aboutUsPageParagraphs = aboutUsPageTextElement.findElements(By.tagName("p"));
        for (WebElement element : aboutUsPageParagraphs) {
            selectedItems.add(element.getText());
        }
        return selectedItems;
    }

    public ArrayList getRankingMethodologyPageInfo() {
        waitToElementIsVisible(rankingMethodologyElement);
        scrollIntoElement(rankingMethodologyElement);
        ArrayList selectedItems = new ArrayList<>();
        List<WebElement> rankingMethodologyText = rankingMethodologyElement.findElements(By.tagName("div"));
        for (WebElement element : rankingMethodologyText) {
            selectedItems.add(element.getText());
        }
        return selectedItems;
    }


    public ArrayList getContactPageInfo() {
        waitToElementIsVisible(mainContentElement);
        scrollIntoElement(mainContentElement);
        waitToElementIsVisible(mainContentElement.findElement(By.className("WinnersLogo")));
        waitToElementIsVisible(mainContentElement.findElement(By.className("phone-icon")));
        waitToElementIsVisible(mainContentElement.findElement(By.className("mail-icon")));
        waitToElementIsVisible(mainContentElement.findElement(By.className("location-icon")));
        ArrayList selectedItems = new ArrayList<>();
        List<WebElement> contactPageElement = contactInfoElement.findElements(By.tagName("div"));
        for (WebElement element : contactPageElement) {
            selectedItems.add((element.getText()));
        }
        return selectedItems;
    }

    public ArrayList getPrivacyPolicyPageInfo() {
        waitToElementIsVisible(privacyPolicyElement);
        scrollIntoElement(privacyPolicyElement);
        ArrayList selectedItems = new ArrayList<>();
        List<WebElement> privacyPolicyPageText = privacyPolicyElement.findElements(By.tagName("div"));
        for (WebElement element : privacyPolicyPageText) {
            selectedItems.add((element.getText()));
        }
        return selectedItems;
    }

    public ArrayList getTermsOfServicePageInfo() {
        waitToElementIsVisible(termsOfServiceElement);
        scrollIntoElement(termsOfServiceElement);
        ArrayList selectedItems = new ArrayList<>();
        List<WebElement> termsOfServicePageText = termsOfServiceElement.findElements(By.tagName("div"));
        for (WebElement element : termsOfServicePageText) {
            selectedItems.add((element.getText()));
        }
        return selectedItems;
    }

    public void clickOnBookmakersSubMenuPages(String value) {
        waitToElementIsVisible(footerBookmakersElement);
        scrollIntoElement(footerBookmakersElement);
        List<WebElement> footerBookmakersPartElements = footerBookmakersElement.findElements(By.className("footer-block-item"));
        for (WebElement element : footerBookmakersPartElements) {
            if (element.getText().equals((value))) {
               clickOnElement(element);
                break;
            }
        }
    }

    public String checkHeaderActiveComponent() {
        waitToElementIsVisible(headerActiveElement);
        scrollIntoElement(headerActiveElement);
        String activeElementName = headerActiveElement.getText();
        return activeElementName;
    }

    public String checkEsportsHeaderComponent() {
        waitToElementIsVisible(headerEsportsElement.findElement(By.className("esport-dropdown-button-text")));
        scrollIntoElement(headerEsportsElement);
        String activeEsportsName = headerEsportsElement.findElement(By.className("esport-dropdown-button-text")).getText();
        return activeEsportsName;
    }

    public String getNewsletterHeaderText() {
        waitToElementIsVisible(footerNewsletterElement);
        scrollIntoElement(footerNewsletterElement);
        String newsletterHeaderText = footerNewsletterElement.findElement(By.className("footer-block-title")).getText();
        return newsletterHeaderText;
    }

    public String getNewsletterDescriptionText() {
        waitToElementIsVisible(footerNewsletterElement);
        scrollIntoElement(footerNewsletterElement);
        String newsletterDescriptionText = footerNewsletterElement.findElement(By.className("news-letter-paragraph")).getText();
        return newsletterDescriptionText;
    }

    public void newsletterInput(String value) {
        waitToElementIsVisible(newsletterInputElement);
        newsletterInputElement.clear();
        newsletterInputElement.sendKeys(value);
    }


    public void clickOnIamInterestedButton() {
        waitToElementIsVisible(iAmInterestedButtonElement);
        clickOnElement(iAmInterestedButtonElement);
    }

    public String getNewsletterErrorText() {
        waitToElementIsVisible(newsLetterErrorElement);
        return newsLetterErrorElement.getText();
    }

    public String getNewsletterSuccessText() throws InterruptedException {
        //Thread.sleep(2000);
        waitToElementIsVisible(newsLetterSuccessElement);
        return newsLetterSuccessElement.getText();
    }

    public void clickOnReviewsSubMenuElement(String value) {
        waitToElementIsVisible(footerBookmakerReviewsElement);
        scrollIntoElement(footerBookmakerReviewsElement);
        List<WebElement> footerBookmakersReviewElements = footerBookmakerReviewsElement.findElements(By.className("footer-block-item"));
        for (WebElement element : footerBookmakersReviewElements) {
            if (element.getText().equals((value))) {
                element.click();
                //clickOnElement(element);
                break;
            }
        }
    }


    public String getNewsPageSubHeader() {
        waitToElementIsVisible(footerNewsElement);
        scrollIntoElement(footerNewsElement);
        footerNewsElement.findElement(By.className("footer-block-item")).click();
        waitToElementIsVisible(newsPageElement);
        return newsPageElement.getText();
    }

    public void footerItems() {
        scrollIntoElement(footerElement);
        waitToElementIsVisible(bottomPartElement);
        waitToElementIsVisible(footerNewsletterElement);
        waitToElementIsVisible(footerWinnersElement);
        waitToElementIsVisible(footerBookmakersElement);
        waitToElementIsVisible(footerBookmakerReviewsElement);
        waitToElementIsVisible(footerNewsElement);
        waitToElementIsVisible(footerSocialsElement);
    }

    public ArrayList getContentByTitle(String startTitle, String endTitle) throws InterruptedException {
        ArrayList contentItems = new ArrayList();
        String xpath;
        if (endTitle == null) {
            xpath = "//div[text() = " + "'" + startTitle + "'" + "]/following-sibling::*";
        } else {
            xpath = "//div[text() = " + "'" + startTitle + "'" + "]/following-sibling::*[following::div[text() ="
                    + "'" + endTitle + "'" + "]]";
        }
        List<WebElement> contentRowsElements = webDriver.findElements(By.xpath(xpath));
        for (WebElement contentRowElement : contentRowsElements) {
            if (contentRowElement.getTagName().equals("p")) {
                if (contentRowElement.getText().equals("")) {
                    continue;
                }
                contentItems.add(contentRowElement.getText());
            }
            if (contentRowElement.getTagName().equals("ul")) {
                for (WebElement liElement : contentRowElement.findElements(By.tagName("li")))
                    contentItems.add(liElement.getText());
            }
            System.out.println(contentItems);
        }
        return contentItems;
        }
    }
    



