package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static io.appium.java_client.AppiumBy.xpath;
import static io.qameta.allure.Allure.step;

public class OnboardingTest extends TestBase {

    @Test
    void completeGettingStartedTest() {
        step("Проверить первый экран: знания Википедии", () -> {
            $(androidUIAutomator("new UiSelector().text(\"All the world's knowledge\")")).shouldBe(visible);
            $(androidUIAutomator("new UiSelector().textContains(\"free online encyclopedia\")")).shouldBe(visible);
            forward();
        });

        step("Проверить второй экран: данные и конфиденциальность", () -> {
            $(accessibilityId("Data & Privacy")).shouldBe(visible);
            $(androidUIAutomator("new UiSelector().textContains(\"Usage data collected for this app is anonymous\")")).shouldBe(visible);
            forward();
        });

        step("Проверить третий экран: языки чтения", () -> {
            $(accessibilityId("Read in more than 300 languages")).shouldBe(visible);
            $(androidUIAutomator("new UiSelector().text(\"Add or edit your languages\")")).shouldBe(visible);
            forward();
        });

        step("Проверить четвёртый экран: персональная лента", () -> {
            $(accessibilityId("Follow your curiosity")).shouldBe(visible);
            $(androidUIAutomator("new UiSelector().textContains(\"Select topics that interest you\")")).shouldBe(visible);
            $(xpath("//*[@content-desc='Next']/..")).shouldBe(visible).click();
        });

        step("Пропустить выбор интересов и проверить главный экран", () -> {
            $(androidUIAutomator("new UiSelector().text(\"What are you interested in?\")")).shouldBe(visible);
            $(androidUIAutomator("new UiSelector().text(\"Skip\")")).click();
            $(accessibilityId("Home")).shouldBe(visible);
        });
    }

    private void forward() {
        $(xpath("//*[@content-desc='Forward']/..")).shouldBe(visible).click();
    }
}
