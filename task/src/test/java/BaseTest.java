import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import io.github.bonigarcia.wdm.WebDriverManager;

@TestInstance(Lifecycle.PER_CLASS)
abstract public class BaseTest {
    @BeforeAll
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "2880x1800";
        Configuration.headless = false;
    }

    @AfterAll
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}
