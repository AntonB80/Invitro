import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestTask extends BaseTest {
    final String BASE_URL = "https://www.invitro.ru/radiology/";
    final String ANALIZES_URL = "https://www.invitro.ru/analizes/for-doctors/";
    final String RESULTS_URL = "https://lk3.invitro.ru/no-registration-results";
    final String NEW_CITY = "Омск";
    final String TITLE_PAGE = "Введите индивидуальный номер заказа, чтобы посмотреть результаты анализов";
    final String WARNING_TEXT = "Поля Код ИНЗДата рожденияФамилия обязательны для заполнения";
    final String CODE_INZ = "231231231";
    final String BIRTHDAY = "11.12.2000";
    final String LAST_NAME = "тест";

    @Test
    public void clickAllMenu() {
        MainPage clickAllMenu = new MainPage(BASE_URL);
        clickAllMenu.clickAllMenuItems();
    }

    /*
     * 1. в разделе медицинские услуги https://www.invitro.ru/radiology/ сделать
     * прокликивание всего меню. в том числе с прокликиванием по подгруппам
     */
    @Test
    public void changeCity() {
        MainPage changeCity = new MainPage(BASE_URL);
        changeCity.changeCity(NEW_CITY);
    }

    /*
     * 2. поменять город. нажать на город (например Москва) -> нажать
     * "выбрать другой" -> ввести в поиск "Омск". произвести поиск и убедиться что
     * поиск верный и после клика на результат поиска "Омск" отобразился "Омск"
     */
    @Test
    public void checkingTestResults() {
        MainPage checkingTestResults = new MainPage(BASE_URL);
        checkingTestResults.checkingTestResultsButton(TITLE_PAGE);
        ResultsPage testResultsPage = new ResultsPage();
        testResultsPage.testResultsPage(TITLE_PAGE, WARNING_TEXT, CODE_INZ, BIRTHDAY, LAST_NAME);
    }

    /**
     * 4. создать шаг на запоминание суммы продукта на странице анализы
     * https://www.invitro.ru/analizes/for-doctors/,
     * добавить продукт в корзину со страницы анализы
     * https://www.invitro.ru/analizes/for-doctors/,
     * создать шаг сравнивающий сумму в корзине с запомненной суммой. Исходя из
     * условий больше\меньше\равно 10000р вывести сообщение в консоль (больше или
     * меньше) или вызвать ошибку, если равно. Реализовать передачу запомненной
     * суммы между шагами
     */
    @Test
    public void comparisonPricesFromAnalysisPageAndCartPage() {
        GetPriceFromAnalizes priceFromAnalizesPageInt = new GetPriceFromAnalizes(ANALIZES_URL);
        int priceAnalizesPageInt = priceFromAnalizesPageInt.getPriceFromAnalizes();
        GetPriceFromCart totalPriceFromCartInt = new GetPriceFromCart();
        int totalPriceInCartInt = totalPriceFromCartInt.getPriceFromCart();
        new AnalizesPaga(priceAnalizesPageInt, totalPriceInCartInt);

    }

    /*
     * 5. создать параметризованный шаг
     * When Ввести код анализа в поле поиска 1515
     * где вместо "1515" можно указать любое значение и данное значение будет
     * указываться в поисковую строку
     */

    @ParameterizedTest
    @ValueSource(strings = { "1515", "5151", "333" })
    public void searchWithDifferentValues(String code) {
        EnterCodeInSearchField input = new EnterCodeInSearchField(RESULTS_URL);
        input.searchWithDifferentValues(code);
    }

    /*
     * 6. создать параметризованный шаг с четким указанием значений
     * выбираю раздел Пациентам
     * где вместо "Пациентам" можно указать значения: Пациентам, Врачам,
     * Франчайзинг, Корпоративным клиентам, Прессе. другие значения нельзя будет
     * указать
     */
    @ParameterizedTest
    @ValueSource(strings = { "Врачам", "Пациентам", "Франчайзинг", "Корпоративным клиентам", "Прессе", "клиентам",
            "другие значения" })
    public void selectSection(String section) {
        new SelectSection(BASE_URL, section);
    }
}
