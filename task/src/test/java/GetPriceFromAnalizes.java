import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class GetPriceFromAnalizes {
    GetPriceFromAnalizes(String url) {
        open(url);
    }

    /**
     * Получает стоимость анализа. Переопределяет в int и возвращает
     * Переходим м корзину
     * 
     * @return Цена анализа «Гликированный гемоглобин (HbA1С, Glycated Hemoglobin)»
     */
    public int getPriceFromAnalizes() {
        String priceText = $x(
                "//a[contains(text(), 'Гликированный гемоглобин')]/../../following-sibling::div//div[@class = 'analyzes-item__total--sum']")
                .getText();
        String[] price = priceText.split(" ₽");
        String priceTextWithoutSpaces = price[0].replaceAll(" ", "");
        int priceFromAnalizesPageInt = Integer.parseInt(priceTextWithoutSpaces);
        $x("//a[contains(text(), 'Гликированный гемоглобин')]/../../following-sibling::div//div[@class = 'analyzes-item__total--sum']/../following-sibling::a")
                .click();
        $x("//a[contains(text(), 'Гликированный гемоглобин')]/../../following-sibling::div//div[@class = 'analyzes-item__total--sum']/../following-sibling::a[@title]")
                .shouldNotBe(exist);
        $("div.invitro_header-menu div.invitro-header-cart__icon").click();
        $("[class^='Breadcrumbs_breadcrumbs'] li:nth-child(2)").shouldHave(text("Корзина"));
        return priceFromAnalizesPageInt;
    }
}
