package PageObjects;

import com.codeborne.selenide.Selenide;
import org.junit.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DemoPage {
    private static final String libraryCardHref = "a[href=\"libraryCard\"]";
    private static final String preElement = "pre";

    public static void checkMainPageIsOpened(){
        $(libraryCardHref).shouldBe(visible);
    }

    public static void openLibraryCards(String baseUrl, String endpointLibraryCards){
        $(libraryCardHref).click();
        $(preElement).shouldBe(visible);
        String currentUrl = Selenide.webdriver().driver().getCurrentFrameUrl();
        Assert.assertEquals("Unexpected current url. ", baseUrl + endpointLibraryCards, currentUrl);
    }

}
