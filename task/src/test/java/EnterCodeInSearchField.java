import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.SelenideElement;

public class EnterCodeInSearchField {

    public EnterCodeInSearchField(String url) {
        open(url);
    }

    /**
     * Ввод номер заказа
     * 
     * @param code номер заказа
     */
    public void searchWithDifferentValues(String code) {
        SelenideElement earchField = $("[name='orderNumber']");
        earchField.setValue(code);
    }
}
