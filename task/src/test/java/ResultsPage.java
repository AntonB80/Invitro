import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import org.openqa.selenium.interactions.Actions;
import com.codeborne.selenide.SelenideElement;

public class ResultsPage extends BaseTest {

        private final SelenideElement orderNumberField = $("input[name = 'orderNumber']");
        private final SelenideElement birthdayField = $("input[name = 'birthday']");
        private final SelenideElement lastNameField = $("input[name = 'lastName']");

        /**
         * Задание #3
         * нажать "получить результаты анализов", проверить заголовок страницы, что
         * равен
         * "Введите индивидуальный номер заказа, чтобы посмотреть результаты анализов".
         * не заполнять поля и нажать "Найти результаты". проверить что поля выделены
         * красным и появилось предупреждение
         * "Поля Код ИНЗ Дата рождения Фамилия обязательны для заполнения"
         * 
         * заполнить поля. Пример:
         * код ИНЗ:231231231
         * Дата рождения: 11.12.2000
         * Фамилия: тест
         * 
         * Проверить что в поле код ИНЗ указано 231231231
         * Проверить что в поле Дата рождения указано 11.12.2000
         * Проверить что в поле Фамилия указано тест
         * 
         * * Сценарий:
         * 1. Открывается страница с результатами.
         * 2. Проверяется, что заголовок страницы соответствует
         * ожидаемому titlePage.
         * 3. Нажимается кнопка "Найти результаты".
         * 4. Проверяется, что фон поля "Номер заказа" имеет красный
         * цвет.
         * 5. Проверяется, что фон поля "Дата рождения" имеет красный
         * цвет.
         * 6. Проверяется, что фон поля "Фамилия" имеет красный цвет.
         * 7. Проверяется, что на странице присутствует
         * предупреждающий текст warningText.
         * 8. В поле "Номер заказа" вводится значение codeINZ и
         * выполняется переход к следующему полю.
         * 9. В поле "Дата рождения" выполняется клик, используя
         * Actions с отступом 45 пикселей по горизонтали.
         * 10. В поле "Дата рождения" вводится значение birthday и
         * выполняется переход к следующему полю.
         * 11. В поле "Фамилия" вводится значение lastName и
         * выполняется переход к следующему полю.
         * 12. Проверяется, что в поле "Номер заказа" отображается
         * введенное значение codeINZ.
         * 13. Проверяется, что в поле "Дата рождения" отображается
         * введенное значение birthday.
         * 14. Проверяется, что в поле "Фамилия" отображается
         * введенное значение lastName.
         * 
         * @param titlePage   заголовок страницы
         * @param warningText ткуст предупреждения
         * @param codeINZ     код ИНЗ
         * @param birthday    Дата рождения
         * @param lastName    Фамилия
         * 
         * 
         */

        public void testResultsPage(
                        String titlePage,
                        String warningText,
                        String codeINZ,
                        String birthday,
                        String lastName) {
                $("[class^='UnauthResultsPage_bodyContainer']")
                                .shouldHave(text(titlePage));
                $x("//button[text() = 'Найти результаты']").click();
                $("input[name = 'orderNumber']").shouldHave(
                                cssValue("Background", WebElementColor.clorRed.getColor()));
                $("input[name = 'birthday']").shouldHave(
                                cssValue("Background", WebElementColor.clorRed.getColor()));
                $("input[name = 'lastName']").shouldHave(
                                cssValue("Background", WebElementColor.clorRed.getColor()));
                $("[class^='UnauthResultsPage_error']")
                                .shouldHave(text(warningText));
                orderNumberField.setValue(codeINZ).pressTab();
                // $("input[name = 'birthday']").click();

                // dateField.click(ClickOptions.usingJavaScript().offset(60, 0));
                new Actions(birthdayField.getWrappedDriver())
                                .moveToElement(birthdayField).moveByOffset(65, 0)
                                .click()
                                .perform();
                birthdayField.setValue(birthday).pressTab();
                lastNameField.setValue(lastName).pressTab();
                orderNumberField.shouldHave(value(codeINZ));
                birthdayField.shouldHave(value(birthday));
                lastNameField.shouldHave(value(lastName));

        }
}
