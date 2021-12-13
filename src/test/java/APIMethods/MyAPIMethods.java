package APIMethods;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Assert;

import static TestObjects.HookStepDefs.requestSpec;
import static io.restassured.RestAssured.given;

public class MyAPIMethods {
    private static Response resp;
    public static String endpointLibraryCard = "/libraryCard";

    public static void assertStatusIsOK(int statusCode){
        Assert.assertEquals("Unexpected status code: ", 200, statusCode);
    }

    public static void assertStatusIsCreated(int statusCode){
        Assert.assertEquals("Unexpected status code: ", 201, statusCode);
    }

    public static void assertStatusIsNotFound(int statusCode){
        Assert.assertEquals("Unexpected status code: ", 404, statusCode);
    }

    public static Response getLibraryCard(String param, String value){
        resp = given()
                .spec(requestSpec)
                .param(param, value)
                .get(endpointLibraryCard);
        assertStatusIsOK(resp.getStatusCode());
        return resp;
    }

    public static Response postLibraryCard(JSONObject request){
        resp = given()
                .spec(requestSpec)
                .body(request.toJSONString())
                .post(endpointLibraryCard);
        assertStatusIsCreated(resp.getStatusCode());
        return resp;
    }

    public static void deleteLibraryCard(int cardID){
        resp = given()
                .spec(requestSpec)
                .delete(endpointLibraryCard + "/" + cardID);
        MyAPIMethods.assertStatusIsOK(resp.getStatusCode());
    }
}
