package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Log;


import java.util.ArrayList;
import java.util.List;

public class FilterPage extends BasePage {

    public static String selectedCountryInSubHeader;

    @FindBy(className = "Filter")
    private WebElement filterElement;

    @FindBy(className = "FilterMenu")
    private WebElement filterMenuElement;

    @FindBy(className = "Search")
    private WebElement searchBookmakerElement;

    @FindBy(className = "bookmaker-list-items")
    private WebElement bookmakersListElement;

    @FindBy(className = "CountrySelect")
    private WebElement selectCountryElement;

    @FindBy(css = ".FilterCashout .RadioButton")
    private List<WebElement> cashoutAvailableElements;

    @FindBy(xpath = "//label[text()='Yes']")
    private WebElement yesElement;

    @FindBy(className = "WinnersLogo")
    private WebElement winnersLogoElement;

    @FindBy(className = "react-dropdown-select-dropdown-handle")
    private WebElement countrySelectElement;

    @FindBy(id = "selenium-test-search-Licensed-country")
    private WebElement searchLicensedCountry;

    @FindBy(id = "selenium-test-filter-country-id")
    private WebElement countryFieldElement;

    @FindBy(css = ".tt-subheader-country-select .react-dropdown-select-content")
    private WebElement countryInSubHeaderElement;

    @FindBy(css = ".tt-subheader-country-select .search-items")
    private WebElement selectCountryInSubHeaderElement;

    @FindBy(css = ".tt-subheader-country-select .Search input")
    private WebElement fillCountryInSubHeaderElement;


    @FindBy(css = ".tt-subheader-country-select-state .react-dropdown-select-content")
    private WebElement countryStateInSubHeaderElement;

    @FindBy(css = ".tt-subheader-country-select-state .search-item")
    private List<WebElement> selectCountryStateInSubHeaderElement;

    @FindBy(css = ".tt-subheader-country-select-state .Search input")
    private WebElement fillCountryStateInSubHeaderElement;


    @FindBy(css = ".tt-filter-country-select .react-dropdown-select-content")
    private WebElement countryFilterSectionElement;

    @FindBy(css = ".tt-filter-country-select .search-items")
    private WebElement countryFilterItemsElement;

    @FindBy(css = ".tt-filter-country-select .Search input")
    private WebElement fillCountryFilterElement;


    @FindBy(css = ".tt-license-country-select .react-dropdown-select-content")
    private WebElement licenseCountryFilterSectionElement;

    @FindBy(css = ".tt-license-country-select .search-items")
    private WebElement licenseCountryFilterItemsElement;

    @FindBy(css = ".tt-license-country-select .Search input")
    private WebElement licenseCountryFilterElement;

    @FindBy(css = ".tt-subheader-country-select .react-dropdown-select-content .search-content .search-content-text")
    private WebElement countryIsElement;

    @FindBy(css = ".tt-subheader-country-select-state .react-dropdown-select-content .search-content .search-content-text")
    private WebElement stateIsElement;

    @FindBy(className = "bookmaker-list-empty")
    private WebElement emptyBookmakerListTextElement;

    @FindBy(className = "RadarChart")
    private WebElement comparisonChartElement;

    @FindBy(className = "close-icon")
    private WebElement searchFieldCleanElement;


    public FilterPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getEmptyListText() {
        waitToElementIsVisible(emptyBookmakerListTextElement);
        return emptyBookmakerListTextElement.getText();
    }

    public boolean getChartElement() {
        waitToElementIsVisible(comparisonChartElement);
        return true;
    }

    public String getFilteredCountry(String countryCode) {
        fillText(fillCountryInSubHeaderElement, countryCode);
        WebElement filteredItem = webDriver.findElement(By.className("search-item-text"));
        return filteredItem.getText();
    }

    public void selectCountryByCountryCode(String countryCode) {
        fillText(fillCountryInSubHeaderElement,countryCode);
        clickOnElement(webDriver.findElement(By.className("search-item")));
    }

    public void clickOnBestBettingSitesForDropdown() {
        clickOnElement(countryInSubHeaderElement);
    }

    public void selectCountryInHeader(String value) {
        waitToElementIsVisible(countryInSubHeaderElement);
        clickOnElement(countryInSubHeaderElement);
        fillText(fillCountryInSubHeaderElement, value);
        List<WebElement> searchItemElements = selectCountryInSubHeaderElement.findElements(By.className("search-item"));
        for (WebElement searchItemElement : searchItemElements) {
            if (searchItemElement.getText().equals(value)) {
                clickOnElement(searchItemElement);
                break;
            }

        }
    }

    public void selectCountryStateInHeader(String value) {
        waitToElementIsVisible(countryStateInSubHeaderElement);
        clickOnElement(countryStateInSubHeaderElement);
        fillText(fillCountryStateInSubHeaderElement, value);
            for (WebElement searchItemElement : selectCountryStateInSubHeaderElement) {
                if (searchItemElement.getText().equals(value)) {
                    clickOnElement(searchItemElement);
                }
                break;
            }
    }

