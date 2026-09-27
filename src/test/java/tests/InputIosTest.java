package tests;

import com.codeborne.selenide.ClickOptions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.qameta.allure.Allure.step;

public class InputIosTest extends TestBaseIos {

    @Test
    void textInputTest() {
        String text = "Hello, BrowserStack!";

        step("Нажать кнопку Text", () -> {
            $(accessibilityId("Text Button"))
                    .click(ClickOptions.withOffset(0, 20));
        });

        step("Ввести текст", () -> {
            $(accessibilityId("Text Input")).sendKeys(text);
        });

        step("Нажать Return", () -> {
            $(accessibilityId("Text Input")).pressEnter();
        });

        step("Проверить отображение введённого текста", () -> {
            $(accessibilityId("Text Output")).shouldHave(text(text));
        });

        step("Проверить очистку поля ввода", () -> {
            $(accessibilityId("Text Input"))
                    .shouldHave(attribute("value", "Enter a text"));
        });
    }
}
