package TestObjects;

import APIMethods.MyAPIMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.List;

import static APIMethods.MyAPIMethods.*;
import static TestObjects.HookStepDefs.requestSpec;
import static io.restassured.RestAssured.given;

public class MyAPIStepdefs {

    private int cardId;
    private String fullname = "";

    @Given("Library card with fullname {string} doesn't exist yet")
    public void libraryCardDoesnTExistYet(String fullname) {
        if (this.fullname.isEmpty()) {
            this.fullname = fullname;
        }
        Response resp = getLibraryCard("fullname", this.fullname);
        List<String> id = resp.getBody().path("id");
        Assert.assertEquals("Unexpected: card with fullname " + this.fullname + " was found. ", 0, id.size());
    }

    @When("An employee creates library card")
    public void employeeCreatesLC() {
        JSONObject request = new JSONObject();
        request.put("fullname", this.fullname);
        Response resp = postLibraryCard(request);
        Allure.addAttachment("Params", "Params: " + request.toJSONString() + "\n" +
                "Response: " + resp.asPrettyString());
        resp = getLibraryCard("fullname", this.fullname);
        List<Integer> cardsId = resp.getBody().path("id");
        cardId = cardsId.get(0);
    }

    @Then("Library card can be deleted")
    public void libraryCardCanBeDeleted() {
        deleteLibraryCard(cardId);
        libraryCardDoesnTExistYet(this.fullname);
    }

    @Then("Library card can be updated with {string}")
    public void libraryCardCanBeUpdatedWith(String newFullname) {
        JSONObject request = new JSONObject();
        request.put("fullname", newFullname);
        Response resp = patchLibraryCard(cardId, request);
        resp = getLibraryCard("fullname", newFullname);
        List<String> id = resp.getBody().path("id");
        Assert.assertEquals("Unexpected: card with fullname " + newFullname + " wasn't found after update. ", 1, id.size());
    }
}
