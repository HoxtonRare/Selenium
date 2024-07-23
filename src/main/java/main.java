import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class main {
    public static void main(String[] args){
        WebDriver driver = new ChromeDriver();
        String expectedText = "1 товар";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        try {
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
            assertEquals(expectedText, product.getText());
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
    }

}