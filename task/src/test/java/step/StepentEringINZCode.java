package step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class StepentEringINZCode {

    private StepentEringINZCode() {
        open("https://lk3.invitro.ru/no-registration-results");
    }

    @Given("Open analysis page {string}")
    public void openAnalysisPage(String url) {
        open(url);
    }

    @When("Enter code analysis in the search field {string}")
    public void searchWithDifferentValues(String code) {
        SelenideElement earchField = $("[name='orderNumber']");
        earchField.sendKeys(code);
    }

}
