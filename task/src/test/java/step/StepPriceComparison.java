package step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

// import org.junit.platform.suite.api.ConfigurationParameter;
// import org.junit.platform.suite.api.IncludeEngines;
// import org.junit.platform.suite.api.SelectPackages;
// import org.junit.platform.suite.api.Suite;

// import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
// import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// @Suite
// @IncludeEngines("cucumber")
// @SelectPackages("io.cucumber.skeleton")
// @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
// @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "io.cucumber.skeleton")
public class StepPriceComparison {
    private int priceFromAnalizesPageInt;
    private int totalPriceFromCartInt;

    public StepPriceComparison() {
    }

    public StepPriceComparison(int priceFromAnalizesPageInt, int totalPriceFromCartInt) {
        this.priceFromAnalizesPageInt = priceFromAnalizesPageInt;
        this.totalPriceFromCartInt = totalPriceFromCartInt;
        openAnalysisPage("https://www.invitro.ru/analizes/for-doctors/");
    }

    @Given("Open analysis page {string}")
    public void openAnalysisPage(String url) {
        open(url);
    }

    @When("Take the cost analysis 'Glycated hemoglobin' and added to cart")
    public int getPriceFromAnalizesPageInt() {
        String priceText = $x(
                "//a[contains(text(), 'Гликированный гемоглобин')]/../../following-sibling::div//div[@class = 'analyzes-item__total--sum']")
                .getText();
        String[] price = priceText.split(" ₽");
        String priceTextWithoutSpaces = price[0].replaceAll(" ", "");
        int priceFromAnalizesPageInt = Integer.parseInt(priceTextWithoutSpaces);
        $x("//a[contains(text(), 'Фруктозамин')]/../../following-sibling::div//div[@class = 'analyzes-item__total--sum']/../following-sibling::a")
                .scrollIntoView(false);
        $x("//a[contains(text(), 'Гликированный гемоглобин')]/../../following-sibling::div//div[@class = 'analyzes-item__total--sum']/../following-sibling::a")
                .click();
        $x("//a[contains(text(), 'Гликированный гемоглобин')]/../../following-sibling::div//div[@class = 'analyzes-item__total--sum']/../following-sibling::a[@title]")
                .shouldNotBe(exist);

        return priceFromAnalizesPageInt;
    }

    @And("Going to the cart")
    public void goingToCart() {
        $("div.invitro_header-menu div.invitro-header-cart__icon").click();
        $("[class^='Breadcrumbs_breadcrumbs'] li:nth-child(2)").shouldHave(text("Корзина"));
    }

    @And("Take the cost of analysis 'Glycated hemoglobin'")
    public int getPriceFromCart() {
        String totalPriceValue = $x(
                "//button[contains(text(), 'Гликированный гемоглобин')]/../../following-sibling::div//span").getText();
        String[] totalPriceStrings = totalPriceValue.split(" ₽");
        String totalPriceStringsWithoutSpaces = totalPriceStrings[0].replaceAll(" ", "");
        int totalPriceFromCartInt = Integer.valueOf(totalPriceStringsWithoutSpaces);
        return totalPriceFromCartInt;
    }

    @Then("Comparison the prices of the analysis in the cart with the price of the analysis from the analysis page")
    public void comparisonPrice() {
        if (totalPriceFromCartInt > priceFromAnalizesPageInt) {
            System.out.println("Сумма в корзине больше сохраненной суммы");

        } else if (totalPriceFromCartInt < priceFromAnalizesPageInt) {
            System.out.println("Сумма в корзине меньше сохраненной суммы");
        } else {
            throw new AssertionError("Сумма в корзине равна сохраненной сумме, что не должно быть");
        }
    }

}
