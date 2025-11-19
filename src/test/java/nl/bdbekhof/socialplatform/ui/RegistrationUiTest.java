package nl.bdbekhof.socialplatform.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistrationUiTest {

    private WebDriver driver;
    private void waitForServer(String url, int timeoutSec) {
        long start = System.currentTimeMillis();
        boolean ok = false;

        while (!ok && (System.currentTimeMillis() - start) < timeoutSec * 1000) {
            try {
                HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
                con.setConnectTimeout(1000);
                con.connect();
                ok = (con.getResponseCode() < 500);
            } catch (Exception ignore) {}

            try { Thread.sleep(500); } catch (Exception ignored) {}
        }

        if (!ok) throw new IllegalStateException("Server did not start within timeout");
    }


    @BeforeAll
    void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void startBrowser() {
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--headless=new");
        opts.addArguments("--disable-gpu");
        opts.addArguments("--disable-dev-shm-usage");
        opts.addArguments("--no-sandbox");
        driver = new ChromeDriver(opts);
    }

    @AfterEach
    void quitBrowser() {
        if (driver != null) driver.quit();
    }

    @Test
    void registerNewUser() {

        // 60 second wait for GitHub ci to start server
        waitForServer("http://localhost:8080/register", 60);

        driver.get("http://localhost:8080/register");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

        driver.findElement(By.id("email")).sendKeys("test@local");
        driver.findElement(By.id("username")).sendKeys("TestUser");
        driver.findElement(By.id("password")).sendKeys("Password123!");
        driver.findElement(By.id("register")).click();

        assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    private void waitForServer() {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 10000) {
            try {
                new java.net.URL("http://localhost:8080/register").openStream().close();
                return; // server is up
            } catch (Exception ignored) {
                try { Thread.sleep(200); } catch (InterruptedException e) { }
            }
        }
        fail("Server did not start within timeout");
    }
}
