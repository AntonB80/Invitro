import org.junit.jupiter.api.Test;

public class TestTask extends BaseTest {
    final String BASE_URL = "https://www.invitro.ru/radiology/";
    final String NEW_CITY = "Омск";
    final String ANALIZES_URL = "https://www.invitro.ru/analizes/for-doctors/";
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

    @Test
    public void changeCity() {
        MainPage changeCity = new MainPage(BASE_URL);
        changeCity.changeCity(NEW_CITY);
    }

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
}
