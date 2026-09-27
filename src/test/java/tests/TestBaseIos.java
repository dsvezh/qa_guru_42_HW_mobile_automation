package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackIosDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBaseIos {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = BrowserstackIosDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(false).savePageSource(false));
        open();
    }

    @AfterEach
    void addAttachments() {
        String sessionId = null;
        try {
            sessionId = Selenide.sessionId().toString();
            Attach.pageSource();
        } catch (RuntimeException error) {
            Attach.attachAsText("Ошибка получения вложений", error.toString());
        } finally {
            closeWebDriver();
        }

        if (sessionId != null) {
            try {
                Attach.addVideo(sessionId);
            } catch (RuntimeException error) {
                Attach.attachAsText("Ошибка получения видео", error.toString());
            }
        }
    }
}
