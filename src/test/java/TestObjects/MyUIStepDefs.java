package TestObjects;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static APIMethods.MyAPIMethods.endpointLibraryCard;
import static PageObjects.DemoPage.checkMainPageIsOpened;
import static PageObjects.DemoPage.openLibraryCards;
import static Properties.ConfProperties.getProperty;
import static UIMethods.MyUIMethods.checkLogs;

public class MyUIStepDefs {
    private static String baseURL;

    @When("User opens browser")
    public void userOpensBrowser() {
        baseURL = getProperty("baseURI") + ":" + getProperty("basePort");
        Selenide.open(baseURL);
        checkMainPageIsOpened();
    }

    @Then("User can open library cards page")
    public void userOpensLibraryCards() {
        openLibraryCards(baseURL, endpointLibraryCard);
        checkLogs();
    }

}