import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class TestWb {
    private WebDriver driver;
    private final String EXPECTED_TEXT = "1 товар";
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSearchAndAddToCartWb() throws InterruptedException {
        driver.get("https://www.wildberries.ru/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='main-page__content']/descendant::article[1]/div/a")));
        driver.findElement(By.id("searchInput")).sendKeys("мобильный телефон");

        Thread.sleep(1000);
        driver.findElement(By.id("applySearchBtn")).click();

        WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='product-card-list']/descendant::article[1]/div/a")));
        card.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label = 'Добавить в корзину']")))
                .click();
        driver.findElement(By.xpath("//span[@class = 'navbar-pc__icon navbar-pc__icon--basket']")).click();

        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'accordion__goods-count']")));
        asserts.checkAddedCart(EXPECTED_TEXT, product);
    }
}