    public ArrayList getStates() {
        clickOnElement(countryStateInSubHeaderElement);
        ArrayList selectedItems = new ArrayList<>();
        for (WebElement itemElements: selectCountryStateInSubHeaderElement) {
            selectedItems.add(itemElements.getText());
        }
        Log.warn(String.valueOf(selectedItems));
        return selectedItems;
    }

    public void selectCountryInFilterSection(String value) {
        waitToElementIsVisible(countryFilterSectionElement);
        clickOnElement(countryFilterSectionElement);
        fillText(fillCountryFilterElement, value);
        List<WebElement> searchItemElements = countryFilterItemsElement.findElements(By.className("search-item"));
        if (searchItemElements.size() == 1)
            for (WebElement searchItemElement : searchItemElements) {
                if (searchItemElement.getText().equals(value))
                    clickOnElement(searchItemElement);
            }
    }

    public void selectCountryInLicenseCountry(String value) {
        waitToElementIsVisible(licenseCountryFilterSectionElement);
        clickOnElement(licenseCountryFilterSectionElement);
        fillText(licenseCountryFilterElement, value);
        List<WebElement> searchItemElements = licenseCountryFilterItemsElement.findElements(By.className("search-item"));
        if (searchItemElements.size() == 1)
            for (WebElement searchItemElement : searchItemElements) {
                if (searchItemElement.getText().equals(value))
                    clickOnElement(searchItemElement);
            }
    }


    public void clickOnSearchIconsBySectionName(String sectionName) {
        List<WebElement> filterDropdownElements = filterElement.findElements(By.className("FilterDropdown"));
        for (WebElement filterDropDownElement : filterDropdownElements) {
            if (filterDropDownElement.getText().equals(sectionName)) {
                List<WebElement> divs = filterDropDownElement.findElements(By.tagName("div"));
                for (WebElement div : divs) {
                    if (div.getAttribute("class").equals("Search Expandable")) {
                        scrollIntoElement(div);
                        clickOnElement(div);
                    }
                }
                break;
            }
        }
    }

    public void clickOnExpandIconsBySectionName(String sectionName) {
        List<WebElement> filterDropdownElements = filterElement.findElements(By.className("FilterDropdown"));
        for (WebElement filterDropDownElement : filterDropdownElements) {
            if (filterDropDownElement.getText().equals(sectionName)) {
                List<WebElement> divs = filterDropDownElement.findElements(By.tagName("div"));
                for (WebElement div : divs) {
                    if (div.getAttribute("class").equals("filter-dropdown-button")) {
                        scrollIntoElement(div);
                        clickOnElement(div);
                        waitToElementIsVisible(webDriver.findElement(By.className("DropdownOpen")));
                    }
                }
                break;
            }
        }
    }


    public void fillValueOnFilterSection(String sectionName, String value) {
        List<WebElement> filterDropdownElements = filterElement.findElements(By.className("FilterDropdown"));
        for (WebElement filterDropDownElement : filterDropdownElements) {
            if (filterDropDownElement.getText().contains(sectionName)) {
                List<WebElement> divs = filterDropDownElement.findElements(By.tagName("div"));
                for (WebElement div : divs) {
                    if (div.getAttribute("class").equals("Search Expandable Expanded")) {
                        WebElement input = div.findElement(By.tagName("input"));
                        scrollIntoElement(input);
                        fillText(input, value);
                        waitToElementIsVisible(webDriver.findElement(By.className("DropdownOpen")));
                        break;
                    }
                }
            }
            break;
        }
    }

    public void selectCheckbox(String sectionName, String value)  {
        scrollIntoElement(filterElement);
        waitToElementIsVisible(filterElement);
        WebElement dropDownElement = webDriver.findElement(By.className("DropdownOpen"));
        waitToElementIsVisible(dropDownElement);
        scrollIntoElement(webDriver.findElement(By.className("DropdownOpen")));
        if (dropDownElement.getText().contains(sectionName)) {
            List<WebElement> divs = dropDownElement.findElements(By.tagName("div"));
            for (WebElement div : divs) {
                scrollIntoElement(selectCountryElement);
                if (div.getAttribute("class").equals("FilterMenu")) {
                    List<WebElement> filterMenuItems = div.findElements(By.className("filter-menu-item"));
                    for (WebElement filterMenuItem : filterMenuItems) {
                        if (filterMenuItem.getText().equals(value)) {
                            WebElement checkbox = filterMenuItem.findElement(By.className("Checkbox"));
                            scrollIntoElement(checkbox);
                            clickOnElement(checkbox);
                        }
                    }
                    break;
                }
            }
        }
    }


