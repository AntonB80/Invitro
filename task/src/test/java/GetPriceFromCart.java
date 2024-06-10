import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class GetPriceFromCart {
    /**
     * Получает итоговую стоимость из корзины. Переопределяет в int и возвращает ее
     * 
     * @return Возвращает итоговую стоимость из корзины
     */
    public int getPriceFromCart() {

        $("[class^='Breadcrumbs_breadcrumbs'] li:nth-child(2)").shouldHave(text("Корзина"));
        String totalPriceValue = $x("//div[text() = 'Итого:']/following-sibling::div").getText();
        String[] totalPriceStrings = totalPriceValue.split(" ₽");
        String totalPriceStringsWithoutSpaces = totalPriceStrings[0].replaceAll(" ", "");
        int totalPriceFromCartInt = Integer.valueOf(totalPriceStringsWithoutSpaces);
        return totalPriceFromCartInt;
    }

}
