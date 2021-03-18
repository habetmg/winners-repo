package pages;

import org.apache.commons.math3.util.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparisonPage extends BasePage {

    @FindBy(className = "ButtonCompare")
    private WebElement compareWithOthersButtonElement;

    public String currentUrl0;

    public ComparisonPage(WebDriver webDriver) {
        super(webDriver);
    }

    //Coordinate check test methods
    Double dFrmt=100000000000.0;

    public List<Pair<Double, Double>> getComparisonChartCoordinates() {
        List<WebElement> el = webDriver.findElements(By.xpath("//*[local-name()='svg' and @xmlns='http://www.w3.org/2000/svg']/*[local-name()='g'] /*[local-name()='g']/*[local-name()='g']"));
        List<Pair<Double, Double>> lstCoorDom = new ArrayList<>();
        for (WebElement z : el) {
            String j = z.getAttribute("innerHTML");
            if (j.contains("<path d=\"M0") && j.contains("Z\"")) {
                String[] pairs = j.substring(j.indexOf("<path d=\"M0"), j.indexOf("Z\""))
                        .replace("<path d=\"M", "").replace("z\"", "")
                        .replace(",", " ").split("L", 0);
                for (String pairStr : pairs) {
                    String[] pair = pairStr.split(" ");
                    lstCoorDom.add(Pair.create(Math.round(Double.parseDouble(pair[0])*dFrmt)/dFrmt,
                            Math.round(Double.parseDouble(pair[1])*dFrmt)/dFrmt));
                }
            }
        }
        return lstCoorDom;
    }

    public Number getOverallRatingFromApi(List<Number> args) {

        Number apiOverall = args.get(0);
        args.remove(0);
        System.out.println("\nAPI Overall Rating: " + apiOverall + "; Ratings by Services: " + args+"\n");
        return apiOverall;
    }
    public Double calculateOverallRatingOnDomCoordinates(List<Number> args) {

        args.remove(0);
        double overallRatingDom = 0.0;        double sumOfHypotenuses = 0.0;

        for (int i = 0; i < getComparisonChartCoordinates().size(); ++i) {
            sumOfHypotenuses = sumOfHypotenuses + Math.sqrt(Math.pow(getComparisonChartCoordinates().get(i).getFirst(), 2)
                    + Math.pow(getComparisonChartCoordinates().get(i).getSecond(), 2));
        }
        overallRatingDom = Math.round(10 * sumOfHypotenuses / (190 * getComparisonChartCoordinates().size())*10.0)/10.0;
        //*10 for adjusting with 'a/10' format of overall rating on the site

        return overallRatingDom;
    }

    public List<Pair<Double, Double>> createCoordinatesOnApiRatings(List<Number> args) {
        List<Pair<Double, Double>> lstCoorApi = new ArrayList<>();

        args.remove(0);
        for (int i = 0; i < args.size(); i++) {
            lstCoorApi.add
                    (Pair.create(
                            Math.round(args.get(i).doubleValue() * 190 / 10 * Math.sin(360 * ((Math.PI / 180)
                                    / args.size()) * i)*dFrmt)/dFrmt,
                            Math.round(args.get(i).doubleValue() * 190 / 10 * Math.cos(360 * ((Math.PI / 180)
                                    / args.size()) * i)*-1*dFrmt)/dFrmt));
            //*(-1) at the end is for rotating the ordinate (y) ax for 180 degrees as in chart; 190/10 pixels to rating units ratio
        }
        System.out.println("\nDOM: " + getComparisonChartCoordinates());
        System.out.println("API: "+lstCoorApi);
        return lstCoorApi;
    }

    public  String getTooltip(String arg1,String arg2) throws InterruptedException {

        Thread.sleep(3000);
        WebElement el; String actualTooltip=null;  Integer numberInList=1;

        for (int i = 1; i < 100; ++i) {
            if (webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div[" + i + "]/div[1]/a/img"))
                    .getAttribute("title").equals(arg1)) {
                numberInList = i;
                break;
            }
        }

        if (arg2.equals("bookmaker")) {
            el = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div[" + numberInList + "]/div[1]/a/img"));
        }else {
            el=webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div[" + numberInList + "]/div[4]"));
            if (arg2.equals("buttonAfterClick")) {
                el.click();
            }
        }
        actualTooltip = el.getAttribute("title");
        System.out.println("Actual Title of Tool Tip: "+actualTooltip);
        return actualTooltip;
    }

    public String getNameFromLegendElement(){

      WebElement  el = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[1]/div[1]/div[2]/div"));
      System.out.println(el.getAttribute("textContent"));
      return el.getAttribute("textContent");
    }

    public ArrayList getNamesAndColorsFromLegend(Integer args) {

        ArrayList allLegendElements=new ArrayList();

        if (args>5){args=5;}
        for (int i = 1; i < args + 1; ++i) {

            WebElement el = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[1]/div[1]/div[2]/div[" + i + "]"));
            String elString = el.getAttribute("innerHTML");

            if (elString.contains("background-color: ") && elString.contains("<div class=\"close-icon\"")) {
                elString = elString.substring(elString.indexOf("background-color: "), elString.indexOf("<div class=\"close-icon\""))
                        .replace("background-color: ", "").replace("<div class=\"close-icon\"", "")
                .replace(";\"></div>","");
                allLegendElements.add(elString);
            }
          }
        System.out.println(allLegendElements);
        return allLegendElements;
    }

    public void clickOnCompareWithOthersButton() throws InterruptedException {
        JavascriptExecutor js = ((JavascriptExecutor) webDriver);
        js.executeScript("window.scrollTo(0,300)");
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[1]/div/a[1]")).click();
    }

    public List<String> addNBookmakersFromNbr(Integer arg1,Integer arg2,String arg3) throws InterruptedException {

        List<String> bookmakersTmpList = new ArrayList<>();

        if (arg1>5){arg1=5;}
        if (arg3=="descending") {
            for (int i = arg2; i > arg2 - arg1; --i) {
                WebElement el = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div[" + i + "]/div[1]/a/img"));
                bookmakersTmpList.add((String) el.getAttribute("title"));
                Thread.sleep(2000);
                webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div[" + i + "]/div[4]")).click();
            }
        }else{
            for (int i=arg2;i<arg2+arg1;++i) {

                WebElement el=webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div["+i+"]/div[1]/a/img"));
                bookmakersTmpList.add((String)el.getAttribute("title"));
                Thread.sleep(2000);
                webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div[" + i + "]/div[4]")).click();
            }
        }
        System.out.println(bookmakersTmpList);
        return bookmakersTmpList;
    }

    public ArrayList chartsColorExpected(Integer args){

        ArrayList chartsColorExpected=new ArrayList();
        String[] colorList= new String[]{"Blue", "Green", "Red","Purple","Orange"};
        for (int i=0; i<args;++i){
            chartsColorExpected.add(colorList[i]);
        }
        Collections.reverse(chartsColorExpected);
        return chartsColorExpected;
    }

    public String findClassNameOfComparisonButton() throws InterruptedException {
        Thread.sleep(5000);
        WebElement el=webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[1]/div[1]/button[2]"));
        return (el.getAttribute("className"));
    }

    public void clickOnComparisonButton() throws InterruptedException {
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[1]/div[1]/button[2]")).click();
    }
    public void switchToNextTab() throws InterruptedException {
        Thread.sleep(2000);
        ArrayList<String> tabs2 = new ArrayList<String> (webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs2.get(1));
    }

    public void clickOnShowAllButton() throws InterruptedException {
        Thread.sleep(2000);
        try{webDriver.findElement(By.className("bookmaker-show-all-btn")).click();}
        finally {return;}
    }

    public void clickOnBonusesButton() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> el= webDriver.findElements(By.className("ReviewNavBarItem"));
        for (WebElement i:el) {
            if (i.getAttribute("outerText").equals("Bonuses")) {
                i.click();
                break;
            }
        }
    }
    public String bookmakerNameInHeader () throws InterruptedException {
        Thread.sleep(1000);
        WebElement el=webDriver.findElement(By.className("bookmaker-name"));
        String elName=el.getAttribute("outerText");
        return elName;
    }
    public String bookmakerNameIsInHeader (){
        WebElement el=webDriver.findElement(By.className("bookmaker-name"));
        String elName=el.getAttribute("parentElement");
        return elName;
    }

    public String compareWithOthersInHeader () throws InterruptedException {
        Thread.sleep(1000);
        WebElement el=webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[1]/div/a[1]"));
        String elName=el.getAttribute("outerText");
        return elName;
    }
    public String compareWithOthersIsInHeader (){
        WebElement el=webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[1]/div/a[1]"));
        String elName=el.getAttribute("parentElement");
        return elName;
    }

    public String websideInHeader (){
        WebElement el=webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[1]/div/a[2]"));
        String elName=el.getAttribute("outerText");
        return elName;
    }
    public String websideIsInHeader () {
        WebElement el = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[1]/div/a[2]"));
        String elName = el.getAttribute("parentElement");
        return elName;
    }

    public void clickBonusComparisonButon(){
        webDriver.findElement(By.xpath("//*[@id=\"bonus\"]/div[4]/a")).click();
     }
}