    public void selectValueForCashoutAvailableSection(String value) {
        for (WebElement cashoutAvailableElement : cashoutAvailableElements) {
            if (cashoutAvailableElement.getText().equals(value)) {
                WebElement radioButtonElement = cashoutAvailableElement.findElement(By.tagName("label"));
                clickOnElement(radioButtonElement);
                break;
            }
        }
    }

    public void selectCountryInLicenseCountryFilterSection(String value) {
        List<WebElement> searchItemElements = webDriver.findElement(By.className("search-items")).findElements(By.className("search-item"));
    }

    public ArrayList getCheckedFilterItemsBySectionName(String sectionName) throws InterruptedException {
        Thread.sleep(4000);
        WebElement dropDownElement = webDriver.findElement(By.className("DropdownOpen"));
        waitToElementIsVisible(dropDownElement);
        scrollIntoElement(dropDownElement);
        ArrayList selectedItems = new ArrayList<>();
        if (dropDownElement.getText().contains(sectionName)) {
            waitToElementIsVisible(dropDownElement);
            List<WebElement> divs = dropDownElement.findElements(By.tagName("div"));
            for (WebElement div : divs)
                if (div.getAttribute("class").equals("FilterMenu")) {
                    List<WebElement> checkBoxItems = div.findElements(By.className("filter-menu-item"));
                    for (WebElement checkBoxItem : checkBoxItems) {
                        if (checkBoxItem.getAttribute("class").equals("filter-menu-item")) {
                            WebElement checkbox = checkBoxItem.findElement(By.className("Checkbox"));
                            if (checkbox.findElement(By.tagName("input")).isSelected()) {
                                selectedItems.add(checkbox.getText());
                            }
                        }
                    }
                }
        }
        return selectedItems;
    }

    public String getCheckedCashoutAvailableSectionItem() throws InterruptedException {
        Thread.sleep(4000);
        String selectedItem = null;
        for (WebElement radioButtonElement : cashoutAvailableElements) {
            WebElement element = radioButtonElement.findElement(By.tagName("input"));
            if (element.isSelected()) {
                selectedItem = radioButtonElement.getText();
                break;
            }
        }
        return selectedItem;
    }

    public void clickOnShowAllButtonBySectionName(String sectionName) throws InterruptedException {
        Thread.sleep(4000);
        WebElement dropDownElement = webDriver.findElement(By.className("DropdownOpen"));
        scrollIntoElement(dropDownElement);
        if (dropDownElement.getText().contains(sectionName)) {
            waitToElementIsVisible(dropDownElement);
            List<WebElement> divs = dropDownElement.findElements(By.tagName("div"));
            for (WebElement div : divs) {
                if (div.getAttribute("class").equals("FilterMenu")) {
                    List<WebElement> filterMenuItems = div.findElements(By.className("filter-menu-item"));
                    for (WebElement filterMenuItem : filterMenuItems) {
                        if (filterMenuItem.getAttribute("class").equals("filter-menu-item show-all")) {
                            clickOnElement(filterMenuItem);
                        }
                    }
                    break;
                }
            }
        }
    }

    public int getFilterItemsCountBySectionName(String sectionName) throws InterruptedException {
        Thread.sleep(4000);
        int filterItemsCount = 0;
        WebElement dropDownElement = webDriver.findElement(By.className("DropdownOpen"));
        waitToElementIsVisible(dropDownElement);
        scrollIntoElement(dropDownElement);
        if (dropDownElement.getText().contains(sectionName)) {
            waitToElementIsVisible(dropDownElement);
            List<WebElement> divs = dropDownElement.findElements(By.tagName("div"));
            for (WebElement div : divs)
                if (div.getAttribute("class").equals("FilterMenu")) {
                    List<WebElement> checkBoxItems = div.findElements(By.className("filter-menu-item"));
                    filterItemsCount = checkBoxItems.size() - 1;
                }
        }
        return filterItemsCount;
    }

    public void clickOnWinnersLogo() {
        clickOnElement(winnersLogoElement);
    }

    public void searchBookmakerName(String value) {
        searchBookmakerElement.findElement(By.tagName("input")).clear();
        searchBookmakerElement.findElement(By.tagName("input")).sendKeys(value);
        //waitToElementIsVisible(searchFieldCleanElement);
        //waitToElementClickable(searchFieldCleanElement);
    }

    public String getSelectedLicenseCountry() {
        waitToElementIsVisible(licenseCountryFilterSectionElement);
        return licenseCountryFilterSectionElement.getText();
    }


    public String getSelectedCountryInFilter() {
        return countryFilterSectionElement.getText();
    }

    public String getSelectedCountryInSubHeader() {
        selectedCountryInSubHeader = countryInSubHeaderElement.getText();
        return selectedCountryInSubHeader;
    }

    public String countryIs() {
        waitToElementIsVisible(countryIsElement);
        return countryIsElement.getText();
    }
    public String stateIs() {
        waitToElementIsVisible(stateIsElement);
        return stateIsElement.getText();
    }
}


