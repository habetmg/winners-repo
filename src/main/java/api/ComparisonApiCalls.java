package api;

import com.jayway.jsonpath.JsonPath;
import config.AppConfig;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import pages.BookmakersTablePage;
import rest.RequestMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.BookmakerApiCalls.bookmakerId;

public class ComparisonApiCalls {
    AppConfig appConfig = ConfigFactory.create(AppConfig.class);
    RequestMethods requestMethods = new RequestMethods();
    Map<String, String> requestHeaders = new HashMap<>();

    public ComparisonApiCalls() {
        requestHeaders.put("Authorization", Authentication.token);
    }

    public List<Number> getRatingsFromApi(String bookmakerId) {

        List<Number> lstRat= new ArrayList<>();
        String[] services = new String[]{"overall", "bonus", "products", "responsibleGaming",
                "depositAndWithdrawal", "onboarding", "customerServices", "utility"};

        Response response = requestMethods.makeGetRequest(appConfig.getApiReviewsService() + "/bookmakers/"
                + bookmakerId,requestHeaders,200);
        for (String i:services) {
            lstRat.add(JsonPath.read(response.asString(), "$.reviews." + i + ".rating"));
        }
    return lstRat;
    }

    public int getTotalNumberOfBookmakersFromApi() throws InterruptedException {

        Response response = requestMethods.makeGetRequest("https://api-bookmakers.priotix.xyz/" +
           "bookmakers?filter[0][type]=countries&filter[0][values][0][id]=5ede36d8f045090013da564&sort=-reviews.overall.rating&limit=100"
           ,requestHeaders,200); //All Countries = 5ede36d8f045090013da564
        int numberOfBookmakers=(JsonPath.read(response.asString(), "$.total"));
        System.out.println(numberOfBookmakers);
        return numberOfBookmakers;
    }

    public ArrayList checkBookmakerListOrder (ArrayList args) throws InterruptedException {
        ArrayList comparisonList=new ArrayList();
        for (int i=0;i< args.size()-1;++i){
            Response response = requestMethods.makeGetRequest(appConfig.getApiReviewsService() + "/bookmakers/"
                    + args.get(i),requestHeaders,200);
            Number bonusRatingFirst=(JsonPath.read(response.asString(), "$.reviews.bonus.rating"));
            comparisonList.add(args.get(i));
            comparisonList.add(bonusRatingFirst);
            response = requestMethods.makeGetRequest(appConfig.getApiReviewsService() + "/bookmakers/"
                    + args.get(i+1),requestHeaders,200);
            Number bonusRatingSecond=(JsonPath.read(response.asString(), "$.reviews.bonus.rating"));
            comparisonList.add(args.get(i+1));
            comparisonList.add(bonusRatingSecond);
        }
        return comparisonList;
    }
}
