package api;

import com.jayway.jsonpath.JsonPath;
import config.AppConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import rest.RequestHelpers;
import rest.RequestMethods;
import utils.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BackOfficeApiCalls {

    public final String RESOURCES_PATH = "src/test/resources/data/backoffice/";
    public final String CREATE_TAG_GROUP = RESOURCES_PATH + "create-tag-group.json";
    public final String UPDATE_CREATED_TAG_GROUP = RESOURCES_PATH + "update-created-tag-group.json";
    public final String COUNTRIES_TAG_GROUP_CODE_ZMB_AGE_18 = RESOURCES_PATH + "tags/countries-codezmb-age18.json";
    public final String COUNTRIES_TAG_GROUP_CODE_ZMBIK_AGE_21 = RESOURCES_PATH + "tags/countries-codezmbik-age21.json";
    Response createTagGroupResponse;
    Response updateTagGroupResponse;
    RequestMethods requestMethods = new RequestMethods();
    RequestHelpers requestHelpers = new RequestHelpers();
    AppConfig envConfig = ConfigFactory.create(AppConfig.class);
    public String tagGroupId;
    Map<String, String> requestHeaders = new HashMap<>();

    public BackOfficeApiCalls() throws IOException {
        requestHeaders.put("Authorization", Authentication.token);
    }

    public void createTagGroup() throws Exception {
        createTagGroupResponse = makePostRequest(envConfig.getApiReviewsService() + "tag-groups",
                CREATE_TAG_GROUP, requestHeaders, 200);
        tagGroupId = (JsonPath.read(createTagGroupResponse.asString(), "$.document.id"));
    }

    public void updateTagGroup() throws Exception {
        updateTagGroupResponse = makePutRequest(envConfig.getApiReviewsService() + "tag-groups/" + tagGroupId,
                UPDATE_CREATED_TAG_GROUP, requestHeaders, 200);
    }

    public void setCountryCodeAndAge() throws Exception {
        makePutRequest(envConfig.getApiReviewsService() + "tag-groups/" + "5ece82f0053031001047ca5b",
                COUNTRIES_TAG_GROUP_CODE_ZMB_AGE_18, requestHeaders, 200);
    }

    public void unsetCountryCodeAndAge() throws Exception {
        makePutRequest(envConfig.getApiReviewsService() + "tag-groups/" + "5ece82f0053031001047ca5b",
                COUNTRIES_TAG_GROUP_CODE_ZMBIK_AGE_21, requestHeaders, 200);
    }

    public boolean createTagGroupWithSameName(int statusCode) throws Exception {
        createTagGroupResponse = makePostRequest(envConfig.getApiReviewsService() + "tag-groups",
                CREATE_TAG_GROUP, requestHeaders, statusCode);
        return true;
    }

    public String getTagGroupById() {
        Response response = requestMethods.makeGetRequest(envConfig.getApiReviewsService() + "tag-groups/" + tagGroupId, requestHeaders, 200);
        return tagGroupId = (JsonPath.read(response.asString(), "$.id"));
    }

    public ArrayList getUpdatedTagGroupValues() {
        ArrayList<Object> values = new ArrayList<>();
        String name = (JsonPath.read(updateTagGroupResponse.asString(), "$.document.tags[0].name"));
        String slug = (JsonPath.read(updateTagGroupResponse.asString(), "$.document.tags[0].slug"));

        String type = (JsonPath.read(updateTagGroupResponse.asString(), "$.document.type"));
        values.add(name);
        values.add(slug);
        values.add(type);
        return values;
    }

    public void deleteTagGroupById() {
        requestMethods.makeDeleteRequest(envConfig.getApiReviewsService() + "tag-groups/" + tagGroupId, requestHeaders, 204);

    }

    public Response makePutRequest(String requestUrl, String jsonObject, Map<String, String> requestHeaders, int responseStatusCode) throws Exception {
        RequestSpecification requestSpecification = requestHelpers.setContentTypeJson().headers((requestHeaders)).when().body((jsonObject.toString()));
        Response response = requestSpecification.when().put(requestUrl);
        Log.warn(response.getBody().asString());
        requestHelpers.checkResponseStatusCode(response, responseStatusCode);
        return response;
    }

    public Response makePostRequest(String requestUrl, String jsonObject, Map<String, String> requestHeaders, int responseStatusCode) throws Exception {
        RequestSpecification requestSpecification = requestHelpers.setContentTypeJson().headers((requestHeaders)).when().body(jsonObject.toString());
        Response response = requestSpecification.post(requestUrl);
        Log.warn(response.getBody().asString());
        requestHelpers.checkResponseStatusCode(response, responseStatusCode);
        return response;
    }
}
