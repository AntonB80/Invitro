
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class MainPage {

    public MainPage(String url) {
        open(url);
    }

    /**
     * Задание #1
     * в разделе медицинские услуги https://www.invitro.ru/radiology/ сделать
     * прокликивание всего меню. в том числе с прокликиванием по подгруппам
     * 
     * Сценарий
     * 1. Получение коллекции элементов основного меню:
     * - Код получает коллекцию элементов меню, используя CSS-селектор
     * "[class^= 'side-bar-second__items']".
     * - Проверяется, что коллекция не пуста и содержит элементы.
     * 2. Проверка каждого элемента меню:
     * - Для каждого элемента меню в коллекции выполняются следующие действия:
     * - Получается текст элемента меню с помощью getText().
     * - Элемент меню кликается.
     * - Проверяется, что заголовок страницы (#titlePage h1) содержит текст элемента
     * меню, используя assertTrue(textTitleBlock.contains(textMenuItems)).
     * 3. Проверка подменю:
     * - Код получения коллекции подменю, используя CSS-селектор
     * "[class^= 'side-bar-second__items'] div li".
     * - Фильтруем коллекцию подменю по видимости и формируем коллекцию видимых
     * элементов подменю.
     * - Проверяется, что коллекция подменю не пуста и содержит видимые элементы,
     * используя visibleSubMenuItems.size() > 0 & !visibleSubMenuItems.isEmpty().
     * - Для каждого видимого элемента подменю выполняются следующие действия:
     * - Получается текст элемента подменю с помощью getText().
     * - Элемент подменю прокручивается в видимую область (scrollIntoView(false)) и
     * кликается.
     * - Проверяется, что заголовок страницы (#titlePage h1) содержит текст элемента
     * подменю, используя assertTrue(textSubTitleBlock.contains(textSubMenuItems)).
     */
    public void clickAllMenuItems() {

        ElementsCollection menuItems = $$("[class^= 'side-bar-second__items']");
        assertTrue(menuItems.size() > 0);
        for (int i = 0; i < menuItems.size(); i++) {
            String textMenuItems = menuItems.get(i).getText();
            menuItems.get(i).click();
            String textTitleBlock = $("#titlePage h1").getText();
            assertTrue(textTitleBlock.contains(textMenuItems));
            ElementsCollection subMenuItems = $$("[class^= 'side-bar-second__items'] div li");
            ElementsCollection visibleSubMenuItems = subMenuItems.filter(visible);
            if (visibleSubMenuItems.size() > 0 & !visibleSubMenuItems.isEmpty()) {
                for (int r = 0; r < visibleSubMenuItems.size(); r++) {
                    String textSubMenuItems = visibleSubMenuItems.get(r).getText();
                    visibleSubMenuItems.get(r).scrollIntoView(false).click();
                    String textSubTitleBlock = $("#titlePage h1").getText();
                    assertTrue(textSubTitleBlock.contains(textSubMenuItems));
                }

            }

        }
    }

    /**
     * Задание #2
     * поменять город. нажать на город (например Москва) -> нажать
     * "выбрать другой" -> ввести в поиск "Омск". произвести поиск и
     * убедиться что
     * поиск верный и после клика на результат поиска "Омск"
     * отобразился "Омск"
     * 
     * Сценарий:
     * 1. Клик на элемент с названием города:
     * - Код находит элемент с классом .city__name.city__btn' и
     * кликает на него с
     * помощью cssCelector.
     * - Проверяется, что элемент с название города найден и успешно
     * кликнут.
     * 
     * 2. Клик на кнопку изменить город:
     * - Код находит кнопку с классом "city__change-btn", проверяет,
     * что она видима,
     * включена и существует, используя
     * $(".city__change-btn").shouldBe(visible,
     * enabled, exist), и затем кликает на нее с помощью .click().
     * - Проверяется, что кнопка изменить город найдена, видима,
     * включена и успешно
     * кликнута.
     * 
     * 3. Проверка наличия поля ввода города:
     * - Код находит поле ввода города с ID
     * "select-basket-city-input", проверяет,
     * что оно видимо, включено и существует, используя
     * $("input#select-basket-city-input").shouldBe(visible, enabled,
     * exist).
     * - Проверяется, что поле ввода города найдено и соответствует
     * ожидаемому
     * состоянию.
     * 
     * 4. Ввод нового города в поле ввода:
     * - Код вводит новый город в поле ввода с помощью
     * $("input#select-basket-city-input").setValue(newCity).
     * - Проверяется, что новый город успешно введен в поле.
     * 
     * 5. Клик на город в выпадающем списке:
     * - Код находит элемент с текстом "Омск" ($x("//b[text() =
     * 'Омск']")),
     * проверяет, что он видим, используя shouldBe(visible), и
     * кликает на него.
     * - Проверяется, что элемент "Омск" найден, видим и успешно
     * кликнут.
     * 
     * 6. Проверка, что город обновился:
     * - Код находит элемент с классом "city__name city__btn" и
     * проверяет, что его
     * текст содержит новый город, используя
     * $(".city__name.city__btn").shouldHave(text(newCity)).
     * - Проверяется, что новый город успешно установлен и
     * отображается на странице.
     * 
     * @param newCity новое название города
     */

    public void changeCity(String newCity) {
        $(".city__name.city__btn").click();
        $(".city__change-btn").shouldBe(visible, enabled, exist).click();
        $("input#select-basket-city-input").shouldBe(visible, enabled, exist);
        $("input#select-basket-city-input").setValue(newCity);
        $x("//b[text() = 'Омск']").shouldBe(visible).click();
        $(".city__name.city__btn").shouldHave(text(newCity));
    }

    /**
     * Переход с основной страницы на страницу «Результаты анализов» через
     * соответствующую кнопку и проверка заголовка
     * 
     * @param titlePage заголовок страницы страницы результатов анализов
     */
    public void checkingTestResultsButton(String titlePage) {
        $(".invitro_header-get_result").click();
        $("[class^='UnauthResultsPage_bodyContainer']")
                .shouldHave(text(titlePage));
    }
}
