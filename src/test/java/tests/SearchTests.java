package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SearchTests extends TestBase {

    @Test
    void successfulSearchTest() {
        step("Type search", () -> {
            WebDriver driver = getWebDriver();

            List<WebElement> skipOnboarding = driver.findElements(
                    id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")
            );
            if (!skipOnboarding.isEmpty()) {
                skipOnboarding.get(0).click();
            }

            List<WebElement> search = driver.findElements(accessibilityId("Search Wikipedia"));
            if (search.isEmpty()) {
                search = driver.findElements(id("org.wikipedia.alpha:id/search_container"));
            }
            if (search.isEmpty()) {
                throw new IllegalStateException("Search element was not found");
            }
            search.get(0).click();

            driver.findElement(id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    void searchForLabradorRetrieverArticleTest() {
        step("Открыть приложение Wikipedia", () -> {
            // Приложение запускается в TestBase.
            WebDriver driver = getWebDriver();
            List<WebElement> skipOnboarding = driver.findElements(
                    id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")
            );
            if (!skipOnboarding.isEmpty()) {
                skipOnboarding.get(0).click();
            }
        });

        step("Нажать на кнопку «Поиск»", () -> {
            $(accessibilityId("Search Wikipedia")).click();
        });

        step("Ввести в поиск запрос «Labrador Retriever»", () -> {
            $(id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Labrador Retriever");
        });

        step("Проверить наличие статьи Labrador Retriever в результатах", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                    .findBy(text("Labrador Retriever"))
                    .shouldBe(visible);
        });

        step("Открыть первую статью", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                    .first()
                    .click();
        });

        step("Проверить, что открылась статья Labrador Retriever", () -> {
            $(androidUIAutomator(
                    "new UiSelector().text(\"Labrador Retriever\")"))
                    .shouldBe(visible);
        });
    }
}
