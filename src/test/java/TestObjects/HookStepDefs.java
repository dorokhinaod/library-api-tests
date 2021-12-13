package TestObjects;

import Properties.ConfProperties;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

import static Properties.ConfProperties.*;

public class HookStepDefs {

    public static RequestSpecBuilder requestBuilder;
    public static RequestSpecification requestSpec;

    @Before("@API")
    public static void setupSpecBuilder(){
        requestBuilder = new RequestSpecBuilder()
                .setBaseUri(getProperty("baseURI"))
                .setPort(Integer.parseInt(getProperty("basePort")))
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON);
        requestSpec = requestBuilder.build();
    }

    @Before("@UI")
    public static void setupBrowser(){
        Configuration.headless = true;
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        Configuration.browserCapabilities.setCapability("goog:loggingPrefs", logPrefs);
    }

    @After("@UI")
    public void onTestFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot1 = (Selenide.screenshot(OutputType.BYTES));
            scenario.attach(screenshot1, "image/png", scenario.getName());
        }
    }

}
