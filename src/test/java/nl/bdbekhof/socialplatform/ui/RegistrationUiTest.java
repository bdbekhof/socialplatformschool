package nl.bdbekhof.socialplatform.ui;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistrationUiTest {
    private WebDriver driver;

    @BeforeAll
    void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(opts);
    }

    @AfterEach
    void teardown() { if (driver != null) driver.quit(); }

    @Test
    void registerNewUser() {
        driver.get("http://localhost:8080/register");
        driver.findElement(By.id("email")).sendKeys("test@local");
        driver.findElement(By.id("username")).sendKeys("TestUiUser");
        driver.findElement(By.id("password")).sendKeys("Password123!");
        driver.findElement(By.id("register")).click();
        // expect redirect to /login
        assertTrue(driver.getCurrentUrl().contains("/login") || driver.getPageSource().contains("login"));
    }
}
