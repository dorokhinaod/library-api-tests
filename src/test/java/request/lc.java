package request;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class lc {

    public static String getLC = "/libraryCard/{id}";

    public static ResponseSpecBuilder builder;
    public static ResponseSpecification successResponseSpec, failedResponseSpec;

    @BeforeClass
    public static void setupResponseSpecBuilder(){
        builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);
        successResponseSpec = builder.build();
        failedResponseSpec = builder.expectStatusCode(404).build();
//        builder.addResponseSpecification(failedResponceSpec).expectStatusCode(404).build();
    }

    @Test
    public static void getNotExistedLC(int id){
        baseURI = "http://localhost:3000/";
        given()
                .get(getLC, id)
                .then()
                .spec(failedResponseSpec)
                .log().all();
    }

    @Test
    public static void getExistedLC(int id){
        baseURI = "http://localhost:3000/";
        given()
                .get(getLC, id)
                .then()
                .spec(successResponseSpec)
                .log().all();
    }

}
