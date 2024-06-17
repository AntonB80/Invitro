import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codeborne.selenide.WebDriverRunner;

import io.qameta.allure.Step;

public class SelectSection {
    /**
     * Перебор значений разделов с четким указанием значений ("Врачам", "Пациентам",
     * "Франчайзинг", "Корпоративным", "Прессе")
     * Открывает раздел, проверяет значение в адресной строке
     * В случае указания других разделов -> исключение
     * 
     * @param section Наименование секции
     */
    public SelectSection(String url) {
        open(url);
    }

    public SelectSection(String url, String section) {
        open(url);
        $("#buttonOpenPopupTargetSTATICSTRINGFORID.invitro_header-target_audience").click();
        switch (section) {
            case "Пациентам":
                $x("//div[@id = 'popupTargetSTATICSTRINGFORID']//span[text() = 'Пациентам']").click();
                String urlPatients = WebDriverRunner.getWebDriver().getCurrentUrl();
                assertTrue(urlPatients.contains("https://www.invitro.ru/"));
                break;
            case "Врачам":
                $x("//div[@id = 'popupTargetSTATICSTRINGFORID']//span[text() = 'Врачам']").click();
                String urlDoctors = WebDriverRunner.getWebDriver().getCurrentUrl();
                assertTrue(urlDoctors.contains("https://www.invitro.ru/doctors/"));
                break;
            case "Франчайзинг":
                $x("//div[@id = 'popupTargetSTATICSTRINGFORID']//span[text() = 'Франчайзинг']").click();
                String urlFranchize = WebDriverRunner.getWebDriver().getCurrentUrl();
                assertTrue(urlFranchize.contains("https://www.invitro.ru/franchize/"));
                break;
            case "Корпоративным клиентам":
                $x("//div[@id = 'popupTargetSTATICSTRINGFORID']//span[text() = 'Корпоративным клиентам']").click();
                String urlMedical = WebDriverRunner.getWebDriver().getCurrentUrl();
                assertTrue(urlMedical.contains("https://www.invitro.ru/medical/"));
                break;
            case "Прессе":
                $x("//div[@id = 'popupTargetSTATICSTRINGFORID']//span[text() = 'Прессе']").click();
                String urlAbout = WebDriverRunner.getWebDriver().getCurrentUrl();
                assertTrue(urlAbout.contains("https://www.invitro.ru/about/"));
                break;

            default:
                throw new IllegalArgumentException("Недопустимое значение раздела: " + section);
        }

    }

    @Step("Step 1")
    public void selectSection(String section) {
        $("#buttonOpenPopupTargetSTATICSTRINGFORID.invitro_header-target_audience").click();
        switch (section) {
            case "Пациентам":
                $x("//div[@id = 'popupTargetSTATICSTRINGFORID']//span[text() = 'Пациентам']").click();
                String urlPatients = WebDriverRunner.getWebDriver().getCurrentUrl();
                assertTrue(urlPatients.contains("https://www.invitro.ru/"));
                break;
            case "Врачам":
                $x("//div[@id = 'popupTargetSTATICSTRINGFORID']//span[text() = 'Врачам']").click();
                String urlDoctors = WebDriverRunner.getWebDriver().getCurrentUrl();
                assertTrue(urlDoctors.contains("https://www.invitro.ru/doctors/"));
                break;
            case "Франчайзинг":
                $x("//div[@id = 'popupTargetSTATICSTRINGFORID']//span[text() = 'Франчайзинг']").click();
                String urlFranchize = WebDriverRunner.getWebDriver().getCurrentUrl();
                assertTrue(urlFranchize.contains("https://www.invitro.ru/franchize/"));
                break;
            case "Корпоративным клиентам":
                $x("//div[@id = 'popupTargetSTATICSTRINGFORID']//span[text() = 'Корпоративным клиентам']").click();
                String urlMedical = WebDriverRunner.getWebDriver().getCurrentUrl();
                assertTrue(urlMedical.contains("https://www.invitro.ru/medical/"));
                break;
            case "Прессе":
                $x("//div[@id = 'popupTargetSTATICSTRINGFORID']//span[text() = 'Прессе']").click();
                String urlAbout = WebDriverRunner.getWebDriver().getCurrentUrl();
                assertTrue(urlAbout.contains("https://www.invitro.ru/about/"));
                break;

            default:
                throw new IllegalArgumentException("Недопустимое значение раздела: " + section);
        }
    }
}
