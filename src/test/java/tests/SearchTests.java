package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import java.util.Map;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    @Test
    void successfulSearchTest() {
        step("Type search", () -> {
            skipOnboarding();
            $(id("org.wikipedia.alpha:id/nav_tab_search")).click();
            $(accessibilityId("Close")).click();
            $(id("org.wikipedia.alpha:id/search_card")).click();
            enterSearchQuery("Appium");
        });
        step("Verify content found", () ->
                $(id("org.wikipedia.alpha:id/search_src_text"))
                        .shouldHave(attribute("text", "Appium")));
    }

    @Test
    void searchForLabradorRetrieverArticleTest() {
        step("Открыть приложение Wikipedia", () -> {
            // Приложение запускается в TestBase.
            skipOnboarding();
        });

        step("Нажать на кнопку «Поиск»", () -> {
            $(id("org.wikipedia.alpha:id/nav_tab_search")).click();
            $(accessibilityId("Close")).click();
            $(id("org.wikipedia.alpha:id/search_card")).click();
        });

        step("Ввести в поиск запрос «Labrador Retriever»", () -> {
            enterSearchQuery("Labrador Retriever");
        });

        step("Проверить наличие статьи Labrador Retriever в результатах", () -> {
            $(androidUIAutomator(
                    "new UiSelector().text(\"Labrador Retriever\")"))
                    .should(exist);
        });

        step("Открыть первую статью", () -> {
            ((JavascriptExecutor) getWebDriver()).executeScript(
                    "mobile: clickGesture",
                    Map.of("x", 500, "y", 330)
            );
        });

        step("Проверить, что открылась статья Labrador Retriever", () -> {
            $(androidUIAutomator(
                    "new UiSelector().text(\"Labrador Retriever\")"))
                    .shouldBe(visible);
        });
    }

    private void skipOnboarding() {
        for (int screen = 0; screen < 3; screen++) {
            $(xpath("//*[@content-desc='Forward']/..")).click();
        }
        $(xpath("//*[@content-desc='Next']/..")).click();
        $(androidUIAutomator("new UiSelector().text(\"Skip\")")).click();
    }

    private void enterSearchQuery(String query) {
        sleep(1000);
        new Actions(getWebDriver())
                .moveToLocation(500, 145)
                .click()
                .perform();
        ((JavascriptExecutor) getWebDriver()).executeScript(
                "mobile: type",
                Map.of("text", query)
        );
    }

}
