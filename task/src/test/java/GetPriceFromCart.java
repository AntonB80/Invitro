import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class GetPriceFromCart {

    /**
     * Получает стоимость 'Гликированный гемоглобин' из корзины. Переопределяет в
     * int и возвращает ее
     * 
     * @return Возвращает стоимости анализа 'Гликированный гемоглобин' из корзины
     */
    public int getPriceFromCart() {

        $("[class^='Breadcrumbs_breadcrumbs'] li:nth-child(2)").shouldHave(text("Корзина"));
        String totalPriceValue = $x(
                "//button[contains(text(), 'Гликированный гемоглобин')]/../../following-sibling::div//span").getText();
        String[] totalPriceStrings = totalPriceValue.split(" ₽");
        String totalPriceStringsWithoutSpaces = totalPriceStrings[0].replaceAll(" ", "");
        int totalPriceFromCartInt = Integer.valueOf(totalPriceStringsWithoutSpaces);
        return totalPriceFromCartInt;
    }

}
