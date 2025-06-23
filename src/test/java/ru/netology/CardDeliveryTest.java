package ru.netology;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    public static String setLocalDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy",
                new Locale("ru")));
    }


    @Test
    void shouldApplication() {
        String date = setLocalDate(3);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        //$(byText("Выберите дату встречи с представителем банка")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Орлов Константин");
        $("[data-test-id=phone] input").setValue("+79994253266");
        //$x("//*[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date),
                Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldHuphenCity() {
        String date = setLocalDate(3);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Костин Николай");
        $("[data-test-id=phone] input").setValue("+79390893452");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date),
                Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldMoreThreeDays() {
        String date = setLocalDate(4);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        //$(byText("Выберите дату встречи с представителем банка")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Зоркин Иван");
        $("[data-test-id=phone] input").setValue("+79095056789");
        //$x("//*[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date),
                Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldResultDoubleSurnam() {
        String date = setLocalDate(3);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Тверь");
        //$(byText("Выберите дату встречи с представителем банка")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Орлов-Соколов Лев");
        $("[data-test-id=phone] input").setValue("+79998435274");
        //$x("//*[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date),
                Duration.ofSeconds(15)).shouldBe(visible);
    }
}