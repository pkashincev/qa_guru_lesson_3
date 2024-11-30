import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoqaPracticeFormTests {

    @BeforeAll
    static void setBrowserConfig() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue("Pavel");
        $("#lastName").setValue("Kashintsev");
        $("#userEmail").setValue("address@test.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("0123456789");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__year-select").selectOption("1990");
        $(".react-datepicker__month").$(byText("27")).click();
        $("#subjectsInput").setValue("Chemistry").pressEnter();
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("picture.jpg");
        $("#currentAddress").setValue("...some address...");
        $("#state").click();
        $("#state").$(byText("Haryana")).click();
        $("#city").click();
        $("#city").$(byText("Karnal")).click();
        $("#submit").click();

        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(".table>thead>tr>th").shouldHave(text("Label"));
        $(".table>thead>tr>th").sibling(0).shouldHave(text("Values"));
        $(".table").$(byText("Student Name")).sibling(0).shouldHave(text("Pavel Kashintsev"));
        $(".table").$(byText("Student Email")).sibling(0).shouldHave(text("address@test.com"));
        $(".table").$(byText("Gender")).sibling(0).shouldHave(text("Male"));
        $(".table").$(byText("Mobile")).sibling(0).shouldHave(text("0123456789"));
        $(".table").$(byText("Date of Birth")).sibling(0).shouldHave(text("27 April,1990"));
        $(".table").$(byText("Subjects")).sibling(0).shouldHave(text("Chemistry"));
        $(".table").$(byText("Subjects")).sibling(0).shouldHave(text("Maths"));
        $(".table").$(byText("Hobbies")).sibling(0).shouldHave(text("Reading"));
        $(".table").$(byText("Hobbies")).sibling(0).shouldHave(text("Music"));
        $(".table").$(byText("Picture")).sibling(0).shouldHave(text("picture.jpg"));
        $(".table").$(byText("Address")).sibling(0).shouldHave(text("...some address..."));
        $(".table").$(byText("State and City")).sibling(0).shouldHave(text("Haryana Karnal"));
    }
}