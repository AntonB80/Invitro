import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import io.github.bonigarcia.wdm.WebDriverManager;

abstract public class BaseTest {
    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "2880x1800";
        Configuration.headless = false;
    }

    @AfterAll
    public static void tearDown() {
        Selenide.closeWebDriver();
    }

}
