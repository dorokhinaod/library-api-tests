package e2e;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureResultsWriter;
import io.qameta.allure.AllureUtils;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Rule;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class WorkWithLC {

    public static String getLC = "/libraryCard/{id}";
    public static String postLC = "/libraryCard";

    public static ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder()
        .expectStatusCode(200 | 201);
    public static ResponseSpecification successResponseSpec, failedResponseSpec;
    public static RequestSpecBuilder requestBuilder;
    public static RequestSpecification requestSpec;

    @BeforeClass
    public static void setupSpecBuilder(){
        requestBuilder = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(3000)
            .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON);
        requestSpec = requestBuilder.build();
        responseBuilder = new ResponseSpecBuilder()
            .expectStatusCode(200);
        successResponseSpec = responseBuilder.build();
        responseBuilder = new ResponseSpecBuilder()
            .expectStatusCode(404);
        failedResponseSpec = responseBuilder.build();
    }

    @Test
    void basicActionsWithLC() {
        Allure.feature("Library cards");
        Allure.description("An employee can create, search, update and delete library cards");
        Response resCount = given()
                .spec(requestSpec)
                .get(postLC + "?fullname=Doe John");
        JsonPath jsonPathEvaluator = resCount.jsonPath();
        List<String> id = jsonPathEvaluator.get("id");
        int count = id.size();
        Assert.assertEquals(count, 0, "Test library is found before creation.");
        Allure.step("Step 1. Library card doesn't exist. Response: " + count);
        JSONObject req = new JSONObject();
        req.put("fullname", "Doe John");
        given()
                .spec(requestSpec)
                .body(req.toJSONString())
                .post(postLC)
                .then()
                .statusCode(201)
                .log().all();
        Allure.step("Step 2. Library card is created");
        given()
                .spec(requestSpec)
                .get(postLC + "?fullname=Doe John")
                .then()
                .spec(successResponseSpec)
                .log().all();
        Allure.step("Step 3. Library card can be found");
        given()
                .spec(requestSpec)
                .delete(getLC, 3)
                .then()
                .statusCode(200)
                .log().all();
        Allure.step("Step 4. Library card can be deleted");
    }

}
