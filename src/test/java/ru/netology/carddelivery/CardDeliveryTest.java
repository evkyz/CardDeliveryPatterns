package ru.netology.carddelivery;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.selector.ByText;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.carddelivery.dataUser.date;
import static ru.netology.carddelivery.dataUser.newDate;

class CardDeliveryTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void testReplaneDate() {
        $x("//input[@placeholder=\"Город\"]").setValue(dataUser.city());
        $x("//input[@placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dataUser.date());
        $x("//input[@name=\"name\"]").setValue(dataUser.name());
        $x("//input[@name=\"phone\"]").setValue(dataUser.phone());
        $x("//span[@class=\"checkbox__box\"]").click();
        $x("//*[text()=\"Запланировать\"]").click();
        $x("//input[@placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dataUser.newDate());
        $x("//*[text()=\"Запланировать\"]").click();
        $x("//*[text()=\"Перепланировать\"]").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + newDate()), Duration.ofSeconds(15));
    }

    @Test
    void testOldDate() {
        $x("//input[@placeholder=\"Город\"]").setValue(dataUser.city());
        $x("//input[@placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dataUser.date());
        $x("//input[@name=\"name\"]").setValue(dataUser.name());
        $x("//input[@name=\"phone\"]").setValue(dataUser.phone());
        $x("//span[@class=\"checkbox__box\"]").click();
        $x("//*[text()=\"Запланировать\"]").click();
        $x("//input[@placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dataUser.date());
        $x("//*[text()=\"Запланировать\"]").click();
        $x("//*[text()=\"Перепланировать\"]").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date()), Duration.ofSeconds(15));
    }

    @Test
    void badDate() {
        $x("//input[@placeholder=\"Город\"]").setValue(dataUser.city());
        $x("//input[@placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dataUser.date());
        $x("//input[@name=\"name\"]").setValue(dataUser.name());
        $x("//input[@name=\"phone\"]").setValue(dataUser.phone());
        $x("//span[@class=\"checkbox__box\"]").click();
        $x("//*[text()=\"Запланировать\"]").click();
        $x("//input[@placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dataUser.badDate());
        $x("//*[text()=\"Запланировать\"]").click();
        $(new ByText("Заказ на выбранную дату невозможен")).should(visible);
    }
}
